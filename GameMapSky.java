import java.awt.*;
import javax.swing.*;

public class GameMapSky extends JComponent {
    private Image gameMapSky;

    public GameMapSky(){
        ImageIcon img = new ImageIcon("./assets/gameMapSky.png");
        gameMapSky = img.getImage();
        setBounds(0, 0, 1024, 768);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameMapSky != null) {
            g.drawImage(gameMapSky, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
