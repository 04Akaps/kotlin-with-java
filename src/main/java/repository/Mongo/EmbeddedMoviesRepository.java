package repository.Mongo;

import model.entity.EmbeddedMovies;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmbeddedMoviesRepository extends MongoRepository<EmbeddedMovies, ObjectId> {
}
