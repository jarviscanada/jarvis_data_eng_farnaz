package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "createdAt",
        "id",
        "idString",
        "text",
        "entities",
        "coordinates",
        "retweetCount",
        "favoriteCount",
        "favorited",
        "retweeted"
})

public class Tweet {

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("id")
    private long id;

    @JsonProperty("id_str")
    private String idString;

    @JsonProperty("text")
    private String text;

    @JsonProperty("entities")
    private Entities entities;

    @JsonProperty("coordinates")
    private Cooridinates coordinates;

    @JsonProperty("retweet_count")
    private int retweetCount;

    @JsonProperty("favorite_count")
    private int favoriteCount;

    @JsonProperty("favorited")
    private boolean favorited;

    @JsonProperty("retweeted")
    private boolean retweeted;

    public Tweet() {
    }

    public Tweet(String text, Cooridinates coordinates) {
        setText(text);
        setCoordinates(coordinates);
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;

    }

    @JsonProperty("getid")
    public long getId() {
        return id;
    }

    @JsonProperty("setid")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("id_str")
    public String getIdString() {
        return idString;
    }

    @JsonProperty("id_str")
    public void setIdString(String idString) {
        this.idString = idString;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("entities")
    public Entities getEntities() {
        return entities;
    }

    @JsonProperty("entities")
    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    @JsonProperty("coordinates")
    public Cooridinates getCoordinates() {
        return coordinates;
    }

    @JsonProperty("coordinates")
    public void setCoordinates(Cooridinates coordinates) {
        this.coordinates = coordinates;
    }

    @JsonProperty("retweet_count")
    public int getRetweetCount() {
        return retweetCount;
    }

    @JsonProperty("retweet_count")
    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    @JsonProperty("favorite_count")
    public int getFavoriteCount() {
        return favoriteCount;
    }

    @JsonProperty("favorite_count")
    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    @JsonProperty("favorited")
    public boolean isFavorited() {
        return favorited;
    }

    @JsonProperty("favorited")
    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    @JsonProperty("retweeted")
    public boolean isRetweeted() {
        return retweeted;
    }

    @JsonProperty("retweeted")
    public void setRetweeted(boolean retweeted) {
        this.retweeted = retweeted;
    }
}
