# My Personal Project

### **Task 1** - Project Idea

### **Task 2** - Project proposal
**What will the application do?** <br>
It will be able to simulate, track and allow a user to play a game of minesweeper. 

**Who will use it?** <br>
Anyone who wants to play minesweeper.
 
**Why is this project of interest to you?** <br>
Minesweeper was a game my mom really liked to play when I was a kid- she called it a "smart" game where it would force 
your brian into being strategetic and smart about your moves so it has a lot of cool memories for me.


### **Task 3** - User Stories

- As a user, I want to be able to start a new blank game and thus add multiple squares that are bombs to a 
  list of squares
- As a user, I want to be able to flag a tile
- As a user, I want to be able to unearth a tile(multiple) and see what state it is below
- As a user, I want to be able to unearth a whole bunch of tiles if the tile I have pressed is blank
- As a user, I want to be able to have the ability to click on a bomb and end game
- As a user, I want to be able to win the game once I have unearthed all the non-bomb tiles
- As a user, I want to be able to save the game I am playing down to the state of each square on the board 
- As a user, I want to be able to load a game exactly where I left it off, and continue making moves.


## Instructions for Grader

- You can generate the first required event related to adding bombs to a board by clicking either the "new game"
or "load game" button when first starting up the program, or anytime during the program's run.
- You can generate the second required event related adding bombs to a board by **double-clicking** any button on the board when 
it is loaded into the gui, and it will reveal what is underneath it.
- You can locate my visual component by clicking on a bomb, or winning a game
- You can save the state of my application by pressing save game. This button will only be enabled when you have a
board onscreen
- You can reload the state of my application by pressing load game.

## Credit + References:
- CPSC 210 curriculum
- the Json read and write design from the JsonSerializationDemo, link provided here: 
  - https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
   Classes adapted: 
    - JsonWriter
    - JsonReader
   Methods adapted
    - toJson in my Board and Square classes