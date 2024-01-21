package org.designpattern.behavioral_pattern.Observer.javaObserver;

import java.util.Observable;

// 주제(Subject) 역할을 하는 클래스
class NewsAgency extends Observable {
  private String news;

  public void setNews(String news) {
    this.news = news;
    setChanged(); // Observable 클래스의 메서드로, 주제(Subject)의 상태가 변경되었음을 나타내는 역할.
    //setChaged() 메서드를 먼저 실행하고 notifyObservers()를 실행해야함.
    notifyObservers(news); // Observer에게 변경된 데이터를 전달
  }
}
