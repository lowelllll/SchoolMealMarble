package Marble;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BoardMain extends JFrame implements Runnable, ActionListener {
    private Frame mainFrame;
    private Label headerLabel;
    private Panel controlPanel;
    private ImageIcon Logo;
    private JLabel lb;
    
    public BoardMain() {
        prepareGUI();
    }
 
    public static void main(String[] args) {
        BoardMain awtControlDemo = new BoardMain();
        awtControlDemo.showButton();
        //Sound.BACK.loop();
       }
    
    private void prepareGUI() {
        // Frame 에 대한 셋팅
        mainFrame = new Frame("급식 마블");
        mainFrame.setBounds(200, 100, 1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        mainFrame.setLayout(new GridLayout(4, 0));
        mainFrame.setVisible(true);
        mainFrame.setResizable(true);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        // 상단 텍스트
        headerLabel = new Label();
        headerLabel.setAlignment(Label.LEFT);
        
        URL url3 =getClass().getClassLoader().getResource("logo.png");
        ImageIcon Logo = new ImageIcon(url3);
        JLabel imageLabel = new JLabel(Logo);
        
       
       
        // 하단 텍스트
     
        controlPanel = new Panel();
        controlPanel.setLayout(new FlowLayout());
        
        mainFrame.add(headerLabel);
        mainFrame.add(imageLabel);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
        
        mainFrame.setResizable(true); 
    }
    
    private void showButton() {
 
        Button btnStart = new Button("게임 시작");
        Button btnHowtoplay = new Button("게임 방법");
        Button btnCancel = new Button("게임 종료");
        

        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
               Board game=new Board();
	       		game.setVisible(true);
	       		game.setBounds(100, 200, 1300, 800);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                game.setVisible(true);
//                game.setResizable(true);
                game.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent windowEvent) {
                        System.exit(0);
                    }
                });                
            }
        });
 
        btnHowtoplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Howtoplay gamepaly = new Howtoplay();
                gamepaly.setBounds(0, 200, 600, 800);
                //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gamepaly.setVisible(true);
                gamepaly.setResizable(true);
                gamepaly.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent windowEvent) {
                    	
                    	gamepaly.dispose();
                    }
                });
            }
        });
 
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
       
        controlPanel.add(btnStart);
        controlPanel.add(btnHowtoplay);
        controlPanel.add(btnCancel);
        mainFrame.setVisible(true);
    }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub      
   }

   @Override
   public void run() {
      // TODO Auto-generated method stub      
   }
}
