import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// LC-355
// https://leetcode.com/problems/design-twitter/

public class DesignTwitter {

  static class Twitter {

    private static int compare(Tweet t1, Tweet t2) {
      return t2.timestamp - t1.timestamp;
    }

    private static class Tweet {

      int tweetId;
      int timestamp;

      public Tweet(int tId, int time) {
        tweetId = tId;
        timestamp = time;
      }
    }

    private Map<Integer, Set<Integer>> followMap = new HashMap<>();
    private Map<Integer, List<Tweet>> tweetMap = new HashMap<>();
    private static int timestamp = 0;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {

    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
      Tweet newTweet = new Tweet(tweetId, timestamp++);
      if (!tweetMap.containsKey(userId)) {
        tweetMap.put(userId, new ArrayList<>());
      }
      tweetMap.get(userId).add(newTweet);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed
     * must be posted by users who the user followed or by the user herself. Tweets must be ordered
     * from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
      List<Integer> result = new ArrayList<>();
      PriorityQueue<Tweet> pq = new PriorityQueue<>(Twitter::compare);

      follow(userId, userId);
      Set<Integer> followSet = followMap.get(userId);
      followSet.addAll(followMap.get(userId));

      followSet.stream()
        .filter(followee -> tweetMap.containsKey(followee))
        .map(followee -> tweetMap.get(followee))
        .forEach(pq::addAll);

      int size = pq.size();
      while (result.size() < 10 && size > 0) {
        Tweet poll = pq.poll();
        result.add(poll.tweetId);
        size--;
      }

      return result;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
      if (!followMap.containsKey(followerId)) {
        followMap.put(followerId, new HashSet<>());
      }
      followMap.get(followerId).add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
      followMap.computeIfAbsent(followerId, k -> new HashSet<>()).remove(followeeId);
    }
  }
}
