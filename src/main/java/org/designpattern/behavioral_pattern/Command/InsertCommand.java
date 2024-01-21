package org.designpattern.behavioral_pattern.Command;

//Concrete Command
public class InsertCommand implements TextCommand {
  private TextCommandReceiver textCommandReceiver;
  private String insertedText;
  private int position;

  public InsertCommand(TextCommandReceiver textCommandReceiver, int position, String insertedText) {
    this.textCommandReceiver = textCommandReceiver;
    this.position = position;
    this.insertedText = insertedText;
  }

  @Override
  public void execute() {
    textCommandReceiver.insert(position, insertedText);
  }

  @Override
  public void undo() {
    int endIndex = position + insertedText.length();
    textCommandReceiver.delete(position, endIndex);
  }
}