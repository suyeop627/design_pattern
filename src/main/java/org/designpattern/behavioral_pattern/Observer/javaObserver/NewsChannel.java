package org.designpattern.behavioral_pattern.Observer.javaObserver;

import java.util.Observable;
import java.util.Observer;

// 관찰자(Observer) 역할을 하는 클래스
class NewsChannel implements Observer {
  private String channelName;

  public NewsChannel(String channelName) {
    this.channelName = channelName;
  }

  // 주제에서 데이터를 전달받아 업데이트하는 메서드
  @Override
  public void update(Observable o, Object arg) {
    String news = (String) arg;
    System.out.println(channelName + " received news: " + news);
  }

}