package org.designpattern.behavioral_pattern.Observer;
//Subject interface
interface Subject {
  void addObserver(Observer observer);
  void removeObserver(Observer observer);
  void notifyObservers();
}