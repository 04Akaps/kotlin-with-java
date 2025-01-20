package org.example.controller

import model.entity.Movies
import model.entity.MoviesWithComments
import model.response.Response
import org.example.annotation.OrderCustomAnnotation
import org.example.annotation.PageCustomAnnotation
import org.example.annotation.SizeCustomAnnotation
import org.example.service.MongoService
import org.jetbrains.annotations.NotNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/mongo")
class MongoController(
    private val mongoService: MongoService
) {

    @GetMapping("/movies-by-latest")
    fun latestMoves(
        @PageCustomAnnotation page: Int,
        @SizeCustomAnnotation size: Int,
    ): Response<List<Movies>> {
        return mongoService.findLatestMovies(page, size);
    }

    @GetMapping("/movie/{movieTitle}")
    fun getMovieByTitle(@PathVariable @NotNull movieTitle: String): Response<Movies>? {
        return mongoService.findMovieByTitle(movieTitle)
    }

    @GetMapping("/movies-by-comment-count")
    fun getMoviesByCommentCount(
        @RequestParam @NotNull minimum: Int,
        @RequestParam @NotNull max: Int,
        @PageCustomAnnotation page: Int,
        @SizeCustomAnnotation size: Int,
        @OrderCustomAnnotation order: String
    ): Response<List<Movies>> {
        return mongoService.findMoviesByCommentCount(
            minimum, max,
            page, size,
            order
        )
    }


    @GetMapping("/movie-with-comments/{movieTitle}")
    fun findMoviesWithComments(@PathVariable @NotNull movieTitle: String): Response<MoviesWithComments> {
        return mongoService.findMoviesWithComments(movieTitle)
    }

}