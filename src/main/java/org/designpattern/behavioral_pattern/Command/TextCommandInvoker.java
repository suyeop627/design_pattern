package org.designpattern.behavioral_pattern.Command;

import java.util.Stack;
//Invoker - 명령을 실행하고, 필요에 따라 관리하는 객체
public class TextCommandInvoker {

  //실행한 커멘드를 stack 에 저장 -> 실행 취소 시, 가장 최근 커멘드 취소
  private final Stack<TextCommand> commandHistory = new Stack<>();
  //실행 취소한 커멘드를 stack에 저장 -> 다시실행 시, 가장 최근 커멘드 실행
  private final Stack<TextCommand> redoHistory = new Stack<>();

  public void executeCommand(TextCommand command) {
    command.execute();
    commandHistory.push(command);
    redoHistory.clear();
  }

  //실행취소
  public void undo() {
    if (!commandHistory.isEmpty()) {
      TextCommand command = commandHistory.pop();
      command.undo();
      redoHistory.push(command);
    }
  }

  //다시 실행
  public void redo() {
    if (!redoHistory.isEmpty()) {
      TextCommand command = redoHistory.pop();
      command.execute();
      commandHistory.push(command);
    }
  }
}
