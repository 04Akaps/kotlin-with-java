package model.entity;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "comments")
@Getter
public class Comments {
    @Id
    private ObjectId id;

    @Field("date")
    private Date date;

    @Field("email")
    private String email;

    @Field("movie_id")
    private ObjectId movieId;

    @Field("name")
    private String name;

    @Field("text")
    private String text;
}
