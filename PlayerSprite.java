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

        public PlayerSprite(double a, double b, double s, Color c, String k){
            x = a;
            y = b;
            size = s;
            color = c;
            kingdomName = k;
            getImageFromFile();
        }

        public void getImageFromFile(){
            try{
                racer1 = ImageIO.read(getClass().getResourceAsStream("assets/strawberryRacer.png"));
                racer2 = ImageIO.read(getClass().getResourceAsStream("assets/blueberryRacer.png"));
            }catch(IOException ex){
                System.out.println("WE DID NOT FIND IT");
            }
        }

        public BufferedImage pokeImage(){
            if(kingdomName == "Strawberry"){
                return racer1;
            } else {
                return racer2;
            }
        }

        public void drawSprite(Graphics2D g2d){
            g2d.translate(x, y);
            g2d.drawImage(pokeImage(), 0,0, null);
            g2d.translate(-x, -y);
        }

        public void moveH(double n){
            x += n;
        }

        public void moveV(double n){
            y += n;
        }

        public void setX(double n){
            x = n;
        }

        public void setY(double n){
            y = n;        
        }

        public double getX(){
            return x;
        }

        public double getY(){
            return y;
        }

        public Rectangle2D getHitbox() {
            return new Rectangle2D.Double(x, y, 200, 100);
        }
    }