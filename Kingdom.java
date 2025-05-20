import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class Kingdom extends JComponent {
    public static final String STRAWBERRY = "Strawberry";
    public static final String BLUEBERRY = "Blueberry";

    private Image defaultImage;
    private Image hoverImage1;
    private Image hoverImage2;
    private Image currentImage;
    private boolean blueberryPicked;
    private boolean strawberryPicked;
    private Clip clip2;
    private Clip clip3;
    private Clip clip4;
    private Clip clip5;


    private String selectedKingdomType = null;

    private final Rectangle hoverArea1 = new Rectangle(528, 247, 418, 374);  // Strawberry
    private final Rectangle hoverArea2 = new Rectangle(76, 247, 418, 373);   // Blueberry
    private int lastHovered = 0;

    public Kingdom() {
        defaultImage = new ImageIcon("./assets/chooseKingdom.png").getImage();
        hoverImage1 = new ImageIcon("./assets/strawberryhover.png").getImage();
        hoverImage2 = new ImageIcon("./assets/blueberryhover.png").getImage();
        currentImage = defaultImage;

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
        File music5 = new File("./assets/chooseKingdom.wav");
        AudioInputStream audioStream5 = AudioSystem.getAudioInputStream(music5);
        clip5 = AudioSystem.getClip();
        clip5.open(audioStream5);
       } catch (Exception ex) {
        ex.printStackTrace();
        System.err.println("Failed to load or play back.wav");
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
                } else {
                    currentImage = defaultImage;
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
                    selectedKingdomType = STRAWBERRY;
                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    }
                    strawberryPicked = true;
                    System.out.println("Kingdom: " + STRAWBERRY );
                } else if (hoverArea2.contains(p)) {
                    selectedKingdomType = BLUEBERRY;
                    blueberryPicked = true;
                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    } 
                    System.out.println("Kingdom: " + BLUEBERRY);
                }
            }
        });
    }

    public String getType() {
        return selectedKingdomType;
    }

    public void changeKingdomType(String x){
        selectedKingdomType = x;
    }
    public boolean isStrawberryPicked(){
        return strawberryPicked;
    }

    public boolean isBlueberryPicked(){
        return blueberryPicked;
    }

    public void playSound(){
        if (clip5!=null){
            clip5.setFramePosition(0);
            clip5.start();
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
