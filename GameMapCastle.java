import java.awt.*;
import javax.swing.*;

public class GameMapCastle extends JComponent{
    private Image gameMapCastle;
    private int moveX;

    public GameMapCastle(int x){
        ImageIcon img = new ImageIcon("./assets/gameBackgrounStatic.png");
        gameMapCastle = img.getImage();
        moveX = x;
        setBounds(0, 0, 1024, 768);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameMapCastle != null) {
            g.drawImage(gameMapCastle, moveX, 0, getWidth(), getHeight(), this);
        }
    }
}
