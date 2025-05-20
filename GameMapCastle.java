/**
 * The GameMapCastle class manages the castle background elements in the game.
 * It handles the rendering of the castle with parallax scrolling effect.
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
import javax.swing.*;

public class GameMapCastle extends JComponent{
    private Image gameMapCastle;
    private int moveX;

    /**
     * Initializes the castle background with the specified x position.
     * 
     * @param x the initial x position of the castle
     */
    public GameMapCastle(int x){
        ImageIcon img = new ImageIcon("./assets/gameBackgrounStatic.png");
        gameMapCastle = img.getImage();
        moveX = x;
        setBounds(0, 0, 1024, 768);
    }

    /**
     * Renders the castle background at its current position.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameMapCastle != null) {
            g.drawImage(gameMapCastle, moveX, 0, getWidth(), getHeight(), this);
        }
    }
}
