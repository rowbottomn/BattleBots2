PROJECT TITLE: ArenaBot
PURPOSE OF PROJECT: 
VERSION or DATE: July 08 2026   
HOW TO START THIS PROJECT: java BattleBotArena.java
AUTHORS: Nathan Rowbottom, Sam Scott

COMPETITOR INSTRUCTIONS:
1. Clone or download the following git repo BattleBots **need this link**
2. Run the BattleBotArena.java program to make sure the default setting are acceptable on your screen (and the most people's).
3. Create a your Bot program by making a .java file and extending the Bot class.
4. Add in all the appropriate concrete method stubs as required by the abstract method signatures.
5. Use the Drone class to add in the appropriate methods like loading images and a teamname.
6. Add in the constructor call for your Bot subclass to the BattleBotArena.java file (~ 600 lines)

|Order of Implementation             |Deliverable or Success Criteria|  
|---|---|  
|Create Bot subclass add-in concrete filler methods   |Instance of Bot Subclass can be instantiated in Arena |  
|Implement Moving interface by providing critical methods   |Bot can move to a target location |  
|Implement Sensing interface by providing critical methods                  |Bot can move to closest bot |  
|Implement Dodging interface by providing critical methods                  |Bot can move away from closest dangerous bullet|  
|Implement Attacking interface by providing critical methods                |Bot can fire at a bot|  
|Enhanced behaviours                 |Bot hunts bots, Bot can get ammo when needed, Bot does not fire when shot is blocked, Teamwork, Prefiring|  