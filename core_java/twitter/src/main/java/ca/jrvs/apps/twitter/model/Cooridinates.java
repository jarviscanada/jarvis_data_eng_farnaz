package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "longLat",
        "type"
})

public class Cooridinates {

    @JsonProperty("coordinates")
    private float[] longLat;
    @JsonProperty("type")
    private String type;

    public Cooridinates() {
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Cooridinates(float[] longLat, String type) {
        setLongLat(longLat);
        setType(type);
    }

    @JsonProperty("coordinates")
    public float[] getLongLat() {
        return longLat;
    }

    @JsonProperty("coordinates")
    public void setLongLat(float[] coordinates) {
        this.longLat = coordinates;
    }
}