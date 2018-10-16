package Marble;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import Select.MyitemListener;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

class Mybutton extends JButton {
	Mybutton(String s) {
		super(s);
	}

	int index = 0;


}
class diceG extends Thread {
	ImageIcon[] img;
	JLabel diceLabel;
	boolean flag=false;
	int num;
	diceG(JLabel diceLabel, ImageIcon[] img,int num) {
		this.img = img;
		this.diceLabel = diceLabel;	
		this.num=num;
	}

	public void pinish() {
		flag=true;
	}

	public void run() {
			try {
				diceLabel.setIcon(img[6]);
				sleep(500);
				diceLabel.setIcon(img[num]);
				
			} catch (InterruptedException e) {
				return;
			}
		}
	

}

//ㅋㅋㅋ
@SuppressWarnings("unused")
public class Board extends JFrame {
	
	JButton diceButton;
	JPanel contentPane;
	JTextField userinfo_name;
	JTextField userinfo_Money;
	JTextField userinfo_TotalMoney;
	JTextField cominfo_name;
	JTextField cominfo_Money;
	JTextField cominfo_TotalMoney;
	JTextField diceLabel;
	JTextArea infoField;
	JCheckBox[] c=new JCheckBox[28];
	Mybutton[] b = new Mybutton[28];
	User user = new User(this, 944, 635);
	Com com = new Com(this, 924, 635);
	Dice dice = new Dice();
	Prompt pr = new Prompt();
	Place p[] = Place.placearr();
	JButton mg;
	JLabel checkPay;
	JLabel checkForsale;
	FistTurn turn = new FistTurn();
	int count = 60;
	private JLabel tuurn;
	int sum=0;
	Board board;
	public Board() {
		sound.BACK.loop();
		setTitle("\uAE09. \uC2DD. \uB9C8. \uBE14");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 1300, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// user의 정보를 담은 textfield
		userinfo_name = new JTextField("user");
		userinfo_name.setBackground(Color.WHITE);
		userinfo_name.setBounds(1133, 583, 116, 21);
		contentPane.add(userinfo_name);

		userinfo_Money = new JTextField("2000000");
		userinfo_Money.setBounds(1133, 609, 116, 21);
		contentPane.add(userinfo_Money);

		userinfo_TotalMoney = new JTextField("0");
		userinfo_TotalMoney.setBounds(1133, 640, 116, 21);
		contentPane.add(userinfo_TotalMoney);

		// com의 정보를 담은 textfield
		cominfo_name = new JTextField("Com");
		cominfo_name.setBackground(Color.WHITE);
		cominfo_name.setBounds(1133, 245, 116, 21);
		contentPane.add(cominfo_name);

		cominfo_Money = new JTextField("2000000");
		cominfo_Money.setBounds(1133, 275, 116, 21);
		contentPane.add(cominfo_Money);

		cominfo_TotalMoney = new JTextField("0");

		cominfo_TotalMoney.setBounds(1133, 305, 116, 21);
		contentPane.add(cominfo_TotalMoney);

		diceLabel = new JTextField("");
		diceLabel.setBounds(549, 370, 85, 60);
		contentPane.add(diceLabel);
		
		checkPay = new JLabel("");
        checkPay.setBounds(700, 430, 400, 15);
        contentPane.add(checkPay);//통행료를 표시해주는 라벨
        
        checkForsale = new JLabel("");
        checkForsale.setBounds(700, 460, 400, 15);
        contentPane.add(checkForsale);
        
        contentPane.add(user.userFace);//유저의 말 추가
        contentPane.add(com.comFace);//컴퓨터의 말 추가
        
		mg = new JButton("폐교");//error
		mg.setBounds(698, 488, 100, 35);
		mg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되면
				int comPay = 0 ;
				for(int i=0;i<p.length;i++){	
					if(c[i].isSelected()){
							
									if(p[i].owner==user.name){
										p[i].owner = null;
										System.out.println("유저"+p[i].name+"지역 팜");
										removeOwnerBoard(i);
										comPay += p[i].build;
									}
						}
					}
				sum=0;
				user.Money+=comPay;
				user.TotalMoney-=comPay;
				user.Money-=p[user.location].pay;
				com.Money+=p[user.location].pay;
				setCom(com.Money, com.TotalMoney);
				setUser(user.Money, user.TotalMoney);
				for(int i=0;i<c.length;i++)
					c[i].setVisible(false);
				
				diceButton.setEnabled(true);
				mg.setEnabled(false);
				checkPay.setText("");
				checkForsale.setText("");
			}
		});
		
		//세계여행
		
		mg.setEnabled(false);
		
		contentPane.add(mg);
		
		 diceButton = new JButton("");
		diceButton.setBounds(545, 468, 100, 100);
		 diceButton.setIcon(new ImageIcon("img/dicebutton.png"));
		 
		 diceButton.setContentAreaFilled(false);
		 diceButton.setOpaque(false);
		 diceButton.setBorderPainted(false);
		contentPane.add(diceButton);
		diceButton.addActionListener(new dicelistener());
			

		JLabel lblNewLabel_2 = new JLabel("\uC8FC\uC0AC\uC704 \uC22B\uC790");
		lblNewLabel_2.setBounds(552, 326, 100, 21);
		contentPane.add(lblNewLabel_2);

		ButtonList();
		setUserName(pr.NamePrompt(user));
		Cbox();
		pr.TurnPrompt();
		turn.Turn();

		setVisible(false);
		setBounds(200, 100, 1300, 800);
	}
	//////////////////////////////////////////////////////

	public void move(int num) {
		user.move(num);
	}

	public void setUser(int Money, int TotalMoney) {
		userinfo_Money.setText("" + Money + "");
		userinfo_TotalMoney.setText("" + TotalMoney + "");
	}

	public void setUserName(String name) {
		userinfo_name.setText(name);
	}

	
	public void setCom(int Money, int TotalMoney) {
		cominfo_Money.setText("" + Money + "");
		cominfo_TotalMoney.setText("" + TotalMoney + "");
	}

	public void setComName(String name) {
		cominfo_name.setText(name);
	}

	public void setdiceLabel(int num) {
		diceLabel.setText("" + num + "");
	}

	private void setFocusTraversalPolicy(Component[] components) {
	}


	void ButtonList() {
		// index작업해주어야함

		

		b[0] = new Mybutton(p[0].name);
		b[0].setBounds(894, 605, 100, 75);
		contentPane.add(b[0]);
		b[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(0);
				}
			});
		
		b[1] = new Mybutton(p[1].name);
		b[1].setBounds(797, 620, 85, 60);
		contentPane.add(b[1]);
		b[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(1);
				}
			});
		
		b[2] = new Mybutton(p[2].name);
		b[2].setBounds(700, 620, 85, 60);
		contentPane.add(b[2]);
		b[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(2);
				}
			});
		
		b[3] = new Mybutton(p[3].name);
		b[3].setBounds(603, 620, 85, 60);
		contentPane.add(b[3]);
		b[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(3);
				}
			});
		

		b[4] = new Mybutton(p[4].name);
		b[4].setBounds(506, 620, 85, 60);
		contentPane.add(b[4]);
		b[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(4);
				}
			});
		

		b[5] = new Mybutton(p[5].name);
		b[5].setBounds(409, 620, 85, 60);
		contentPane.add(b[5]);
		b[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(5);
				}
			});
		

		b[6] = new Mybutton(p[6].name);
		b[6].setBounds(312, 620, 85, 60);
		contentPane.add(b[6]);
		b[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(6);
				}
			});
		
		b[7] = new Mybutton(p[7].name);
		b[7].setBounds(200, 605, 100, 75);
		contentPane.add(b[7]);
		b[7].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(7);
				}
			});
		
		b[8] = new Mybutton(p[8].name);
		b[8].setBounds(200, 535, 85, 60);
		contentPane.add(b[8]);
		b[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(8);
				}
			});
		
		b[9] = new Mybutton(p[9].name);
		b[9].setBounds(200, 465, 85, 60);
		contentPane.add(b[9]);
		b[9].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(9);
				}
			});
		
		b[10] = new Mybutton(p[10].name);
		b[10].setBounds(200, 395, 85, 60);
		contentPane.add(b[10]);
		b[10].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(10);
				}
			});
		

		b[11] = new Mybutton(p[11].name);
		b[11].setBounds(200, 325, 85, 60);
		contentPane.add(b[11]);
		b[11].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(11);
				}
			});

		b[12] = new Mybutton(p[12].name);
		b[12].setBounds(200, 255, 85, 60);
		contentPane.add(b[12]);
		b[12].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(12);
				}
			});

		b[13] = new Mybutton(p[13].name);
		b[13].setBounds(200, 185, 85, 60);
		contentPane.add(b[13]);
		b[13].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(13);
				}
			});

		b[14] = new Mybutton(p[14].name);
		b[14].setBounds(200, 100, 100, 75);
		contentPane.add(b[14]);
		b[14].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(14);
				}
			});

		b[15] = new Mybutton(p[15].name);
		b[15].setBounds(312, 101, 85, 60);
		contentPane.add(b[15]);
		b[15].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(15);
				}
			});

		b[16] = new Mybutton(p[16].name);
		b[16].setBounds(409, 101, 85, 60);
		contentPane.add(b[16]);
		b[16].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(16);
				}
			});

		b[17] = new Mybutton(p[17].name);
		b[17].setBounds(506, 101, 85, 60);
		contentPane.add(b[17]);
		b[17].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(17);
				}
			});

		b[18] = new Mybutton(p[18].name);
		b[18].setBounds(603, 100, 85, 60);
		contentPane.add(b[18]);
		b[18].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(18);
				}
			});

		b[19] = new Mybutton(p[19].name);
		b[19].setBounds(700, 100, 85, 60);
		contentPane.add(b[19]);
		b[19].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(19);
				}
			});

		b[20] = new Mybutton(p[20].name);
		b[20].setBounds(797, 101, 85, 60);
		contentPane.add(b[20]);
		b[20].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(20);
				}
			});

		b[21] = new Mybutton(p[21].name);
		b[21].setBounds(894, 100, 100, 75);
		contentPane.add(b[21]);
		b[21].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(21);
				}
			});

		b[22] = new Mybutton(p[22].name);
		b[22].setBounds(909, 185, 85, 60);
		contentPane.add(b[22]);
		b[22].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(22);
				}
			});

		b[23] = new Mybutton(p[23].name);
		b[23].setBounds(909, 255, 85, 60);
		contentPane.add(b[23]);
		b[23].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(23);
				}
			});

		b[24] = new Mybutton(p[24].name);
		b[24].setBounds(909, 325, 85, 60);
		contentPane.add(b[24]);
		b[24].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(24);
				}
			});

		b[25] = new Mybutton(p[25].name);
		b[25].setBounds(909, 395, 85, 60);
		contentPane.add(b[25]);
		b[25].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(25);
				}
			});

		b[26] = new Mybutton(p[26].name);
		b[26].setBounds(909, 465, 85, 60);
		contentPane.add(b[26]);
		b[26].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(26);
				}
			});

		b[27] = new Mybutton(p[27].name);
		b[27].setBounds(909, 535, 85, 60);
		contentPane.add(b[27]);
		b[27].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {//매각버튼을 누르게 되
				Trip(27);
				}
			});

		for (int i = 0; i < 28; i++){
			b[i].setEnabled(false);
			b[i].setBackground(Color.white);
			b[i].index = i;
			b[i].setFont(new Font("Gothic",Font.CENTER_BASELINE,10));
		}
		

		// 컴퓨터 정보를 담은 라벨
		JPanel panel = new JPanel();
		ImageIcon cominfo = new ImageIcon("img/coninfoP.png");
		JLabel imgLabelcom = new JLabel(cominfo);
		imgLabelcom.setBounds(1020, 111, 252, 221);
		contentPane.add(imgLabelcom);
		imgLabelcom.setLayout(null);

		// 유저의 정보를 담은 라벨
		JPanel panel_1 = new JPanel();
		ImageIcon userinfo = new ImageIcon("img/useinfoP.png");
		JLabel imgLabel = new JLabel(userinfo);
		imgLabel.setBounds(1020, 445, 252, 221);
		contentPane.add(imgLabel);
		imgLabel.setLayout(null);
		
		tuurn = new JLabel("\uB0A8\uC740 \uD134:60");
		tuurn.setBounds(555, 248, 100, 15);
		contentPane.add(tuurn);
		
		//땅의 정보
		//유저가 움직일때만 땅의 정보가 바뀜
		//유저가 땅을 사게되면 땅의 정보가 바뀜 owner
		
		infoField = new JTextArea("");
		infoField.setBounds(330, 245, 130,80);
		contentPane.add(infoField);
		infoField.setColumns(10);
		infoField.setEditable(false); 


		
		// 유저의 말
	}
	
