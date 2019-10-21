/**
 * ENUM containing the directions 
 * Pieces can move using these directions
 */
public enum Direction {
	
	UP("Up"), 
    DOWN("Down"), 
    RIGHT("Right"), 
    LEFT("Left");

    private final String type;
    
    /**
     * Default constructor initializes instance variables
     * @param type of the direction
     */
    Direction(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}
