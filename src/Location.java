/**
 * Location class holding the location of the x and y coordinates
 */
public class Location {

    private int x;
    private int y;

    /**
     * Default constructor initializes instance variables
     */
    public Location() {
        this(-1, -1);
    }

    /**
     * Overloaded constructor 
     * @param x
     * @param y
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * gets the x coordinate
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * sets the x coordinate to param x
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * gets the y location coordinate
     * @return the y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * sets the y coordinate to param y
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * clear the x and y coordinates
     */
    public void clear() {
        x = -1;
        y = -1;
    }

    /**
     * method that sets location
     * @param location
     */
    public void setLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
    }

    /**
     * equals method implemented
     * @param location 
     * @return boolean value
     */
    public boolean equals(Location location) {
        return x == location.getX() && y == location.getY();
    }

    /**
     * toString method implemented
     * @return String
     */
    public String toString() {
        if (x != -1 && y != -1) {
            return "(" + Parser.PossibleRows.values()[x] + "," + (y + 1) + ")"; 
        }

        else {
            return "not a valid location - (" + x + "," + y + ")";
        }
    }

    public int comparesTo(Location location, boolean horizontalMovement) {
        if (horizontalMovement) {
            if (x > location.getX()) {
                return 1;
            }

            else if (x == location.getX()) {
                return 0;
            }

            else {
                return -1;
            }
        }

        else {
            if (y > location.getY()) {
                return 1;
            }

            else if (y == location.getY()) {
                return 0;
            }

            else {
                return -1;
            }
        }
    }
}