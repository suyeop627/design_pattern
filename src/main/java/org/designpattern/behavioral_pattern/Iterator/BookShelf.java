package org.designpattern.behavioral_pattern.Iterator;

import java.util.ArrayList;
import java.util.List;
//ConcreteAggregate 구현
public class BookShelf implements BookCollection{
  private final List<String> books;

  public BookShelf() {
    this.books = new ArrayList<>();
  }

  public void addBook(String title) {
    books.add(title);
  }

  @Override
  public BookIterator createIterator() {
    return new BookListIterator(books);
  }
}
