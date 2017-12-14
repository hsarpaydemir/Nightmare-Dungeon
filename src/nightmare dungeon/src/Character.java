

import java.awt.Graphics; //these will be changed
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.*;

public class Character extends Entity {
    //Attributes
    protected boolean attacked;
    protected int health;
    final protected int MAX_HEALTH = 300;
    protected int speed;
    protected double directionX, directionY;
    protected int attackDamage,attackSpeed;
    protected ArrayList<Projectile> projectile;
    protected boolean alive;
    protected int projectileCount;
    protected String name;
    protected boolean vulnerable;

    //Constructor
    public Character(int x, int y, int typeID,int width, int height, int health , int speed, int attackDamage, int attackSpeed){
        //calling the constructor of Entity class
        super(x,y,typeID,width,height);
        //initializing the attributes
        this.health = health ;
        this.speed = speed ;
        this.directionX = 0;
        this.directionY = 0;
        this.attackDamage=attackDamage;
        this.attackSpeed=attackSpeed;
        attacked= false;
        projectile = new ArrayList<Projectile>();
        alive = true;
        vulnerable = true;
    }
    //changes the x and y coordinates of the player
    public void move(int roomWidth, int roomHeight){

        if(this instanceof Player) {
            boolean inRangeWidthMin = (this.getX() > 0);
            boolean inRangeWidthMax = (this.getX() < roomWidth);
            boolean inRangeHeightMin = (this.getY() > 0);
            boolean inRangeHeightMax = (this.getY() < roomHeight);

            System.out.println("x: " + this.getX() + " y: " + this.getY());

            if (inRangeWidthMin && inRangeWidthMax && inRangeHeightMin && inRangeHeightMax) {
                this.setX(x + (int) (directionX * speed));
                this.setY(y + (int) (directionY * speed));
                System.out.println("x: " + this.getX() + " y: " + this.getY());
            } else if (inRangeWidthMax && inRangeWidthMin) {
                if (!inRangeHeightMax) {
                    System.out.println("debug1");
                    this.setY(roomHeight - 1);
                } else if (!inRangeHeightMin) {
                    System.out.println("debug2");
                    this.setY(y + 1);
                }
            } else if (inRangeHeightMax && inRangeHeightMin) {
                if (!inRangeWidthMin) {
                    System.out.println("debug2");
                    this.setX(x + 1);
                } else if (!inRangeWidthMax) {
                    System.out.println("debug3");
                    this.setX(roomWidth - 1);
                }
            }
        else if(!inRangeHeightMax && !inRangeWidthMin){
            //System.out.println("debug4");
            this.setX(x+1);
            this.setY(roomHeight-1);
        }
        else if(!inRangeHeightMax && !inRangeWidthMax){
            //System.out.println("debug5");
            this.setX(roomWidth+1);
            this.setY(roomHeight-1);
        }
        else if(!inRangeHeightMin && !inRangeWidthMin){
            //System.out.println("debug6");
            this.setX(x+1);
            this.setY(y+1);
        }
        else if(!inRangeHeightMin && !inRangeWidthMax){
            //System.out.println("debug7");
            this.setX(roomWidth-1);
            this.setY(y+1);
        }
        }else if(this instanceof Monster){
            x +=  (directionX * speed);
            y +=  (directionY * speed);
            //this.setX(x + (int) (directionX * speed));
            //this.setY(y + (int) (directionY * speed));
        }

        //System.out.println("x: " + this.getX() + " y: " + this.getY());

    }

    //GETTERS
    public int getHealth(){
        return health;
    }

    public int getMaxHealth(){
        return MAX_HEALTH;
    }
    public int getAttackDamage(){
        return attackDamage;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public String getName() {
        return name;
    }

    public double getDirectionX() {
        return directionX;
    }

    public double getDirectionY() {
        return directionY;
    }

    public int getProjectileCount() {
        return projectileCount;
    }

    public int getSpeed()

    {
        return speed;
    }
    //SETTERS

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setDirectionX(double directionX) {
        this.directionX = directionX;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setDirectionY(double directionY) {
        this.directionY = directionY;
    }

    public void setProjectileCount(int projectileCount) {
        this.projectileCount = projectileCount;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /*
    * this method is used for attacking (shooting projectile)
    *
    * */
    public boolean attack(long startTime,int x, int y, double dirX, double dirY) //x and y are directions
    {
        //if there are no projectiles in the room
        if(projectile.size()==0){
            addProjectile(new Projectile(startTime,1,4,3,x,y,1,1,dirX,dirY));
        return true;
        }
        else //if there are projectiles
            //if the time differences between last projectile and this one is greater then half a second
            if(startTime-projectile.get(projectile.size()-1).startTime>1*1*1000) {
                addProjectile(new Projectile(startTime, 1, 4, 3, x, y, 1, 1, dirX, dirY)); //add the projectile
                return true;
            }
    return false;
    }
    public void addProjectile(Projectile p)
    {
        projectile.add(p);
    }

    public ArrayList<Projectile> getProjectile()
    {

        return projectile;
    }
    //update the position of projectile
    public void updateProjectile(int index) {



            projectile.get(index).updatePosition( );

            /*
               probably will add remove etc.
            */



    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public void removeProjectile(int index){
        projectile.remove(index);
    }

    public void setHealth(int health) {
        if(vulnerable)
        this.health+= health;
    }





}
