package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonParser;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TwitterDao implements CrdDao<Tweet, String> {

    //URI constants
    private static final String API_BASE_URI = "https://api.twitter.com/1.1/statuses/";
    private static final String CREATE_PATH = "update.json?status=";
    private static final String READ_PATH = "show.json?id=";
    private static final String DELETE_PATH = "destroy/";
    //URI symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    //Response Code
    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;

    @Autowired
    public TwitterDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Tweet create(Tweet tweet) {
        //Construct URI
        URI uri;
        try {
            uri = getCreateURI(tweet);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid tweet input: " + e);
        }

        //Execute HTTP Request
        HttpResponse response = httpHelper.httpPost(uri);

        //Validate response and send response to Tweet object
        return parseResponseBody(response);
    }

    /**
     * Check response status code Convert Response Entity to Twwt
     */
    protected Tweet parseResponseBody(HttpResponse response) {
        Tweet tweet = null;
        //Check response status
        int status = response.getStatusLine().getStatusCode();
        if (status != HttpStatus.SC_OK) {
            throw new RuntimeException("Unexpected status code : " + status);
        } else if (response.getEntity() != null) {

            //Convert Response Entity to string
            String jsonStr;
            try {
                jsonStr = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                throw new RuntimeException("Failed to convert entity to string " + e);
            }
            //Convert JSON String to Tweet object
            try {
                return JsonParser.toObjectFromJson(jsonStr, Tweet.class);
            } catch (IOException e) {
                throw new RuntimeException("Unable to convert JSON str to Object : " + e);
            }
        } else {
            throw new RuntimeException("Empty response body");
        }

    }

    //Create URI for posting a tweet
    private URI getCreateURI(Tweet entity) throws URISyntaxException {
        String status = entity.getText();
        float[] coordinates = entity.getCoordinates().getLongLat();
        StringBuilder stringBuilder = new StringBuilder(API_BASE_URI);
        stringBuilder.append(CREATE_PATH);
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        stringBuilder.append(percentEscaper.escape(status));
        stringBuilder.append(AMPERSAND);
        stringBuilder.append("long");
        stringBuilder.append(EQUAL);
        stringBuilder.append(percentEscaper.escape(String.valueOf(coordinates[0])));
        stringBuilder.append(AMPERSAND);
        stringBuilder.append("lat");
        stringBuilder.append(EQUAL);
        stringBuilder.append(percentEscaper.escape(String.valueOf(coordinates[1])));
        System.out.println(stringBuilder.toString());
        return new URI(stringBuilder.toString());
    }

    //Create URI for deleting a tweet with its given id
    private URI getDeleteUri(String id) throws URISyntaxException {
        StringBuilder stringBuilder = new StringBuilder(API_BASE_URI);
        stringBuilder.append(DELETE_PATH);
        stringBuilder.append(id);
        stringBuilder.append(".json");
        return new URI(stringBuilder.toString());
    }

    //Find tweets by id
    @Override
    public Tweet findById(String s) {
        URI uri;
        try {
            uri = getReadUri(s);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid Tweet ID : " + e);
        }

        HttpResponse response = httpHelper.httpGet(uri);
        return parseResponseBody(response);
    }

    //Create URI for retrieving a tweet with its given id
    private URI getReadUri(String id) throws URISyntaxException {
        StringBuilder stringBuilder = new StringBuilder(API_BASE_URI);
        stringBuilder.append(READ_PATH);
        stringBuilder.append(id);
        return new URI(stringBuilder.toString());
    }

    // Delete a Tweet by given ID
    @Override
    public Tweet deleteById(String s) {
        URI uri;
        try {
            uri = getDeleteUri(s);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid Tweet ID : " + e);
        }
        HttpResponse response = httpHelper.httpPost(uri);
        return parseResponseBody(response);
    }


}