package com.bmiedlar.twitter;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class TwitterClientService {

  private static final String CONSUMER_KEY = "X";
  private static final String CONSUMER_SECRET = "X";
  private static final String TOKEN = "X-X";
  private static final String SECRET = "X";

  public void runTwitterClient() {
    var msgQueue = new LinkedBlockingQueue<String>(100000);
    var eventQueue = new LinkedBlockingQueue<Event>(1000);

    var host = new HttpHosts(Constants.STREAM_HOST);
    var endpoint = new StatusesFilterEndpoint();
    List<Long> followings = List.of(1234L, 566788L);
    List<String> terms = List.of("football", "java");
    endpoint.followings(followings);
    endpoint.trackTerms(terms);


    var auth = new OAuth1(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, SECRET);

    ClientBuilder builder = new ClientBuilder()
        .name("mysql-feeder")
        .hosts(host)
        .authentication(auth)
        .endpoint(endpoint)
        .processor(new StringDelimitedProcessor(msgQueue))
        .eventMessageQueue(eventQueue);

    Client twitterClient = builder.build();

    twitterClient.connect();

    for (int i = 0; i < 20; i++) {
      new Thread(new MySQLWriter(msgQueue)).start();
    }
  }
}

