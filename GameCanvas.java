import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;

public class GameCanvas extends JComponent {
    private Clip clip1;

    private Kingdom chooseKingdom;
    private ArrayList<JComponent> elements;
    private IntroBackground introBackground;
    private Engine chooseEngine;
    private Wheels wheels;
    private Brakes brakes;
    private Addon addon;
    private ReadyScreen readyScreen;

    public GameCanvas() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        setPreferredSize(new Dimension(1024, 768));
        setLayout(null);

        File music1 = new File("./assets/intro.WAV");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(music1);
        clip1 = AudioSystem.getClip();
        clip1.open(audioStream);

        introBackground = new IntroBackground();
        chooseKingdom = new Kingdom();  
        chooseEngine = new Engine();
        wheels = new Wheels();
        brakes = new Brakes();
        addon = new Addon();

        readyScreen = new ReadyScreen(chooseKingdom);  
    }

    public void start() {
        clip1.start();
        add(introBackground);
        introBackground.playSound();
        introBackground.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (introBackground.playPicked()) {
                    System.out.println("\nTime to build your car!\n");
                    remove(introBackground);
                    add(chooseKingdom);
                    repaint();
                }
            }
        });

        // Kingdom selection
        chooseKingdom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (chooseKingdom.isBlueberryPicked() || chooseKingdom.isStrawberryPicked()) {
                    remove(chooseKingdom);
                    add(chooseEngine);
                    chooseEngine.playSound();
                    repaint();
                }
            }
        });

        // Engine selection
        chooseEngine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (chooseEngine.isShortcakePicked() || chooseEngine.isChocoPicked() || chooseEngine.isMatchaPicked()) {
                    remove(chooseEngine);
                    chooseEngine.nullify();
                    add(wheels);
                    wheels.playSound();
                    repaint();
                }
            }
        });

        // Wheel selection and back
        wheels.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (wheels.isBackPicked()) {
                    wheels.nullify();
    
                    remove(wheels);
                    chooseEngine.nullify();
                    add(chooseEngine);
                    repaint();
                    System.out.println("\nWent back to Engine selection.\n");
                } else if (wheels.isSlicksPicked() || wheels.isWetsPicked()) {
                    brakes.playSound();
                    remove(wheels);
                    wheels.nullify();
                    add(brakes);
                    repaint();
                }
            }
        });

        // Brakes selection and back
        brakes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (brakes.isBackPicked()) {
                    brakes.changebackPicked(false);
                    brakes.nullify();
                    remove(brakes);
                    add(wheels);
                    repaint();
                    System.out.println("\nWent back to Wheels selection.\n");
                } else if (brakes.isCandyPicked() || brakes.isStripePicked() || brakes.isMagicPicked()) {
                    remove(brakes);
                    add(addon);
                    addon.playSound();
                    repaint();
                }
            }
        });

        // Addon selection and back
        addon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (addon.isBackPicked()) {
                    addon.changebackPicked(false);
                    addon.nullify();
                    remove(addon);
                    brakes.nullify();
                    add(brakes);
                    repaint();
                    System.out.println("\nWent back to Brakes selection.\n");
                } else if (addon.isAddonPicked() || addon.isXPicked()) {
                    remove(addon);
                    add(readyScreen);
                    readyScreen.playSound();
                    repaint();
                }
            }
        });

        // Ready screen back button and readiness checks
        readyScreen.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (readyScreen.isBlueBerryReady() || readyScreen.isStrawberryReady()) {
            remove(readyScreen);
            repaint();
            if (onReady != null) onReady.run(); 
        }
    }
});


        clip1.close();
    }

    private Runnable onReady;
    public void setOnReady(Runnable onReady) {
    this.onReady = onReady;
}

}
