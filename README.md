# JumpIN

Java code for the JumpIN game
Currently working towards Milestone 3
**Deadline: Nov 18th**

## For Developers
Please push to the development branch. Every once in a while, the code from the development branch will be code reviewed, verified and then merged into the main master branch. 

## Project Description

The goal of this team project is to develop a software version of a one-person puzzle game officially known as “Jump IN’”. The rules of the game can be found at: https://youtu.be/8sEoYzcmOfc . Pay attention to the ways rabbits can jump, to the way foxes can slide, and to the notation used for the moves in the solution! I also have the game in my office, so feel free to come and try it if you need any clarification. The project is divided into 4 iterations, each ending with a milestone corresponding to deliverables that will be graded. You will be able to use the TA feedback from iteration i for iteration i+1.

## Milestone 1

A text-based playable version of the game, i.e., a user should be able to play the game via the console using the keyboard, and see a text-based view of the board after each move. The initial configuration of the puzzle can be done programmatically, e.g., in a constructor or main() method. Also required, the UML modeling of the problem domain (class diagrams, sequence diagrams, complete variable and method signatures), detailed description of the choice of data structures and relevant operations: you are providing an initial design and implementation for the Model part of the MVC. Do not worry about any GUI yet. 

 - Deliverables: readme file (see explanation below) + code (source +
   executable in a jar file) + UML diagrams + documentation, all in one
   zip file. 
 - **Deadline  Monday Oct 21st. Weight: 15% of the overall project grade.**
 
## Milestone 2

GUI-based version (now you’re adding the View and the Controller!) of the game. Display must be in a JFrame, and user input is via the mouse. You have freedom for other GUI decisions. Also required: Unit tests for the Model

 - Deliverables: readme file + design + corresponding tests + code + documentation, all in one zip file. In particular, document the changes you made to your UML and data structures from Milestone 1 and explain why.
 - **Deadline  Monday Nov 4th. Weight: 20% of the overall project grade.**
 
## Milestone 3

Additional features: solver + unlimited undo/redo. The solver uses either depth-first or breadth-first search to explore all possible moves and sequences of moves, till it finds the solution. You can integrate the solver in the GUI in terms of hints that could be given to the user. Undo/redo allows the user to backtrack and try other moves, and the user should be able to backtrack as far back as necessary.

 - Deliverables: readme file + code + corresponding tests + refined design + documentation. The program must work robustly, and the code must be “smellfree” (we will be hunting for smells!). Make sure that you document the changes since the last iteration, and the reason for those changes.
 - **Deadline  Monday Nov 18th. Weight: 30% of the overall project grade.**
 
## Milestone 4

Two more things: 1- Save/load features. You may use Java Serialization to achieve this. 2- Game level builder. The levels may be saved in XML or JSON. The game level builder should use the solver to validate whether your level design can actually yield a solution.

 - Deliverables: readme file + code + tests + documentation. Your project should be well packaged, and the program(s) should be easy to install and run.
 - **Deadline  Monday Dec 2nd. Weight: 35% of the overall project grade.**
 
## Other Information

Milestones must contain all necessary files and documentation, even those items that are unchanged from previous milestones. Missing files cannot be submitted after the deadline, no exceptions. Verify that your submission contains all necessary files (in particular, don’t forget to include your source code!) before submitting on cuLearn. The “readme” file, listed as a deliverable for each iteration, is typically a short text file that lists and describes: the rest of the deliverables, the authors, the changes that were made since the previous deliverable, the known issues (known issues are graded less severely than undocumented ones!) and the roadmap ahead. “Documentation” includes up-to-date UML diagrams, detailed descriptions of design decisions made, complete user manuals, and javadoc documentation.

Note that nobody is stopping you from working ahead of schedule! In fact, iteration i+1 will very often give you good insight into iteration i. This is a **team** project. Each team should have 4 or 5 members. A project’s success obviously depends on contributions from each member! So divide the work and cooperate. **Each contribution (source code, documentation, etc.) must contain the name of its author**: we will use this to determine whether there is any significant difference in the quality and quantity of the contributions of the team members. If any such difference is detected, the individual grades will be adjusted accordingly. You are expected to use Github to manage your project (version control, issue-tracking, etc...), but the deliverables for each milestone should also be submitted for marking on cuLearn. Please use a private Github team repository; Github provides this to student accounts; but we are also happy to create a private repo should you need one. A TA and/or the instructor will also be members of each of the Github teams, so that they can track your use of the tool, verify that all group members are contributing, and their feedback will be given by opening new Github Issues. 

The marking scheme for each iteration will be comprised of: completeness of the deliverables, the quality of the deliverables, and, for iterations 2 to 4, we also look at whether you took into account the feedback you received from the TA in the previous iteration. 

**Copyright matters!**
Note that “Jump IN” is a commercial product, and there’s copyright attached to it. It is one thing to use copyright material under “fair use” for educational purposes, it would be quite another to profit from someone else’s work by offering a copycat version. The law is complex on that front, so please exercise due diligence if you want to make your Github repo public once the course is over.