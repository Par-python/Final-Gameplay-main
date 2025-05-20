import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class Engine extends JComponent {
    // Constants for types
    public static final String SHORTCAKE = "Shortcake Core";
    public static final String CHOCO = "ChocolocateOverload Core";
    public static final String MATCHA = "Matcha Core";

    private Image defaultImage;
    private Image hoverImage1;
    private Image hoverImage2;
    private Image hoverImage3;
    private Image currentImage;
    private boolean shortcakePicked;
    private boolean chocoPicked;
    private boolean matchaPicked;
    private Clip clip2;
    private Clip clip3;
    private Clip clip4;
    private Clip clip5;


    private String pickedEngineType;

    private final Rectangle hoverArea1 = new Rectangle(77, 211, 230, 448);   // Shortcake
    private final Rectangle hoverArea2 = new Rectangle(397, 211, 230, 446);  // Choco
    private final Rectangle hoverArea3 = new Rectangle(717, 211, 230, 446);  // Matcha
    private int lastHovered = 0;

    


    public Engine() {
        defaultImage = new ImageIcon("./assets/Engine.png").getImage();
        hoverImage1 = new ImageIcon("./assets/shortcake.png").getImage();
        hoverImage2 = new ImageIcon("./assets/choco.png").getImage();
        hoverImage3 = new ImageIcon("./assets/matcha.png").getImage();
        currentImage = defaultImage;

        pickedEngineType = null;

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
            File music5 = new File("./assets/chooseEngine.wav");
            AudioInputStream audioStream5 = AudioSystem.getAudioInputStream(music5);
            clip5 = AudioSystem.getClip();
            clip5.open(audioStream5);

        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Failed to load or play chooseAddon.wav");
        }

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                int currentHovered = 0;
                if (hoverArea1.contains(p)) {
                    if (currentImage != hoverImage1) {
                        currentImage = hoverImage1;
                        currentHovered = 1;
                    }
                } else if (hoverArea2.contains(p)) {
                    if (currentImage != hoverImage2) {
                        currentImage = hoverImage2;
                        currentHovered = 2;
                    }
                } else if (hoverArea3.contains(p)) {
                    if (currentImage != hoverImage3) {
                        currentImage = hoverImage3;
                        currentHovered = 3;
                    }
                } else {
                    if (currentImage != defaultImage) {
                        currentImage = defaultImage;

                    }
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
                    pickedEngineType = SHORTCAKE;
                    shortcakePicked = true;
                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    }

                    System.out.println("Engine: " + SHORTCAKE);
                } else if (hoverArea2.contains(p)) {
                    pickedEngineType = CHOCO;
                    chocoPicked = true;
                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    }
                    System.out.println("Engine: " + CHOCO );
                } else if (hoverArea3.contains(p)) {
                    pickedEngineType = MATCHA;
                    matchaPicked = true;
                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    }
                    System.out.println("Engine: " + MATCHA);
                }
            }
        });
    }

    public boolean isShortcakePicked(){
        return shortcakePicked;
    }

    public boolean isChocoPicked(){
        return chocoPicked;
    }

    public boolean isMatchaPicked(){
        return matchaPicked;
    }
    public String getEngineType() {
        return pickedEngineType;
    }
    public void changeEngine(String x){
        pickedEngineType = x;
    }

    public void nullify(){
        pickedEngineType = null;
        shortcakePicked = false;
        chocoPicked = false;
        matchaPicked = false;
        currentImage = defaultImage;
    }

    public void playSound(){
       if (clip5!=null){
           clip5.setFramePosition(0);
           clip5.start();
           clip5 = null;
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
