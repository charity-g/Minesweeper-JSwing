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
- You can generate the second required event related adding bombs to a board by clicking any button on the board when 
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
- StackOverFlow

# Phase 4 - Task 2
Sat Nov 26 15:16:38 PST 2022: Saved board loaded.
Sat Nov 26 15:16:38 PST 2022: Bomb set at column 6 and row 2
Sat Nov 26 15:16:38 PST 2022: Bomb set at column 1 and row 7
Sat Nov 26 15:16:38 PST 2022: Bomb set at column 8 and row 0
Sat Nov 26 15:16:38 PST 2022: Bomb set at column 8 and row 3
Sat Nov 26 15:16:38 PST 2022: Bomb set at column 2 and row 2
Sat Nov 26 15:16:38 PST 2022: Bomb set at column 6 and row 6
Sat Nov 26 15:16:38 PST 2022: Bomb set at column 8 and row 1
Sat Nov 26 15:16:38 PST 2022: Bomb set at column 5 and row 5
Sat Nov 26 15:16:38 PST 2022: Bomb set at column 8 and row 6
Sat Nov 26 15:16:38 PST 2022: Bomb set at column 1 and row 4
Sat Nov 26 15:16:38 PST 2022: All 10 bombs set on board.
Sat Nov 26 15:16:38 PST 2022: Board created with 9 width, 9 height, and 10 bombs on board.

# Phase 4 - Task 3
I would definitely change a few design choices I had made.

1. The algorithm that sets bombs on Board is very heavy, I would like to refactor it out of the Board class.
2. Reduced coupling between Game and Board. Sometimes it is difficult to tell when Board's methods end and Game's 
begin, so I would like to take time to write down all the methods concerning changing something drastic on the board, 
such as unearth a bunch of tiles
3. My GUI is actually the 2nd, refactored version after the brute force which I am pretty pleased with. However, I
found it difficult to add a button to flag/unflag squares on the board, because of the way I had structured my 
design, which separated the board modificiation buttons to not have knowledge of the board, but pass information to 
GameFrame to switch the boards. This proves problematic for a flag/unflag square button, as it would need information 
about the board directly to flag it, so I will consider refactoring to allow knowledge of a board.
