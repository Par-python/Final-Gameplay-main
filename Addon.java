/**
 * The Addon class manages the add-on selection interface for the game, providing visual feedback and sound effects for user interactions.
 * It handles the selection of the add-ons, with hover effects and click responses.
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
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class Addon extends JComponent {

    public static final String ADDON = "Cinnamon Turbo";
    public static final String NONE = "None";
    public boolean addonPicked;
    public boolean xPicked;
    public boolean backPicked;
    private Image defaultImage;
    private Image hoverImage1;
    private Image hoverImage2;
    private Image hoverImage3;
    private Image currentImage;
    private Brakes brakes;
    private Clip clip2;
    private Clip clip3;
    private Clip clip4;
    private Clip clip5;
    private int lastHovered = 0;

    private String AddonType = null;

    private final Rectangle hoverArea1 = new Rectangle(249, 211, 230, 446); // Cinnamon Turbo
    private final Rectangle hoverArea2 = new Rectangle(546, 211, 230, 446); // No Add-on
    private final Rectangle hoverArea3 = new Rectangle(53, 51, 65, 65);     // Back button

    /**
     * Initializes the add-on selection screen with necessary images, sound effects, and interactive elements.
     * Sets up mouse listeners for hover effects and click interactions with appropriate sound feedback.
     */
    public Addon() {
        
        defaultImage = new ImageIcon("./assets/addon.png").getImage();
        hoverImage1 = new ImageIcon("./assets/addonhover.png").getImage();
        hoverImage2 = new ImageIcon("./assets/xhover.png").getImage();
        hoverImage3 = new ImageIcon("./assets/exitaddon.png").getImage();
        currentImage = defaultImage;
        addonPicked = false;
        xPicked = false;
        backPicked = false;
        brakes = new Brakes();

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
            File music5 = new File("./assets/chooseAddons.wav");
            AudioInputStream audioStream5 = AudioSystem.getAudioInputStream(music5);
            clip5 = AudioSystem.getClip();
            clip5.open(audioStream5);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Failed to load or play chooseAddons.wav");
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
                    AddonType = ADDON;
                    addonPicked = true;
                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    }
                    System.out.println("+ " + AddonType + " add-on");
                } else if (hoverArea2.contains(p)) {
                    AddonType = NONE;
                    xPicked = true;
                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    }
                    System.out.println("No add-on added");
                } else if (hoverArea3.contains(p)) {
                    AddonType = null;
                    brakes.nullify();
                    backPicked = true;
                    brakes.changeBrake(null);
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
     * Returns the currently selected add-on type.
     * 
     * @return the currently selected add-on type
     */
    public String getType() {
        return AddonType;
    }

    /**
     * Checks if the Cinnamon Turbo add-on has been selected.
     * 
     * @return true if an add-on was picked
     */
    public boolean isAddonPicked() {
        return addonPicked;
    }

    /**
     * Checks if the "No Add-on" option has been selected.
     * 
     * @return true if 'no add-on' was picked
     */
    public boolean isXPicked() {
        return xPicked;
    }

    /**
     * Checks if the back button has been clicked.
     * 
     * @return true if the back button was clicked
     */
    public boolean isBackPicked() {
        return backPicked;
    }

    /**
     * Updates the currently selected add-on type.
     * 
     * @param x the addon to replace the current AddonType with
     */
    public void changeAddonType(String x) {
        AddonType = x;
    }

    /**
     * Resets the add-on selection state to default values.
     */
    public void nullify() {
        AddonType = null;
        addonPicked = false;
        xPicked = false;
        currentImage = defaultImage;
    }

    /**
     * Updates the back button selection state.
     * 
     * @param x the boolean value to set for backPicked
     */
    public void changebackPicked(boolean x) {
        backPicked = x;
    }

    /**
     * Plays the add-on selection sound effect once.
     */
    public void playSound(){
        if (clip5 !=null){
            clip5.setFramePosition(0);
            clip5.start();
            clip5 = null;
        }
    }

    /**
     * Renders the current add-on selection screen image.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentImage != null) {
            g.drawImage(currentImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
