
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
--If a horizontal fox wants to move left, it needs to be moved by it's head.
--if a horizontal fox wants to move right, it needs to be moved by it's tail.
--If a vertical fox wants to move up, it needs to be moved by it's head.
--if a vertical fox wants to move down, it needs to be moved by it's tail.

-Information on deselecting a piece.
--If you select a piece and then want to deselect it, just place it back it its own location.

---------------------------------------------------------------------------------------------------
Design Decisions:
 
-Separating the model, view and controller in accordance with the MVC model

-The Parser class was created so that the user input can be filtered through

-The board class contains squares which represent squares on the board. Each square has a location, 
and the square can also contain a piece

-Each Hole can also contain another Piece inside of it. 

-Each Fox or Rabbit can validate its own movement. Once it is a valid move, it can ask the Board to move 
 the Piece.

-The JumpIN game checks if the Board is completed after each successful move. 

-The Board is set to be complete once all the Rabbits are placed in the Holes.

---------------------------------------------------------------------------------------------------
Known Issues: 

-Fox movement (Not really an issue but definitely can be improved)

--Problems
---If a horizontal fox wants to move left, it needs to be moved by it's head.
---if a horizontal fox wants to move right, it needs to be moved by it's tail.
---If a vertical fox wants to move up, it needs to be moved by it's head.
---if a vertical fox wants to move down, it needs to be moved by it's tail.

--Solution
---Check if a tail was selected to move right, it should move the head and then the tail with it.
---Vice versa for the head and the vertical direction fox.
---------------------------------------------------------------------------------------------------
Changes since last iteration:
-Adding interactive GUI
-GUI is rendered using the view class and controlled using the controller class.
-The game supports 5 levels now.
-Making Fox and Rabbit extend Piece instead of Animal, and updating UML diagram.
-Changing contents of design document.
-Creating JUnit tests for the functionality of the game and game logic.
-Creating a separate folder to organize the test cases, and a test suite to run the test cases.
-Creating a directory for the GUI images to be stored.
-Creating JMenuBar to have options like exit and reset.

---------------------------------------------------------------------------------------------------
Future Road Map: 

-Show all available locations user can move when they select a piece
-Tell user minimum number of moves to win the level
-Allow hints to be given to the user within the GUI
-Allow for unlimited redo/undo using a stack to keep track of moves
-Allow for game to be saved
-Fixing the known issue with the fox movement


---------------------------------------------------------------------------------------------------
