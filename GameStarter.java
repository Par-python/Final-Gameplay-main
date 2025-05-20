import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameStarter {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException  {
    GameFrame pf = new GameFrame(1024, 768);
    pf.connectToServer();
    pf.setUpGUI();
    }
}