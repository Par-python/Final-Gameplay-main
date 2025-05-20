/**
 * The GameCanvas class manages the main game interface and component transitions.
 * It handles the flow between different game screens including kingdom selection, engine selection, and ready screen.
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
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import javax.swing.*;

public class GameCanvas extends JComponent {
    private Clip clip1;

    private Kingdom chooseKingdom;
    private IntroBackground introBackground;
    private Engine chooseEngine;
    private Wheels wheels;
    private Brakes brakes;
    private Addon addon;
    private ReadyScreen readyScreen;

    //getting parts
    private boolean onReady;
    private String enginePass;
    private String brakePass;
    private boolean addOnPass;

    private GameFrame frame;

    /**
     * Initializes the game canvas with all necessary components and audio.
     */
    public GameCanvas(GameFrame f) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        frame = f;
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

        enginePass = "ShortCake Core";
        brakePass = "Stripes Core";
        addOnPass = true;

        onReady = false;

        readyScreen = new ReadyScreen(chooseKingdom);  
    }

    /**
     * Starts the game by initializing the intro screen and setting up component transitions.
     */
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
            onReady = true;
            enginePass = chooseEngine.getEngineType();
            brakePass = brakes.getType();
            addOnPass = addon.isAddonPicked();
            frame.setEngineType(enginePass);
            frame.setBrakeType(brakePass);
            frame.setTurbo(addOnPass);

        }
    }
    });

    clip1.close();
    }

    public String getChosenEngine(){
        return enginePass;
    }

    public String getChosenBrakes(){
        return brakePass;
    }

    public boolean getChosenAddOn(){
        return addOnPass;
    }

    /**
     * Checks if the game is ready to start.
     * 
     * @return true if the game is ready to start
     */
    public boolean setOnReady(){
        return onReady;
    }

}
