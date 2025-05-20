/**
 * The IntroBackground class manages the game's introduction screen interface.
 * It handles user interactions, hover effects, and sound feedback for the start screen.
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
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class IntroBackground extends JComponent {


    private Image defaultImage;
    private Image hoverImage1;
    private Image hoverImage2;
    private Image currentImage;
    private boolean playPicked;
    private Clip clip2;
    private Clip clip3;
    private Clip clip4;


    private final Rectangle hoverArea1 = new Rectangle(309,451,407,145);  // Strawberry
    private int lastHovered = 0;
    
    /**
     * Initializes the introduction screen with images, sounds, and mouse listeners.
     */
    public IntroBackground() {
        hoverImage1 = new ImageIcon("./assets/first.png").getImage();
        hoverImage2 = new ImageIcon("./assets/second.png").getImage();
        currentImage = defaultImage;
        playPicked = false;
        setBounds(0, 0, 1024, 768);
       try {
           File music2 = new File("./assets/hover.wav");
           AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(music2);
           clip2 = AudioSystem.getClip();
           clip2.open(audioStream2);
       } catch (Exception ex) {
           ex.printStackTrace();
           System.err.println("Failed to load or play hover.wav");
       }

       try {
           File music3 = new File("./assets/click.wav");
           AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(music3);
           clip3 = AudioSystem.getClip();
           clip3.open(audioStream3);
       } catch (Exception ex) {
           ex.printStackTrace();
           System.err.println("Failed to load or play click.wav");
       }

       try {
        File music4 = new File("./assets/welcome.wav");
        AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(music4);
        clip4 = AudioSystem.getClip();
        clip4.open(audioStream3);
        } catch (Exception ex) {
        ex.printStackTrace();
        System.err.println("Failed to load or play click.wav");
        }

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                int currentHovered = 0;
                currentImage = hoverImage1;
                if (hoverArea1.contains(p)) {
                    currentImage = hoverImage2;
                    currentHovered = 1;
                } else {
                    currentImage = hoverImage1;
                }
                if (currentHovered != 0 && currentHovered != lastHovered) {
                    if (clip2 != null) {
                        clip2.setFramePosition(0); // Rewind to start
                        clip2.start();
                    }
                }
                lastHovered = currentHovered;
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                if (hoverArea1.contains(p)) {
                    playPicked = true;
                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    }
                } 
            }
        });
    }


    /**
     * Checks if the play button has been clicked.
     * 
     * @return true if the play button was clicked
     */
    public boolean playPicked(){
        return playPicked;
    }


    /**
     * Plays the welcome sound effect.
     */
    public void playSound(){
        if (clip4!=null){
            clip4.setFramePosition(0);
            clip4.start();
        } 
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentImage != null) {
            g.drawImage(currentImage, 0, 0, this);
        }
    }
}
