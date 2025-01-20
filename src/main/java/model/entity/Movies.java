package model.entity;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;


@Document(collection = "movies")
@Getter
public class Movies {
    @Id
    private ObjectId id;

    @Field("cast")
    private List<String> cast;

    @Field("countries")
    private List<String> countries;

    @Field("directors")
    private List<String> directors;

    @Field("fullplot")
    private String fullplot;

    @Field("genres")
    private List<String> genres;

    @Field("languages")
    private List<String> languages;

    @Field("lastupdated")
    private String lastupdated;

    @Field("num_mflix_comments")
    private int numMflixComments;

    @Field("plot")
    private String plot;

    @Field("poster")
    private String poster;

    @Field("rated")
    private String rated;

    @Field("released")
    private Date released;

    @Field("runtime")
    private int runtime;

    @Field("title")
    private String title;

    @Field("writers")
    private List<String> writers;

    @Field("year")
    private int year;

    @Field("type")
    private String type;
}
