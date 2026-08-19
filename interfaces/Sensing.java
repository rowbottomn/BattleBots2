package interfaces;

import arena.BotInfo;
import arena.Bullet;

/**
 * The Sensing interface is designed to provide hints about needed functionality to make a quality Bot program. 
 * Sensing deals with specifically finding the closest Bot and Bullet objects and whether a Bullet is a danger to the Bot.
 * NOTE: Based on individual algorithms, not all functions may need to be made, but the skills to make all of specified concrete methods are probably needed to properly understand how to make a competent Bot. 
 * NOTE: The Sensing interface is part 2 of 4 interfaces which scaffold the difficulty of providing core functionality of a good Bot program.
 * NOTE: The order of these interfaces is indicative of the hierarchy of needs of the Bot behavior and difficulty.
 * Example: If you cannot sense whether a Bullet object will hit you, because you cannot dodge it anyway.
 *
 * @author Nathan Rowbottom
 * @version 1.03 July 08 2026 
 * 
 */

public interface Sensing
{

    /**
     * getClosest finds the closest BotInfo object from a BotInfo array
     *
     * @param  bots  array of BotInfo objects to get the closest one from.
     * @param  x double of the x position of the location to find the closest bot to.
     * @param  y double of the y position of the location to find the closest bot to.
     * @return The BotInfo object that is closest to the location in the array.
     */   
    BotInfo getClosest(BotInfo[] bots, double x, double y);
    
    /**
     * getClosest finds the closest BotInfo object from a BotInfo array
     * NOTE: This should probably just call the overloaded method that uses the x, y values.
     * @param  bots The array of BotInfo objects to get the closest one from.
     * @param  activeBot The BotInfo of the active bot, usually your bot.
     * @return The BotInfo object that is closest to the location in the array.
     */
    BotInfo getClosest(BotInfo[] bots, BotInfo activeBot);
    
    /**
     * getClosest finds the closest Bullet object from a Bullet array
     *
     * @param  bullets The array of Bullet objects to get the closest one from
     * @param  x double of the x position of the location to find the closest bot to.
     * @param  y double of the y position of the location to find the closest bot to.
     * @return The BotInfo object that is closest to the location in the array.
     */  
    Bullet getClosest(Bullet[] bullets, double x, double y);
    
    /**
     * getClosest finds the closest Bullet object from a Bullet array
     * NOTE: This should probably just call the overloaded method that uses the x, y values.
     * @param  bullets The array of Bullet objects to get the closest one from
     * @param  activeBot The BotInfo of the active bot, usually your bot
     * @return The Bullet object that is closest to the location in the array.
     */
    Bullet getClosest(Bullet[] bullets, BotInfo activeBot);
    

    /**
     * isBulletDangerous determines whether a bullet object will pass through the given location.
     * That is, it is travelling towards the location and in line with the location.
     * @param  b The Bullet object to consider
     * @param  x double of the x position of the location to determine whether the Bullet object will pass through.
     * @param  y double of the y position of the location to determine whether the Bullet object will pass through.
     * @return boolean Whether a Bullet object will pass through the location or is considered dangerous.
     */
    boolean isBulletDangerous(Bullet b, double x, double y);
    
    /**
     * isBulletDangerous determines whether a bullet object will pass through the location of a given BotInfo object.
     * That is, it is travelling towards the location and in line with the Bot object represented by the BotInfo object.
     * NOTE: In almost all cases, this should just call the overloaded method and pass along the location of the BotInfo object.
     * NOTE: Although the BotInfo parameter is called me, any BotInfo could be used to determine where a Bullet object might pose a danger it.
     * @param  b The Bullet object to consider
     * @param  activeBot The BotInfo object of the location to determine whether the Bullet object will pass through.  Usually your bot.
     * @return boolean Whether a Bullet object will pass through the location or is considered dangerous.
     */
    boolean isBulletDangerous(Bullet b, BotInfo activeBot);
    
    /**
     * getDangerousBullets determines which Bullet objects in a Bullet array are dangerous
     * and returns them in a smaller Bullet array.
     * NOTE: This method should call isBulletDangerous method in most cases
     * @param  bullets The array of Bullet objects to consider
     * @param  x double of the x position of the location to determine whether each of the Bullet objects will pass through.
     * @param  y double of the y position of the location to determine whether each of the Bullet objects will pass through.
     * @return Bullet array of Bullet objects that will pass through the given location or is considered dangerous.
     */
    Bullet[] getDangerousBullets(Bullet[] bullets, double x, double y);
    
    /**
     * getDangerousBullets determines which Bullet objects in a Bullet array are dangerous
     * and returns them in a smaller Bullet array.
     * NOTE: This method could call the isBulletDangerous method
     * NOTE: In almost all cases, this should just call the overloaded method and pass along the location of the BotInfo object.
     * NOTE: Although the BotInfo parameter is called me, any BotInfo could be used to determine where a Bullet object might pose a danger it.
     * @param  bullets The array of Bullet objects to consider.
     * @param  activeBot The BotInfo object of the location to determine whether each of Bullet objects will pass through.
     * @return Bullet array of Bullet objects that will pass through the given location or is considered dangerous.
     */
    Bullet[] getDangerousBullets(Bullet[] bullets, BotInfo activeBot);

}
