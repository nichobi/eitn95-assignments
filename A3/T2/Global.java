package T2;

public class Global {
  public static final int MOVE = 0, END_INTERACTION = 1, MEASURE = 2;
  public static final double MOVE_TIME = 0.5, MEASURE_TIME = 1.0;

  public static double time = 0;
  public static int interactedWithAll = 0;

  public static final String EQUALS = " = ", COMMA = ", ";
  public static final int T = 1, L = 20, STUDENTS = 20;
  public static final double V = 2;
  public static final int NORTH = 0, NORTH_EAST = 1, EAST = 2, SOUTH_EAST = 3,
      SOUTH = 4, SOUTH_WEST = 5, WEST = 6, NORTH_WEST = 7;

  public static EventListClass eventList = new EventListClass();

  public static void insertEvent(int type, double TimeOfEvent) {
    eventList.InsertEvent(type, TimeOfEvent);
  }
}