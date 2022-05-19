package T2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Properties;

public class Field extends Global {
  ArrayList<Person> people;
  private Square[][] squares;
	public Deque<Interaction> interactionQueue;

  public Field() {
    this.people = new ArrayList<Person>();
    readConfig();

    this.squares = new Square[Global.L][Global.L];
    for (int x = 0; x < Global.L; x++) {
      for (int y = 0; y < Global.L; y++) {
        this.squares[x][y] = new Square(x, y);
      }
    }
    
    interactionQueue = new ArrayDeque<Interaction>();
  }

  public Person interactablePersonInSquare(int x, int y) {
    return squares[x][y].getInteractablePerson();
  }

  /**
   * Move everyone who is currently not interacting with another.
   */
  public void moveAll() {
    for (Person p : people) {
      if (!p.isInteracting()) {
        int[] oldSquare = p.getCurrentSquare();
        p.move();

        if(p.isSameSquare(oldSquare[0], oldSquare[1])) {
          // Hasn't moved to a new square
          continue;
        }

        int[] newSquare = p.getCurrentSquare();
        squares[oldSquare[0]][oldSquare[1]].removePerson(p);
        squares[newSquare[0]][newSquare[1]].addPerson(p);

        Person interactablePerson = interactablePersonInSquare(newSquare[0], newSquare[1]);
        if(interactablePerson == null) {
          // There are no available people to interact with =(
          continue;
        }

        p.beginInteraction();
        interactablePerson.beginInteraction();

        interactionQueue.push(new Interaction(p, interactablePerson));
        // TODO: Add a new interactionDone event into the event queue
      }
    }
  }

  public void endInteraction() {
    Interaction in = interactionQueue.pop();
    in.getP1().finishInteraction(in.getP2());
    in.getP2().finishInteraction(in.getP1());
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

  public void readConfig() {
    Properties config = new Properties();

    try {
      String path = GenerateSimulation.CUSTOM_CONFIG_PATH;
      FileInputStream fis = new FileInputStream(path);
      config.load(fis);
      fis.close();
    } catch (Exception e) {
    }

    // Read config variables
    Global.T = Integer.parseInt(config.getProperty("t"));
    String vStr = config.getProperty("v");
    String[] splitted = vStr.split("-");
    if (splitted.length == 2) {
      Global.V_MIN = Integer.parseInt(splitted[0]);
      Global.V_MAX = Integer.parseInt(splitted[1]);
    } else {
      Global.V_MIN = Integer.parseInt(vStr);
      Global.V_MAX = Integer.parseInt(vStr);
    }

    // Read config people positions
    BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
          GenerateSimulation.CUSTOM_CONFIG_PATH
      ));

      int i = 0;
			String line;
			while ((line = reader.readLine()) != null) {
        if(i < 2) {
          // Skip the first 2 parameters
          i++;
          continue;
        }
        people.add(new Person(line));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
}
