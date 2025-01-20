package org.example.service


import exception.CustomException
import exception.ErrorCode
import lombok.RequiredArgsConstructor
import model.entity.Comments
import model.entity.Movies
import model.entity.MoviesWithComments
import model.enums.MongoTableCollector
import model.response.Response

import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.stereotype.Service
import repository.Mongo.utils.MongoMethod
import org.bson.Document
import org.springframework.data.mongodb.core.aggregate
import org.springframework.data.mongodb.core.aggregation.Aggregation.*



@Service
@RequiredArgsConstructor
class MongoService(
     private val template: Map<MongoTableCollector, MongoTemplate>
) {

    fun findLatestMovies(page: Int, size: Int): Response<List<Movies>> {
        val pageable = MongoMethod.pageable(page, size)
        val sort = Sort.by(Sort.Direction.DESC, "released")
        val criteriaMap = emptyMap<String, Any>()
        val query: Query = MongoMethod.createQuery(pageable, criteriaMap, sort)

        val template = template[MongoTableCollector.SampleMflix]
            ?: throw CustomException(ErrorCode.FailedToFindTemplate)

        val result: List<Movies> = template.find(query, Movies::class.java, )

        return Response.success(result)
    }

    fun findMovieByTitle(title: String): Response<Movies>? {
        val filter: MutableMap<String, Any> = mutableMapOf()
        filter["title"] = title

        val template = template[MongoTableCollector.SampleMflix]
            ?: throw CustomException(ErrorCode.FailedToFindTemplate)

        val query:Query = MongoMethod.createQuery(filter)

        val result: Movies? = template.findOne(query, Movies::class.java)

        return Response.success(result)
    }

    fun findMoviesByCommentCount(
        minimum: Int, max: Int,
        page: Int, size: Int,
        order: String
    ): Response<List<Movies>> {
        val filter: MutableMap<String, Any> = mutableMapOf()

        filter["num_mflix_comments"] = Criteria().gte(minimum).lte(max)

        val template = template[MongoTableCollector.SampleMflix]
            ?: throw CustomException(ErrorCode.FailedToFindTemplate)

        val pageable = MongoMethod.pageable(page,size)
        val sort = Sort.by(Sort.Direction.DESC, "released")

        val query = MongoMethod.createQuery(pageable, filter, sort)

        val results: List<Movies> = template.find(query, Movies::class.java)

        return Response.success(results)
    }


    fun findMoviesWithComments(
        title: String,
    ): Response<MoviesWithComments> {
        val template = template[MongoTableCollector.SampleMflix]
            ?: throw CustomException(ErrorCode.FailedToFindTemplate)

        val pipeline = listOf(
            match(Criteria.where("title").`is`(title)),
            lookup("comments", "_id", "movie_id", "comments"),
            project("title", "comments")
                .and("_id").`as`("id")
        )

        val results = template.aggregate(newAggregation(pipeline), "movies", MoviesWithComments::class.java).uniqueMappedResult

        return Response.success(results)
    }



}
