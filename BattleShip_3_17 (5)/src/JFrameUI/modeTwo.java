package JFrameUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import BattleShip.*;
class modeTwo extends JFrame implements MouseListener {
    int gridNum=8;
    int width=1200,height=800;
    JFrame thisJfram;
    Label promptLabel;
    boolean gameStart;
    PlayMusic hitshipMusic;
    boolean Player1sturn;
    CheckerBoard checkerBoard1, checkerBoard2;
    int lx1=100,ly1=150,lx2=700,ly2=150;
    //    JButton setShipbtn=new JButton("setShip");
    JButton randombtn=new JButton("setShip");
    //    boolean p1Setup=false,p2Setup=false;
    JButton yesbtn=new JButton("Set up");
    void Repaint(){
        checkerBoard1.draw_Board();
        checkerBoard2.draw_Board();

        thisJfram.repaint();
    }
    void addButtons(CheckerBoard checkerBoard)
    {
        try {
            this.remove(randombtn);
            randombtn=new JButton("setShip");
        }catch (Exception e){}
        try {
            this.remove(yesbtn);
            yesbtn=new JButton("Yes");
        }catch (Exception e){}
        //randombtn
        randombtn.setForeground(Color.BLACK);
        randombtn.setBackground(Color.WHITE);
        if(checkerBoard==checkerBoard1)
            randombtn.setBounds(width/2-450, 600, 100, 50);
        else  randombtn.setBounds(width/2+200, 600, 100, 50);

        randombtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
//                System.out.println("addaction"+checkerBoard.title);
                RandShip(checkerBoard);
                checkerBoard.draw_Board();

                Repaint();
            }
        });
        this.add(randombtn);
        //yesbtn
        yesbtn.setForeground(Color.BLACK);
        yesbtn.setBackground(Color.WHITE);
        if(checkerBoard==checkerBoard1)
            yesbtn.setBounds(width/2-250, 600, 100, 50);
        else  yesbtn.setBounds(width/2+400, 600, 100, 50);

        yesbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(checkerBoard==checkerBoard1&&checkerBoard.getSetup()) {
                    addButtons(checkerBoard2);
                    Repaint();
                }
                else if(checkerBoard==checkerBoard2&&checkerBoard.getSetup())
                {
                    //start game
                    try {
                        thisJfram.remove(randombtn);
                        randombtn=new JButton("setShip");
                    }catch (Exception E){}
                    try {
                        thisJfram.remove(yesbtn);
                        yesbtn=new JButton("Yes");
                    }catch (Exception E){}
                    startGame();
                    Repaint();
                }

            }
        });
        this.add(yesbtn);
    }
    void drawPrompt(String msg){
//        try {
//            this.remove(promptLabel);
//        }catch (Exception e){}
        promptLabel.setText(msg);

//        this.add(promptLabel);
    }
