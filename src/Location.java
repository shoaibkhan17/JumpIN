/**
 * Location class holding the location of the x and y coordinates.
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class Location {

    /**
     * X coordinate
     */
    private int x;

    /**
     * Y coordinate
     */
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
     * Gets the x coordinate
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Sets the x coordinate to param x
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y location coordinate
     * @return the y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets the y coordinate to param y
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Clears the x and y coordinates
     */
    public void clear() {
        x = -1;
        y = -1;
    }

    /**
     * Method that sets the location
     * @param location
     */
    public void setLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
    }

    /**
     * Method that compares if the two locations are the same.
     * @param location 
     * @return true if the two locations are the same
     */
    public boolean equals(Location location) {
        return x == location.getX() && y == location.getY();
    }

    /**
     * Returns the alphanumeric location coordinates.
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
    
    /**
     * Returns the numeric location coordinates.
     * @return String
     */
    public String toStringNumeric() {
        return "(" + x + "," + y + ")";
    }

    /**
     * Function that compares that compares if the location is greater or smaller
     * to the other location.
     * @param location
     * @param horizontalMovement
     * @return
     */
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