package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {


    @Mock
    CrdDao dao;

    @InjectMocks
    TwitterService service;

    @Test
    public void postTweet() {
        Tweet postTweet = TweetUtil.createTweet();
        when(dao.create(any())).thenReturn(postTweet);
        service.postTweet(postTweet);
    }

    @Test
    public void postTweetWithExceededLength() {
        Tweet postTweet = TweetUtil.createTweetwithExceedeLength();
        when(dao.create(any())).thenReturn(postTweet);
        service.postTweet(postTweet);
    }


    @Test
    public void showTweet() {
        Tweet postTweet = TweetUtil.createTweet();
        when(dao.findById(any())).thenReturn(postTweet);

        String id = "123456";
        String[] idFields = {"created_at", "id_str"};
        Tweet showTweet = service.showTweet(id, idFields);

        assertEquals(showTweet.getText(), postTweet.getText());
    }

    @Test
    public void showTweetWithWrongIdFormat() {
        Tweet postTweet = TweetUtil.createTweet();
        when(dao.findById(any())).thenReturn(postTweet);

        String id = "WrongFormat";
        String[] idFields = {"created_at", "id_str"};
        Tweet showTweet = service.showTweet(id, idFields);

        assertEquals(showTweet.getText(), postTweet.getText());
    }

    @Test
    public void deleteTweets() {
        String[] ids = {"123456"};
        Tweet postTweet = TweetUtil.createTweet();
        List<Tweet> tweets = new LinkedList<>();
        tweets.add(postTweet);

        when(dao.deleteById((anyString()))).thenReturn(postTweet);
        service.deleteTweets(ids);

        assertEquals(tweets.get(0).getText(), postTweet.getText());
    }

}