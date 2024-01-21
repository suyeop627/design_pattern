package org.designpattern.behavioral_pattern.Command;
//Concrete Command
public class DeleteCommand implements TextCommand {
  private TextCommandReceiver textCommandReceiver;
  private int position;
  private String deletedText;

  public DeleteCommand(TextCommandReceiver textCommandReceiver, int position, int length) {
    this.textCommandReceiver = textCommandReceiver;
    this.position = position;
    this.deletedText = textCommandReceiver.substring(position, position + length);
  }

  @Override
  public void execute() {
    int endIndex = position + deletedText.length();
    textCommandReceiver.delete(position, endIndex);
  }

  @Override
  public void undo() {
    textCommandReceiver.insert(position, deletedText);
  }
}