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
        this.init();
	}
	
	/**
	 * Overloaded constructor 
	 * @param level level number
	 * @param board instance of Board
	 */
    public LevelBuilder(int level, Board board) {
    	this(board);
        this.level = level;
    }
    
    /**
     * Method to initialize the level builder object
     */
    public void init() {
        squares = board.getSquares();
        currentPiece = null;
        currentLocation = null;
        foxBodyLocation = null;
        horizontalMovement = null;
        possibleHoleLocations = new ArrayList<>();
        possibleHoleLocations.add(new Location(0, 0));
        possibleHoleLocations.add(new Location(4, 0));
        possibleHoleLocations.add(new Location(2, 2));
        possibleHoleLocations.add(new Location(0, 4));
        possibleHoleLocations.add(new Location(4, 4));
    }
    
    /**
     * Method to create and return a mushroom object
     * @param location the mushroom location
     * @return Mushroom
     */
    private Piece createMushroom(Location location) {
    	return new Mushroom(location);
    }
    
    /**
     * Method to create and return a rabbit object.
     * Rabbit colors are choose in random.
     * @param location the rabbit location
     * @return Rabbit
     */
    private Rabbit createRabbit(Location location) {
    	int randomNumber = (int) (Math.random() * ((2 - 0) + 1));
    	return new Rabbit(Constants.RABBIT_COLORS.values()[randomNumber], location);
    }
    
    /**
     * Method to create and return a horizontal fox
     * @param location the fox location
     * @return Fox[] horizontal foxes
     */
    private Fox[] createFoxHorizontal(Location location) {
    	Fox[] foxes = new Fox[2];
    	
    	if (location.getX() < 4) {
    		Location bodyLocation = new Location(location);
    		bodyLocation.setX(location.getX() + 1);
    		if (board.getSquareAtLocation(bodyLocation).hasPiece()) {
    			return null;
    		}
    		foxes[0] = new Fox(location, bodyLocation, true, false);
    		foxes[1] = new Fox(bodyLocation, location, true, true);
    		return foxes;
    	}
    	
    	return null;
    }
    
    /**
     * Method to create and return a vertical fox
     * @param location the fox location
     * @return Fox[] vertical foxes
     */
    private Fox[] createFoxVertical(Location location) {
    	Fox[] foxes = new Fox[2];
    	
    	if (location.getY() < 4) {
    		Location bodyLocation = new Location(location);
    		bodyLocation.setY(location.getY() + 1);
    		if (board.getSquareAtLocation(bodyLocation).hasPiece()) {
    			return null;
    		}
    		foxes[0] = new Fox(location, bodyLocation, false, false);
    		foxes[1] = new Fox(bodyLocation, location, false, true);
    		return foxes;
    	}
    	
    	return null;
    }
    
    /**
     * Method to place the horizontal foxes
     * @param square the square to place the fox on
     */
    private void placeHorizontalFoxes(Square square) {
		Fox[] horizontalFoxes = this.createFoxHorizontal(square.getLoc());
		if (horizontalFoxes == null) {
			square.setCounter(3);
			square.doClick();
		}
		
		else {
			square.setPiece(horizontalFoxes[0]);
			Square secondSquare = board.getSquareAtLocation(horizontalFoxes[1].getPieceLocation());
			secondSquare.setPiece(horizontalFoxes[1]);
    		square.setCounter(3);	
    		secondSquare.setCounter(5);
		}
    }
    
    /**
     * Method to place the horizontal foxes
     * @param square the square to place the fox on
     */
    private void placeVerticalFoxes(Square square) {
		Fox[] verticalFoxes = this.createFoxVertical(square.getLoc());
		if (verticalFoxes == null) {
			square.setCounter(5);
			if (square.hasPiece()) {
				square.doClick();
			}
		}
		
		else {
			square.setPiece(verticalFoxes[0]);
			Square secondSquare = board.getSquareAtLocation(verticalFoxes[1].getPieceLocation());
			secondSquare.setPiece(verticalFoxes[1]);
    		square.setCounter(4);
    		secondSquare.setCounter(5);
		}
    }
    
    /**
     * Method to add piece to the square
     * @param square the square to add the piece on
     */
    private void addPiece(Square square) {
		Piece piece = square.getPiece();
    	switch (square.getCounter()) {
    	case 0:
    		square.setPiece(this.createMushroom(square.getLoc()));
    		square.setCounter(1);
    		break;
    	case 1:
    		square.setPiece(this.createRabbit(square.getLoc()));
    		square.setCounter(2);
    		break;
    	case 2:
    		this.placeHorizontalFoxes(square);
    		break;
    	case 3: 
    		if (piece.getType() == PieceType.FOX) {
    			Fox fox = (Fox) square.getPiece();
    			board.getSquareAtLocation(fox.getBodyLocation()).removePiece();
    			square.removePiece();
    		}
    		this.placeVerticalFoxes(square);
    		break;
    	case 4:
    		if (piece.getType() == PieceType.FOX) {
        		Fox removeFox = (Fox) square.getPiece();
        		board.getSquareAtLocation(removeFox.getBodyLocation()).removePiece();
        		square.removePiece();
    		}
    		break;
    	case 5:
    		if (piece.getType() == PieceType.FOX) {
        		Fox removeFox = (Fox) square.getPiece();
        		Square secondSquare = board.getSquareAtLocation(removeFox.getBodyLocation());
        		secondSquare.removePiece();
        		secondSquare.setCounter(0);
    		}   		
    		square.removePiece();
    		square.setCounter(0);
    		break;
    	default:
    		break;
    	}
    }
    
    /**
     * Method to handle adding piece to the square
     * On click method
     * @param square the source of the action event
     */
    protected void buildHandler(Square square) {
    	
    	if (square.getPiece() == null) {
    		square.setPiece(this.createMushroom(square.getLoc()));
    		square.setCounter(1);
    	}
    	
    	else {
    		Piece piece = square.getPiece();
    		
    		switch (piece.getType()) {
    		case HOLE:
    			Hole hole = (Hole) piece;
    			if (hole.isOccupied()) {
    				hole.removePiece();
    			}
    			
    			else {
        			Rabbit rabbit = this.createRabbit(square.getLoc());
        			hole.setPiece(rabbit);
    			}
    			break;
    		
			default:
				this.addPiece(square);
				break;
    		}
    	}
    }
    
    /**
     * Method to check if the file exists
     * @param fileName name of the file
     * @throws Exception thrown if exception occurs
     */
    public void checkFileExists(String fileName) throws Exception {
		File saveDirectory = new File(Constants.SAVED_LEVEL_PATH);
		File[] savedGameFiles = saveDirectory.listFiles();
		
		
		
		for (int i = 0; i < savedGameFiles.length; i++) {
			if (fileName.equals(savedGameFiles[i].getName())) {
				throw new Exception("Level " + level + " already exists.\nPlease save under a different level number.");
			}
		}
		
    }
    
    /**
     * Method to deep copy the board object using serialization
     * @return deepClonedBoard copy of the board
     * @throws IOException if exception occurs
     * @throws ClassNotFoundException if exception occurs
     */
    public Board deepCopyBoard() throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeObject(board);
		out.flush();
		byte[] yourBytes = bos.toByteArray();
		bos.close();
		ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
		ObjectInput in = new ObjectInputStream(bis);
		Board deepClonedBoard = (Board) in.readObject();
		in.close();
		return deepClonedBoard;
    }
    
    /**
     * Method to check if the level is solvable
     * @throws Exception if exception occurs
     */
    public void checkIfLevelIsSolvable() throws Exception {
    	if (board.rabbitCount == 0 || board.rabbitCount > Constants.BOARD_SIZE) {
    		throw new Exception("The custom build level is not solvable.");
    	}
    	
    	else {
    		try {
    			board.rabbitCount = this.getRabbitRount();
    			Board deepClonedBoard = this.deepCopyBoard();
    			AutoSolver solver = new AutoSolver(deepClonedBoard, null);
    			boolean solvable = solver.autoSolve(0);
    			
    			if (!solvable) {
    				throw new Exception("The custom build level is not solvable.");
    			}
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    			throw new Exception("Try again");
    		} catch (ClassNotFoundException e) {
    			e.printStackTrace();
    			throw new Exception("Try again");
    		}
    	}
    }
    
    /**
     * Method to save the file 
     * @param level current level that is saved
     * @return boolean true if it can be saved, else false if it cannot be saved
     * @throws Exception if an exception occurs
     */
    public boolean saveFile(int level) throws Exception {
    	String fileName = "level" + level + ".xml";
    	this.level = level;
    	board.rabbitCount = this.getRabbitRount();
    	this.checkFileExists(fileName);
    	this.checkIfLevelIsSolvable();
    	FileWriter writer = new FileWriter(Constants.SAVED_LEVEL_PATH + fileName);
    	writer.write(this.exportToXML(level));
    	writer.close();
    	return true;
    }

    /**
     * Method to get the rabbit count in the game
     * @return int count, this is the count of the number of rabbits
     */
	private int getRabbitRount() {
    	int count = 0;
    	Square squares[][] = board.getSquares();
		for (int y = 0; y < Constants.BOARD_SIZE; y++) {
			for (int x = 0; x < Constants.BOARD_SIZE; x++) {
				if (squares[x][y].hasPiece()) {
					if (squares[x][y].getPiece().getType() == PieceType.RABBIT) {
						count++;
					}
				}
			}
		}
		return count;
    }
    
	/**
	 * Method to generate the XML content of the game
	 * @param level for which the XML is generated
	 * @return string containing the XML representation
	 */
    public String exportToXML(int level) {
    	String xml = "<Level>\n";
    	xml += "    <LevelNumber>" + level + "</LevelNumber>\n";
    	xml += "    <RabbitCount>" + board.rabbitCount + "</RabbitCount>";
    	xml += board.toXML() + "\n";
    	xml += "</Level>";
    	
    	return xml;
    }
    
    /**
     * Method to parse the start tags of the xml file
     * 
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *        there are no attributes, it shall be an empty
     *        Attributes object.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {    	
    	current = Constants.XMLTerms.valueOf(qName);
    }
    
    /**
     * Method to parse the end tags of the xml file.
     * 
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
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
     * Receive notification of character data inside an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method to take specific actions for each chunk of character data
     * (such as adding the data to a node or buffer, or printing it to
     * a file).</p>
     *
     * @param ch The characters.
     * @param start The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
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
     * Method to parse the XML content from a file
     * @throws Exception if an exception occurs
     */
    private void parseXML() throws Exception {  	
    	File file = new File(Constants.SAVED_LEVEL_PATH + "level" + level + ".xml");	
        SAXParserFactory SAXFactory = SAXParserFactory.newInstance();
        SAXParser SAXParser = SAXFactory.newSAXParser();
        SAXParser.parse(file, this);
    }
    
    /**
     * Method to add holes to the board object
     * @throws Exception if an exception occurs
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
     * Method to build the level
     * @return boolean true if the level can be build
     * else returns false
     */
    public Boolean buildLevel() {
    	try {
			if (level != 0) {
				this.parseXML();
			}
			this.addHoles();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
}
