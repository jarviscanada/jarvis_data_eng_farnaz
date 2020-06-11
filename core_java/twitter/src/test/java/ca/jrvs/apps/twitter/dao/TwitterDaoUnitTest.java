package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonParser;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {

    @Mock
    TwitterHttpHelper mockHelper;

    @InjectMocks
    TwitterDao dao;

    @Test
    public void postTweet() throws Exception {
        //Test failed Request
        String hashTag = "#abc";
        String text = "@someone sometext" + " " + hashTag + " " + System.currentTimeMillis();
        //Exception is expected here
        when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            dao.create(TweetUtil.createTweet());
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }
        //Test happy path
        // However, we don't want to call parseResponseBody
        //We will make a spyDao which can fake parseResponseBody return value
        String tweetJsonStr = "{\n"
                + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "   \"id\":1097607853932564480,\n"
                + "   \"id_str\":\"1097607853932564480\",\n"
                + "   \"text\":\"test with loc223\",\n"
                + "   \"entities\":{\n"
                + "      \"hashtags\":[],\n"
                + "      \"user_mentions\":[]\n"
                + "   },\n"
                + "   \"coordinates\":null,\n"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorited\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";

        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
        //mock parseResponseBody
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any());
        Tweet tweet = spyDao.create(TweetUtil.createTweet());
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void showTweet() throws Exception {
        String hashTag = "#abc";
        String text = "@someone sometext" + " " + hashTag + " " + System.currentTimeMillis();
        String jsonString = "{\n"
                + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "   \"id\":1097607853932564480,\n"
                + "   \"id_str\":\"1097607853932564480\",\n"
                + "   \"text\":\"test with loc223\",\n"
                + "   \"entities\":{\n"
                + "      \"hashtags\":[],\n"
                + "      \"user_mentions\":[]\n"
                + "   },\n"
                + "   \"coordinates\":null,\n"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorited\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";
        String id = "1097607853932564480";

        when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            dao.findById(id);
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        //Test Happy path
        when(mockHelper.httpGet(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(jsonString, Tweet.class);
        //mock parseResponseBody
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any());
        Tweet tweet = spyDao.findById(id);
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void deleteTweet() throws Exception {
        String jsonString = "{\n"
                + "   \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
                + "   \"id\":1097607853932564480,\n"
                + "   \"id_str\":\"1097607853932564480\",\n"
                + "   \"text\":\"test with loc223\",\n"
                + "   \"entities\":{\n"
                + "      \"hashtags\":[],\n"
                + "      \"user_mentions\":[]\n"
                + "   },\n"
                + "   \"coordinates\":null,\n"
                + "   \"retweet_count\":0,\n"
                + "   \"favorite_count\":0,\n"
                + "   \"favorited\":false,\n"
                + "   \"retweeted\":false\n"
                + "}";
        String id = "1097607853932564480";

        //Test Happy path
        when(mockHelper.httpPost(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(jsonString, Tweet.class);
        //mock parseResponseBody
        doReturn(expectedTweet).when(spyDao).parseResponseBody(any());
        Tweet tweet = spyDao.deleteById(id);
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }
}