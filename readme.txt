
---------------------------------------------------------------------------------------------------
Authors:

-Khalil Aalab - 101070879
-Kamaluddin Shakirki - 101054933
-Simon Yacoub - 101044159
-Aiman Sharif - 101062765
-Shoaib Khan - 101033582

---------------------------------------------------------------------------------------------------
User Manual:

-Basic Information
--Currently five levels are developed.
--Level 2 is selected by default.
--The goal of the game is to place all the rabbits inside holes.
--The game can be played as a text-based game only, or with the GUI
--Rabbits can jump over objects, including mushrooms, foxes and other rabbits
--Foxes can slide on empty spaces in the direction that the fox is oriented

-Instructions on how to play the game.
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

--Keep playing the game until all the rabits are in a hole
-- In this case, just the one rabit needs to be inside one of the holes

-Information on hole status.
--After every move, a text is printed which displays the hole status.
--The purpose of this is to let the users know if there is a rabbit inside the hole. 

-Information on moving the fox.
--If a horizontal fox wants to move left, it needs to be moved by it's head.
--if a horizontal fox wants to move right, it needs to be moved by it's tail.
--If a vertical fox wants to move up, it needs to be moved by it's head.
--if a vertical fox wants to move down, it needs to be moved by it's tail.

-Information on deselecting a piece.
--If you select and piece and then want to deselect it.
--Then, just place it back it it's own location.

---------------------------------------------------------------------------------------------------
Design Decisions:
 
-The board class contains squares which represent squares on the board. Each square has a location, 
and the square can also contain a piece

-2D array of type Square is used to create the Board.

-Each Square can have a piece inside of it.

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
Future Road Map: 

-Allow hints to be given to the user within the GUI
-Allow for unlimited redo/undo using a stack to keep track of moves
-


---------------------------------------------------------------------------------------------------
