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
 * DOCUMENT LEFT TO BE DONE 
 * TODO
 * 
 * @author Khalil Aalab - 101070879
 * @author Kamaluddin Shakiri - 101054933
 * @author Simon Yacoub - 101044159
 * @author Md Aiman Sharif - 101062765
 * @author Shoaib Khan - 101033582
 *
 */
public class LevelBuilder extends DefaultHandler {
	private XMLTerms current;
	private Board board;
	private Square[][] squares;
	private int level;
	private enum XMLTerms {Level, LevelNumber, Mushroom, Coordinate1, Coordinate2, Rabbit, Color, RabbitCount, Fox, Movement}
	private Piece currentPiece;
	private Location currentLocation;
	private Location foxBodyLocation;
	private Rabbit.RABBIT_COLORS currentRabbitColor;
	private ArrayList<Location> possibleHoleLocations; 
	private Boolean horizontalMovement;
	public static final String SAVED_LEVEL_PATH = "Levels/";
	
	public LevelBuilder(Board board) {
        this.board = board;
        this.init();
	}
	
    public LevelBuilder(int level, Board board) {
    	this(board);
        this.level = level;
    }
    
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
    
    private Piece createMushroom(Location location) {
    	return new Mushroom(location);
    }
    
    private Rabbit createRabbit(Location location) {
    	int randomNumber = (int) (Math.random() * ((2 - 0) + 1));
    	return new Rabbit(Rabbit.RABBIT_COLORS.values()[randomNumber], location);
    }
    
    
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
    
    private void placeVericalFoxes(Square square) {
		Fox[] verticalFoxes = this.createFoxVertical(square.getLoc());
		if (verticalFoxes == null) {
			square.setCounter(5);
			square.doClick();
			
		}
		
		else {
			square.setPiece(verticalFoxes[0]);
			Square secondSquare = board.getSquareAtLocation(verticalFoxes[1].getPieceLocation());
			secondSquare.setPiece(verticalFoxes[1]);
    		square.setCounter(4);
    		secondSquare.setCounter(5);
		}
    }
    
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
    		this.placeVericalFoxes(square);
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
    
    public void checkFileExists(String fileName) throws Exception {
		File saveDirectory = new File(SAVED_LEVEL_PATH);
		File[] savedGameFiles = saveDirectory.listFiles();
		for (int i = 0; i < savedGameFiles.length; i++) {
			if (fileName.equals(savedGameFiles[i].getName())) {
				throw new Exception("Level " + level + " already exists.\nPlease the level under a different name.");
			}
		}
		
    }
    
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
    
    public void checkIfLevelIsSolvable() throws Exception {
    	if (board.rabbitCount == 0 || board.rabbitCount > 5) {
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
    
    public boolean saveFile(int level) throws Exception {
    	String fileName = "level" + level + ".xml";
    	board.rabbitCount = this.getRabbitRount();
    	this.checkFileExists(fileName);
    	this.checkIfLevelIsSolvable();
    	FileWriter writer = new FileWriter(SAVED_LEVEL_PATH + fileName);
    	writer.write(this.exportToXML(level));
    	writer.close();
    	return true;
    }

	private int getRabbitRount() {
    	int count = 0;
    	Square squares[][] = board.getSquares();
		for (int y = 0; y < Board.BOARD_SIZE; y++) {
			for (int x = 0; x < Board.BOARD_SIZE; x++) {
				if (squares[x][y].hasPiece()) {
					if (squares[x][y].getPiece().getType() == PieceType.RABBIT) {
						count++;
					}
				}
			}
		}
		return count;
    }
    
    public String exportToXML(int level) {
    	String xml = "<Level>\n";
    	xml += "    <LevelNumber>" + level + "</LevelNumber>\n";
    	xml += "    <RabbitCount>" + board.rabbitCount + "</RabbitCount>";
    	xml += board.toXML() + "\n";
    	xml += "</Level>";
    	return xml;    	
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {    	
    	current = XMLTerms.valueOf(qName);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	XMLTerms endElement = XMLTerms.valueOf(qName);
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
    
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
    	String string = new String(ch, start, length);
    	if (string.contains("\n")) {
    		return;
    	}
    	
    	switch (current) {
			case Color:
				currentRabbitColor = Rabbit.RABBIT_COLORS.valueOf(string);
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
         
    private void parseJSON() throws Exception {  	
    	File file = new File(SAVED_LEVEL_PATH + "level" + level + ".xml");	
        SAXParserFactory SAXFactory = SAXParserFactory.newDefaultInstance();
        SAXParser SAXParser = SAXFactory.newSAXParser();
        SAXParser.parse(file, this);
    }
    
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
