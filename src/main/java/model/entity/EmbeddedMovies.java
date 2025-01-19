package model.entity;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collation = "embedded_movies")
@Getter
public class EmbeddedMovies {
    @Id
    private ObjectId id;

    @Field("lastupdated")
    private String lastUpdated;

    @Field("tomatoes.critic.numReviews")
    private int tomatoesCriticNumReviews;

    @Field("tomatoes.viewer.rating")
    private double tomatoesViewerRating;

    @Field("tomatoes.dvd")
    private Date tomatoesDvd;

    @Field("tomatoes.fresh")
    private int tomatoesFresh;

    @Field("type")
    private String type;

    @Field("rated")
    private String rated;

    @Field("genres")
    private List<String> genres;

    @Field("tomatoes.rotten")
    private int tomatoesRotten;

    @Field("writers")
    private List<String> writers;

    @Field("num_mflix_comments")
    private int numMflixComments;

    @Field("cast")
    private List<String> cast;

    @Field("title")
    private String title;

    @Field("runtime")
    private int runtime;

    @Field("directors")
    private List<String> directors;

    @Field("imdb.id")
    private int imdbId;

    @Field("poster")
    private String poster;

    @Field("countries")
    private List<String> countries;

    @Field("tomatoes.viewer.numReviews")
    private int tomatoesViewerNumReviews;

    @Field("tomatoes.lastUpdated")
    private Date tomatoesLastUpdated;

    @Field("fullplot")
    private String fullPlot;

    @Field("tomatoes.critic.rating")
    private double tomatoesCriticRating;

    @Field("tomatoes.production")
    private String tomatoesProduction;

    @Field("languages")
    private List<String> languages;

    @Field("released")
    private Date released;

    @Field("awards.text")
    private String awardsText;

    @Field("year")
    private int year;

    @Field("imdb.votes")
    private int imdbVotes;

    @Field("plot")
    private String plot;

    @Field("tomatoes.critic.meter")
    private int tomatoesCriticMeter;

    @Field("awards.nominations")
    private int awardsNominations;

    @Field("imdb.rating")
    private double imdbRating;

    @Field("awards.wins")
    private int awardsWins;

    @Field("tomatoes.viewer.meter")
    private int tomatoesViewerMeter;

    @Field("plot_embedding")
    private List<Object> plotEmbedding;

}
