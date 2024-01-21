package org.designpattern.behavioral_pattern.Command;
//Receiver - 실제 작업을 처리하는 객체
public class TextCommandReceiver {
  private StringBuilder text = new StringBuilder();
  public String substring(int start, int end) {
    return text.substring(start,end);
  }

  public void delete(int start, int end) {
    text.delete(start, end);
    System.out.println("Current Text: "+text);
  }

  public void insert(int offset, String textToInsert) {
    text.insert(offset, textToInsert);
    System.out.println("Current Text: "+text);
  }
}

