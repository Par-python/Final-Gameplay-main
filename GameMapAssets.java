import java.awt.*;
import javax.swing.*;

public class GameMapAssets extends JComponent {
    private Image gameMapAssets;
    
    public GameMapAssets(){
        ImageIcon img = new ImageIcon("./assets/gameMapAssets.png");
        gameMapAssets = img.getImage();
        setBounds(0, 0, 4096, 768);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameMapAssets != null) {
            int bgWidth = 4096;
            g.drawImage(gameMapAssets, 0, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 2, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 3, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 4, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 5, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 6, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 7, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 8, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 9, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 10, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 11, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 12, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 13, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 14, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 15, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 16, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 17, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 18, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 19, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 20, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 21, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 22, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 23, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 24, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 25, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 26, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 27, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 28, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 29, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 30, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 31, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 32, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 33, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 34, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 35, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 36, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 37, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 38, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 39, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 40, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 41, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 42, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 43, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 44, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 45, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 46, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 47, 0, getWidth(), getHeight(), this);
            g.drawImage(gameMapAssets, bgWidth * 48, 0, getWidth(), getHeight(), this);
        }
    }
}
