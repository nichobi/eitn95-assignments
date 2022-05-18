package T2;

import java.util.ArrayList;

public class Field extends Global {
  private Square[][] squares;
  ArrayList<Person> people;

	public Field(ArrayList<Person> people) {
    this.squares = new Square[Global.L][Global.L];
    this.people = people;

    for (int x = 0; x < Global.L; x++) {
      for (int y = 0; y < Global.L; y++) {
        this.squares[x][y] = new Square(x, y);
      }
    }
	}

  public Person talkablePersonInSquare(int x, int y) {
    return squares[x][y].getAvailablePerson();
  }

  public void movePersonToSquare(Person p, int x, int y) {
    // TODO:
  }

  public boolean allDone() {
    for(Person p : this.people) {
      if(!p.done()) {
        return false;
      }
    }
    return true;
  }

}
