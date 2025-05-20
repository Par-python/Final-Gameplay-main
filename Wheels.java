import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class Wheels extends JComponent {
    // Constants
    public static final String SLICKS = "Slicks";
    public static final String WETS = "Wets";
    public boolean slicksPicked;
    public boolean wetsPicked;
    public boolean backPicked;
    private Image defaultImage;
    private Image hoverImage1;
    private Image hoverImage2;
    private Image hoverImage3;
    private Image currentImage;
    private Engine engine;
    private Clip clip2;
    private Clip clip3;
    private Clip clip4;
    private Clip clip5;



    private String selectedWheelsType = null;


    private final Rectangle hoverArea1 = new Rectangle(249, 211, 230, 446); // Slicks
    private final Rectangle hoverArea2 = new Rectangle(546, 211, 230, 446); // Wets
    private final Rectangle hoverArea3 = new Rectangle(53,51,65,65);
    private int lastHovered = 0;
    public Wheels() {
        defaultImage = new ImageIcon("./assets/Wheels.png").getImage();
        hoverImage1 = new ImageIcon("./assets/slicks.png").getImage();
        hoverImage2 = new ImageIcon("./assets/wets.png").getImage();
        hoverImage3 = new ImageIcon("./assets/exitWheels.png").getImage();
        currentImage = defaultImage;
        slicksPicked = false;
        wetsPicked = false;
        backPicked = false;
        engine = new Engine();

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


       // Click sound
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
           File music5 = new File("./assets/chooseWheels.wav");
           AudioInputStream audioStream5 = AudioSystem.getAudioInputStream(music5);
           clip5 = AudioSystem.getClip();
           clip5.open(audioStream5);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Failed to load or play chooseWheels.wav");
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
                    selectedWheelsType = SLICKS;
                    slicksPicked = true;
                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    } 
                    System.out.println("Wheels: " + SLICKS);
                } else if (hoverArea2.contains(p)) {
                    selectedWheelsType = WETS;
                    wetsPicked = true;

                    if (clip3 != null) {
                        clip3.stop();
                        clip3.setFramePosition(0);
                        clip3.start();
                    }
                    System.out.println("Wheels: " + WETS);
                } else if (hoverArea3.contains(p)) {
                    selectedWheelsType = null;
                    engine.nullify();
                    backPicked = true;
                    engine.changeEngine(null);

                    if (clip4 != null) {
                        clip4.stop();
                        clip4.setFramePosition(0);
                        clip4.start();
                    }
                }
            }
        });
    }

    public String getType() {
        return selectedWheelsType;
    }

    public boolean isSlicksPicked(){
        return slicksPicked;
    }

    public boolean isWetsPicked(){
        return wetsPicked;
    }

    public boolean isBackPicked(){
        return backPicked;
    }

    public void changeWheelType(String x){
        selectedWheelsType = x;
    }

    public void nullify(){
        selectedWheelsType = null;
        slicksPicked = false;
        wetsPicked = false;
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
