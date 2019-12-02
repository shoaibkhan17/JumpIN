import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Level Builder class containing logic that allows the user
 * to build a custom level 
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 * 
 */
public class LevelBuilder extends DefaultHandler {
	private Constants.XMLTerms current;
	private Board board;
	private Square[][] squares;
	private int level;
	private Piece currentPiece;
	private Location currentLocation;
	private Location foxBodyLocation;
	private Constants.RABBIT_COLORS currentRabbitColor;
	private ArrayList<Location> possibleHoleLocations; 
	private Boolean horizontalMovement;
	
	/**
	 * Default Constructor initializing instance variables
	 * @param board instance of Board
	 */
	public LevelBuilder(Board board) {
        this.board = board;
        this.squares = board.getSquares();
        this.currentPiece = null;
        this.currentLocation = null;
        this.foxBodyLocation = null;
        this.horizontalMovement = null;
        this.possibleHoleLocations = new ArrayList<>();
        possibleHoleLocations.add(new Location(0, 0));
        possibleHoleLocations.add(new Location(4, 0));
        possibleHoleLocations.add(new Location(2, 2));
        possibleHoleLocations.add(new Location(0, 4));
        possibleHoleLocations.add(new Location(4, 4));
	}
	
	/**
	 * Overloaded constructor
	 * @param level
	 * @param board
	 */
    public LevelBuilder(int level, Board board) {
    	this(board);
        this.level = level;
    }
    
    /**
     * Method which handles the start element
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {    	
    	current = Constants.XMLTerms.valueOf(qName);
    }
    
    /**
     * Method which handles the end element
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	Constants.XMLTerms endElement = Constants.XMLTerms.valueOf(qName);
    	switch (endElement) {
			case Mushroom:
				currentPiece = new Mushroom(currentLocation);
				squares[currentLocation.getX()][currentLocation.getY()].setPiece(currentPiece);
				break;
			case Rabbit:
				currentPiece = new Rabbit(currentRabbitColor, currentLocation);
				squares[currentLocation.getX()][currentLocation.getY()].setPiece(currentPiece);
				break;
			case Fox:
				currentPiece = new Fox(currentLocation, foxBodyLocation, horizontalMovement, false);
				Piece foxBody = new Fox(foxBodyLocation, currentLocation, horizontalMovement, true);
				squares[currentLocation.getX()][currentLocation.getY()].setPiece(currentPiece);
				squares[foxBodyLocation.getX()][foxBodyLocation.getY()].setPiece(foxBody);
				break;
			default:
				break;
    	}
    }
    
    /**
     * 
     */
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
    	String string = new String(ch, start, length);
    	if (string.contains("\n")) {
    		return;
    	}
    	
    	switch (current) {
			case Color:
				currentRabbitColor = Constants.RABBIT_COLORS.valueOf(string);
				break;
			case Coordinate1:
				String[] coord = string.split(",");
				currentLocation = new Location(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));				
				break;
			case Coordinate2:
				String[] bodyCoord = string.split(",");
				foxBodyLocation = new Location(Integer.parseInt(bodyCoord[0]), Integer.parseInt(bodyCoord[1]));	
				break;
			case LevelNumber:
				if (Integer.parseInt(string) != level) {
					throw new SAXException("The level does not match the xml.");
				}
				break;
			case RabbitCount:
				board.rabbitCount = Integer.parseInt(string);
				break;
			case Movement:
				horizontalMovement = string.contains("Horizontal");
			default:
				break;
	    	}
    }
         
    /**
     * Method which parses JSON
     * @throws Exception
     */
    private void parseJSON() throws Exception {  	
    	File file = new File(LEVELS_PATH + "level" + level + ".xml");	
        SAXParserFactory SAXFactory = SAXParserFactory.newInstance();
        SAXParser SAXParser = SAXFactory.newSAXParser();
        SAXParser.parse(file, this);
    }
    
    /**
     * Method to add holes 
     * @throws Exception
     */
    private void addHoles() throws Exception {
    	for (Location holeLoc: possibleHoleLocations) {
    		int x = holeLoc.getX();
    		int y = holeLoc.getY();
    		
    		// No piece where the hole is being placed.
    		if (!squares[x][y].hasPiece()) {
    			board.holeLocations.add(holeLoc);
    			squares[x][y].setPiece(new Hole());
    		}
    		
    		// There is a piece, where a hole is being placed.
    		else {
    			Piece piece = squares[x][y].getPiece();
    			switch (piece.getType()) {
				case FOX:
					throw new Exception("Foxes cannot be placed there");
				case HOLE:
					throw new Exception("Holes should not be added to the xml file");
				case RABBIT:
					Rabbit rabbit = (Rabbit) piece;
					Hole hole = new Hole();
					hole.setPiece(rabbit);
					squares[x][y].setPiece(hole);
					board.holeLocations.add(holeLoc);
					break;
				default:
					break;
    			}
    		}
    	}
    }
    
    /**
     * Method which builds the level
     * @return true if level can be built else false if it cannot be built
     */
    public Boolean buildLevel() {
    	try {
			if (level != 0) {
				this.parseJSON();
			}
			this.addHoles();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
}
