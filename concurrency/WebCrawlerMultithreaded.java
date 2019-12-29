import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// LC-1242
// https://leetcode.com/problems/web-crawler-multithreaded/

// Input:
//  urls = [
//  "http://news.yahoo.com",
//  "http://news.yahoo.com/news",
//  "http://news.yahoo.com/news/topics/",
//  "http://news.google.com",
//  "http://news.yahoo.com/us"
//  ]
//  edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
//  startUrl = "http://news.yahoo.com/news/topics/"
//  Output: [
//  "http://news.yahoo.com",
//  "http://news.yahoo.com/news",
//  "http://news.yahoo.com/news/topics/",
//  "http://news.yahoo.com/us"
//  ]

// Input:
//  urls = [
//  "http://news.yahoo.com",
//  "http://news.yahoo.com/news",
//  "http://news.yahoo.com/news/topics/",
//  "http://news.google.com"
//  ]
//  edges = [[0,2],[2,1],[3,2],[3,1],[3,0]]
//  startUrl = "http://news.google.com"
//  Output: ["http://news.google.com"]
//  Explanation: The startUrl links to all other pages that do not share the same hostname.

// Constraints:
//
//  1 <= urls.length <= 1000
//  1 <= urls[i].length <= 300
//  startUrl is one of the urls.
//  Hostname label must be from 1 to 63 characters long, including the dots,
//  may contain only the ASCII letters from 'a' to 'z', digits from '0' to '9'
//  and the hyphen-minus character ('-').
//
//  The hostname may not start or end with the hyphen-minus character ('-').
//  See:  https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames
//  You may assume there're no duplicates in url library.

public class WebCrawlerMultithreaded {

  interface HtmlParser {

    List<String> getUrls(String str);
  }

  class Solution {

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
      String hostname = extractHostname(startUrl);

      Set<String> visited = new ConcurrentSkipListSet<>();
      visited.add(startUrl);

      return crawl(startUrl, htmlParser, hostname, visited).collect(Collectors.toList());
    }

    private Stream<String> crawl(String startUrl, HtmlParser htmlParser, String hostname,
      Set<String> visited) {
      try (Stream<String> stream = htmlParser.getUrls(startUrl)
        .parallelStream()
        .filter(url -> matchHostname(url, hostname))
        .filter(visited::add)
        .flatMap(url -> crawl(url, htmlParser, hostname, visited))) {

        return Stream.concat(Stream.of(startUrl), stream);
      }
    }

    private String extractHostname(String url) {
      int idx = url.indexOf('/', 7);
      return (idx != -1) ? url.substring(0, idx) : url;
    }

    private boolean matchHostname(String url, String hostname) {
      return url.startsWith(hostname) && (url.length() == hostname.length()
        || url.charAt(hostname.length()) == '/');
    }
  }
}