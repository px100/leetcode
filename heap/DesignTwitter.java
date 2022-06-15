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

    private record Tweet(int tweetId, int timestamp) {

    }

    private Map<Integer, Set<Integer>> followMap;
    private Map<Integer, List<Tweet>> tweetMap;
    private int timestamp;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
      this.followMap = new HashMap<>();
      this.tweetMap = new HashMap<>();
      this.timestamp = 0;
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
      Tweet newTweet = new Tweet(tweetId, timestamp++);
      tweetMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(newTweet);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users
     * who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
      List<Integer> result = new ArrayList<>();

      follow(userId, userId);
      Set<Integer> followSet = followMap.get(userId);
      followSet.addAll(followMap.get(userId));

      PriorityQueue<Tweet> pq = new PriorityQueue<>(Twitter::compare);
      for (Integer followee : followSet) {
        pq.addAll(tweetMap.getOrDefault(followee, List.of()));
      }

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
      followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
      followMap.computeIfAbsent(followerId, k -> new HashSet<>()).remove(followeeId);
    }
  }
}
