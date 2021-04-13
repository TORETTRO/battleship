package JFrameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class modeTwoChoose extends JFrame {
    Server server;
    Client client;
    public modeTwoChoose(){
//        new JFrameUI.Server();

        int width=400,height=300;
        JButton serverbtn,clientbtn;
//        this.setTitle("Board");
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        JTextArea  title =new JTextArea("Make sure that the player chooses\r\n again after the player chooses");
        title.setLineWrap(true);
        title.setEditable(false);
        title.setBackground(new Color(238,238,238));
        title.setFont(new Font("", Font.BOLD, 15));
        title.setForeground(Color.BLACK);
        title.setBounds(width/2-300/2+30, 50, 300, 100);
        this.add(title);


        serverbtn =new JButton("Player1");
        serverbtn.setForeground(Color.BLACK);
        serverbtn.setBackground(Color.WHITE);
        serverbtn.setBounds(width/2-100-100/2, 150, 100, 50);
        this.add(serverbtn);
        serverbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Thread t = new Thread(new Runnable(){
                    public void run(){
                        server=new Server();
                    }});
                t.start();
            }


        });

        clientbtn =new JButton("Player2");
        clientbtn.setForeground(Color.BLACK);
        clientbtn.setBackground(Color.WHITE);
        clientbtn.setBounds(width/2+100-100/2, 150, 100, 50);
        this.add(clientbtn);
        clientbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Thread t = new Thread(new Runnable(){
                    public void run(){
                        client=new Client();
                    }});
                t.start();

            }
        });
        this.setVisible(true);
//        new JFrameUI.Server();
    }

    public static void main(String[] args) {
//        new JFrameUI.Server();
//        new JFrameUI.Client();
        new modeTwoChoose();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new JFrameUI.Server();
//                new JFrameUI.modeTwoChoose();
            }
        });
    }
}
