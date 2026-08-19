package bots;

import arena.*;
import interfaces.Dodging;
import interfaces.Moving;
import interfaces.Sensing;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class MyBot extends Bot implements Moving, Sensing, Dodging{

    protected Image[] images;
    protected int currentMove;
    protected BotInfo me;

    @Override
    public void newRound() {
        
    }

    @Override
    public int getMove(BotInfo me, boolean shotOK, BotInfo[] liveBots, BotInfo[] deadBots, Bullet[] bullets) {
        this.me = me;
        //am I in danger?
        Bullet[] dangers = getDangerousBullets(bullets, me);
        if (dangers.length>0) {
            Bullet danger = getClosest(dangers, me);
            if (!isLocationSafe(me.getX(), me.getY(), bullets)) {
                return moveFrom(danger.getX()+BattleBotArena.BULLET_SPEED, danger.getY()+BattleBotArena.BULLET_SPEED, me.getX()+Bot.RADIUS, me.getY()+Bot.RADIUS);
            }    
        }
        
        BotInfo target = liveBots[0];
        currentMove = moveTo(target.getX(), target.getY(), me.getX(), me.getY());
        currentMove = BattleBotArena.STAY;
        return currentMove;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        if(currentMove == BattleBotArena.STAY) currentMove = 1;
       g.drawImage(images[currentMove-1], (int)me.getX(), (int)me.getY(), Bot.RADIUS*2, Bot.RADIUS*2, null);
    }

    @Override
    public String getName() {
        return "MyBot";
    }

    @Override
    public String getTeamName() {
        return "MyBot";
    }

    @Override
    public String outgoingMessage() {
        return "Lol";
    }

    @Override
    public void incomingMessage(int botNum, String msg) {
        
    }

    @Override
    public String[] imageNames() {
        String[] images = {"roomba_up.png","roomba_down.png","roomba_left.png","roomba_right.png"};
        return images;    
    }

    @Override
    public void loadedImages(Image[] images) {
        this.images = images;        
    }

    @Override
    public double calcDist(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        return Math.sqrt(dx*dx +dy*dy);
    }

    @Override
    public double calcManhattanDist(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        return Math.abs(dx)+Math.abs(dy);
    }

    @Override
    public int moveTo(double toX, double toY, double currentX, double currentY) {
        double dx = toX - currentX;
        double dy = toY - currentY;

        if (Math.abs(dx)<Math.abs(dy)) {
            if (dy>0) {
                return BattleBotArena.DOWN;
            }
            else{
                return BattleBotArena.UP;
            }   
        }
        else{
            if (dx>0) {
                return BattleBotArena.RIGHT;
            }
            else{
                return BattleBotArena.LEFT;
            }
        }

    }

    @Override
    public BotInfo getClosest(BotInfo[] bots, double x, double y) {
        BotInfo closest = bots[0];
        double closeDist = Double.MAX_VALUE;
        for (int i = 1; i < bots.length; i++) {
            BotInfo bot = bots[i];
            if (calcManhattanDist(bot.getX(), bot.getY(), x, y)< closeDist) {
                closest = bot;
            }
        }

        return closest;
    }

    @Override
    public BotInfo getClosest(BotInfo[] bots, BotInfo activeBot) {
       
        return getClosest(bots, activeBot.getX(), activeBot.getY());
    }

    @Override
    public Bullet getClosest(Bullet[] bullets, double x, double y) {
        Bullet closest = bullets[0];
        double closeDist = Double.MAX_VALUE;
        for (int i = 1; i < bullets.length; i++) {
            Bullet b = bullets[i];
            if (calcManhattanDist(b.getX(), b.getY(), x, y)< closeDist) {
                closest = b;
            }
        }

        return closest;
    }

    @Override
    public Bullet getClosest(Bullet[] bullets, BotInfo activeBot) {
        return getClosest(bullets, me.getX()+Bot.RADIUS, me.getY()+Bot.RADIUS);
    }

    @Override
    public boolean isBulletDangerous(Bullet b, double x, double y) {
        double dx = b.getX() - x;
        double dy = b.getY() - y;

        //check if it is lined up in the y direction with the center of a bot
        if (Math.abs(dy)<= Bot.RADIUS ) {
           if (dx*b.getXSpeed()<0) {
             return true;   
           }
            
        }
        else if (Math.abs(dx)<= Bot.RADIUS ) {
           if (dy*b.getYSpeed()<0) {
             return true;   
           }
        }
        return false;
    }

    @Override
    public boolean isBulletDangerous(Bullet b, BotInfo activeBot) {
        // TODO Auto-generated method stub
        return isBulletDangerous(b, activeBot.getX()+Bot.RADIUS, activeBot.getY() + Bot.RADIUS );
    }

    @Override
    public Bullet[] getDangerousBullets(Bullet[] bullets, double x, double y) {
        // TODO Auto-generated method stub
        //make new array and put dangerous bullets into it to return
        ArrayList <Bullet> dangers = new ArrayList<>();
        for(Bullet b : bullets){
            if (isBulletDangerous(b, x, y)) {
                dangers.add(b);
            }
        }

        return  dangers.toArray(new Bullet[dangers.size()]);    
    }

    @Override
    public Bullet[] getDangerousBullets(Bullet[] bullets, BotInfo activeBot) {
        // TODO Auto-generated method stub
        return getDangerousBullets(bullets,activeBot.getX()+Bot.RADIUS, activeBot.getY() + Bot.RADIUS );
    }

    @Override
    public int moveFrom(double avoidX, double avoidY, double currentX, double currentY) {
        // TODO Auto-generated method stub
        double dx = currentX-avoidX;
        double dy = currentY - avoidY;
        int possibleMove = -1;
        
        if (dx > dy) {

            possibleMove = moveTo(currentX,currentY+100*dy, currentX,currentY);   
        
        }
        else {
            possibleMove = moveTo(currentX+100*dx,currentY, currentX,currentY);   
        }
        return possibleMove;
    }

    @Override
    public boolean isLocationSafe(double x, double y, Bullet[] bullets) {
        // TODO Auto-generated method stub
        return getDangerousBullets(bullets, x, y).length == 0;
    }

    @Override
    public boolean isBlockedX(double moveX, double moveY, double currentX, double currentY, BotInfo[] bots) {
        //get the difference in position;

       
       double dx = moveX - currentX;
        
       //use a for loop to check each position in x as we move from one to the other.
       for (double x = currentX; moveX-x<BattleBotArena.BOT_SPEED;x+=BattleBotArena.BOT_SPEED) {
           BotInfo closest = getClosest(bots, x, currentY);
            if(calcManhattanDist(closest.getX(), closest.getY(), x, currentY) <=Bot.RADIUS*2+BattleBotArena.BOT_SPEED){
                return true;
            }

            if (x>=BattleBotArena.RIGHT_EDGE||x<=BattleBotArena.LEFT_EDGE) {
                return true;
            }
       }
       return false;
    }

    @Override
    public boolean isBlockedY(double moveX, double moveY, double currentX, double currentY, BotInfo[] bots) {

       double dy = moveY - currentY;
        
       //use a for loop to check each position in x as we move from one to the other.
       for (double y = currentY; moveY-y<BattleBotArena.BOT_SPEED;y+=BattleBotArena.BOT_SPEED) {
           BotInfo closest = getClosest(bots, currentX, y);
            if(calcManhattanDist(closest.getX(), closest.getY(), currentX, y) <=Bot.RADIUS*2+BattleBotArena.BOT_SPEED){
                return true;
            }

            if (y>=BattleBotArena.TOP_EDGE||y<=BattleBotArena.TOP_EDGE) {
                return true;
            }
       }
       return false;
    }

    @Override
    public boolean[] whereIsDangerous(double currentX, double currentY, Bullet[] bullets) {
        // TODO Auto-generated method stub
        boolean [] dangers = new boolean[4];
        //top
        dangers[0] = isLocationSafe(currentX, currentY-BattleBotArena.BOT_SPEED, bullets);
        dangers[1] = isLocationSafe( currentX, currentY+BattleBotArena.BOT_SPEED, bullets);
        dangers[2] = isLocationSafe(currentX-BattleBotArena.BOT_SPEED, currentY, bullets);
        dangers[3] = isLocationSafe(currentX+BattleBotArena.BOT_SPEED, currentY, bullets);
        return dangers;
    }

    @Override
    public boolean[] whereIsBlocked(double currentX, double currentY, BotInfo[] bots) {
        boolean [] blocks = new boolean[4];
        //top
        blocks[0] = isBlockedY(currentX, currentY, currentX, currentY-BattleBotArena.BOT_SPEED, bots);
        blocks[1] = isBlockedY(currentX, currentY, currentX, currentY+BattleBotArena.BOT_SPEED, bots);
        blocks[2] = isBlockedX(currentX, currentY, currentX-BattleBotArena.BOT_SPEED, currentY, bots);
        blocks[3] = isBlockedX(currentX, currentY, currentX+BattleBotArena.BOT_SPEED, currentY, bots);
        
        //down
        //left

        //right
        return blocks;
    }




    
}
