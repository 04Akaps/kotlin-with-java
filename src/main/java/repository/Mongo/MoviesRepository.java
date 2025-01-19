package repository.Mongo;

import model.entity.Comments;
import model.entity.Movies;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoviesRepository extends MongoRepository<Movies, ObjectId> {
}
