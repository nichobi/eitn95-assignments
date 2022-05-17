package T2;

public class Field extends Global {
  private Square[][] squares;

	public Field() {
    this.squares = new Square[Global.L][Global.L];

    for (int x = 0; x < Global.L; x++) {
      for (int y = 0; y < Global.L; y++) {
        this.squares[x][y] = new Square(x, y);
      }
    }
	}

  public void movePersonToSquare(Person p, int x, int y) {
    //squares.
  }

}
