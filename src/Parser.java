/**
 * Parser
 * 
 * The parser class is responsible for translating the user's input 
 * into something the game logic can process
 */
public class Parser {

    public enum PossibleRows {A, B, C, D, E}
    public boolean isValidLocation; 
    private String text; 
    private final Location location;

    /**
     * Default constructor initializing instance variables
     */
    public Parser() {
        location = new Location();
        isValidLocation = false;
    }

    /**
     * method to set text
     * @param text
     */
    public void setText(String text) {
        location.clear();
        isValidLocation = false;
        this.text = text;
        this.check();
    }

    /**
     * method that checks whether the input of the user is valid or not
     */
    public void check() {
        if (text.length() == 2) {
            if (this.convertRowToInt(text.charAt(0)) && this.convertColumnToInt(text.charAt(1))) {
                isValidLocation = true;
                return;
            }
        }

        this.location.clear();
        isValidLocation = false;
    }

    /**
     * converts column to integer value
     * @param text 
     * @return true if it can be converted, false if it cannot 
     */
    private boolean convertColumnToInt(char text) {
        try {
            int num = Integer.parseInt(text + "");

            if (num <= 5 && num >= 1) {
                location.setY(num - 1);
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * converts a row to an integer value
     * @param text
     * @return true if it can be converted, false if it cannot 
     */
    private boolean convertRowToInt(char text) {
        String check = text + "";
        for (PossibleRows r: PossibleRows.values()) {
            if (r.name().equalsIgnoreCase(check)) {
                location.setX(r.ordinal());
                return true;
            }
        }
        return false;
    }

    /**
     * gets the location
     * @return Location
     */
    public Location getLocation() {
        return this.location;
    }
    
}