
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

- Overall principles: 
Separating the model, view and controller in accordance with the MVC model
The Board is set to be complete once all the Rabbits are placed in the Holes.

- Animal Class:
-----------Animal class is the parent class of all moving pieces on the board (Fox and Rabbit). 
Animals are selectable and movable. Classes that extend Animal must be responsible for handling their 
own movement. This is delegation, because the board is not the one moving, it is the animal moving, so the animal
themselves take care of whether they can move, and how they actually move.

- AutoSolver Class:
---------The AutoSolver class is a class that attempts to solve a level of JumpIn by trying different moves
and comparing game state. It does this by creating and searching through all the possible moves. See the AutoSolver 
logic section below for more information about this class and how it works.

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
handle these events. It handles the event by interpreting the kind of event that occurred and, calling
the appropriate methods to adjust the board and update the view. The reason that Controller implements Runnable interface
is because we want to have multiple threads executing in parallel in our program, and this is used when we have 
use the auto-solve feature, so that we can make the moves to solve the level one at a time so the user can see them 
happen in sequence.

- Fox Class:
-----------Fox is a subclass of the abstract Animal class. It can be moved only either horizontally or vertically,
so this information was captured using a boolean as an instance variable (only 2 options). Fox contains its location 
as well as the location of its body part. Fox is responsible for handling the movement of the fox by updating its locations.

- Hole Class:
-----------Hole is a subclass of the abstract Piece class. It is stationary and cannot be moved. It can 
contain an Animal piece. The reason why hole can contain an Animal, but not a Piece, is because a general piece 
cannot be inside a hole, because that would include things like mushrooms as well. But only something such as a Rabbit
can be inside the Hole.

- JumpIN Class:

----------The text-based version of the game. The reasoning for this game was because we didn't want the controller of 
the game in the text-based version as well as the text-based view to be in the same class as the game logic. The 
reasoning for this design decision is that we knew there would be a GUI in the 2nd iteration and beyond, and having 
support for a text-based view and controller must be seperate from the logic, because the view and controller change, 
but the model must be the same regardless. 

- Location Class:
-----------The Location class is used to create positions for the squares on the board. This class is also used
to maintain and update the positions of pieces on the board. It has methods to get and set one or both of its coordinates,
as well as important methods such as toString(), equals(), and compareTo(), which are used to represent, check for equality,
and compare locations respectively. We choose to keep the coordinates as "int" because decimals are not relevant.s

- Move Class:

-----------The Move class creates an instance of a move on the board. It retains the Animal piece moved and the location
it was moved to. This class is utilized by the undo/redo functionality to undo moves made by the player. The decision
we made was to not store the oldLocation as it is not necessary, because the Animal itself has its location stored 
as an instance variable. 

- MoveStack Class:
-----------The MoveStack class is used to make an instance of a stack that contains moves. A stack was used 
because when undoing, you want to undo the last move made, and when redoing, you want to redo the last undo 
made. A stack uses the FIFO (first-in, first-out) principle, which does just that.

- Mushroom Class:

------------Mushroom is a subclass of the Piece class and cannot be moved or selected. Its only purpose is
as an obstacle on the board which the Rabbit can jump over. A Rabbit can jump over several mushrooms in one move 
if they are all in a row. 

- Parser Class:
-----------Used to parse user input for text-based version. It is used because it separates processing 
the user input from the actual game logic (decoupling). It was not necessary for Parser to have a reference to the board,
because in the text-based version, the class called JumpIn has both a reference to a board and a parser, and simply
uses the parser functionality and calls the functions on the board. 

- Piece Class:
-----------Piece class is the parent class of all pieces on the board. 
A piece has a type (RABBIT, MUSHROOM, etc..), a piece can either be selectable and movable, or unselectable
and unmovable. All none moving pieces extend Piece (Hole and Mushroom). Moving pieces extend Animal which
extends Piece.

- PieceType Class:
-----------PieceType is used to declare an enum with all valid piece types such as RABBIT, FOX, HOLE, etc.
The reasoning for using the PieceType class was so that we would decouple the logic in Piece from PieceType, 
and if we want to change the acceptable types in the game, we have them organized in a neat fashion.

- Rabbit Class:
-----------Rabbit is a subclass of the abstract Animal class. It can be moved by jumping over other obstacles.
Rabbit contains it's location and is responsible for handing movement of the rabbit by updating it's location.
A Rabbit also has a colour, which is important when distinguishing when there are multiple rabbits on the board

