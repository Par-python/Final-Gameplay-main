/**
 * The Brakes class manages the brake selection interface for the game, providing visual feedback and sound effects for user interactions.
 * It handles the selection between Candy, Stripe, and Magic brake types, with hover effects and click responses.
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
**/

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class Brakes extends JComponent {
    public static final String CANDY = "Candy Brakes";
    public static final String STRIPE = "Stripe Brakes";
    public static final String MAGIC = "Magic Brakes";

    private Image defaultImage;
    private Image hoverImage1, hoverImage2, hoverImage3, hoverImage4;
    private Image currentImage;
    private boolean candyPicked;
    private boolean stripePicked;
    private boolean magicPicked;
    private boolean backPicked;
    private Wheels wheels;
    private int lastHovered = 0;
    private Clip clip2;
    private Clip clip3;
    private Clip clip4;
    private Clip clip5;

    private String selectedBrakeType = null;

    private final Rectangle hoverArea1 = new Rectangle(77, 211, 230, 448);   // Candy
    private final Rectangle hoverArea2 = new Rectangle(397, 211, 230, 446);  // Stripe
    private final Rectangle hoverArea3 = new Rectangle(717, 211, 230, 446);  // Magic
    private final Rectangle hoverArea4 = new Rectangle(53, 51, 65, 65);      // Exit

    /**
     * Initializes the brake selection screen with images, sounds, and mouse listeners.
     */
    public Brakes() {
        defaultImage = new ImageIcon("./assets/brakes.png").getImage();
        hoverImage1 = new ImageIcon("./assets/candy.png").getImage();
        hoverImage2 = new ImageIcon("./assets/stripe.png").getImage();
        hoverImage3 = new ImageIcon("./assets/magic.png").getImage();
        hoverImage4 = new ImageIcon("./assets/exitBreaks.png").getImage();
        currentImage = defaultImage;
        wheels = new Wheels();
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
            File music4 = new File("./assets/back.wav");
            AudioInputStream audioStream4 = AudioSystem.getAudioInputStream(music4);
            clip4 = AudioSystem.getClip();
            clip4.open(audioStream4);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Failed to load or play back.wav");
        }

        try {
            File music5 = new File("./assets/chooseBrakes.wav");
            AudioInputStream audioStream5 = AudioSystem.getAudioInputStream(music5);
            clip5 = AudioSystem.getClip();
            clip5.open(audioStream5);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Failed to load or play chooseBrakes.wav");
        }

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                int currentHovered = 0;

                if (hoverArea1.contains(p)) {
                    currentImage = hoverImage1;
                    currentHovered = 1;
                } else if (hoverArea2.contains(p)) {
                    currentImage = hoverImage2;
                    currentHovered = 2;
                } else if (hoverArea3.contains(p)) {
                    currentImage = hoverImage3;
                    currentHovered = 3;
                } else if (hoverArea4.contains(p)) {
                    currentImage = hoverImage4;
                    currentHovered = 4;
                } else {
                    currentImage = defaultImage;
                }

                if (currentHovered != 0 && currentHovered != lastHovered) {
                    if (clip2 != null) {
                        clip2.setFramePosition(0);
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
                    selectedBrakeType = CANDY;
                    candyPicked = true;
                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    }
                } else if (hoverArea2.contains(p)) {
                    selectedBrakeType = STRIPE;
                    stripePicked = true;
                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    }
                } else if (hoverArea3.contains(p)) {
                    selectedBrakeType = MAGIC;
                    magicPicked = true;
                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    }
                } else if (hoverArea4.contains(p)) {
                    selectedBrakeType = null;
                    wheels.changeWheelType(null);
                    backPicked = true;
                    wheels.nullify();
                    if (clip4 != null) {
                        clip4.stop();
                        clip4.setFramePosition(0);
                        clip4.start();
                    }
                }
            }
        });
    }

    /**
     * Checks if the Candy brakes option has been selected.
     * 
     * @return true if Candy brakes were picked
     */
    public boolean isCandyPicked(){
        return candyPicked;
    }

    /**
     * Checks if the Stripe brakes option has been selected.
     * 
     * @return true if Stripe brakes were picked
     */
    public boolean isStripePicked(){
        return stripePicked;
    }

    /**
     * Checks if the Magic brakes option has been selected.
     * 
     * @return true if Magic brakes were picked
     */
    public boolean isMagicPicked(){
        return magicPicked;
    }

    /**
     * Checks if the back button has been clicked.
     * 
     * @return true if the back button was clicked
     */
    public boolean isBackPicked(){
        return backPicked;
    }

    /**
     * Returns the currently selected brake type.
     * 
     * @return the currently selected brake type
     */
    public String getType() {
        return selectedBrakeType;
    }

    /**
     * Updates the back button selection state.
     * 
     * @param x the boolean value to set backPicked to
     */
    public void changebackPicked(boolean x){
        backPicked = x;
    }

    /**
     * Resets the brake selection state to default values.
     */
    public void nullify(){
        candyPicked = false;
        stripePicked = false;
        magicPicked = false;
        currentImage = defaultImage;
    }

    /**
     * Updates the currently selected brake type.
     * 
     * @param x the brake type to set as selected
     */
    public void changeBrake(String x){
        selectedBrakeType = x;
    }

    /**
     * Plays the brake selection sound effect once.
     */
    public void playSound(){
        if (clip5 != null){
            clip5.setFramePosition(0);
            clip5.start();
            clip5 = null;
        }
    }

    /**
     * Renders the current brake selection screen image.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentImage != null) {
            g.drawImage(currentImage, 0, 0, this);
        }
    }
}
