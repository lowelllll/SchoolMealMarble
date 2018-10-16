package Marble;

import java.util.Random;

public class FistTurn {
	//Random random = new Random();
	boolean comTurn,userTurn;
	Prompt pr = new Prompt();
	int turn=0;
	public void Turn(){
		int turn=(int)(Math.random()*2)+1;
		if(turn==2){
			pr.NofirstTurnPrompt();
			
			comTurn=true;
			userTurn=false;
		}
		else{
			pr.firstTurnPrompt();
			
			System.out.println(turn);
			comTurn=false;
			userTurn=true;
		}
			
	}
	
}
