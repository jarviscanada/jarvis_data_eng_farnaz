package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {
    private TwitterController controller;

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
        Service service = new TwitterService(dao);
        //pass dependency
        controller = new TwitterController(service);
    }

    @Test
    public void postTweet() throws JsonProcessingException {
        String name = "@someone";
        String postTweet = "some_text #abc " + name + System.currentTimeMillis();
        String[] args = {"Post", postTweet, "-75:25"};

        Tweet tweet = controller.postTweet(args);

        assertNotNull(tweet.getCoordinates());
        assertEquals(2, tweet.getCoordinates().getLongLat().length);
        assertEquals(-75, tweet.getCoordinates().getLongLat()[0], 0);
        assertEquals(25, tweet.getCoordinates().getLongLat()[1], 0);
        System.out.println(JsonParser.toJson(postTweet, true, false));
    }

    @Test
    public void postTweetWithWrongArgs() throws JsonProcessingException {
        String name = "@someone";
        String postTweet = "some_text #abc " + name + System.currentTimeMillis();
        String[] args = {"Post", postTweet, "-75:25:12"};
        Tweet tweet = controller.postTweet(args);

        assertNotNull(tweet.getCoordinates());
        assertEquals(2, tweet.getCoordinates().getLongLat().length);
        assertEquals(-75, tweet.getCoordinates().getLongLat()[0], 0);
        assertEquals(25, tweet.getCoordinates().getLongLat()[1], 0);
        System.out.println(JsonParser.toJson(postTweet, true, false));
    }

    @Test
    public void showTweet() throws JsonProcessingException {
        String name = "@someone";
        String postTweet = "some_text #abc " + name + System.currentTimeMillis();
        String[] args = {"Post", postTweet, "-75:25"};

        Tweet tweeted = controller.postTweet(args);
        String id = tweeted.getIdString();
        String[] showArgs = {"Show", id, postTweet};
        Tweet tweet = controller.showTweet(showArgs);

        assertNotNull(tweet.getCoordinates());
        assertEquals(2, tweet.getCoordinates().getLongLat().length);
        assertEquals(-75, tweet.getCoordinates().getLongLat()[0], 0);
        assertEquals(25, tweet.getCoordinates().getLongLat()[1], 0);
        System.out.println(JsonParser.toJson(postTweet, true, false));
    }

    @Test
    public void deleteTweet() {
        String name = "@someone";
        String postTweet = "some_text #abc " + name + System.currentTimeMillis();
        String[] args = {"Post", postTweet, "-75:25"};
        Tweet tweeted = controller.postTweet(args);
        String id = tweeted.getIdString();
        String[] showArgs = {"Delete", id, postTweet};

        List<Tweet> deletedTweet = controller.deleteTweet(showArgs);
        assertEquals(tweeted.getText(), deletedTweet.get(0).getText());
    }
}