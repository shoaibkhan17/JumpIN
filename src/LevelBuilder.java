import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LevelBuilder extends DefaultHandler {
	private static final String LEVELS_PATH = "Levels/";
	private XMLTerms current;
	private Board board;
	private Square[][] squares;
	private int level;
	private enum XMLTerms {Level, LevelNumber, Mushroom, Coordinate1, Coordinate2, Rabbit, Color, RabbitCount}
	private Piece currentPiece;
	private Location currentLocation;
	private Rabbit.RABBIT_COLORS currentRabbitColor;
	private ArrayList<Location> possibleHoleLocations; 
	     
    public LevelBuilder(int level, Board board) {
        this.board = board;
        squares = board.getSquares();
        this.level = level;
        currentPiece = null;
        currentLocation = null;
        possibleHoleLocations = new ArrayList<>();
        possibleHoleLocations.add(new Location(0, 0));
        possibleHoleLocations.add(new Location(4, 0));
        possibleHoleLocations.add(new Location(2, 2));
        possibleHoleLocations.add(new Location(0, 4));
        possibleHoleLocations.add(new Location(4, 4));
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//    	System.out.println("Start: " + qName);
    	
    	current = XMLTerms.valueOf(qName);
//    	System.out.println("current at "+ current);
    	
//    	switch (current) {
//			case Color:
//				break;
//			case Coordinate1:
//				break;
//			case Coordinate2:
//				break;
//			case Level:
//				break;
//			case LevelNumber:
//				break;
//			case Mushroom:
//				currentPiece = new Mushroom();
//				break;
//			case Rabbit:
//				break;
//			default:
//				break;
//	    }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	XMLTerms endElement = XMLTerms.valueOf(qName);
    	switch (endElement) {
		case Color:
			break;
		case Coordinate1:
			break;
		case Coordinate2:
			break;
		case Level:
			break;
		case LevelNumber:
			break;
		case Mushroom:
			currentPiece = new Mushroom();
			squares[currentLocation.getX()][currentLocation.getY()].setPiece(currentPiece);
			break;
		case Rabbit:
			currentPiece = new Rabbit(currentRabbitColor, currentLocation);
			squares[currentLocation.getX()][currentLocation.getY()].setPiece(currentPiece);
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
				break;
			case Level:
				break;
			case LevelNumber:
				break;
			case Mushroom:
				break;
			case Rabbit:
				break;
			case RabbitCount:
				board.rabbitCount = Integer.parseInt(string);
				break;
			default:
				break;
	    	}
    }
         
    public void parseJSON() throws Exception {  	
    	File file = new File(LEVELS_PATH + "level" + level + ".xml");	
    	System.out.println("in file " + file.getName());	
        SAXParserFactory SAXFactory = SAXParserFactory.newDefaultInstance();
        SAXParser SAXParser = SAXFactory.newSAXParser();
        SAXParser.parse(file, this);
    }
    
    public void addHoles() throws Exception {
    	for (Location holeLoc: possibleHoleLocations) {
    		int x = holeLoc.getX();
    		int y = holeLoc.getY();
    		if (!squares[x][y].hasPiece()) {
    			squares[x][y].setPiece(new Hole());
    		}
    	}
    	
    }
    
    public Boolean buildLevel() {
    	try {
			this.parseJSON();
			this.addHoles();
			return true;
		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		}
    	
    }
    
	public static void main(String[] args) {
		Board board = new Board();
		int level = 1;
		LevelBuilder levelBuilder = new LevelBuilder(level, board);
		levelBuilder.board.printBoard();
		levelBuilder.buildLevel();
		levelBuilder.board.printBoard();
	}
}
