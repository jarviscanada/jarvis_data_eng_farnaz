package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.JsonParser;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

    @Mock
    Service service;

    @InjectMocks
    TwitterController controller;

    @Test
    public void postTweet() {
        Tweet postTweet = TweetUtil.createTweet();
        when(service.postTweet(any())).thenReturn(postTweet);
        String[] args = {"Post", postTweet.getText(), "-75:25"};
        controller.postTweet(args);
    }

    @Test
    public void postTweetWithWrongArgs() throws JsonProcessingException {
        String name = "@someone";
        String postTweet = "some_text #abc " + name + System.currentTimeMillis();
        String[] args = {"Post", postTweet, "-75:25:12"};
        when(service.postTweet(any())).thenThrow(IllegalArgumentException.class);
        Tweet tweet = controller.postTweet(args);

        assertNotNull(tweet.getCoordinates());
        assertEquals(2, tweet.getCoordinates().getLongLat().length);
        assertEquals(-75, tweet.getCoordinates().getLongLat()[0], 0);
        assertEquals(25, tweet.getCoordinates().getLongLat()[1], 0);
        System.out.println(JsonParser.toJson(postTweet, true, false));
    }

    @Test
    public void showTweet() {
        String name = "@someone";
        String postTweet = "some_text #abc " + name + System.currentTimeMillis();
        String[] args = {"Post", postTweet, "-75:25"};
        Tweet newTweet = TweetUtil.createTweet();
        Tweet tweet = service.postTweet(newTweet);
        when(service.showTweet(anyString(), any())).thenReturn(new Tweet());
        Tweet showTweet = controller.showTweet(args);

        assertEquals(showTweet.getText(), tweet);
    }

    @Test
    public void showTweetWithWrongArgs() {
        String name = "@someone";
        String postTweet = "some_text #abc " + name + System.currentTimeMillis();
        String[] args = {"Post", postTweet, "-75:25", "SomethingElse"};
        Tweet newTweet = TweetUtil.createTweet();
        Tweet tweet = service.postTweet(newTweet);
        when(service.showTweet(anyString(), any())).thenReturn(new Tweet());
        Tweet showTweet = controller.showTweet(args);

        assertEquals(showTweet.getText(), tweet);
    }

    @Test
    public void deleteTweet() {
        List<Tweet> tweetList = new ArrayList<>();
        String name = "@someone";
        String postTweet = "some_text #abc " + name + System.currentTimeMillis();
        String[] args = {"Post", postTweet, "-75:25"};
        Tweet newTweet = TweetUtil.createTweet();
        tweetList.add(newTweet);
        when(service.deleteTweets(any())).thenReturn(tweetList);
        tweetList = controller.deleteTweet(args);
        assertFalse(tweetList.isEmpty());
    }
}