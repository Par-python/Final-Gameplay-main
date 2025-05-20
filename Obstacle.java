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

    public Obstacle(double a, double b, int obsImage){
        x = a;
        y = b;
        imageName = new String[8];
        obstacleImage = obsImage;
        randomObstacleImage();
    }

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

    public double getX(){
        return x;
    }

    public Rectangle2D getHitbox() {
        return new Rectangle2D.Double(x, y, 50, 50);
    }

    public boolean isColliding(Rectangle2D hitbox){
        return getHitbox().intersects(hitbox);
    }

    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
}
