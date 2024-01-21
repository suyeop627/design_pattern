package org.designpattern.behavioral_pattern.Observer.javaObserver;

public class Client {
  public static void main(String[] args) {
    // 주제 생성
    NewsAgency newsAgency = new NewsAgency();

    // 관찰자 생성
    NewsChannel channel1 = new NewsChannel("Channel 1");
    NewsChannel channel2 = new NewsChannel("Channel 2");

    // 주제에 관찰자 등록
    newsAgency.addObserver(channel1);
    newsAgency.addObserver(channel2);

    // 주제에서 뉴스 업데이트
    newsAgency.setNews("Breaking News: Important event!");

    // 주제에서 또 다른 뉴스 업데이트
    newsAgency.setNews("Weather Forecast: Sunny day ahead!");
  }
}
