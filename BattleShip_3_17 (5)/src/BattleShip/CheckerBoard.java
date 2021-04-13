package BattleShip;

import javax.swing.*;
import java.awt.*;

public class CheckerBoard {
     int gridNum = 8;
     int gridSize = 50;
     String title;
     int Location_x,Location_y;
     //是否设置完毕
     boolean Setup;
    boolean gameStar;
    JFrame jFrame;
    JLabel board[][] = new JLabel[gridNum][gridNum];
    Grid[][] grids=new Grid[gridNum][gridNum];
    public String gridstoString(){
        String res="";
//        res="gridNum:"+gridNum+"*"+"gridSize:"+gridSize+"*"+"title:"
//                +title+"*"+"Location_x:"+getLocation_x()+"*"+"Location_y:"+getLocation_y();
        for(int i=0;i<gridNum;i++)
        {
            String line="";
            if(i!=0)line+="newline ";
            for(int j=0;j<gridNum;j++)
            {
                line+=grids[i][j].getType()+" ";
            }
            res+=line;
        }
        return res;
    }

    public int getGridNum() {
        return gridNum;
    }

    public void setGridNum(int gridNum) {
        this.gridNum = gridNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLocation_x() {
        return Location_x;
    }

    public void setLocation_x(int location_x) {
        Location_x = location_x;
    }

    public int getLocation_y() {
        return Location_y;
    }

    public void setLocation_y(int location_y) {
        Location_y = location_y;
    }

    public boolean isSetup() {
        return Setup;
    }

    public boolean isGameStar() {
        return gameStar;
    }

    public void setGameStar(boolean gameStar) {
        this.gameStar = gameStar;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public JLabel[][] getBoard() {
        return board;
    }

    public void setBoard(JLabel[][] board) {
        this.board = board;
    }

    public Grid[][] getGrids() {
        return grids;
    }

    public void setGrids(Grid[][] grids) {
        this.grids = grids;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    //游戏是否开始

     public boolean getSetup(){return Setup;}
     public void setSetup(boolean b){Setup=b;}
    public CheckerBoard(String title, int Location_x, int Location_y, JFrame jFrame)
    {
        this.title=title;
        this.Location_x=Location_x;
        this.Location_y=Location_y;
        this.jFrame=jFrame;
        for (int i = 0; i < gridNum; i++)
            for (int j = 0; j < gridNum; j++) {
                grids[i][j] = new Grid();
                board[i][j] = new JLabel();
                grids[i][j].Type = GridType.SEA;

            }

        Setup=false;
        gameStar=false;
    }
    public void Set_Grid_according_to_location(int x, int y, Grid gridType)
    {
        grids[x][y]=gridType;
    }
    public void draw_Board()
    {
        //绘制网格
//        int grid = 8;

        JLabel Title = new JLabel(title);
//            jLabel.setForeground(Color.black);
        Title.setFont(new Font("", Font.BOLD, 40));
        Title.setSize(gridSize,gridSize);
        Title.setBounds(Location_x+gridNum*gridSize/2-40,Location_y-120,400,50);
        jFrame.add(Title);
        // 行
        for(int i=0;i<gridNum;i++){
            JLabel jLabel = new JLabel("   "+(char)('A'+i)+"");
//            jLabel.setForeground(Color.black);
//            jLabel.setFont(new Font("", Font.BOLD, 20));
            jLabel.setSize(gridSize,gridSize);
            jLabel.setLocation(Location_x+i*gridSize,Location_y-gridSize);
            jFrame.add(jLabel);
        }
        for(int i=0;i<gridNum;i++){

            //
            JLabel text = new JLabel("   "+(i+1)+"");
//            jLabel.setForeground(Color.black);
            text.setFont(new Font("", Font.BOLD, 20));
            text.setSize(gridSize,gridSize);
            text.setLocation(Location_x-gridSize,Location_y+i*gridSize);
            jFrame.add(text);
            for(int j=0;j<gridNum;j++){
//                board[i][j].setText();
                board[i][j].setSize(gridSize,gridSize);
                //设置每个Label的位置
                board[i][j].setLocation(Location_x+i*gridSize,Location_y+j*gridSize);
//                if(i==0&&j==0) System.out.println((Location_x+i*gridSize)+" "+(Location_y+j*gridSize));
               if( (grids[i][j].Type==GridType.SHIP&&gameStar==false)){
                    board[i][j].setBackground(Color.CYAN);
//                   System.out.println("grids: ("+i+" "+j+")");
                }
               else if(gameStar==true&&grids[i][j].Type==GridType.HIP_SHIP){
                   board[i][j].setBackground(Color.RED);
//                   System.out.println("hipship");
               }
               else if(gameStar==true&&grids[i][j].Type==GridType.HIP_SEA){
//                   System.out.println("hipsea");
                   board[i][j].setBackground(new Color(241, 129, 129));
               }
               else{
                    board[i][j].setBackground(Color.white);
                }
                board[i][j].setOpaque(true);
                board[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                jFrame.add(board[i][j]);
            }
        }
    }
}
