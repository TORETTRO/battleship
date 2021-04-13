package JFrameUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class PlayMusic {
    AudioInputStream audioInput;
    Clip clip;
    boolean isplay;
    long clipTimePositon;
    boolean getIsplay(){return  isplay;}
    void stop()
    {
        clip.stop();
    }
    void play()
    {
        clip.setMicrosecondPosition(clipTimePositon);
        clip.start();
    }

    void changeState()
    {
        isplay=!isplay;
        if(isplay)
        {
            clip.setMicrosecondPosition(clipTimePositon);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
//            System.out.println("start");
        }
        else
        {
            clipTimePositon=clip.getMicrosecondPosition();
            clip.stop();
//            System.out.println("stop");
        }
    }
    void playMusic(String musicLocation)
    {
        try
        {
            File musicPath = new File(musicLocation);

            if(musicPath.exists())
            {
                isplay=true;
                audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
//                JOptionPane.showMessageDialog(null,"press OK to stop playing");

//                System.out.println("down");
            }
            else
            {

            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filepath = "src\\bgm\\bgm.wav";
        PlayMusic play = new PlayMusic();
        play.playMusic(filepath);
    }
}