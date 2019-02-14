package com.bmiedlar.twitter;


import twitter4j.TwitterException;

public class TwitterFeederApplication {


  public static void main(String[] args) throws InterruptedException, TwitterException {
    var twitterClient = new TwitterClientService();
    twitterClient.runTwitterClient();
  }


}
