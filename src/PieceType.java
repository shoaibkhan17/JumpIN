/**
 * ENUM containing the different types of pieces
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public enum PieceType {
    RABBIT('R'), 
    FOX('F'), 
    MUSHROOM('M'), 
    HOLE('O');

    private final char type;
    
    /**
     * Default constructor initializing instance variables
     */
    PieceType(char type) {
        this.type = type;
    }

    /**
     * gets the type
     * @return char
     */
    public char getType() {
        return type;
    }
}