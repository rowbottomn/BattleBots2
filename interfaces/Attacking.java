package interfaces;

import arena.BotInfo;
import arena.Bullet;

/**
 * The Attacking interface is designed to provide hints about needed functionality to make a quality Bot program. 
 * Attacking deals with knowing whether a Bot object should fire at another Bot object or not, how long has passed 
 * between shots, tracking (moving towards) a suitable bot to attack from a location, and knowing whether a Bullet 
 * objects path will be blocked.
 * NOTE: Based on individual algorithms, not all functions may need to be made, but the skills to make all of specified 
 * concrete methods are probably needed to properly understand how to make a competent Bot. 
 * NOTE: The Attacking interface is part 4 of 4 interfaces which scaffold the functionality of difficulty of providing 
 * core functionality of a good Bot program.
 * NOTE: The order of these interfaces is indicative of the hierarchy of needs of the Bot behavior and difficulty.
 * Example: If you cannot move or sense where an opponent is, then there is little need to fire.
 *
 * @author Nathan Rowbottom
 * @version 1.03 Dec 16 2024 Made most method signatures
 * @version 1.04 Aug 11 2026 Modified to include minDist parameter in trackBot method signature
 * @version 1.05 Aug 19 2026 Split bullet blocking into two methods and split the tracking into two simpler methods.
*/

public interface Attacking
{
    
    /**
     * shouldFire Given a location and all the bots, returns the int that would fire in a direction that might hit a bot.
     * NOTE: Depending on algorithms and strategy, There are many ways to determine that a bullet might hit a bot but some might make use 
     * of sensing methods.
     * @param  x The x location to consider firing from.
     * @param  y The y location to consider firing from.
     * @param  bots The array of Bot objects to consider firing at.
     * @return The int of a move that will fire at from the location.
     */
    int shouldFire(double x, double y, BotInfo[] bots);
    
    /**
     * getTarget Given all the liveBots, returns one that seems a good target to move against
     * NOTE: Depending on algorithms and strategy, there are many ways to determine that a viable target.
     * @param  bots The array of Bot objects to select a target from.
     * @return The BotInfo instance of viable target.
     */
    BotInfo getTarget(BotInfo[] bots);
    
    /**
     * trackBot Given a botInfo's location and all the bots, returns an int that moves towards one of them and lines up to fire at a bot.
     * NOTE: Depending on algorithms and strategy, there are many ways to determine which bots are a priority for firing at, but sensing
     * methods might help.
     * NOTE: Any algorithm should probably make use of moving methods to determine the best move to get to the target bot.
     * @param  x The current x location to consider moving from.
     * @param  y The current y location to consider moving from.
     * @param  target The BotInfo instance which has been chosen as a viable target.
     * @return The int of a move that will move towards the target.
     */
    int trackBot(double currentX, double currentY, BotInfo target);
    
    /**
     * isBulletPathBlockedX Given a target x location, a Bullet instance and all the dead bots, 
     * returns whether the bullet will hit the intended target or hit a tombstone marker.
     * NOTE: You can contruct test Bullet objects before you fire to use as parameters for this method. 
     * NOTE: This might be similar or the same as finding which bots to fire at.
     * @param  targetX The x position under consideration for firing at.
     * @param  bullet The bullet object whose path is to be checked.
     * @param  bots The array of Bot objects which are obstacles to firing at.
     * @return boolean Whether the path is blocked by any dead bots in the array.
     */
    boolean isBulletPathBlockedX(double targetX, Bullet bullet, BotInfo[] deadBots);
    
    /**
     * isBulletPathBlockedY Given a target y location, a Bullet instance and all the dead bots, 
     * returns whether the bullet will hit the intended target or hit a tombstone marker.
     * NOTE: You can contruct test Bullet objects before you fire to use as parameters for this method. 
     * NOTE: This might be similar or the same as finding which bots to fire at.
     * @param  targetY The y position under consideration for firing at.
     * @param  bullet The bullet object whose path is to be checked.
     * @param  bots The array of Bot objects which are obstacles to firing at.
     * @return boolean Whether the path is blocked by any dead bots in the array.
     */
    boolean isBulletPathBlockedY(double targetY, Bullet bullet, BotInfo[] deadBots);    
    
}
