import java.awt.*;
import javax.swing.*;

public class GameCountdown extends JComponent {
    private Image gameCountdown1;
    private Image gameCountdown2;
    private Image gameCountdown3;
    private Image gameCountdownGo;
    private Image currentImage;
    private int countdown;
    
    public GameCountdown(){
        ImageIcon img1 = new ImageIcon("./assets/gameStart1Countdown.png");
        ImageIcon img2 = new ImageIcon("./assets/gameStart2Countdown.png");
        ImageIcon img3 = new ImageIcon("./assets/gameStart3Countdown.png");
        ImageIcon img4 = new ImageIcon("./assets/gameStartGoCountdown.png");
        gameCountdown1 = img1.getImage();
        gameCountdown2 = img2.getImage();
        gameCountdown3 = img3.getImage();
        gameCountdownGo = img4.getImage();
        currentImage = img3.getImage();
        setBounds(0, 0, 1024, 768);

        countdown =0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameCountdown1 != null) {
            countdown+= 10;
            System.out.println(countdown);
            
            if(countdown == 500){
                currentImage = gameCountdown2;
            }else if (countdown == 1000){
                currentImage = gameCountdown1;
            } else if (countdown == 1500){
                currentImage = gameCountdownGo;
            }

            g.drawImage(currentImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
