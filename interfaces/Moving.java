package interfaces;

/**
 * The Moving interface is designed to provide hints about needed functionality to make a quality Bot program. 
 * Moving deals with knowing how far away something is awau from your bot, along with moving to a location (moving away functionality is left to the Dodging interface).
 * NOTE: Based on individual algorithms, not all functions may need to be made, but the skills to make all of specified concrete methods are probably needed to properly understand how to make a competent Bot. 
 * NOTE: The Moving interface is part 1 of 4 interfaces which scaffold the functionality of difficulty of providing core functionality of a good Bot program.
 * NOTE: The order of these interfaces is indicative of the hierarchy of needs of the Bot behavior and difficulty. You may choose to work on providing concrete methods out of order but...
 * For example, if do not know how to move, then there is no point in sensing whether a Bullet object will hit you because you cannot dodge it anyway. *
 * @author Nathan Rowbottom
 * @version 1.03 July 08 2026, 
 * 
 */
 public interface Moving
{
    
    /**
     * calcDist calculates the Euclidean Distance (diagonal distance) between two points.
     * NOTE: This should be less useful than the Manhattan Distance in most instances but is a good starting point.
     *
     * @param  x1 double of the x position of the first point to calculate the distance between.
     * @param  y1 double of the y position of the first point to calculate the distance between.
     * @param  x2 double of the x position of the second point to calculate the distance between.
     * @param  y2 double of the y position of the second point to calculate the distance between.
     * @return double of the distance between two points.
     */
    double calcDist(double x1, double y1, double x2, double y2);


    /**
     * calcManhattanDist calculates the Manhattan Distance (block-wise movement) between two points
     *
     * @param  x1 double of the x position of the first point to calculate the Manhattan Distance between.
     * @param  y1 double of the y position of the first point to calculate the Manhattan Distance between.
     * @param  x2 double of the x position of the second point to calculate the Manhattan Distance between.
     * @param  y2 double of the y position of the second point to calculate the Manhattan Distance between.
     * @return double of the Manhattan distance between two points.
     */
    double calcManhattanDist(double x1, double y1, double x2, double y2);

    
    /**
     * moveTo returns the move each turn to move from the current location to travel to the
     * target location.
     * NOTE: Depending on your algorithm, reversing the x,y points when you call this will reverse the direction travelled (could be important later).
     *
     * @param  toX double of the x position of the point the bot should travel to.
     * @param  toY double of the y position of the first point to calculate the distance between.
     * @param  currentX double of the x position of the bot's current location to move from.
     * @param  currentY double of the y position of the bot's current location to move from.
     * @return double of the distance between two points.
     */
    int moveTo(double toX, double toY, double currentX, double currentY);
    
    
}
