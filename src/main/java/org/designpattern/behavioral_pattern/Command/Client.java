package org.designpattern.behavioral_pattern.Command;
//Client
public class Client {
  public static void main(String[] args) {
    TextCommandInvoker textCommandInvoker = new TextCommandInvoker();
    TextCommandReceiver textCommandReceiver = new TextCommandReceiver();

    TextCommand insertCommand1 = new InsertCommand(textCommandReceiver, 0, "insert text1 ");
    textCommandInvoker.executeCommand(insertCommand1);//Current Text: insert text1

    TextCommand insertCommand2 = new InsertCommand(textCommandReceiver, 7, "123456 ");
    textCommandInvoker.executeCommand(insertCommand2);//Current Text: insert 123456 text1

    textCommandInvoker.undo();//Current Text: insert text1
    textCommandInvoker.redo();//Current Text: insert 123456 text1

    TextCommand deleteCommand1 = new DeleteCommand(textCommandReceiver, 0, 7);
    textCommandInvoker.executeCommand(deleteCommand1);//Current Text: 123456 text1

    TextCommand deleteCommand2 = new DeleteCommand(textCommandReceiver, 0, 3);
    textCommandInvoker.executeCommand(deleteCommand2);//Current Text: Current Text: 456 text1

    textCommandInvoker.undo();//Current Text: 123456 text1
    textCommandInvoker.redo();//Current Text: 456 text1

  }
}
