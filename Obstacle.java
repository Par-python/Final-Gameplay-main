/**
 * The Obstacle class manages the game's obstacles and their interactions.
 * It handles obstacle rendering, collision detection, and position management.
 * 
 * @author Lance Arnel G. Camacho (245288)
 * @author Jerome John C. Pardo (246268)
 * @version 20 May 2025
 * 
 * I have not discussed the Java language code in my program
 * with anyone other than my instructor or the teaching assistants
 * assigned to this course.
 * 
 * I have not used Java language code obtained from another student,
 * or any other unauthorized source, either modified or unmodified.
 * If any Java language code or documentation used in my program
 * was obtained from another source, such as a textbook or website,
 * that has been clearly noted with a proper citation in the comments
 * of my program.
 */
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Obstacle {
    
    private double x, y;
    private BufferedImage obstacle1;
    private BufferedImage obstacle2;
    private BufferedImage obstacle3;
    private BufferedImage obstacle4;
    private BufferedImage obstacle5;
    private BufferedImage obstacle6;
    private BufferedImage obstacle7;
    private int obstacleImage;
    private String[] imageName;

    /**
     * Initializes an obstacle with specified position and image type.
     * 
     * @param a the x-coordinate of the obstacle
     * @param b the y-coordinate of the obstacle
     * @param obsImage the type of obstacle image to use
     */
    public Obstacle(double a, double b, int obsImage){
        x = a;
        y = b;
        imageName = new String[8];
        obstacleImage = obsImage;
        randomObstacleImage();
    }

    /**
     * Loads and initializes random obstacle images from the assets directory.
     */
    public void randomObstacleImage(){
        try {
            for(int i = 0; i < 8; i++){
                String fileName = "obstacle" + (i + 1) + ".png";
                imageName[i] = fileName;
            }
            String imageFile1 = "./assets/" + imageName[1];
            String imageFile2 = "./assets/" + imageName[2];
            String imageFile3 = "./assets/" + imageName[3];
            String imageFile4 = "./assets/" + imageName[4];
            String imageFile5 = "./assets/" + imageName[5];
            String imageFile6 = "./assets/" + imageName[6];
            String imageFile7 = "./assets/" + imageName[7];

            obstacle1 = ImageIO.read(getClass().getResourceAsStream(imageFile1));
            obstacle2 = ImageIO.read(getClass().getResourceAsStream(imageFile2));
            obstacle3 = ImageIO.read(getClass().getResourceAsStream(imageFile3));
            obstacle4 = ImageIO.read(getClass().getResourceAsStream(imageFile4));
            obstacle5 = ImageIO.read(getClass().getResourceAsStream(imageFile5));
            obstacle6 = ImageIO.read(getClass().getResourceAsStream(imageFile6));
            obstacle7 = ImageIO.read(getClass().getResourceAsStream(imageFile7));

        } catch(IOException ex){
            System.out.println("BRO DID NOT FIND IT");
        }
    }

    /**
     * Renders the obstacle sprite at its current position.
     * 
     * @param g2d the Graphics2D context for rendering
     */
    public void drawSprite(Graphics2D g2d){
        int newX = (int) x;
        int newY = (int) y;
        if(obstacleImage == 1){
            g2d.drawImage(obstacle1, newX, newY, null);
        } else if (obstacleImage == 2){ 
            g2d.drawImage(obstacle2, newX, newY, null);
        } else if (obstacleImage == 3){ 
            g2d.drawImage(obstacle3, newX, newY, null);
        } else if (obstacleImage == 4){ 
            g2d.drawImage(obstacle4, newX, newY, null);
        } else if (obstacleImage == 5){ 
            g2d.drawImage(obstacle5, newX, newY, null);
        } else if (obstacleImage == 6){ 
            g2d.drawImage(obstacle6, newX, newY, null);
        } else if (obstacleImage == 7){ 
            g2d.drawImage(obstacle7, newX, newY, null);
        } 
    }

    /**
     * Returns the x-coordinate of the obstacle.
     * 
     * @return the x-coordinate
     */
    public double getX(){
        return x;
    }

    /**
     * Returns the hitbox of the obstacle for collision detection.
     * 
     * @return the Rectangle2D representing the obstacle's hitbox
     */
    public Rectangle2D getHitbox() {
        return new Rectangle2D.Double(x, y, 50, 50);
    }

    /**
     * Checks if this obstacle is colliding with another hitbox.
     * 
     * @param hitbox the hitbox to check collision against
     * @return true if there is a collision
     */
    public boolean isColliding(Rectangle2D hitbox){
        return getHitbox().intersects(hitbox);
    }

    /**
     * Sets the x-coordinate of the obstacle.
     * 
     * @param x the new x-coordinate
     */
    public void setX(double x){
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the obstacle.
     * 
     * @param y the new y-coordinate
     */
    public void setY(double y){
        this.y = y;
    }
}
