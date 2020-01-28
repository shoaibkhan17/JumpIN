# JumpIN

Java code for the JumpIN game
Currently working towards Milestone 4
**Deadline: Dec 2nd**

## For Developers
Please push to the development branch. Every once in a while, the code from the development branch will be code reviewed, verified and then merged into the main master branch. 

## Project Description

The goal of this team project is to develop a software version of a one-person puzzle game officially known as “Jump IN’”. The rules of the game can be found at: https://youtu.be/8sEoYzcmOfc . Pay attention to the ways rabbits can jump, to the way foxes can slide, and to the notation used for the moves in the solution! I also have the game in my office, so feel free to come and try it if you need any clarification. The project is divided into 4 iterations, each ending with a milestone corresponding to deliverables that will be graded. You will be able to use the TA feedback from iteration i for iteration i+1.

## Milestone 1

A text-based playable version of the game, i.e., a user should be able to play the game via the console using the keyboard, and see a text-based view of the board after each move. The initial configuration of the puzzle can be done programmatically, e.g., in a constructor or main() method. Also required, the UML modeling of the problem domain (class diagrams, sequence diagrams, complete variable and method signatures), detailed description of the choice of data structures and relevant operations: you are providing an initial design and implementation for the Model part of the MVC. Do not worry about any GUI yet. 
 
## Milestone 2

GUI-based version (now you’re adding the View and the Controller!) of the game. Display must be in a JFrame, and user input is via the mouse. You have freedom for other GUI decisions. Also required: Unit tests for the Model
 
## Milestone 3

Additional features: solver + unlimited undo/redo. The solver uses either depth-first or breadth-first search to explore all possible moves and sequences of moves, till it finds the solution. You can integrate the solver in the GUI in terms of hints that could be given to the user. Undo/redo allows the user to backtrack and try other moves, and the user should be able to backtrack as far back as necessary.
 
## Milestone 4

Two more things: 1- Save/load features. You may use Java Serialization to achieve this. 2- Game level builder. The levels may be saved in XML or JSON. The game level builder should use the solver to validate whether your level design can actually yield a solution.

## Credits for Graphical Resources

The pictures and graphical resources used in this game were obtained [here](https://www.smartgames.eu/uk/one-player-games/jumpin).

## License and Disclaimer

> This application is for educational purposes. JumpIN' is a registered commercial product. The developers are not responsible for the distribution of this product.
