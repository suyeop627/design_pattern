package org.designpattern.behavioral_pattern.Memento;
//Client
public class Client {
  public static void main(String[] args) {

    Character playerCharacter = new Character("Hero", 100);
    CharacterCaretaker caretaker = new CharacterCaretaker(playerCharacter);

    playerCharacter.equipItem("Sword");
    playerCharacter.equipItem("Shield");

    caretaker.save();
    playerCharacter.printCharacterInfo();

    playerCharacter.takeDamage(20);
    caretaker.save();
    playerCharacter.printCharacterInfo();


    playerCharacter.takeDamage(20);
    playerCharacter.printCharacterInfo();

    caretaker.undo();
    playerCharacter.printCharacterInfo();

    caretaker.undo();
    playerCharacter.printCharacterInfo();

  }
}
