
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

-Information on moving the fox.
--Read the below section.

-Information on deselecting a piece.
--If you select a piece and then want to deselect it, just place it back it its own location.

---------------------------------------------------------------------------------------------------
IMPORTANT NOTE WHEN MOVING THE FOXES IN THE VIEW:

-For fox whose head is facing up and whose tail is facing down
--If you want to move it UP, move it by selected its HEAD.
--If you want to move it DOWN, move it by selected its TAIL.

-For fox whose head is facing left and whose tail is facing right
--If you want to move it RIGHT, move it by selected its TAIL.
--If you want to move it LEFT, move it by selected its HEAD.

---------------------------------------------------------------------------------------------------
Design Decisions:

- Overall: 

---------Separating the model, view and controller in accordance with the MVC model
---------checks if the Board is completed after each successful move. 
---------The Board is set to be complete once all the Rabbits are placed in the Holes.

- Board Class:
 
---------The board class contains squares which represent squares on the board. Each square has a location, 
and the square can also contain a piece

- View Class:

----------The Parser class was created so that the user input can be filtered through

- Controller Class:

----------The Parser class was created so that the user input can be filtered through

- TextBased Class:

----------The Parser class was created so that the user input can be filtered through

- Hole Class:

-----------Each Hole can also contain another Piece inside of it. 

- Fox Class:

-----------Each Fox or Rabbit can validate its own movement. Once it is a valid move, it can ask the Board to move the Piece.

- Rabbit Class:

-----------Each Hole can also contain another Piece inside of it. 

- Mushroom Class:

-----------Each Hole can also contain another Piece inside of it. 

- Piece Class:

-----------Each Hole can also contain another Piece inside of it. 

- Square Class:

-----------Each Hole can also contain another Piece inside of it.

- Location Class:

-----------Each Hole can also contain another Piece inside of it. 

- PieceType Class:

-----------Each Hole can also contain another Piece inside of it. 

- CombinedIcon Class:

-----------Each Hole can also contain another Piece inside of it. 

- Parser Class:

-----------Each Hole can also contain another Piece inside of it. 

---------------------------------------------------------------------------------------------------
Changes since last iteration:

-The game supports 5 levels now.
-Changing contents of design document to explain in detail our design decisions
-Creating JUnit tests for the functionality of the game and game logic.
-Modifying JMenuBar to have new options such as undo, redo etc.
-Allow for unlimited redo/undo using a stack to keep track of moves
-Tell user minimum number of moves to win the level

---------------------------------------------------------------------------------------------------
Future Road Map: 

-Show all available locations user can move when they select a piece
-Allow hints to be given to the user within the GUI
-Allow for game to be saved
-Fixing the known issue with the fox movement
---------------------------------------------------------------------------------------------------