public void Cbox(){
		
		c[0]=new JCheckBox();
		
		
		c[7]=new JCheckBox();
		
		
		c[14]=new JCheckBox();
		
		
		c[21]=new JCheckBox();
		
	
		
		c[1]=new JCheckBox();
		c[1].setBounds(828,580,20,20);
		contentPane.add(c[1]);
		//c[1].addItemListener(new MyitemListener());
		
		c[2]=new JCheckBox();
		c[2].setBounds(733,580,20,20);
		contentPane.add(c[2]);
		
		c[3]=new JCheckBox();
		c[3].setBounds(633,580,20,20);
		contentPane.add(c[3]);
		
		c[4]=new JCheckBox();
		c[4].setBounds(533,580,20,20);
		contentPane.add(c[4]);
		
		c[5]=new JCheckBox();
		c[5].setBounds(439,580,20,20);
		contentPane.add(c[5]);
		
		c[6]=new JCheckBox();
		c[6].setBounds(342,580,20,20);
		contentPane.add(c[6]);
		
		
		
		c[8]=new JCheckBox();
		c[8].setBounds(300,545,20,20);
		contentPane.add(c[8]);
		
		c[9]=new JCheckBox();
		c[9].setBounds(300,480,20,20);
		contentPane.add(c[9]);
		
		c[10]=new JCheckBox();
		c[10].setBounds(300,410,20,20);
		contentPane.add(c[10]);
		
		c[11]=new JCheckBox();
		c[11].setBounds(300,340,20,20);
		contentPane.add(c[11]);
		
		c[12]=new JCheckBox();
		c[12].setBounds(300,270,20,20);
		contentPane.add(c[12]);
		
		c[13]=new JCheckBox();
		c[13].setBounds(300,200,20,20);
		contentPane.add(c[13]);
		
		
		
		c[15]=new JCheckBox();
		c[15].setBounds(342,180,20,20);
		contentPane.add(c[15]);
		
		c[16]=new JCheckBox();
		c[16].setBounds(439,180,20,20);
		contentPane.add(c[16]);
		
		c[17]=new JCheckBox();
		c[17].setBounds(533,180,20,20);
		contentPane.add(c[17]);
		
		c[18]=new JCheckBox();
		c[18].setBounds(633,180,20,20);
		contentPane.add(c[18]);
		
		c[19]=new JCheckBox();
		c[19].setBounds(733,180,20,20);
		contentPane.add(c[19]);
		
		c[20]=new JCheckBox();
		c[20].setBounds(828,180,20,20);
		contentPane.add(c[20]);
		
		
		
		c[22]=new JCheckBox();
		c[22].setBounds(870,200,20,20);
		contentPane.add(c[22]);
		
		c[23]=new JCheckBox();
		c[23].setBounds(870,270,20,20);
		contentPane.add(c[23]);
		
		c[24]=new JCheckBox();
		c[24].setBounds(870,340,20,20);
		contentPane.add(c[24]);
		
		c[25]=new JCheckBox();
		c[25].setBounds(870,410,20,20);
		contentPane.add(c[25]);
		
		c[26]=new JCheckBox();
		c[26].setBounds(870,480,20,20);
		contentPane.add(c[26]);
		
		c[27]=new JCheckBox();
		c[27].setBounds(870,545,20,20);
		contentPane.add(c[27]);
		
		for(int i=0;i<c.length;i++){
			c[i].setVisible(false);
			c[i].addItemListener(new MyitemListener());
		}
		
		
		
	}
		class MyitemListener implements ItemListener{
		
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
				int selected =1;
				if(e.getStateChange()==ItemEvent.SELECTED)
					selected =1;//셀렉트를 하면
				else
					selected =-1;//셀렉트를 풀면
		//		if(e.getItem()==fruits[0])
		//			sum = sum+selected*100;
		//		else if(e.getItem()==fruits[1])
		//			sum = sum+selected*500;
		//		else 
		//			sum = sum+selected*2000;
				 
				if(e.getItem()==c[0])
					sum = sum+selected*p[0].build;
				else if(e.getItem()==c[1])
					sum = sum+selected*p[1].build;
				else if(e.getItem()==c[2])
					sum = sum+selected*p[2].build;
				else if(e.getItem()==c[3])
					sum = sum+selected*p[3].build;
				else if(e.getItem()==c[4])
					sum = sum+selected*p[4].build;
				else if(e.getItem()==c[5])
					sum = sum+selected*p[5].build;
				else if(e.getItem()==c[6])
					sum = sum+selected*p[6].build;
				else if(e.getItem()==c[7])
					sum = sum+selected*p[7].build;
				else if(e.getItem()==c[8])
					sum = sum+selected*p[8].build;
				else if(e.getItem()==c[9])
					sum = sum+selected*p[9].build;
				else if(e.getItem()==c[10])
					sum = sum+selected*p[10].build;
				else if(e.getItem()==c[11])
					sum = sum+selected*p[11].build;
				else if(e.getItem()==c[12])
					sum = sum+selected*p[12].build;
				else if(e.getItem()==c[13])
					sum = sum+selected*p[13].build;
				else if(e.getItem()==c[14])
					sum = sum+selected*p[14].build;
				else if(e.getItem()==c[15])
					sum = sum+selected*p[15].build;
				else if(e.getItem()==c[15])
					sum = sum+selected*p[15].build;
				else if(e.getItem()==c[16])
					sum = sum+selected*p[16].build;
				else if(e.getItem()==c[17])
					sum = sum+selected*p[17].build;
				else if(e.getItem()==c[18])
					sum = sum+selected*p[18].build;
				else if(e.getItem()==c[19])
					sum = sum+selected*p[19].build;
				else if(e.getItem()==c[20])
					sum = sum+selected*p[20].build;
				else if(e.getItem()==c[21])
					sum = sum+selected*p[21].build;
				else if(e.getItem()==c[22])
					sum = sum+selected*p[22].build;
				else if(e.getItem()==c[23])
					sum = sum+selected*p[23].build;
				else if(e.getItem()==c[24])
					sum = sum+selected*p[24].build;
				else if(e.getItem()==c[25])
					sum = sum+selected*p[25].build;
				else if(e.getItem()==c[26])
					sum = sum+selected*p[26].build;
				else if(e.getItem()==c[27])
					sum = sum+selected*p[27].build;
				if (sum >= p[user.location].pay)
					mg.setEnabled(true);
				else
					mg.setEnabled(false);
				
				checkForsale.setText("선택한 학교 폐교시 받는 금액"+sum+"원 입니다.");
			}
			
		}

	void setOwnerBoard(int location){
		//b[location].setIcon(new ImageIcon("F:\\JAVA\\project_marble\\SchoolMealMarble2\\img\\userinfo.jpg"));
		b[location].setBackground(Color.PINK);
		repaint();
	}
	void setOwnerBoardCom(int location){
		b[location].setBackground(new Color(26, 188, 156));
		repaint();
	}
	void removeOwnerBoard(int location){
		b[location].setBackground(Color.WHITE);
		repaint();
	}
	void removeOwnerBoardCom(int location){
		b[location].setBackground(Color.WHITE);
		repaint();
	}
	void Alived(User user){
		user.IslandCount =2;
		pr.IsrandPrompt();
	}
	void Alived(Com com){
		com.IslandCount =2;
		pr.comIsrandPrompt();
	}
	void Market(User user){
		pr.marketPrompt();
		user.Money += 100000;
		//user.TotalMoney += 100000;
		setUser(user.Money,user.TotalMoney);
	}
	void Market(Com com){
		com.Money += 100000;
		//com.TotalMoney += 100000;
		setUser(com.Money,com.TotalMoney);
		
	}
	void setInfoField(User user){
		
		if(p[user.location].build!=0){
			if(p[user.location].owner==null){
				infoField.setText("  현재 지역:"+p[user.location].name+"\n 건설비:"
					+p[user.location].build+"원 \n"+"육성회비:"+p[user.location].pay +"원 \n 학교 주인: 없음");
			}else{
			infoField.setText("  현재 지역:"+p[user.location].name+"\n 건설비:"
					+p[user.location].build+"원 \n"+"육성회비:"+p[user.location].pay +"원 \n 학교 주인: "+p[user.location].owner);
			}
		}else{
			infoField.setText(" 현재 지역:"+p[user.location].name);
		}
		
			
	}
