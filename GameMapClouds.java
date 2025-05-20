import java.awt.*;
import javax.swing.*;

public class GameMapClouds extends JComponent {
    private Image gameMapClouds;
    private int moveX;
    
    public GameMapClouds(int x){
        ImageIcon img = new ImageIcon("./assets/gameMapClouds.png");
        gameMapClouds = img.getImage();
        setBounds(0, 0, 4096, 768);
        moveX = x;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameMapClouds != null) {
            int bgWidth = 4096;
            g.drawImage(gameMapClouds, moveX, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapClouds, bgWidth + moveX, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapClouds, (bgWidth * 2) + moveX, 0, getWidth(), getHeight(), this);
        }
    }
}
