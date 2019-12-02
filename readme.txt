
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
--Currently five levels are developed.
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

- CombinedIcon Class:

-----------CombinedIcon is a class that extends Icon and combines two icons together. It's purpose 
is to combine the icon of a rabbit and a hole when a rabbit is in a hole. 

- Constants Class:

-----------CombinedIcon is a class that extends Icon and combines two icons together. It's purpose 
is to combine the icon of a rabbit and a hole when a rabbit is in a hole. 

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
made.

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

----------The View class is a class that builds and displays a visual representation of the model as a GUI. It has a controller, which it alerts when a button or 
menu is clicked and, a Board which it uses to build the GUI. 

- LevelBuilderView Class:

----------The View class is a class that builds and displays a visual representation of the model as a GUI. It has a controller, which it alerts when a button or 
menu is clicked and, a Board which it uses to build the GUI. 

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
