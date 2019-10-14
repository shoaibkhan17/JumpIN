
public abstract class Piece {
    char type;
    boolean canMove;
    
    public Piece(char type, boolean canMove) {
        this.type = type;
        this.canMove = canMove;
    }
    
    
    public boolean getCanMove() {
    	return this.canMove;
    }
    
    public String toString() {
        return this.type + "";
    }
}
