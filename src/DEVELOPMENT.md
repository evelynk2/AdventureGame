**Fixes that need to be made:**

- Object decomposition into separate classes, while ensuring that 
these classes are differentiated
- Combine the URLAdventure and the Adventure
class

**Plan on Accomplishing Changes**
- Create JSON file first
    - Formatting + thinking of an adventure
    
- Decompose Layout into Room, Direction, etc. classes
- Add new methods to account for JSON changes
- Add new tests to account for JSON changes

**Planned Extensions:**

    - Add items that allow teleportation.
        - changes to JSON formatting?
            - Item: teleportation : true / false
            - once Item is picked up, "Congrats, you have 
            picked up an item that can transport you to
            any UNLOCKED room in the house! Where would
            you like to go?"
        
    - Add simple puzzles or challenges to pickup items.
        - Trivia about Evelyn?
        - Item: trivia : "question"
            - validAnswers: ["answer1", "answer2"]
    - Fighting a monster at the ending room
            
**Problems Encountered**
- misspelling of "transportation" / "teleportation" in the json file
- accounting for only CERTAIN items being teleportation items as well as 
letting the user transport with them at any given time (not just in the room
they picked it up)
