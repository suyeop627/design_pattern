package org.designpattern.behavioral_pattern.Iterator;
//Aggregate 인터페이스
public interface BookCollection {
  BookIterator createIterator();
}
