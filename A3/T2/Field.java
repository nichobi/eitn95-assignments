package T2;

import java.util.ArrayList;

public class Field extends Global {
  private Square[][] squares;
  ArrayList<Person> people;

  public Field() {
    this.people = new ArrayList<Person>();
    // TODO: Insert people from config file

    this.squares = new Square[Global.L][Global.L];
    for (int x = 0; x < Global.L; x++) {
      for (int y = 0; y < Global.L; y++) {
        this.squares[x][y] = new Square(x, y);
      }
    }
  }

  public Person talkablePersonInSquare(int x, int y) {
    return squares[x][y].getAvailablePerson();
  }

  /**
   * Move everyone who is currently not interacting with another.
   */
  public void moveAll() {
    for (Person p : people) {
      if (!p.isInteracting()) {
        int[] oldSquare = p.getCurrentSquare();
        p.move();

        if (!p.isSameBox(oldSquare[0], oldSquare[1])) {
          squares[oldSquare[0]][oldSquare[1]].removePerson(p);

          int[] newSquare = p.getCurrentSquare();
          squares[newSquare[0]][newSquare[1]].addPerson(p);
          // TODO: Check for available interactions in this new square
        }
      }
    }
  }

  public int peopleDone() {
    int done = 0;
    for (Person p : this.people) {
      if (p.done()) {
        done++;
      }
    }
    return done;
  }
}
