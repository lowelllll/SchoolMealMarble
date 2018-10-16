package Marble;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Com {

	final int CPOKETMONEY = 200000;
	int location = 0; // 유저의 위치
	int Money = 2000000; // 현재 가진 금액
	int TotalMoney = 0; // 현재 가진 금액 + 건설비
	String name = "com";
	int IslandCount = 2;// 유저마다의 선도부 카운트
	int y = 0;
	int x = 0;
	int c = 0;
	Board main;
	//main Main;
	Prompt pr= new Prompt();
	JLabel comFace = new JLabel("");
	
	
	Com(Board main,int startX,int startY){
		this.main=main;
		x = startX;
		y = startY;
		comFace.setIcon(new ImageIcon("img/comface.png"));
		comFace.setBounds(x, y, 20, 20);
	}
	
public void move(int num) {
		
	
	

		for (int i = 0; i < num; i++) {
			this.location++;
			
			if (location > 27) {
				location = 0;
			}
			if (location == 0) {
				Money += 200000;
				//TotalMoney += 200000;
				main.setCom(Money, TotalMoney);
				
				// 월급알림창 뜸
			}
			// 해당 객체 이동 GUI
			// 보드*주사위수만큼 x값이동
			if(c<=6){
				System.out.println(c);
				x-=100;
			}
			else if(c>=7&&c<=13){
				y-=75;
				System.out.println(c);	
			}
			else if(c>=14&&c<=20){
				x+=100;
				System.out.println(c);
			}
			else if(c>=21&&c<=27){
				y+=75;
				System.out.println(c);
			}else{
				c=0;
				x-=100;
			}
			c++;
			
			comFace.setLocation(x, y);
		}
		
		
	}

	/*public void paint(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillOval(x, y, 20, 20);

	}*/
	public void Steal(User user, Com com) {
		if(com.Money>=50000){
			user.Money += 50000;
			//user.TotalMoney += 50000;
			com.Money -= 50000;
			//com.TotalMoney -= 50000;
			// 돈을 빼앗았습니다. 알림창
			// com Steal오버라이딩 -> 돈을 빼았았습니다 알림창 (user관측)
			pr.stealedPrompt();
			main.setCom(com.Money,com.TotalMoney );
			main.setUser(user.Money,user.TotalMoney );
		}
	}
}
