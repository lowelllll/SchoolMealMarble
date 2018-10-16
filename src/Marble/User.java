package Marble;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

interface UserInterface {
	// int location; = 사용자 위치 변수
	//void User(String name);

	// int Money; 사용자 현재 가지고 있는 금액
	// int totalMoney; 사용자 토탈 금액 변수
	// int POKETMONET=200000; 시작점 돌때 월급
	//void forSale(Place[] p);// user의 이름과 같은 건물 출력, n = 통행료 ->GUI /
	// 통행료보다 크거나 같으면 매각 완료.
	// 현재 user가 가진 금액 += 매각한 건물들의 건설비

	void backruptcy();// 파산

	void move(int num);

	void Steal(User user);// 도착한 지역에 상대방이 먼저 있을 경우 상대방이 나의 돈을 갈취함
}

public class User {
	public static final int POKETMONEY = 200000;
	int location = 0; // 유저의 위치
	int Money = 2000000; // 현재 가진 금액
	int TotalMoney = 0; // 건설비*2//통행료
	String name = null;
	int IslandCount = 2;// 유저마다의 선도부 카운트
	int haveTrip=0;
	int y=0;
	int x=0;
	int c=0;
	Prompt pr = new Prompt();
	private Board main;
	//private main Main;
	JLabel userFace = new JLabel("");
	
	
	public void UserName(String name) {
		//창에서 이름을 입력하면 이름을 받아옴
		this.name = name;
	}
	User(Board main,int startX,int startY){//생성자
		this.main=main;
		x = startX;
		y = startY;
		userFace.setIcon(new ImageIcon("img/userface.png"));
		userFace.setBounds(x, y, 20, 20);
		
		
	}

	/*public void forSale() {

		for (int i = 0; i < p.length; i++) {
			if (p[i].owner.equals(name)) {
				// 해당 인덱스(i)의 버튼을 활성화함
			}
		}
		// 이 줄에서 배열타입의 매각 가능한 인덱스를 Marbleboard에리턴.
		// 보드에서 선택해서 다 선택해서 통행료가 부족하다면 파산해야함
		// board에서 매각할 지역을 선택 받으면 인덱스들을 배열에 다시 집어넣어서 forSaleSuceess에 리턴함
		// int n=0;
		// forsalearr[n] = 선택한 지역의 인덱스
		// n++
		// 체크박스?
	}*/
	

	public void move(int num) {
		
	
		for (int i = 0; i < num; i++) {
			this.location++;
			if (location > 27) {
				location = 0;
			}
			if (location == 0) {
				Money += 200000;
				//TotalMoney += 200000;
				pr.SalaryPrompt();
				main.setUser(Money, TotalMoney);
				System.out.println("사람이 월급받음");
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
			//main.repaint();
			userFace.setLocation(x, y);
		}
	
		
	}
	
//	public void paint(Graphics g) {
//	
////		g.setColor(Color.PINK);
////		g.fillOval(x, y, 20, 20);
//
//	}
	public void Steal(User user, Com com) {
		if(user.Money>=50000){//돈이 있을때만 뺏음
			com.Money += 50000;
			user.Money -= 50000;
			//com.TotalMoney += 50000;
			//user.TotalMoney -= 50000;
			// 돈을 빼앗겼습니다 알림창
			// com Steal오버라이딩 -> 돈을 빼았았습니다 알림창 (user관측)
			pr.stealPrompt();
			main.setCom(com.Money,com.TotalMoney );
			main.setUser(user.Money,user.TotalMoney );
		}else{
			
		}
		
	}

}
