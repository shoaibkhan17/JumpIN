import java.io.Serializable;

/**
 * Location class holding the location of the x and y coordinates.
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class Location implements Serializable {

    /**
	 * Serial version UID for serialization and de-serialization
	 */
	private static final long serialVersionUID = 6L;

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
     * @param x coordinate
     * @param y coordinate
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Location(Location location) {
    	this.x = location.getX();
    	this.y = location.getY();
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
     * @param x coordinate on the board
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
     * @param y coordinate on the board 
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
     * @param location to be set passed in as a parameter
     */
    public void setLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
    }

    /**
     * Method that compares if the two locations are the same.
     * @param location used to get the x and y coordinate
     * @return true if the two locations are the same
     */
    @Override
    public boolean equals(Object object) {
    	if (this.getClass() == object.getClass()) {
    		Location loc = (Location) object;   		
            return x == loc.getX() && y == loc.getY();
    	}
    	return false;
    }

    /**
     * Returns the alphanumeric location coordinates.
     * @return String string representation of the x and y coordinates
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
     * Returns the numeric location coordinates
     * @return String representation of the x and y coordinate
     */
    public String toStringNumeric() {
        return x + "," + y;
    }

    /**
     * Function that compares whether the x and y location is 
     * greater, smaller or equal to the current location
     * @param location that is used for comparing the coordinates
     * @param horizontalMovement
     * @return 1, 0, -1 if greater than, equal to or less than respectively
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