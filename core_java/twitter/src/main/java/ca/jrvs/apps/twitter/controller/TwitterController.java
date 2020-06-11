package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller {
    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";

    private Service service;

    @Autowired
    public TwitterController(Service service) {
        this.service = service;
    }

    @Override
    public Tweet postTweet(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "USAGE:TwitterCliApp post \"tweet_text\" \"latitude:longitude\"");
        }

        String tweet_txt = args[1];
        String coord = args[2];
        String[] coordArray = coord.split(COORD_SEP);
        if (coordArray.length != 2 || StringUtils.isEmpty(tweet_txt)) {
            throw new IllegalArgumentException("Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        }
        Double longitude = null;
        Double latitude = null;
        try {
            longitude = Double.parseDouble(coordArray[1]);
            latitude = Double.parseDouble(coordArray[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid location format\nUSAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"", e);
        }

        Tweet postTweet = TweetUtil.createTweet();
        return service.postTweet(postTweet);
    }

    @Override
    public Tweet showTweet(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        }
        String id = args[1];
        String validFields = args[2];
        String[] validFieldsArr = validFields.split(COMMA);
        return service.showTweet(id, validFieldsArr);
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
        }
        String id = args[1];
        String[] idArr = id.split(COMMA);
        return service.deleteTweets(idArr);
    }
}