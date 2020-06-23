package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonParser;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {

    private TwitterService service;

    @Before
    public void setUp() throws Exception {
        String CONSUMER_KEY = System.getenv("consumerKey");
        String CONSUMER_SECRET = System.getenv("consumerSecret");
        String ACCESS_TOKEN = System.getenv("accessToken");
        String TOKEN_SECRET = System.getenv("tokenSecret");

        //setup dependency
        HttpHelper twitterHttpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET,
                ACCESS_TOKEN, TOKEN_SECRET);
        TwitterDao dao = new TwitterDao(twitterHttpHelper);
        //pass dependency
        service = new TwitterService(dao);
    }

    @Test
    public void postTweet() throws Exception {
        Tweet postTweet = TweetUtil.createTweet();
        String hashtag = "abc";
        String name = "@someone";
        System.out.println(JsonParser.toJson(postTweet, true, false));
        Tweet tweet = service.postTweet(postTweet);

        assertEquals(postTweet.getText(), tweet.getText());
        assertNotNull(tweet.getCoordinates());
        assertEquals(2, tweet.getCoordinates().getLongLat().length);
        assertEquals(-75, tweet.getCoordinates().getLongLat()[0], 0);
        assertEquals(25, tweet.getCoordinates().getLongLat()[1], 0);
        assertTrue(hashtag.contains(tweet.getEntities().getHashtags().get(0).getText()));
        System.out.println(JsonParser.toJson(postTweet, true, false));
    }

    @Test
    public void postTweetWithExceededLength() throws JsonProcessingException {
        String hashtag = "#abc";
        String name = "@someone";
        Tweet postedTweet = TweetUtil.createTweetwithExceedeLength();
        System.out.println(JsonParser.toJson(postedTweet, true, false));

        Tweet tweet = service.postTweet(postedTweet);

        service.postTweet(TweetUtil.createTweetwithExceedeLength());
        assertEquals(postedTweet.getText(), tweet.getText());
    }

    @Test
    public void showTweet() throws JsonProcessingException {
        String hashtag = "#abc";
        Tweet postTweet = TweetUtil.createTweet();
        Tweet postedTweet = service.postTweet(postTweet);
        String id = postedTweet.getIdString();

        Tweet tweet = service.showTweet(id, null);

        assertEquals(postTweet.getText(), tweet.getText());
        assertNotNull(tweet.getCoordinates());
        assertEquals(2, tweet.getCoordinates().getLongLat().length);
        assertEquals(-75, tweet.getCoordinates().getLongLat()[0], 0);
        assertEquals(25, tweet.getCoordinates().getLongLat()[1], 0);
        assertTrue(hashtag.contains(tweet.getEntities().getHashtags().get(0).getText()));
        System.out.println(JsonParser.toJson(tweet, true, false));
    }

    @Test
    public void showTweetWithWrongIdFormat() {
        String id = "wrongIdFormat";
        Tweet tweet = service.showTweet(id, null);
    }

    @Test
    public void deleteTweets() {
        Tweet postTweet = TweetUtil.createTweet();
        Tweet tweet = service.postTweet(postTweet);
        String id = tweet.getIdString();
        String[] Strid = {id};
        List<Tweet> deletedTweet = service.deleteTweets(Strid);
        assertEquals(tweet.getText(), deletedTweet.get(0).getText());
    }

}