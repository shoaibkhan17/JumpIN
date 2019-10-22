
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
--Currently two levels are developed.
--Level 2 is selected by default.
--Level 2 has one rabbit and 1 fox.
--The goal of the game is to place the rabbit inside the hole.
--Rabbits can jump over objects.
--Fox can slide on an empty space it is face in.

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

--Keep playing the game till the rabbit is inside the hole.

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
Data Structures:
 
1- The board class uses a 2D array of squares in order to represent the game board. 
We used this data structure because we always know the size of the board beforehand,
and it is coded as a constant. 

2- The hole locations, however, are simply an LinkedList because the holes are integrated 
into the 2D array of squares, but we also have a seperate data structure for the holes 
in order to iterate over all the holes when needed, without going over all the non-hole
squares.

---------------------------------------------------------------------------------------------------
Operations:

1- With the 2D array, this also makes iterating over the board easier, as 
we can use a nested for-loop with regards to the row and column of squares on the board.

2- The LinkedList is better than an ArrayList in this case, because there is a lot of appending
at the end that happens when the board is initialized, and also in iteration, LinkedList is 
equal to ArrayList in terms of going over the entire List. So, LinkedList is the implementation
that we decided on.

---------------------------------------------------------------------------------------------------
Design Decisions:
-TODO

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
---Vice vera for the head and the vertical direction fox.

---------------------------------------------------------------------------------------------------
Future Roadmap: 
-TODO

---------------------------------------------------------------------------------------------------
