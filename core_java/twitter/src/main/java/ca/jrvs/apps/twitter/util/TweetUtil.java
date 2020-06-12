package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Cooridinates;
import ca.jrvs.apps.twitter.model.Tweet;

public class TweetUtil {

    public static Tweet createTweet() {
        String name = "@someone";
        String text = "some_text #abc " + name + System.currentTimeMillis();
        float[] longLat = {-75, 25};
        Cooridinates coordinates = new Cooridinates(longLat, "Point");
        return new Tweet(text, coordinates);
    }


    public static Tweet createTweetwithExceedeLength() {
        String text = "ut morbi tincidunt augue interdum velit euismod in pellentesque massa placerat duis ultricies lacus sed turpis tincidunt id aliquet risus feugiat in ante metus dictum at tempor commodo ullamcorper a lacus vestibulum sed arcu non odio euismod lacinia at quis risus sed vulputate odio ut enim blandit volutpat maecenas volutpat blandit aliquam etiam erat velit scelerisque in dictum non consectetur a erat nam at lectus urna duis convallis convallis tellus id interdum velit laoreet id donec ultrices tincidunt arcu non sodales neque sodales ut etiam sit amet nisl purus in mollis nunc sed id semper risus in hendrerit gravida rutrum";
        float[] longLat = {-75, 25};
        Cooridinates coordinates = new Cooridinates(longLat, "Point");
        return new Tweet(text, coordinates);
    }

}