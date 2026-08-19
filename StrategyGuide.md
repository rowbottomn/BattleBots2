# BattleBots Strategy Guide
|Section|Title|Concepts Needed|  
|---|---|---|  
|1 |Getting Started | Basic Java Setup, Basic Running of Project, How the Project is organized|   
|2 |Making Your First Bot| Classes and Objects, Inheritance, Object Properties, Method Overriding| 
|3 |Implementing the Moving Interface | Method overridding, Javadocs, working with arrays of objects|     
|4 |Implementing the Sensing Interface | Method overloading, Array handling |  
|5 |Implementing the Dodging Interface | Array lists, Arraylist/array conversion, constructing objects|
|6 |Implementing the Attacking Interface | |  
|7 |Beating the Competition (ADVANCED TECH)| Looting, Prefiring, preavoidance|    
|8 |Other Ways to Code Bots|||  

## 1. Getting Started
* Download the project and unzip (if needed)
* Open the arena folder and run BattleBotArena.java which is the Main class.
* Watch the video for a better exploration of the project.
* The arena should open in testing mode.  
  * You should see 10 bots moving around.
  * Hit detection is turned off in testing mode and there is unlimited ammo.
  * A user can control one of the bots that is a white square. 
  * Arrow keys to move, wasd keys to fire.
* Clicking a grey bar on near the bottom leaves testing mode and starts match mode.  
  * Match rules and scoring parameters are shown on the bottom left.
  * Click the bar again to start the game.
* Each match starts with bots randomly arranged diagonally on the screen.
  * Mouse scrolling changes the time flow of the each round.
  * As bots are shot, their images are replaced by tombstones.
  * Living bots can loot ammo from defeated bots.
  * Only 4 bullets can be on the screen for one bot at a time.
  * After each round the bots are reset.
  * After all the rounds, the stats are shown for each bot.
  * Bottom right allows you to mute sounds, show names or scores or ammo.

## 2. Making Your First Bot
* Create new class file with name of bot
* Type in class and extend Bot
* Add in filler concrete methods
* Fill in imageNames method to have the Arena load up your bot's images. 
    * use Drone.java to see how or your own by adding them to the images folder.
* Fill in loadedImages method to receive the loaded images.  
    * Note that you need a variable with scope to hold the Image objects.  
    * I usually make an Image[] variable to hold them but Drone.java shows making several variables to hold them.
* Make an int variable with scope to hold your current move.  
    *We set the variable in the getMove method call but you will the need the value to know how to draw your bot with the correct image based on your move.
* For convenience, Make a BotInfo object to hold the BotInfo object sent in when calling the getMove method.
* Use the drawing context object in the draw method to draw your bot.  See Drone.java for help.
* For testing purposes, just have getMove return BattleBotArena.UP which is 1;
* Add the constructor call for your Bot subclass to the BattleBotArena.java code ~615.
* Run code and observe bot behavior.
    * Consider changing the NUM_BOTS variable (~302) in BattleBotArena.java to a smaller number of other bots to test with.

## 3. Implementing the Moving Interface 
* Make calcDist using Pythagorean Theorem
* Make calcManhattanDistance using Math.abs
* Make moveTo method using a simple "creep to" method
* Test moveTo method to move to a location

## 4. Implementing the Sensing Interface
* Make getCloset to get the closest bot to a location.
* Use method overloading to make getClosest to a bot.
* Test methods by having our bot chase the first Bot in the liveBots array.
* Repeat process for getClosest for bullets.
* Make methods for isBulletDangerous
* Make methods for getDangerousBullets

## 5. Implementing the Dodging Interface
* The moveFrom method is the most important method and students might want to just place in filler methods for the rest of the dodging interface until they finish implementing the attacking interface.
* Have bot in getMove method moveFrom the closest of the getDangerousBullets

## 6. Implementing the Attacking Interface
* shouldFire is the most important method for the attacking interface.  
    * This can be largely done by constructing a bullet in each direction and then seeing for each bot whether it would be a dangerous bullet.
    * Making a firing cooldown will be important.
* Make getTarget and use it to select a target
    * Bots that are out of bullets?
* Make trackTarget and confirm that you can move toward the target in a safe way.
    * Remember to switch targets if it the target dies
    * Keeping a minimum distance might be useful. 

## 7. Beating the Competition (ADVANCED TECH)
* Reuse/refactor getTarget and trackBot to find deadBots with loot
* From the Dodging interface, go back and complete isLocationSafe, isBlockedX isBlockedY.
* Use those methods to complete whereIsDangerous and whereIsBlocked
    * Use whereIsBlockedtrying to avoid trying to dodge when blocked by bots or screenedge
    * Use whereIsDangerousdodging to avoid trying to dodge one bullet into the path of another
* Use various methods to complete Attacking methods isBulletPathBlockedX and  isBulletPathBlockedY.
    * Use these methods to avoid wasting ammo firing at liveBots behind deadBots
* Prefireing and predodging are useful behaviours.
    * Fire when the bot_dx/BOT_SPEED < bullet_dy/BULLET_SPEED or bot_dy/BOT_SPEED < bullet_dx/BULLET_SPEED.
## 8. Other Ways to Code Bots
#### It is important to point out that there is a large amount of ways to code your bots.  Some are more effective than others.  
#### One time I coded my bot to "feel" a cummulative repulsive force from bullets based on distance and then used the forces to pick my move, successfully allowed my bot to dodge multiple near bullets. 
#### The interfaces provide scaffolding but are not ment to stiffle innovation.  
#### However, any capable student should be able to show methods that have similar functionality. 
