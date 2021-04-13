package BattleShip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Board extends JFrame implements MouseListener {
    static int gridNum = 8;
    static int gridSize = 50;
    static JLabel board[][] = new JLabel[gridNum][gridNum];
    static Grid[][]p1=new Grid[gridNum][gridNum];
    static Grid[][]p2=new Grid[gridNum][gridNum];
    void _init()
    {
        for(int i=0;i<gridNum;i++)
            for(int j=0;j<gridNum;j++)
            {
                p1[i][j]=new Grid();
                p2[i][j]=new Grid();
                board[i][j]=new JLabel();
                p1[i][j].Type= p2[i][j].Type=GridType.SEA;

            }
//        p1[7][7].Type= BattleShip.GridType.SHIP;
//        p1[2][1].Type=p1[2][2].Type= BattleShip.GridType.SHIP;
//        p1[3][2].Type=p1[3][3].Type=p1[3][4].Type= BattleShip.GridType.SHIP;
//        p1[6][0].Type=p1[6][1].Type=p1[6][2].Type= p1[6][3].Type= BattleShip.GridType.SHIP;
//        p1[5][0].Type=p1[5][1].Type=p1[5][2].Type= p1[5][3].Type= p1[5][4].Type=BattleShip.GridType.SHIP;
//
//        p2[0][0].Type= BattleShip.GridType.SHIP;
//        p2[7][1].Type=p2[7][2].Type= BattleShip.GridType.SHIP;
//        p2[3][2].Type=p2[3][3].Type=p2[3][4].Type= BattleShip.GridType.SHIP;
//        p2[6][0].Type=p2[6][1].Type=p2[6][2].Type= p2[6][3].Type= BattleShip.GridType.SHIP;
//        p2[0][5].Type=p2[1][5].Type=p2[2][5].Type= p2[3][5].Type= p2[4][5].Type=BattleShip.GridType.SHIP;
    }

    public Board(){
        this.setTitle("BattleShip.Board");
        this.setSize(1200,800);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        _init();
//        BattleShip.CheckerBoard checkerBoard1=new BattleShip.CheckerBoard();
//        checkerBoard1.draw_Board("p1",100,150,this);
//        BattleShip.CheckerBoard checkerBoard2=new BattleShip.CheckerBoard();
//        checkerBoard2.draw_Board("p1",700,150,this);
        //        draw_Board("Play1",100,150,p1);
//        draw_Board("Play2",700,150,p2);
        this.addMouseListener(this);
        super.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                System.exit(1);
            }
        });

    }
    void draw_Board(String title, int Location_x, int Location_y, Grid[][] plist)
    {
        //绘制网格
//        int grid = 8;

        JLabel Title = new JLabel(title);
//            jLabel.setForeground(Color.black);
        Title.setFont(new Font("", Font.BOLD, 40));
        Title.setSize(gridSize,gridSize);
        Title.setBounds(Location_x+gridNum*gridSize/2-40,Location_y-120,400,50);
        this.add(Title);
        // 行
        for(int i=0;i<gridNum;i++){
            JLabel jLabel = new JLabel("   "+(char)('A'+i)+"");
//            jLabel.setForeground(Color.black);
            jLabel.setFont(new Font("", Font.BOLD, 20));
            jLabel.setSize(gridSize,gridSize);
            jLabel.setLocation(Location_x+i*gridSize,Location_y-50);
            this.add(jLabel);
        }
        for(int i=0;i<gridNum;i++){

            //
            JLabel text = new JLabel("   "+(i+1)+"");
//            jLabel.setForeground(Color.black);
            text.setFont(new Font("", Font.BOLD, 20));
            text.setSize(gridSize,gridSize);
            text.setLocation(Location_x-50,Location_y+i*gridSize);
            this.add(text);
            for(int j=0;j<gridNum;j++){

                board[i][j].setSize(gridSize,gridSize);
                //设置每个Label的位置
                board[i][j].setLocation(Location_x+i*gridSize,Location_y+j*gridSize);
                System.out.print("( "+j+": "+(Location_x+i*gridSize)+" "+(Location_y+j*gridSize)+" )");
                if( plist[i][j].Type==GridType.SHIP){
                    board[i][j].setBackground(Color.CYAN);
                }else{
                    board[i][j].setBackground(Color.white);
                }
                board[i][j].setOpaque(true);
                board[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                this.add(board[i][j]);
            }
            System.out.println("\n-----");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Board();
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX();
        int y=e.getY();
        System.out.println("mouse: "+x+" "+y);
        int lx=100-9,ly=150-30;
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if(x>=lx+gridSize*i&&x<=lx+gridSize*(i+1)&&y>=ly+gridSize*j&&y<=ly+gridSize*(j+1))
                {
                    System.out.println(i+" "+j);
                }
            }
        }
        System.out.println("click："  + ".\n");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}