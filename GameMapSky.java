/**
 * The GameMapSky class manages the sky background in the game.
 * It handles the rendering of the static sky background that serves as the base layer.
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

public class GameMapSky extends JComponent {
    private Image gameMapSky;

    /**
     * Initializes the sky background with the default image.
     */
    public GameMapSky(){
        ImageIcon img = new ImageIcon("./assets/gameMapSky.png");
        gameMapSky = img.getImage();
        setBounds(0, 0, 1024, 768);
    }

    /**
     * Renders the static sky background image.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameMapSky != null) {
            g.drawImage(gameMapSky, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
