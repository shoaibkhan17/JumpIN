/**
 * Parser
 */
public class Parser {

    public enum PossibleRows {A, B, C, D, E}
    public boolean isValidLocation; 
    private String text; 
    private final Location location;

    public Parser() {
        location = new Location(-1, -1);
        isValidLocation = false;
    }

    public void setText(String text) {
        location.setX(0);
        location.setY(0);
        isValidLocation = false;
        this.text = text; 
    }

    public void check() {
        if (text.length() != 2) {
            isValidLocation = false;
            return;
        }

        this.convertRowToInt(text.charAt(0));
        this.convertColumnToInt(text.charAt(1));
    }

    private void convertColumnToInt(char text) {
        try {
            int num = Integer.parseInt(text + "");

            if (num <= 5 && num >= 1) {
                location.setY(num - 1);
                isValidLocation = true;
            }
            else {
                isValidLocation = false;
            }
        } catch (Exception e) {
            isValidLocation = false;
        }
    }

    private void convertRowToInt(char text) {
        String check = text + "";
        for (PossibleRows r: PossibleRows.values()) {
            if (r.name().equalsIgnoreCase(check)) {
                location.setX(r.ordinal());
                isValidLocation = true;
            }
        }
        isValidLocation = false;
    }

    public Location getLocation() {
        return location;
    }
}