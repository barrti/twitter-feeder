package com.bmiedlar.twitter;

import com.bmiedlar.twitter.model.Tweet;
import twitter4j.Status;
import twitter4j.TwitterObjectFactory;

import java.util.concurrent.LinkedBlockingQueue;

public class MySQLWriter implements Runnable {

  private final LinkedBlockingQueue<String> queue;

  public MySQLWriter(LinkedBlockingQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        String msg = queue.take();
        Status status = TwitterObjectFactory.createStatus(msg);
        var tweet = new Tweet(status);
        System.out.println(tweet);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
