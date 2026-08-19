package interfaces;

import arena.BotInfo;
import arena.Bullet;

/**
 * The Dodging interface is designed to provide hints about needed functionality to make a quality Bot program. 
 * Dodging deals with moving away from a location, which could be a Bullet object, a looted dead Bot, or a dangerous Bot live Bot.
 * It also provides functions that would be useful for choosing next moves that are safe or not blocked.
 * NOTE: Based on individual algorithms, not all functions may need to be made, but the skills to make all of specified concrete methods are probably needed to properly understand how to make a competent Bot. 
 * NOTE: The Dodging interface is part 3 of 4 interfaces which scaffold the functionality of difficulty of providing core functionality of a good Bot program.
 * NOTE: The order of these interfaces is indicative of the hierarchy of needs of the Bot behavior and difficulty.
 * Example: If you cannot move, then there is no point in sensing whether a Bullet object will hit you because you cannot dodge it anyway.
 *
 * @author Nathan Rowbottom
 * @version 1.02 Dec 13 2024
 */
public interface Dodging 
{
    
    /**
     * moveFrom Moves away from a location for dodging or getting away from a location.
     * NOTE: Depending on algorithms and strategy, you might be able to achieve this adequate functionality by tweaking the moveTo method and reversing the parameters for locations.
     * @param  avoidX The x location you want to move to.
     * @param  avoidY The y location you want to move to.
     * @param  currentX The x location to move away from.
     * @param  currentY The y location to move away from.
     * @return The int of a move that will move away from the location.
     */
    abstract int moveFrom(double avoidX, double avoidY, double currentX, double currentY);
    
    /**
     * isLocationSafe Predicts whether that location is going to be hit in the next tick by the bullets currently in the game.
     * NOTE: Depending on algorithms and strategy, what is considered safe and how far along to look might change.
     * NOTE: This should probably make use of isBulletDangerous or getDangerousBullets methods.
     * @param  x the x location to consider.
     * @param  y the y location to consider.
     * @param  bullets The array of Bullet objects to consider.
     * @return boolean Whether the spot is safe from bullets 
     */
     boolean isLocationSafe(double x, double y, Bullet[] bullets);
    
    /**
     * isBlockedX Attempts to determine if moving in the x direction from the current x location to the considered x location 
     * is blocked by Bot objects represented by BotInfo objects (Would a bot be prevented from dodging or getting away from a location)
     * NOTE: This could be called for both alive and dead Bot arrays.
     * @param  moveX The x location you want to move to.
     * @param  moveY The y location you want to move to.
     * @param  currentX The x location to move away from.
     * @param  currentY The y location to move away from.
     * @return boolean that the travel is blocked.
     */
    abstract boolean isBlockedX(double moveX, double moveY, double currentX, double currentY, BotInfo[] bots);
    
    /**
     * isBlockedY Attempts to determine if moving in the y direction from the current location to the considered location 
     * is blocked by Bot objects represented by BotInfo objects.  Would a bot be prevented from dodging or getting away from a location
     * NOTE: This could be called for both alive and dead Bot arrays.
     * @param  moveX The x location you want to move to.
     * @param  moveY The y location you want to move to.
     * @param  currentX The x location to move away from.
     * @param  currentY The y location to move away from.
     * @return boolean that the travel is blocked.
     */
    abstract boolean isBlockedY(double moveX, double moveY, double currentX, double currentY, BotInfo[] bots);
    
    /**
     * whereIsDangerous Returns a boolean array of which directions or moves (if any) would move a Bot object into the path of a Bullet object.
     * NOTE: In most cases, this will probably call the isMoveSafe method for possible moves.
     * NOTE: This could be used in more creative ways to attack opponents.
     * @param  currentX The x location to consider moves from.
     * @param  currentY The y location to consider moves from.
     * @return boolean array of which moves lead to danger.
     * 
    */
    abstract boolean[] whereIsDangerous(double currentX, double currentY, Bullet[] bullets);
    
    /**
     * whereIsDangerous Returns a boolean array of which directions or moves (if any) would block a Bot object from moving from its current location.
     * NOTE: In most cases, this will probably call the isBlocked method for possible moves.
     * NOTE: This could be used in more creative ways to determine whether an opponent can dodge.
     * @param  currentX The x location to consider moves from.
     * @param  currentY The y location to consider moves from.
     * @param  bots An array of Bot objects which could block movement
     * @return boolean array of which moves are blocked by lead to danger.
    */
    abstract boolean[] whereIsBlocked(double currentX, double currentY, BotInfo[] bots);
}
