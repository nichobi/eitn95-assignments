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
      p.updateInteracting();

      if(!p.isInteracting()) {
        int oldX = Math.floor(p.getX());
        int oldY = Math.floor(p.getY());
        p.move();

        if(!p.isSameBox(oldX, oldY)) {
          squares[oldX][oldY].remove(p);
          squares[Math.floor(p.getX())][Math.floor(p.getY())]
          // TODO: Check for available interactions
        }
      }
    }
  }

  public int peopleDone() {
    int done = 0;
    for(Person p : this.people) {
      if(p.done()) {
        done++;
      }
    }
    return done;
  }
}
