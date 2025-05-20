import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class ReadyScreen extends JComponent {

    public boolean blueberry_Ready;
    public boolean strawberry_Ready;
    public boolean backPicked;
    private Image blueberryWait;
    private Image blueberryReady;
    private Image blueberryExit;
    private Image strawberryWait;
    private Image strawberryReady;
    private Image strawberryExit;
    private Image currentImage;
    private Addon addon;
    private Kingdom kingdom;
    private Clip clip2;
    private Clip clip3;
    private Clip clip4;
    private Clip clip5;
    private Clip clip6;
    private int lastHovered = 0;

    private final Rectangle readyArea = new Rectangle(323, 487, 404, 114);
    private final Rectangle exitArea = new Rectangle(53, 51, 65, 65);

    public ReadyScreen(Kingdom kingdom) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.kingdom = kingdom;
        blueberryWait = new ImageIcon("./assets/waitBlueberry.png").getImage();
        blueberryReady = new ImageIcon("./assets/readyBlueberry.png").getImage();
        blueberryExit = new ImageIcon("./assets/exitBlueberry.png").getImage();
        strawberryWait = new ImageIcon("./assets/waitStrawberry.png").getImage();
        strawberryReady = new ImageIcon("./assets/readyStrawberry.png").getImage();
        strawberryExit = new ImageIcon("./assets/exitStrawberry.png").getImage();
        addon = new Addon();
        blueberry_Ready = false;
        strawberry_Ready = false;
        backPicked = false;



        setBounds(0, 0, 1024, 768);


        String type = kingdom.getType();
        if ("Strawberry".equals(type)) {
            currentImage = strawberryWait;
        } else if ("Blueberry".equals(type)) {
            currentImage = blueberryWait;
        } else {
            System.err.println("Warning: Kingdom type is null or unknown. Defaulting to Blueberry wait screen.");
            currentImage = blueberryWait;
        }

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
           File music3 = new File("./assets/readyButton.wav");
           AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(music3);
           clip3 = AudioSystem.getClip();
           clip3.open(audioStream3);
       } catch (Exception ex) {
           ex.printStackTrace();
           System.err.println("Failed to load or play click.wav");
       }

       try {
        File music4 = new File("./assets/unreadyButton.wav");
        AudioInputStream audioStream4 = AudioSystem.getAudioInputStream(music4);
        clip4 = AudioSystem.getClip();
        clip4.open(audioStream4);
       } catch (Exception ex) {
        ex.printStackTrace();
        System.err.println("Failed to load or play back.wav");
       }


       try {
           File music5 = new File("./assets/back.wav");
           AudioInputStream audioStream5 = AudioSystem.getAudioInputStream(music5);
           clip5 = AudioSystem.getClip();
           clip5.open(audioStream5);
       } catch (Exception ex) {
           ex.printStackTrace();
           System.err.println("Failed to load or play back.wav");
       }


       try {
           File music6 = new File("./assets/ready.wav");
           AudioInputStream audioStream5 = AudioSystem.getAudioInputStream(music6);
           clip6 = AudioSystem.getClip();
           clip6.open(audioStream5);
           
       } catch (Exception ex) {
           ex.printStackTrace();
           System.err.println("Failed to load or play chooseAddon.wav");
       }

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                int currentHovered = 0;
                String type = kingdom.getType();


                if ("Blueberry".equals(type) && !blueberry_Ready) {
                    if (exitArea.contains(p)) {
                        currentHovered = 1;
                        currentImage = blueberryExit;
                    } else {
                        currentImage = blueberryWait;
                    }
                }

                if ("Strawberry".equals(type) && !strawberry_Ready) {
                    if (exitArea.contains(p)) {
                        currentHovered = 2;
                        currentImage = strawberryExit;
                    } else {
                        currentImage = strawberryWait;
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
                String type = kingdom.getType();


                if ("Blueberry".equals(type)) {
                    if (readyArea.contains(p)) {

                        if (!blueberry_Ready && !backPicked) {
                            blueberry_Ready = true;
                            currentImage = blueberryReady;
                            if (clip3 != null) {
                                clip3.stop();
                                clip3.setFramePosition(0);
                                clip3.start();
                            }
         
                            repaint();
                            System.out.println("Driver: Ready (Blueberry)");
                        } else if (blueberry_Ready && !backPicked) {
                            blueberry_Ready = false;
                            currentImage = blueberryWait;
                            if (clip4 != null) {
                                clip4.stop();
                                clip4.setFramePosition(0);
                                clip4.start();
                            }
                            repaint();
                            System.out.println("Driver: Not Ready (Blueberry)");
                        }
                    } else if (exitArea.contains(p)) {

                        if (!blueberry_Ready) {
                            addon.nullify();
                            backPicked = true;
                            addon.changeAddonType(null);
                            if (clip5 != null) {
                                clip5.stop();
                                clip5.setFramePosition(0);
                                clip5.start();
                            }
                            System.out.println("Exiting to Maps (Blueberry)");
                        }
                    }
                }


                if ("Strawberry".equals(type)) {
                    if (readyArea.contains(p)) {

                        if (!strawberry_Ready && !backPicked) {
                            strawberry_Ready = true;
                            currentImage = strawberryReady;
                            repaint();
                            if (clip3 != null) {
                                clip3.stop();
                                clip3.setFramePosition(0);
                                clip3.start();
                            }
                            System.out.println("Driver: Ready (Strawberry)");
                        } else if (strawberry_Ready && !backPicked) {
                            strawberry_Ready = false;
                            currentImage = strawberryWait;
                            if (clip4 != null) {
                                clip4.stop();
                                clip4.setFramePosition(0);
                                clip4.start();
                            }
         
                            repaint();
                            System.out.println("Driver: Not Ready (Strawberry)");
                        }
                    } else if (exitArea.contains(p)) {
                        if (!strawberry_Ready) {
                            addon.nullify();
                            backPicked = true;
                            addon.changeAddonType(null);
                            if (clip5 != null) {
                                clip5.stop();
                                clip5.setFramePosition(0);
                                clip5.start();
                            }
                            System.out.println("Exiting to Add-ons (Strawberry)");
                        }
                    }
                }
            }
        });
    }

    public boolean isBlueBerryReady() {
        return blueberry_Ready;
    }

    public boolean isStrawberryReady() {
        return strawberry_Ready;
    }

    public boolean isBackPicked() {
        return backPicked;
    }

    public void nullify() {
        blueberry_Ready = false;
        strawberry_Ready = false;
        if ("Blueberry".equals(kingdom.getType())) {
            currentImage = blueberryWait;
        } else if ("Strawberry".equals(kingdom.getType())) {
            currentImage = strawberryWait;
        } else {
            currentImage = blueberryWait;
        }
        }
    

    public void changebackPicked(boolean x){
        backPicked = x;
    }

    public void playSound(){
        if (clip6!=null){
            clip6.setFramePosition(0);
            clip6.start();
            clip6 = null;
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
