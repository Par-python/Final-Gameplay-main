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


    public boolean playPicked(){
        return playPicked;
    }


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
