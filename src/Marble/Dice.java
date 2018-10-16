package Marble;


public class Dice {
	
	int diceRun(User user){
		//주사위 버튼을 눌렀을때 실행됨.
		int num = (int)(Math.random()*6)+1;
		sound.DICE.play();
				return num;
				
	}
	int diceRun(Com com){
		int num = (int)(Math.random()*6)+1;
		sound.DICE.play();
		return num;
	}
	/*int diceLucky(){//행운의 숫자
		int num = (int)(Math.random()*6)+1;
		return num;
		//화면에 출력
	}*/
}