/**
 * Location
 */
public class Location {

    private int x;
    private int y;

    public Location() {
        x = -1;
        y = -1;
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void clear() {
        x = -1;
        y = -1;
    }

    public void setLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
    }

    public boolean equals(Location location) {
        return x == location.getX() && y == location.getY();
    }

    public String toString() {
        if (x != -1 && y != -1) {
            return "(" + Parser.PossibleRows.values()[x] + "," + (y + 1) + ")"; 
        }

        else {
            return "not a valid location - (" + x + "," + y + ")";
        }
    }
}