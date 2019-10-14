/**
 * Piece is a abstract class that prints out the type of the piece
 * This class also checks and stores the value if a piece can move
 */
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
