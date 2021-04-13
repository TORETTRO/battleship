package JFrameUI;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends JFrame{

    public Main() {
        String filepath = "src\\bgm\\bensound-funkyelement.wav";
        PlayMusic play = new PlayMusic();
        play.playMusic(filepath);

        int width=800;
        int height=600;
        this.setTitle("BattleShip");
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        //

        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src\\image\\battleship.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pack();

        JLabel Title=new JLabel("BattleShip");
        Title.setFont(new Font("", Font.BOLD, 45));
        Title.setBounds(width/2-220/2, 80, 250, 50);
        this.add(Title);

        JButton btn1 =new JButton("1 Vs 1");
        btn1.setForeground(Color.BLACK);
        btn1.setBackground(Color.WHITE);
        btn1.setBounds(width/2-180/2, 150, 180, 50);
        this.add(btn1);
        btn1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new modeOne();
                    }
                });
                if(play.getIsplay())play.changeState();
                Main.this.dispose();
            }
        });


        JButton btn2 =new JButton("Play with LAN friends");
        btn2.setForeground(Color.BLACK);
        btn2.setBackground(Color.WHITE);
        btn2.setBounds(width/2-180/2, 250, 180, 50);
        this.add(btn2);
        btn2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new modeTwoChoose();
                    }
                });
                if(play.getIsplay())play.changeState();
                Main.this.dispose();

            }
        });


        JButton btn3 =new JButton("1 vs PC(AI)");
        btn3.setForeground(Color.BLACK);
        btn3.setBackground(Color.WHITE);
        btn3.setBounds(width/2-180/2, 350, 180, 50);
        this.add(btn3);
        btn3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new modeThree();
                    }
                });
                if(play.getIsplay())play.changeState();
                Main.this.dispose();
            }
        });
        Icon icon_silent = new ImageIcon("src\\image\\silent.png");
        Icon icon_sound = new ImageIcon("src\\image\\sound.png");
        JButton btnbgm =new JButton();
        btnbgm.setIcon(icon_sound);
        btnbgm.setForeground(Color.BLACK);
        btnbgm.setBackground(Color.WHITE);
        btnbgm.setBounds(width-200, 60, 50, 50);
        this.add(btnbgm);
        btnbgm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                play.changeState();
                if(play.getIsplay())
                {
                    btnbgm.setIcon(icon_sound);
                }
                else btnbgm.setIcon(icon_silent);
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args)throws Exception{
        new Main();
    }
}