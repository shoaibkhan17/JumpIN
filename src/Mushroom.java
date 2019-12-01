/**
 * Mushroom class is a subclass of Piece Class.
 * They are stationary and cannot move while the game is in progress
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 */
public class Mushroom extends Piece {
	
	/**
	 * Location that is only used to generate the XML
	 */
	private Location location;

    /**
	 * Serial version UID for serialization and de-serialization
	 */
	private static final long serialVersionUID = 9L;

	/**
     * Mushrooms are not movable,
     */
    private static final boolean IS_MOVABLE = false;

    /**
     * Mushrooms are not selectable
     */
    private static final boolean IS_SELECTABLE = false;

    /**
     * Default constructor initializing instance variables
     */
    public Mushroom(Location location) {
        super(PieceType.MUSHROOM, IS_MOVABLE, IS_SELECTABLE);
        this.location = location;
    }
    
    /**
     * Method to generate the xml structure of the object
     */
    public String toXML() {
    	String xml = "    <Mushroom>\n";
    	xml += "        <Coordinate1>" + location.toStringNumeric() + "</Coordinate1>\n";
    	xml += "    </Mushroom>";
    	return xml;
    }
}
