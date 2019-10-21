
public enum Direction {
	
	UP("Up"), 
    DOWN("Down"), 
    RIGHT("Right"), 
    LEFT("Left");

    private final String type; 
    Direction(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
