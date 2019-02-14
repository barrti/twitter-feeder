package com.bmiedlar.twitter.model;

import twitter4j.Status;

public class Tweet {

  private String content;
  private String source;

  public Tweet(Status status) {
    this.content = status.getText();
    this.source = status.getSource();
  }

  public String getContent() {
    return content;
  }

  public String getSource() {
    return source;
  }

  @Override
  public String toString() {
    return "Tweet{" +
        "content='" + content + '\'' +
        ", source='" + source + '\'' +
        '}';
  }
}