/*	void forsale(Vector v){
		for(int i=0; i<v.size();i++){
			user.Money += p[(int) v.get(i)].build;
			user.TotalMoney -= p[(int) v.get(i)].build;
		}
		setUser(user.Money,user.TotalMoney);
		
	} 매각 망한 코드
*/
		void Trip(int index){
			System.out.println("세계여행 이동");
			
			 if(user.location == index)//같으면 이동 x
			{
				 
				pr.NoTripPlace();
				for (int i = 0; i < 28; i++){
					b[i].setEnabled(false);
				}
				
			}else if (user.location < index )
			{
				user.move(index - user.location);
				OneTurn();
				for (int i = 0; i < 28; i++){
					b[i].setEnabled(false); 
				}//이동후 버튼 비활성
			
			}else if(user.location > index)
			{
				user.move(28 - user.location + index);
				OneTurn();
				for (int i = 0; i < 28; i++){
					b[i].setEnabled(false);
				}//이동후 버튼 비활성
			}
			
			 
	
	}	
		void OneTurn(){
			GAMETURN2:
				setInfoField(user);
				if(user.location==com.location){//내가 뺏김
					user.Steal(user, com);//돈을 빼앗김
				}
				
				if(p[user.location].build==0){
					//특수블록
					if(user.location == 7){//무인도라면
						Alived(user);//카운트를 2로 초기화.
					}
					if(user.location == 14){//매점이용권 지급
						Market(user);
					}
					
				}else{
					
					setInfoField(user);
					if(p[user.location].owner!=null&&p[user.location].owner!=user.name){//땅의 주인이 내가 아니라면 
						pr.PayPrompt(p[user.location].pay);//통행료를 내야한다
						if(p[user.location].pay<user.Money){
							user.Money -= p[user.location].pay;
							//user.TotalMoney -=p[user.location].pay;
							com.Money += p[user.location].pay;//통행료를 받음
							//com.TotalMoney +=p[user.location].pay;//통행료를 받음
							setUser(user.Money,user.TotalMoney);
							setCom(com.Money,com.TotalMoney);
						}else{
							if(p[user.location].pay>user.TotalMoney){//무조건 total머니보다 육성회비가 크면
									//파산
								pr.userLose();
								user.TotalMoney =0;
								pr.winner(user, com);
								System.exit(0);
							}else{
								if(pr.forSalePrompt()==0){
									/*Forsale frame = new Forsale();
									frame.setVisible(true);
									frame.Sale(user, p, p[user.location].pay);
									매각 망한 코드
									*/
									checkPay.setText("육성회비는"+p[user.location].pay+"원 입니다.");
									diceButton.setEnabled(false);
									mg.setEnabled(true);
									for(int i=0;i<c.length;i++){
										if(p[i].owner==user.name)
											c[i].setVisible(true);
									}
									
								}else{//파산
									pr.userLose();
									user.TotalMoney =0;
									pr.winner(user, com);
									System.exit(0);
								}
							}
						}
					}else if(p[user.location].owner==null){//땅의주인이 업는경우
						if(pr.buildPrompt(p[user.location])==0){//건설을 ok할때
							if(p[user.location].build<user.Money){
									p[user.location].owner = user.name;
									user.Money -= p[user.location].build;//현재가진돈은 - 건설비
									//user.TotalMoney -= p[user.location].build;// 총재산은 + 건설비
									user.TotalMoney += p[user.location].build;
									pr.successBuild(p[user.location]);
									setUser(user.Money,user.TotalMoney);
									setOwnerBoard(user.location);
							}else{
								pr.noBuildPrompt();
								turn.comTurn = true;
								//건설비 부족
							}
							//지역의 색깔을 user색으로 변경한다.
						}else{
							turn.comTurn = true;
						}
						
					}
				}
				
		}
		class dicelistener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					diceButton.setIcon(new ImageIcon("img/pdicebutton.png"));
					
					
					count--;
					GAMETURN:
					if (turn.comTurn) {
						/*com턴*/
						if(com.location==7){
							if(com.IslandCount!=0){//카운트가 0이아니라면
								com.IslandCount--;//카운트를 낮추고
								pr.IsrandCountPrompt(com.IslandCount,com.name);
								turn.comTurn = false;
								break GAMETURN;//턴을 나감.
							}else{
								pr.DicePrompt();
								int cnum = dice.diceRun(com);
								System.out.println("컴퓨터주사위 숫자:" + cnum);
								com.move(cnum);
								setdiceLabel(cnum);
								//지역확인 시작
								if(user.location==com.location){//컴퓨터가 뺏김
									com.Steal(user, com);//컴퓨터가 빼앗김
								}
								if(p[com.location].build==0){
									if(com.location==7){//무인도 도착시
										Alived(com);
									}
									if(com.location==14){//매점이용권 지급
										Market(com);
									}
									if(com.location==21){
										break GAMETURN;
									}
									
								}else{
									if(p[com.location].owner!=null&&p[com.location].owner!=com.name){//땅의 주인이 컴퓨터가 아니라면 
										//통행료를 내야한다 컴픀터가 당신의 땅을 밟았습니다!
										if(p[com.location].pay<com.Money){//현재 가진돈이 통행료보다 많을때
											pr.comPayPrompt(p[com.location]);
											com.Money -= p[com.location].pay;
											//com.TotalMoney -=p[com.location].pay;
											user.Money += p[com.location].pay;//user가통행료를 받음
											//user.TotalMoney +=p[com.location].pay;//user가통행료를 받음
											setUser(user.Money,user.TotalMoney);
											setCom(com.Money,com.TotalMoney);
										}else{
											if(p[com.location].pay<com.TotalMoney){//컴퓨터의 총재산이 통행료보다 클때
												/*컴퓨터의 매각*/
												pr.comPayPrompt(p[com.location]);
												int comPay = 0 ;//매각을 한 후 매각한 건물들의 가격을 더해 저장하는 변수 pay와 비교함
												//통행료보다 같거나 많아질때까지
												for(int i=0; i<28; i++){
													if(comPay <= p[com.location].pay){
														if(p[i].owner=="com"){//컴퓨터땅을 팜.
															p[i].owner = null;
															System.out.println("컴퓨터"+p[i].name+"지역 팜");
															removeOwnerBoardCom(i);
															comPay += p[i].build;//comPay에 매각한 후 돌려받을 돈들을 다 더함
														}
													}
												}
												
												com.Money+=comPay;
												com.TotalMoney-=comPay;
												com.Money-=p[com.location].pay;
												user.Money+=p[com.location].pay;
												//user.TotalMoney+=p[com.location].pay;
												setCom(com.Money, com.TotalMoney);
												setUser(user.Money, user.TotalMoney);
												pr.comForsale();
												
											}else{
												pr.comLose();
												System.exit(0);
											}
										}
									}else if(p[com.location].owner==null){//땅의주인이 업는경우
										if(p[com.location].build+400000<=com.Money){//건설을 ok할때 지금돈이 건설비 + 40만원보다 같거나 많을때
											//땅을산다
											p[com.location].owner=com.name;
											com.Money-=p[com.location].build;
											//com.TotalMoney-=p[com.location].build;;
											com.TotalMoney +=p[com.location].build;
											setCom(com.Money,com.TotalMoney);
											setOwnerBoardCom(com.location);//지역의 색깔을 com색으로 변경한다.
											
											
										}else{
											//건설비 부족
											turn.comTurn = false;
											break GAMETURN;
										}
										
									}
								}
							}
							turn.comTurn = false;
							break GAMETURN;
						}
						
						pr.DicePrompt();
						int cnum = dice.diceRun(com);
						System.out.println("컴퓨터주사위 숫자:" + cnum);
						com.move(cnum);
						setdiceLabel(cnum);
						//지역확인 시작
						if(user.location==com.location){//컴퓨터가 뺏김
							com.Steal(user, com);//컴퓨터가 빼앗김
						}
						if(p[com.location].build==0){
							if(com.location==7){//무인도 도착시
								Alived(com);
							}
							if(com.location==14){//매점이용권 지급
								Market(com);
							}
							if(com.location==21){
								break GAMETURN;
							}
							
						}else{
							if(p[com.location].owner!=null&&p[com.location].owner!=com.name){//땅의 주인이 컴퓨터가 아니라면 
								//통행료를 내야한다 컴픀터가 당신의 땅을 밟았습니다!
								if(p[com.location].pay<com.Money){//현재 가진돈이 통행료보다 많을때
									pr.comPayPrompt(p[com.location]);
									com.Money -= p[com.location].pay;
									//com.TotalMoney -=p[com.location].pay;
									user.Money += p[com.location].pay;//user가통행료를 받음
									//user.TotalMoney +=p[com.location].pay;//user가통행료를 받음
									setUser(user.Money,user.TotalMoney);
									setCom(com.Money,com.TotalMoney);
									
								}else{
									if(p[com.location].pay<com.TotalMoney){//컴퓨터의 총재산이 통행료보다 클때
										/*컴퓨터의 매각*/
										pr.comPayPrompt(p[com.location]);
										int comPay = 0 ;//매각을 한 후 매각한 건물들의 가격을 더해 저장하는 변수 pay와 비교함 
										//통행료보다 같거나 많아질때까지
										for(int i=0; i<28; i++){
											if(comPay <= p[com.location].pay){
												if(p[i].owner=="com"){//컴퓨터땅을 팜.
													p[i].owner = null;
													System.out.println("컴퓨터"+p[i].name+"지역 팜");
													removeOwnerBoardCom(i);
													comPay += p[i].build;//comPay에 매각한 후 돌려받을 돈들을 다 더함
												}
											}
										}
										
										com.Money+=comPay;
										com.TotalMoney-=comPay;
										com.Money-=p[com.location].pay;
										user.Money+=p[com.location].pay;
										//user.TotalMoney+=p[com.location].pay;
										setCom(com.Money, com.TotalMoney);
										setUser(user.Money, user.TotalMoney);
										pr.comForsale();
										
									}else{
										pr.comLose();
										System.exit(0);
									}
								}
							}else if(p[com.location].owner==null){//땅의주인이 업는경우
								if(p[com.location].build+400000<=com.Money){//건설을 ok할때 지금돈이 건설비 + 40만원보다 같거나 많을때
									//땅을산다
									p[com.location].owner=com.name;
									com.Money-=p[com.location].build;
									//com.TotalMoney-=p[com.location].build;;
									com.TotalMoney +=p[com.location].build;
									setCom(com.Money,com.TotalMoney);
									setOwnerBoardCom(com.location);//지역의 색깔을 com색으로 변경한다.
									
									
								}else{
									//건설비 부족
									turn.comTurn = false;
									break GAMETURN;
								}
								
							}
						}
						
						
						turn.comTurn = false;
					} else {
						setInfoField(user);
						/*user턴*/
						if(user.location==7){//무인도
							if(user.IslandCount!=0){//카운트가 0이아니라면
								user.IslandCount--;//카운트를 낮추고
								pr.IsrandCountPrompt(user.IslandCount,user.name);
								turn.comTurn = true;
								
								break GAMETURN;//턴을 나감.
							}else{//카운트가 0이라면 아래 모든 문장을 수행하고 break
								pr.IsrandEscapePrompt();
								int unum = dice.diceRun(user);
								user.move(unum);
								System.out.println("주사위 숫자:" + unum);
								setdiceLabel(unum);
								setInfoField(user);
								//지역확인 시작
								if(user.location==com.location){//내가 뺏김
									user.Steal(user, com);//돈을 빼앗김
								}
								if(p[user.location].build==0){
									//특수블록
									//세계여행이 아니라면
									if(user.location == 7){//무인도라면
										Alived(user);//카운트를 2로 초기화.
									}
									if(user.location == 14){//매점이용권 지급
										Market(user);
									}
									if(user.location == 21){//견학가기
										System.out.println("견학도착");
										//pr.tripPrompt();
										for(int i=0;i<28;i++){
											if(i!=21){
												b[i].setEnabled(true);
											}//활성화
											
										}
									
									}
								
								}else{
									setInfoField(user);
									/*infoField.setText("  현재 지역:"+p[user.location].name+"\n 건설비:"
								+p[user.location].build+"원 \n"+"육성회비:"+p[user.location].pay +"원 \n 학교 주인: "+p[user.location].owner+"");
									*///견학을 갔다오면 시작
									if(p[user.location].owner!=null&&p[user.location].owner!=user.name){//땅의 주인이 내가 아니라면 
										pr.PayPrompt(p[user.location].pay);//통행료를 내야한다
										if(p[user.location].pay<user.Money){
											user.Money -= p[user.location].pay;
											com.Money += p[user.location].pay;//통행료를 받음
											//com.TotalMoney +=p[user.location].pay;//통행료를 받음
											setUser(user.Money,user.TotalMoney);
											setCom(com.Money,com.TotalMoney);
											pr.PaiedPrompt(p[user.location]);
										}else{
											if(p[user.location].pay>user.TotalMoney){//무조건 total머니보다 육성회비가 크면
												//파산
													pr.userLose();
													user.TotalMoney =0;
													pr.winner(user, com);
													System.exit(0);
												}else{//totalMoney가 같거나 클때 파산 매각 선택
													if(pr.forSalePrompt()==0){
														//매각
														checkPay.setText("육성회비는"+p[user.location].pay+"원 입니다.");
														diceButton.setEnabled(false);
													//	mg.setEnabled(true);
														for(int i=0;i<c.length;i++){
															if(p[i].owner==user.name)
																c[i].setVisible(true);
														}
														
													}else{//파산
														pr.userLose();
														user.TotalMoney =0;
														pr.winner(user, com);
														System.exit(0);
														}
													}
											
										}
									}else if(p[user.location].owner==null){//땅의주인이 업는경우
										if(pr.buildPrompt(p[user.location])==0){//건설을 ok할때
											if(p[user.location].build<user.Money){
													p[user.location].owner = user.name;
													user.Money -= p[user.location].build;//현재가진돈은 - 건설비
													//user.TotalMoney -= p[user.location].build;// 총재산은 + 건설비
													user.TotalMoney
													+= p[user.location].build;
													pr.successBuild(p[user.location]);
													setInfoField(user);
													setUser(user.Money,user.TotalMoney);
													setOwnerBoard(user.location);
											}else{
												pr.noBuildPrompt();
												turn.comTurn = true;
												break GAMETURN;
												//건설비 부족
											}
											//지역의 색깔을 user색으로 변경한다.
										}else{
											turn.comTurn = true;
											break GAMETURN;
										}
										
									}
								}
								
							}
							turn.comTurn=true;
							break GAMETURN;
							//무인도 탈출
						}
						//무인도가 아니라면~
						
						int unum = dice.diceRun(user);
						user.move(unum);
						System.out.println("주사위 숫자:" + unum);
						setdiceLabel(unum);
						setInfoField(user);
						//지역확인 시작
						if(user.location==com.location){//내가 뺏김
							user.Steal(user, com);//돈을 빼앗김
						}
						
						if(p[user.location].build==0){
							//특수블록
							if(user.location == 7){//무인도라면
								Alived(user);//카운트를 2로 초기화.
							}
							if(user.location == 14){//매점이용권 지급
								Market(user);
							}
							if(user.location==21){
								System.out.println("세계여행 도착");
								pr.tripPrompt();
								//TripYes=0;//초기화
								int i=0;
								for(i=0;i<28;i++){
									if(i!=21){
										b[i].setEnabled(true);
									}//활성화
								}
								
							}
							
						}else{
							
							/*if(TripYes==-1){//견학을 갔다오지 않으면
								pr.NotFoundTripPlace();
								break GAMETURN;
							}*/
							setInfoField(user);
								if(p[user.location].owner!=null&&p[user.location].owner!=user.name){//땅의 주인이 내가 아니라면 
								pr.PayPrompt(p[user.location].pay);//통행료를 내야한다
								if(p[user.location].pay<user.Money){
									user.Money -= p[user.location].pay;
									//user.TotalMoney -=p[user.location].pay;
									com.Money += p[user.location].pay;//통행료를 받음
									//com.TotalMoney +=p[user.location].pay;//통행료를 받음
									setUser(user.Money,user.TotalMoney);
									setCom(com.Money,com.TotalMoney);
								}else{
									if(p[user.location].pay>user.TotalMoney){//무조건 total머니보다 육성회비가 크면
											//파산
										pr.userLose();
										user.TotalMoney =0;
										pr.winner(user, com);
										System.exit(0);
									}else{
										if(pr.forSalePrompt()==0&&user.TotalMoney>=p[user.location].build){
											/*Forsale frame = new Forsale();
											frame.setVisible(true);
											frame.Sale(user, p, p[user.location].pay);
											매각 망한 코드
											*/
											checkPay.setText("육성회비는"+p[user.location].pay+"원 입니다.");
											diceButton.setEnabled(false);
											//mg.setEnabled(true);
											for(int i=0;i<c.length;i++){
												if(p[i].owner==user.name)
													c[i].setVisible(true);
											}
											
										}else{//파산
											pr.userLose();
											user.TotalMoney =0;
											pr.winner(user, com);
											System.exit(0);
										}
									}
								}
							}else if(p[user.location].owner==null){//땅의주인이 업는경우
								if(pr.buildPrompt(p[user.location])==0){//건설을 ok할때
									if(p[user.location].build<user.Money){
											p[user.location].owner = user.name;
											user.Money -= p[user.location].build;//현재가진돈은 - 건설비
											//user.TotalMoney -= p[user.location].build;// 총재산은 + 건설비
											user.TotalMoney += p[user.location].build;
											pr.successBuild(p[user.location]);
											setInfoField(user);
											setUser(user.Money,user.TotalMoney);
											setOwnerBoard(user.location);
											
									}else{
										pr.noBuildPrompt();
										turn.comTurn = true;
										break GAMETURN;
										//건설비 부족
									}
									//지역의 색깔을 user색으로 변경한다.
								}else{
									turn.comTurn = true;
									break GAMETURN;
								}
								
							}
						}
						
						turn.comTurn = true;
					}

					tuurn.setText("남은 턴:"+count);

					/*
					 * int unum = dice.diceRun(user); user.move(unum);
					 * System.out.println("주사위 숫자:"+unum); setdiceLabel(unum);
					 * pr.DicePrompt(); int cnum = dice.diceRun(com);
					 * System.out.println("컴퓨터주사위 숫자:"+cnum); com.move(cnum);
					 * setdiceLabel(cnum);
					 */
					
					if (count <= 0) {
						pr.GameExit();
						pr.winner(user, com);
						System.exit(0);
					}
					diceButton.setIcon(new ImageIcon("img/dicebutton.png"));
				}
				
			
			}
			
		}

