/**
 * The parser class is responsible for translating the user's input 
 * into something the game logic can process.
 * 
* @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakirki - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class Parser {

    /**
     * Possible rows.
     */
    public enum PossibleRows {A, B, C, D, E}

    /**
     * If it is a valid coordinate.
     */
    public boolean isValidLocation; 

    /**
     * Text that needs to be parsed.
     */
    private String text; 

    /**
     * Translated location.
     */
    private final Location location;

    /**
     * Default constructor initializing instance variables
     */
    public Parser() {
        location = new Location();
        isValidLocation = false;
    }

    /**
     * Method to set text
     * @param text
     */
    public void setText(String text) {
        location.clear();
        isValidLocation = false;
        this.text = text;
        this.check();
    }

    /**
     * Method that checks whether the input of the user is valid or not
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
     * Converts column to integer value
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
     * Converts a row to an integer value
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
     * Gets the location
     * @return Location
     */
    public Location getLocation() {
        return this.location;
    }
}