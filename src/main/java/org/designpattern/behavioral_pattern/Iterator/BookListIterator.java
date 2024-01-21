package org.designpattern.behavioral_pattern.Iterator;

import java.util.List;

//ConcreteIterator 구현
public class BookListIterator implements BookIterator {
  private List<String> books;
  private int currentIndex;

  public BookListIterator(List<String> books) {
    this.books = books;
    this.currentIndex = 0;
  }

  @Override
  public boolean hasNext() {
    return currentIndex < books.size();
  }

  @Override
  public String next() {
    if (hasNext()) {
      return books.get(currentIndex++);
    }
    return null;
  }
}
