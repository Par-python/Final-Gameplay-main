/**
 * The PlayerSprite class manages the player character's visual representation and movement.
 * It handles sprite rendering, position updates, and collision detection for the player.
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

public class PlayerSprite{

        private double x, y, size;
        private Color color;
        private String kingdomName;
        private BufferedImage racer1;
        private BufferedImage racer2;

        /**
         * Initializes a player sprite with specified position, size, color, and kingdom.
         * 
         * @param a the x-coordinate
         * @param b the y-coordinate
         * @param s the size of the sprite
         * @param c the color of the sprite
         * @param k the kingdom name
         */
        public PlayerSprite(double a, double b, double s, Color c, String k){
            x = a;
            y = b;
            size = s;
            color = c;
            kingdomName = k;
            getImageFromFile();
        }

        /**
         * Loads the player sprite images from the assets directory.
         */
        public void getImageFromFile(){
            try{
                racer1 = ImageIO.read(getClass().getResourceAsStream("assets/strawberryRacer.png"));
                racer2 = ImageIO.read(getClass().getResourceAsStream("assets/blueberryRacer.png"));
            }catch(IOException ex){
                System.out.println("WE DID NOT FIND IT");
            }
        }

        /**
         * Returns the appropriate sprite image based on the kingdom.
         * 
         * @return the BufferedImage for the current kingdom
         */
        public BufferedImage pokeImage(){
            if(kingdomName == "Strawberry"){
                return racer1;
            } else {
                return racer2;
            }
        }

        /**
         * Renders the player sprite at its current position.
         * 
         * @param g2d the Graphics2D context for rendering
         */
        public void drawSprite(Graphics2D g2d){
            g2d.translate(x, y);
            g2d.drawImage(pokeImage(), 0,0, null);
            g2d.translate(-x, -y);
        }

        /**
         * Moves the sprite horizontally by the specified amount.
         * 
         * @param n the amount to move horizontally
         */
        public void moveH(double n){
            x += n;
        }

        /**
         * Moves the sprite vertically by the specified amount.
         * 
         * @param n the amount to move vertically
         */
        public void moveV(double n){
            y += n;
        }

        /**
         * Sets the x-coordinate of the sprite.
         * 
         * @param n the new x-coordinate
         */
        public void setX(double n){
            x = n;
        }

        /**
         * Sets the y-coordinate of the sprite.
         * 
         * @param n the new y-coordinate
         */
        public void setY(double n){
            y = n;        
        }

        /**
         * Returns the x-coordinate of the sprite.
         * 
         * @return the x-coordinate
         */
        public double getX(){
            return x;
        }

        /**
         * Returns the y-coordinate of the sprite.
         * 
         * @return the y-coordinate
         */
        public double getY(){
            return y;
        }

        /**
         * Returns the hitbox of the sprite for collision detection.
         * 
         * @return the Rectangle2D representing the sprite's hitbox
         */
        public Rectangle2D getHitbox() {
            return new Rectangle2D.Double(x, y, 200, 100);
        }
    }