package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "idString",
        "indices",
        "name",
        "screenName"
})

public class UserMention {

    @JsonProperty("id")
    private long id;
    @JsonProperty("id_str")
    private String idString;
    @JsonProperty("indices")
    private int[] indices;
    @JsonProperty("name")
    private String name;
    @JsonProperty("screen_name")
    private String screenName;

    public UserMention() {
    }

    public UserMention(long id, String idString, int[] indices, String name,
                       String screenName) {
        setId(id);
        setIdString(idString);
        setIndices(indices);
        setName(name);
        setScreenName(screenName);
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
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

    @JsonProperty("indices")
    public int[] getIndices() {
        return indices;
    }

    @JsonProperty("indices")
    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("screen_name")
    public String getScreenName() {
        return screenName;
    }

    @JsonProperty("screen_name")
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