- Square Class:
-----------The Square class extends JButton. Squares appear on the GUI as buttons. Each square retains its
location and the piece on top of it. The reason square would extend JButton was a design decision in order to make in
easy in the GUI, because when we initialize the squares, we create the buttons at the same time. 

- View Class:
----------The View class is a class that builds and displays a visual representation of the model as a GUI. It has a controller, which it alerts when a button or 
menu is clicked and, a Board which it uses to build the GUI. The reason why view has a JFrame as an instance variable
is that we can display the GUI. If we extended JFrame, we might be limiting ourselves, because it could be that 
we need to have view extend something else in the future, and we can only do single-class inheritance.
It should be noted that the view also has a reference to the model (board), as well as the the controller, as per
the MVC pattern. Our design decision therefore was to use this well-known and easy to implement design pattern to 
organize our classes. 

- ViewBuilder
---------- The ViewBuilder class is a class that allows for a common way to build a view, which can be used for both 
the Level Building GUI and the regular GUI. Following this approach allows for a common way to construct the view 
without having to replicate the same code in both the main-game GUI and the level building GUI.

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

- Level builder logic 
----------Level builder has a method that acts like an onclick event handler. When any square is click from the level builder view, it check if the square is empty
if it is, then it added a mushroom on it. If that piece is clicked again, it adds a rabbit on it. If it is clicked again, it adds a horizontal fox, then a vertical 
fox, then finally, it clears the piece. If a horizontal fox cannot be added, it skips and tries to add the vertical fox. If a vertical fox cannot be added, then it
skips and clears the piece. If the selected piece is a hole, it check if it is empty. It is not empty, it clears the rabbit from the hole. It is empty, it added a
rabbit in the hole. After building the level, it asks user to enter a level number to save the level. If that level already exits, it throws an error and asks the
users to enter another level. Then, it checks if the level is solvable. If it is not, it throws another error. If the level is solvable, it generates the XML object
from all the pieces in the board. Then, it writes the XML object to the file and saves it.

- Level builder parsing XML logic
----------For parsing the XML content from the file, DefaultHandler is used. When a tag is started, the tag is stored in a variable. When, the DefaultHandler is
parsing the current tag - if it is a rabbit color, the color is saved. If it is coordinate, the coordinate is saved, and so.. Once, the DefaultHandler is parsing
the end tags, we collect all the stored and necessary information of the end tag object and create the object. Then, the object is placed in the squares using 
the coordinates as the location. After the parsing is complete, we add all the holes at specific locations. If the location contains any other objects besides
a rabbit, the level is invalid. If it contains a rabbit, then the rabbit is added in the hole. XML parsing uses a current board and all the operations are done 
on that board. So, there is no need to return and set a new board.

- Auto solver logic 
----------The auto solver starts by finding all the animals in the game. Then, it goes through each animal and finds all the possible moves. After finding all the
possible moves for each animal, it filters the possible moves into moves to make stack. The filteration of the possible moves is simple. It moves all the pieces and
gets the new game states. Then, it checks if the game state already exists in the tree or not. If it does, it discards the move. If it does not, it considers that 
move. After the move is made, it added the recent move made's game state into the decision tree. If the auto solver hits a road block down the tree, it continues to 
undo till the parent node it LAST branched off from. It continues till the game is solved. If by 1000 tries, the game is still not solved. It returns false. If the 
game is solved, it returns true. The autosolver uses depth first search algorithm. Why DFS? because it will look like a human solving the game when the view updates 
itself in the loop.

---------------------------------------------------------------------------------------------------
Changes since last iteration:

-Updating/finalizing all documentation (UML class/sequence diagrams, Javadoc etc.)
-Improved the auto-solver logic, and allows the level to be solved move-by-move to the user using threads
-Changing contents of design document to explain in detail our design decisions.
-Updating the JUnit tests for the new classes
-Modifying JMenuBar to have the new options such as level builder, level save/load etc.
-Creating a different view for the level builder
-Instead of the controller updating a view, the board dispatches an event lister for the view to update itself
-Using serlization to save and load the board state
-Auto solver uses a complete different logic (check design decisions for more details)
-View and Level builder now extends the view builder class.
-View builder class contains all the helper methods for the views
-Constants class to hold all the constants

---------------------------------------------------------------------------------------------------
