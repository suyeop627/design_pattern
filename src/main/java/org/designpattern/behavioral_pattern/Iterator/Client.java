package org.designpattern.behavioral_pattern.Iterator;
//Client 코드
public class Client {
  public static void main(String[] args) {
    BookShelf bookShelf = new BookShelf();
    bookShelf.addBook("Design Patterns");
    bookShelf.addBook("Clean Code");
    bookShelf.addBook("Java Programming");

    // 이터레이터를 통한 순회
    BookIterator iterator = bookShelf.createIterator();
    while (iterator.hasNext()) {
      String book = iterator.next();
      System.out.println("Book: " + book);
    }
  }
}
