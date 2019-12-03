
---------------------------------------------------------------------------------------------------
Authors:

-Khalil Aalab - 101070879
-Kamaluddin Shakiri - 101054933
-Simon Yacoub - 101044159
-Aiman Sharif - 101062765
-Shoaib Khan - 101033582

---------------------------------------------------------------------------------------------------
User Manual (For milestone 1):

-Basic Information
--Currently 8 levels are developed.
--The goal of the game is to place all the rabbits inside holes.
--The game can be played as a text-based game only, or with the GUI
--Rabbits can jump over objects, including mushrooms, foxes and other rabbits
--Foxes can slide on empty spaces in the direction that the fox is oriented

-Instructions on how to play the game (text-based).
--Let's consider this level

        A   B   C   D   E
      *********************
    1 * O *   *   * M * O *
      *********************
    2 *   * M * R * F * F *
      *********************
    3 *   *   * M *   *   *
      *********************
    4 *   *   *   *   *   *
      *********************
    5 * O *   *   *   * O *
      *********************

--Step 1: Enter the location of the piece you would like to move.
--For example: "C2"
--Step 2: Enter the location where you would like to move this piece.
--For example: "C4"
--The board after these commands.

        A   B   C   D   E
      *********************
    1 * O *   *   * M * O *
      *********************
    2 *   * M *   * F * F *
      *********************
    3 *   *   * M *   *   *
      *********************
    4 *   *   * R *   *   *
      *********************
    5 * O *   *   *   * O *
      *********************

--Keep playing the game until all the rabbits are in a hole
-- In this case, just the one rabbit needs to be inside one of the holes

-Information on hole status.
--After every move, a text is printed which displays the hole status.
--The purpose of this is to let the users know if there is a rabbit inside the hole. 

-Information on de-selecting a piece.
--If you select a piece and then want to de-select it, just place it back it its own location.

---------------------------------------------------------------------------------------------------
Design Decisions:

- Overall: 

---------Separating the model, view and controller in accordance with the MVC model
---------The Board is set to be complete once all the Rabbits are placed in the Holes.

- Animal Class:

-----------Animal class is the parent class of all moving pieces on the board (Fox and Rabbit). 
Animals are selectable and movable. Classes that extend Animal must be responsible for handling their 
own movement.

- AutoSolver Class:
---------The AutoSolver class is a class that attempts to solve a level of JumpIn by trying different moves
and comparing game state. It does this by creating and searching through all the possible moves.

- Board Class:
 
---------The board class handles movement of pieces on the squares on the board. 
Each square has a location, and can contain a piece. After a move has been made the board 
class checks if the game has been won by checking if all the rabbits on the board are in a hole.

- BoardListener Class:
 
---------The boardListener class is an interface which allows the view to listen to the board, in accordance with the MVC pattern.
The model must be the one to update the view, not the controller. It is for this reason that the view implements the boardListener interface,
so when the board changes state and the view is needed to update, the view can then update itself.

- CombinedIcon Class:

-----------CombinedIcon is a class that extends Icon and combines two icons together. It's purpose 
is to combine the icon of a rabbit and a hole when a rabbit is in a hole. 

- Constants Class:

-----------The constants class is a class that we decided to use in order to group various constants that would be 
used between various different classes in our game, such as the length of the board, the colours of the rabbits, file organization 
information, and instructions on the game. Our decision was based on the fact that it is easier to develop this way because
that way, all entire development team knows where the necessary constants for our project are stored, and we can access them easily.

- Controller Class:

----------The controller class' purpose is to receive events that occur on the GUI and then
handle these events.It handles the event by interpreting the kind of event that occurred and, calling
the appropriate methods to adjust the board and update the view. 

- Fox Class:

-----------Fox is a subclass of the abstract Animal class. It can be moved horizontally or vertically.
It contains it's location as well as the location of it's body part. Fox is responsible for 
handling the movement of the fox by updating its locations.

- Hole Class:

-----------Hole is a subclass of the abstract Piece class. It is stationary and cannot be moved. It can 
contain a piece similarly to the way a square does.

- JumpIN Class:

----------The text-based version of the game.

- Location Class:

