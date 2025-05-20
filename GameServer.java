/**
 * The GameServer class manages the multiplayer game server functionality.
 * It handles client connections, player synchronization, and game state management.
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
import java.io.*;
import java.net.*;

public class GameServer {

    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;

    //reference the runnables and storing sockets
    private Socket p1Socket;
    private Socket p2Socket;
    private ReadFromClient p1ReadRunnable;
    private ReadFromClient p2ReadRunnable;
    private WriteToClient p1WriteRunnable;
    private WriteToClient p2WriteRunnable;

    //x and y cords
    private double p1x, p1y, p2x, p2y;

    /**
     * Initializes the game server with default settings and player positions.
     */
    public GameServer(){
        System.out.println("=== GAME SERVER ===");
        numPlayers = 0;
        maxPlayers = 2;

        p1x = 100;
        p1y = 350;

        p2x = 100;
        p2y = 450;

        try {
            ss = new ServerSocket(5000);
        } catch(IOException ex){
            System.out.println("IOException from GameServer constructor");
        }
    }

    /**
     * Accepts and manages client connections, setting up communication channels.
     */
    public void acceptConnections(){
        try{
            System.out.println("Waiting for connections...");

            while(numPlayers < maxPlayers){
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                numPlayers++;
                out.writeInt(numPlayers);
                System.out.println("Player #: " + numPlayers + " has connected.");

                ReadFromClient rfc = new ReadFromClient(numPlayers, in);
                WriteToClient wtc = new WriteToClient(numPlayers, out);

                if(numPlayers == 1 ){
                    p1Socket = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wtc;
                } else {
                    p2Socket = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wtc;

                    p1WriteRunnable.sendStartMsg();
                    p2WriteRunnable.sendStartMsg();


                    //actually sending the info
                    Thread readThread1 = new Thread(p1ReadRunnable);
                    Thread readThread2 = new Thread(p2ReadRunnable);
                    readThread1.start();
                    readThread2.start();

                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    writeThread1.start();
                    writeThread2.start();
                }
            }

            System.out.println("No longer accepting connections");

        } catch(IOException ex){
            System.out.println("IOEXecption from acceptConnections()");
        }
    }

    /**
     * The ReadFromClient class manages reading player position data from connected clients.
     * It updates the server's game state based on received player positions.
     */
    private class ReadFromClient implements Runnable {

        private int playerID;
        private DataInputStream dataIn;

        /**
         * Initializes the ReadFromClient with player ID and input stream.
         * 
         * @param pid the player ID (1 or 2)
         * @param in the DataInputStream for reading player data
         */
        public ReadFromClient(int pid, DataInputStream in){
            playerID = pid;
            dataIn = in;
            System.out.println("RFC" + playerID + "Runnable created");
        }

        /**
         * Continuously reads player position data from the client.
         * Updates the server's game state with received positions.
         */
        public void run(){
            try {

                while(true){
                    if(playerID == 1){
                        p1x = dataIn.readDouble();
                    } else {
                        p2x = dataIn.readDouble();
                    }
                }
            } catch(IOException ex){
                System.out.println("IOException from RFC run()");
            }
        }
    }

    /**
     * The WriteToClient class manages sending game state updates to connected clients.
     * It handles the synchronization of player positions between clients.
     */
    private class WriteToClient implements Runnable {

        private int playerID;
        private DataOutputStream dataOut;

        /**
         * Initializes the WriteToClient with player ID and output stream.
         * 
         * @param pid the player ID (1 or 2)
         * @param out the DataOutputStream for sending player data
         */
        public WriteToClient(int pid, DataOutputStream out){
            playerID = pid;
            dataOut = out;
            System.out.println("WTC" + playerID + "Runnable created");
        }

        /**
         * Continuously sends opponent position data to the client.
         * Maintains a consistent update rate for smooth gameplay.
         */
        public void run(){
            try{

                while(true){
                    if (playerID == 1){
                        //doesnt have y (does it)
                        dataOut.writeDouble(p2x);
                        dataOut.flush();
                    } else {
                        dataOut.writeDouble(p1x);
                        dataOut.flush();
                    }
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException ex){
                        System.out.println("InterreputedExeception from WTC run()");
                    }
                }
            } catch(IOException ex){
                System.out.println("IOException from WTC run()");
            }
        }

        /**
         * Sends the start message to the client when both players are connected.
         */
        public void sendStartMsg(){
            try {
                dataOut.writeUTF("We now have 2 players.");
            } catch (IOException ex) {
               System.out.println("IOException from sendStartMsg()");
            }
        }
    }

    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}