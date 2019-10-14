
public class JumpIN {

	public static void main(String[] args) {
		//Declare board
        Board testingBoard = new Board();
        //initialize board
        testingBoard.initBoard();
        boolean gameOn = true;
        
        //game loop 
        while(gameOn) {
        	testingBoard.playGame();
        	//1.print board
            System.out.println(testingBoard.printBoard());
            //2.checkBoardState
            //3.getMove
            //4.validateMove
            //5.if valid apply move and reprint board
            //6.Check game status
            //7.Resume or break loop
        	
        }
	}
	
	public void playGame() {
		
	}

}
