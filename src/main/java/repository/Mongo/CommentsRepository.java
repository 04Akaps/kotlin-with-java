package repository.Mongo;

import model.entity.Comments;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentsRepository extends MongoRepository<Comments, ObjectId> {
}
