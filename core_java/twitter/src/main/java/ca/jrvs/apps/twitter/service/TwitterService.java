package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Cooridinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service {

    private CrdDao dao;

    private String[] trueStrings = {"created_at",
            "id",
            "id_str",
            "text",
            "source",
            "coordinates",
            "entities",
            "retweet_count",
            "favorite_count",
            "favorited",
            "retweeted"};

    @Autowired
    public TwitterService(CrdDao dao) {
        this.dao = dao;
    }


    @Override
    public Tweet postTweet(Tweet tweet) {
        //Business logic:
        // e.g. text length, lat/lon range, id format
        validatePostTweet(tweet);
        //create tweet via dao
        return (Tweet) dao.create(tweet);
    }


    @Override
    public Tweet showTweet(String id, String[] fields) {
        validateId(id);
        return (Tweet) dao.findById(id);
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {
        if (ids == null) {
            throw new NullPointerException("Empty ID");
        }
        List<Tweet> deletedTweets = new LinkedList<>();
        for (String id : ids) {
            validateId(id);
            deletedTweets.add((Tweet) dao.deleteById(id));
        }
        return deletedTweets;
    }

    private void validatePostTweet(Tweet tweet) {
        if (tweet == null) {
            throw new NullPointerException("Empty Tweet Body");
        }
        if (tweet.getText().length() > 140) {
            throw new IllegalArgumentException("Tweet reached maximum length");
        }
        Cooridinates cooridinates = tweet.getCoordinates();

        if (cooridinates.getLongLat()[0] <= -180 || cooridinates.getLongLat()[1] >= 180 || cooridinates.getLongLat()[1] <= -90 || cooridinates.getLongLat()[1] >= 90) {
            throw new IllegalArgumentException("Out of Range coordinates");
        }

        return;
    }


    private void validateId(String id) {
        if (!id.matches("[0-9]+")) {
            throw new IllegalArgumentException("Invalid tweet id");
        }

    }
}