-----------The Location class is used to create positions for the squares on the board. This class is also used
to maintain and update the positions of pieces on the board. 

- Move Class:

-----------The Move class creates an instance of a move on the board. It retains the piece moved and location
it was moved to. This class is utilized by the undo/redo functionality to undo moves made by the player.

- MoveStack Class:

-----------The MoveStack class is used to make an instance of a stack that contains moves. A stack was used 
because when undoing, you want to undo the last move made, and when redoing, you want to redo the last undo 
made. A stack uses the FIFO (first-in, first-out) principle, which does just that.

- Mushroom Class:

------------Mushroom is a subclass of the Piece class and cannot be moved or selected. It's only purpose is
as an obstacle on the board. 


- Parser Class:

-----------Used to parse user input for text-based version. It is used because it seperates processing 
the user input from the actual game logic (decoupling)

- Piece Class:

-----------Piece class is the parent class of all pieces on the board. 
A piece has a type (RABBIT, MUSHROOM, etc..), a piece can either be selectable and movable, or unselectable
and unmovable. All none moving pieces extend Piece (Hole and Mushroom). Moving pieces extend Animal which
extends Piece.

- PieceType Class:

-----------PieceType is used to declare an enum with all valid piece types such as RABBIT, FOX, HOLE, etc.

- Rabbit Class:

-----------Rabbit is a subclass of the abstract Animal class. It can be moved by jumping over other obstacles.
Rabbit contains it's location and is responsible for handing movement of the rabbit by updating it's location.
A Rabbit also has a colour, which is important when distinguishing when there are multiple rabbits on the board

- Square Class:

-----------The Square class extends JButton. Squares appear on the GUI as buttons. Each square retains its
location and the piece on top of it.

- View Class:

----------The View class is a class that builds and displays a visual representation of the model as a GUI. It has a controller, which it alerts when a button or 
menu is clicked and, a Board which it uses to build the GUI. 

- LevelBuilder Class:

----------The LevelBuilder class is the class which is used to allow the user to build a custom-made level and save it. It should 
also be noted that the use of SAXParser is present in the LevelBuilder class, and the reason for that is because it is a tool that 
we have already learned in the labs, and saves us from having to write our own parser for XML. It is a design decision made by us 
to save time and effort, and not re-invent the wheel. Another decision in this class is to use ByteArrayInput and ByteArrayOutput Streams
when deep copying the board, which is necessary when we want to manipulate the board without actually changing the original board, 
such as when we want to check if the board is solvable. The reason we made this decision was because this is an easy way to deep-copy,
because we simply output then input the bytes, and we've got a deep copy of the board, without initializing each variable one by one.
So, this was a good decision we made to improve efficiency.

- LevelBuilderView Class:

----------The LevelBuilderView class is needed in order to display a frame besides the main game frame, and the frame for this class 
allows for the user to build a custom level. Our decision was to allow the user to click on each button on the board, and this 
would change what piece they want to put at that location. This was a decision we made, to separate the logic of the levelbuilder 
from the view, which is somewhat along the lines of the MVC pattern, except that we do not have a separate controller. We made this 
decision because we thought that creating another class for the controller would be overdoing it, because there would not be 
significant enough benefit to use it. There only needed to be one view for the level builder, and one view for it, and so 
introducing a controller just for the sake of the design pattern is not a logical thing to do; it would just complicate things more. 

---------------------------------------------------------------------------------------------------
Changes since last iteration:

-Updating/finalizing all documentation (UML class/sequence diagrams, Javadoc etc.)
-Improved the auto-solver logic, and allows the level to be solved move-by-move to the user using threads
-Changing contents of design document to explain in detail our design decisions.
-Updating the JUnit tests for the new classes
-Modifying JMenuBar to have the new options such as level builder, level save/load etc.

---------------------------------------------------------------------------------------------------
Optional Future Road Map (Not in Project Requirements): 

-Show all available locations user can move when they select a piece
-Allow hints to be given to the user within the GUI
-Provide sounds when game is won, and when any move is made
-These sounds can correspond to each animal (fox vs. rabbit)

---------------------------------------------------------------------------------------------------
