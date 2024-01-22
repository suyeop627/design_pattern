package org.designpattern.behavioral_pattern.Memento;

import java.util.Stack;
//Caretaker
public class CharacterCaretaker {
  private Character character;
  private Stack<CharacterMemento> history;

  public CharacterCaretaker(Character character) {
    this.character = character;
    this.history = new Stack<>();
  }

  public void save() {
    history.push(character.createMemento());
  }

  public void undo() {
    if (!history.isEmpty()) {
      CharacterMemento previousState = history.pop();
      character.restoreFromMemento(previousState);
    }
  }
}
