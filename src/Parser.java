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
     * 
     */
    public Parser() {
        location = new Location();
        isValidLocation = false;
    }

    /**
     * @param text
     */
    public void setText(String text) {
        location.clear();
        isValidLocation = false;
        this.text = text;
        this.check();
    }

    /**
     * 
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
     * @param text
     * @return
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
     * @param text
     * @return
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
     * @return
     */
    public Location getLocation() {
        return location;
    }
    
}