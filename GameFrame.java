    import java.awt.*;
    import java.awt.event.*;

    import javax.sound.sampled.LineUnavailableException;
    import javax.sound.sampled.UnsupportedAudioFileException;
    import javax.swing.*;

    //for server
    import java.io.*;
    import java.net.*;

    public class GameFrame extends JFrame {

        // gameplay(Local)
        private int width, height;
        private Container contentPane;
        private PlayerSprite me;
        private PlayerSprite enemy;
        private DrawingComponent dc;
        private Timer animationTimer;
        private boolean gas, brake, shiftU, shiftD;
        private static double speed;
        private static int gear;
        private static double prevGear;
        private EngineType engineType;
        private BrakeType brakeType;
        private boolean clutchReleased;
        private Turbo turbo;
        private static int ellapsedTime;
        private boolean playerhasJoined;

        // art
        private GameBackground background;
        private GameMapAssets assets;
        private GameMapClouds clouds;
        private GameMapCastle castle;
        private GameMapSky sky;
        private GameCountdown countdown;
        private Obstacle obstacle1;
        private Obstacle obstacle2;
        private Obstacle obstacle3;
        private Obstacle obstacle4;
        private Kingdom kingdom;
        private GameCanvas gc;
        
        // for server
        private Socket socket;
        private static int playerID;
        private ReadFromServer rfsRunnable;
        private WriteToServer wtsRunnable;

        public GameFrame(int w, int h) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
            width = w;
            height = h;
            gas = false;
            brake = false;
            shiftU = false;
            shiftD = false;
            speed = 0;
            gear = 1;
            prevGear = 1;
            playerhasJoined = false;
            clutchReleased = true;
            obstacle1 = new Obstacle(0, 0, 1);
            obstacle2 = new Obstacle(0, 0, 2);
            obstacle3 = new Obstacle(0, 0, 3);
            obstacle4 = new Obstacle(0, 0, 4);
            engineType = new EngineType("ShortCake Core");
            brakeType = new BrakeType("Stripe Brakes");
            background = new GameBackground();
            assets = new GameMapAssets();
            sky = new GameMapSky();
            countdown = new GameCountdown();
            turbo = new Turbo();
            kingdom = new Kingdom();
            gc = new GameCanvas();
            me = new PlayerSprite(100, 400, 50, Color.BLUE, "Blueberry");
            enemy = new PlayerSprite(100, 500, 50, Color.RED, "Strawberry");
        }

        public void setUpGUI() {
        contentPane = this.getContentPane();
        this.setTitle("Candy Racers Player: " + playerID);
        contentPane.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        contentPane.setLayout(new BorderLayout()); 
        contentPane.add(gc, BorderLayout.CENTER);
        gc.start(); 
        this.revalidate();
        this.repaint();

        Timer waitTimer = new Timer(100, null);
            waitTimer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (gc.setOnReady()) {
                        waitTimer.stop();
                        contentPane.remove(gc);
                        dc = new DrawingComponent();
                        createSprites();
                        createObstacles();
                        contentPane.add(dc);
                        contentPane.revalidate();
                        contentPane.repaint();
                        setUpKeyListener();
                        engineType.checkEngine();
                        brakeType.checkBrake();
                        setUpAnimationTimer();
                    }
                }
            });
            waitTimer.start();

    }
        private void createSprites() {
            if (playerID == 1) {
                me = new PlayerSprite(100, 350, 50, Color.BLUE, "Blueberry");
                kingdom.changeKingdomType("Blueberry");
                enemy = new PlayerSprite(100, 450, 50, Color.RED, "Strawberry");
            } else {
                enemy = new PlayerSprite(100, 350, 50, Color.BLUE, "BlueBerry");
                me = new PlayerSprite(100, 450, 50, Color.RED, "Strawberry");
                kingdom.changeKingdomType("Strawberry");
            }
        }

        private void createObstacles(){
            obstacle1 = new Obstacle(2000, 400, 1);
            obstacle2 = new Obstacle(4900, 500, 2);
            obstacle3 = new Obstacle(5800, 400, 3);
            obstacle4 = new Obstacle(8900, 500, 4);
        }

        public static double getSpeed() {
            return speed;
        }

        public static double getGearUpdate() {
            return gear;
        }

        public static double getPrevGear(){
            return prevGear;
        }

        private void setUpAnimationTimer() {
            int interval = 10;
            ActionListener al = new ActionListener() {
                public void actionPerformed(ActionEvent ae) {

                    double widthX = 4096;
                    boolean isNotNearEnd = true;

                    if(me.getX() > widthX * 29.5){
                        speed = 0;
                        isNotNearEnd = false;
                    }

                    if(turbo.isTurboActive()){
                        engineType.tubroIsActivated();
                    }

                    if (gas && isNotNearEnd) {

                        if(gear != 0){
                            ellapsedTime+=interval;
                        }
                        
                        engineType.checkEngine();
                        if(engineType.isMoneyShift()){
                            speed = engineType.getSpeed() + turbo.getTurboBurst();
                            engineType.resetMoneyShift();
                        } else {
                            speed += engineType.getAccelerationFinal();
                        }
                    } else if (speed < 0) {
                        speed = 0;
                    } else if (speed > 0.01) {
                        if (brake) {
                            speed -= brakeType.getDeccelerationFinal();
                        } else {
                            speed -= 0.25;
                        }
                    }

                    if (shiftU) {
                        if (clutchReleased == true) {
                            if(gear < 6){
                                prevGear = gear;
                                gear += engineType.getGear();
                                clutchReleased = false;
                                System.out.println("Gear:" + gear);
                            }
                        }
                    }

                    if (shiftD) {
                        if (gear != 0 && clutchReleased == true) {
                            prevGear = gear;
                            gear -= engineType.getGear();
                            clutchReleased = false;
                            System.out.println("Gear:" + gear);
                        }
                    }
                

                    me.moveH(speed);

                    dc.repaint();
                }
            };
            animationTimer = new Timer(interval, al);
            animationTimer.start();
        }

        private void setUpKeyListener() {
            KeyListener kl = new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) { }

                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    switch (keyCode) {
                        case KeyEvent.VK_W:
                            gas = true;
                            break;
                        case KeyEvent.VK_S:
                            brake = true;
                            break;
                        case KeyEvent.VK_Q:
                            shiftD = true;
                            break;
                        case KeyEvent.VK_E:
                            shiftU = true;
                            break;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    switch (keyCode) {
                        case KeyEvent.VK_W:
                            gas = false;
                            break;
                        case KeyEvent.VK_S:
                            brake = false;
                            break;
                        case KeyEvent.VK_Q:
                            shiftD = false;
                            clutchReleased = true;
                            break;
                        case KeyEvent.VK_E:
                            shiftU = false;
                            clutchReleased = true;
                            break;
                    }
                }
            };
            dc.addKeyListener(kl);
            dc.setFocusable(true);
            dc.requestFocusInWindow(); 
        }

        public void connectToServer() {
            try {
                socket = new Socket("localhost", 5000);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                playerID = in.readInt();
                System.out.println("You are player# " + playerID);

                if (playerID == 1) {
                    System.out.println("Waiting for player #2 to connect...");
                }
                rfsRunnable = new ReadFromServer(in);
                wtsRunnable = new WriteToServer(out);

                rfsRunnable.waitForStartMsg();

            } catch (IOException ex) {
                System.out.println("IOException from connectToServer");
            }
        }

        private class DrawingComponent extends JComponent {
            protected void paintComponent(Graphics g) {

                Graphics2D g2d = (Graphics2D) g.create();

                double frameWidth = getWidth();
                double playerX = me.getX();
                double cameraX = playerX - frameWidth / 2;

                double cloudsPara = 0.1;
                int backgroundCloudsMove = (int) (-cameraX * cloudsPara);
                clouds = new GameMapClouds(backgroundCloudsMove);

                double castlePara = 0.05 / 10; // very slow
                int backgroundCastleMove = (int) (-cameraX * castlePara);
                castle = new GameMapCastle(backgroundCastleMove);

                sky.paintComponent(g2d);
                clouds.paintComponent(g2d);
                castle.paintComponent(g2d);

                if (me.getX() >= (getWidth() / 2)) {
                    g2d.translate(-cameraX, 0);
                }

                background.paintComponent(g2d);
                enemy.drawSprite(g2d);
                me.drawSprite(g2d);
                assets.paintComponent(g2d);
                obstacle1.drawSprite(g2d);
                obstacle2.drawSprite(g2d);
                obstacle3.drawSprite(g2d);
                obstacle4.drawSprite(g2d);

                if(playerhasJoined){
                    countdown.paintComponent(g2d);
                }

                g2d.dispose();
                
                g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                g.setColor(Color.WHITE);
                g.drawString(String.format("Speed: %.0f", speed * 1), 500, 740);

                int totalSeconds = ellapsedTime / 1000;
                int displayMinutes = totalSeconds / 60;
                int displaySeconds = totalSeconds % 60;
                int displayMilliseconds = ellapsedTime % 1000;

                g.drawString(String.format("%02d:%02d:%03d%n", displayMinutes , displaySeconds, displayMilliseconds),0 , 740 );

                if(gear == 0){
                    g.drawString("Gear: N" , 500, 760);
                } else {
                    g.drawString("Gear: " + gear, 500, 760);
                }
            }
        }

        private class ReadFromServer implements Runnable {

            private DataInputStream dataIn;

            public ReadFromServer(DataInputStream in) {
                dataIn = in;
                System.out.println("RFS Runnable Created");
            }

            public void run() {
                try {

                    while (true) {
                        if (enemy != null) {
                            enemy.setX(dataIn.readDouble());
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("IOException from RFS run()");
                }
            }

            public void waitForStartMsg() {
                playerhasJoined = true;

                try {
                    String startMsg = dataIn.readUTF();
                    System.out.println("Message from server: " + startMsg);

                    Thread readThread = new Thread(rfsRunnable);
                    Thread writeThread = new Thread(wtsRunnable);
                    readThread.start();
                    writeThread.start();
                } catch (IOException ex) {
                    System.out.println("IOException from waitForStartMsg()");
                }
            }
        }

        private class WriteToServer implements Runnable {

            private DataOutputStream dataOut;

            public WriteToServer(DataOutputStream out) {
                dataOut = out;
                System.out.println("WTS Runnable Created");
            }

            public void run() {
                try {

                    while (true) {
                        // doesnt have y(does it)
                        if (me != null) {
                            dataOut.writeDouble(me.getX());
                            dataOut.flush();
                        }
                        try {
                            Thread.sleep(25);
                        } catch (InterruptedException ex) {
                            System.out.println("InterreputedException from WTS run()");
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("IOException from WTS run()");
                }
            }
        }
    }