//    void drawPrompt(String msg)
//    {
//
//    }

    void drawPrompt(CheckerBoard checkerBoard){
//        try {
//            this.remove(promptLabel);
//        }catch (Exception e){}
        promptLabel.setText(checkerBoard.getTitle()+"'s turn");
//        this.add(promptLabel);
    }

    boolean gameOver()
    {
        boolean p1lose=true,p2lose=true;
        for(int i=0;i<gridNum;i++)
        {
            for(int j=0;j<gridNum;j++)
            {
                if(checkerBoard1.getGrids()[i][j].getType()== GridType.SHIP)p1lose=false;
                if(checkerBoard2.getGrids()[i][j].getType()== GridType.SHIP)p2lose=false;
            }
        }
        return p1lose||p2lose;
    }

    void startGame(){
        checkerBoard1.setGameStar(true);
        checkerBoard2.setGameStar(true);
        gameStart=true;
        Player1sturn=true;
        this.add(promptLabel);
//        while(!gameOver())
        {
            drawPrompt(checkerBoard1);
        }
    }
    void RandShip(CheckerBoard checkerBoard)
    {
        int map[][]=new int[gridNum][gridNum];
        int x,y;
        int cnt=1;
        while(cnt<2)
        {
            int dir=(int)(Math.random()*2);
            boolean flag=true;
            x=(int)(Math.random()*gridNum);
            y=(int)(Math.random()*gridNum);
            if(dir==0)
            {
                if(x+cnt>=gridNum)continue;
                for(int i=x;i<x+cnt;i++)
                {
                    if(map[i][y]!=0)
                    {
                        flag=false;
                        break;
                    }
                }
                if(!flag)continue;
                for(int i=x;i<x+cnt;i++)map[i][y]=1;
            }
            else
            {
                if(y+cnt>=gridNum)continue;
                for(int i=y;i<y+cnt;i++)
                {
                    if(map[x][i]!=0)
                    {
                        flag=false;
                        break;
                    }
                }
                if(!flag)continue;
                for(int i=y;i<y+cnt;i++)map[x][i]=1;
            }

            cnt++;
        }
        for(int i=0;i<gridNum;i++)
        {
            for(int j=0;j<gridNum;j++)
            {
                if(map[i][j]==1)
                {
                    checkerBoard.Set_Grid_according_to_location(i,j,new Grid(GridType.SHIP));
//                    System.out.println(" ("+i+" "+j+")");
                }
                else checkerBoard.Set_Grid_according_to_location(i,j,new Grid(GridType.SEA));
            }
        }
        checkerBoard.setSetup(true);
    }
    public modeTwo(){
        this.setTitle("JFrameUI.modeTwo");
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        thisJfram=this;

        init();

        checkerBoard1=new CheckerBoard("Player1",lx1,ly1,this);
        checkerBoard1.draw_Board();
        checkerBoard2=new CheckerBoard("Player2",lx2,ly2,this);
        checkerBoard2.draw_Board();
        addButtons(checkerBoard1);

        this.addMouseListener(this);
        super.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                System.exit(1);
            }
        });

    }
    void init()
    {
        String filepath = "src\\bgm\\hitship.wav";
        hitshipMusic = new PlayMusic();
        hitshipMusic.playMusic(filepath);
        hitshipMusic.stop();
        gameStart=false;
        Player1sturn=false;
        promptLabel=new Label("");
        promptLabel.setAlignment(1);
        promptLabel.setFont(new Font("", Font.BOLD, 25));
        promptLabel.setForeground(Color.BLACK);
        promptLabel.setBackground(Color.WHITE);
        promptLabel.setBounds(width/2-400/2, 600, 400, 100);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new modeTwo();
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x=e.getX()-8;
        int y=e.getY()-32;
        System.out.println("mouse: "+x+" "+y);
        int lx,ly;
        if(Player1sturn)
        {
            lx=lx2;ly=ly2;
        }else{
            lx=lx1;ly=ly1;
        }
        if(gameStart) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (x >= lx + 50 * i && x <= lx + 50 * (i + 1) && y >= ly + 50 * j && y <= ly + 50 * (j + 1))
                    {
                        if(Player1sturn)
                        {
                            if(checkerBoard2.getGrids()[i][j].getType()== GridType.SHIP)
                            {
                                checkerBoard2.Set_Grid_according_to_location(i,j,new Grid(GridType.HIP_SHIP));
                                System.out.println("p1Hit");
                                hitshipMusic.play();
                                drawPrompt(checkerBoard1.getTitle()+"'s turn"+":Hit");
                                if(gameOver())
                                {
                                    gameStart=false;
                                    drawPrompt(checkerBoard1.getTitle()+" You Win!");
                                }
                            }
                            else if(checkerBoard2.getGrids()[i][j].getType()== GridType.SEA)
                            {
                                System.out.println("p1Miss");
                                checkerBoard2.Set_Grid_according_to_location(i,j,new Grid(GridType.HIP_SEA));
                                drawPrompt(checkerBoard2);
                                Player1sturn=!Player1sturn;
                            }
                        }
                        else
                        {
                            if(checkerBoard1.getGrids()[i][j].getType()== GridType.SHIP)
                            {
                                checkerBoard1.Set_Grid_according_to_location(i,j,new Grid(GridType.HIP_SHIP));
                                drawPrompt(checkerBoard2.getTitle()+"'s turn"+":Hit");
                                System.out.println("p2Hit");
                                hitshipMusic.play();
                                if(gameOver())
                                {
                                    gameStart=false;
                                    drawPrompt(checkerBoard2.getTitle()+" You Win!");
                                }
                            }
                            else if(checkerBoard1.getGrids()[i][j].getType()== GridType.SEA)
                            {
                                checkerBoard1.Set_Grid_according_to_location(i,j,new Grid(GridType.HIP_SEA));
                                System.out.println("p2Miss");
                                drawPrompt(checkerBoard1);
                                Player1sturn=!Player1sturn;
                            }
                        }
                        Repaint();
                    }
                }
            }
        }
        System.out.println("鼠标单击："  + ".\n");
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