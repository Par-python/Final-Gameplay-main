/**
 * The GameBackground class manages the scrolling background images for the game.
 * It handles the continuous rendering of multiple background segments to create an infinite scrolling effect.
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

public class GameBackground extends JComponent{
    private Image gameBackground1;
    private Image gameBackground2;
    private Image gameBackground3;
    private Image gameBackground4;
    private Image gameBackground5;

    /**
     * Initializes the game background with multiple background images.
     */
    public GameBackground() {
        ImageIcon img1 = new ImageIcon("./assets/gameBackground.png");
        ImageIcon img2 = new ImageIcon("./assets/gameBackground2.png");
        ImageIcon img3 = new ImageIcon("./assets/gameBackground3.png");
        ImageIcon img4 = new ImageIcon("./assets/gameBackground4.png");
        ImageIcon img5 = new ImageIcon("./assets/gameBackground5.png");
        gameBackground1 = img1.getImage();
        gameBackground2 = img2.getImage();
        gameBackground3 = img3.getImage();
        gameBackground4 = img4.getImage();
        gameBackground5 = img5.getImage();
        setBounds(0, 0, 4096, 768);
    }

    /**
     * Renders the scrolling background images in sequence.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameBackground1 != null) {
            int bgWidth = 4096;
            g.drawImage(gameBackground1, 0, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground2, bgWidth, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 2, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 3, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground5, bgWidth * 4, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 5, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 6, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground5, bgWidth * 7, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground2, bgWidth * 8, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 9, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 10, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground5, bgWidth * 11, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 12, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 13, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground5, bgWidth * 14, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground2, bgWidth * 15, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 16, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 17, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground5, bgWidth * 18, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground2, bgWidth * 19, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 20, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 21, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground5, bgWidth * 22, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 23, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 24, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground5, bgWidth * 25, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground2, bgWidth * 26, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 27, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 28, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground5, bgWidth * 29, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 30, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 31, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground2, bgWidth * 32, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 33, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 34, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground5, bgWidth * 35, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 36, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 37, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground5, bgWidth * 38, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground2, bgWidth * 39, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 40, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 41, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground5, bgWidth * 42, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 43, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 44, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground2, bgWidth * 45, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground3, bgWidth * 46, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground4, bgWidth * 47, 0, getWidth(), getHeight(), this);
            g.drawImage(gameBackground5, bgWidth * 48, 0, getWidth(), getHeight(), this);
        }
    }
}