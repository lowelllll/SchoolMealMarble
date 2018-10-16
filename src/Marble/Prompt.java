package Marble;

import javax.swing.JOptionPane;


public class Prompt {
	
	int forSalePrompt(){
		//매각or파산
		String[] buttons = {"폐교", "파산"};
		int flag = JOptionPane.showOptionDialog(null, "현재 가진 재산이 부족합니다. 폐교나 파산 할 수 있습니다.", "폐교or파산", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, buttons, "폐교");
		//리턴값이 있음
		//null은 프레임이 없는 기본값, 나중에 띄우고 싶은 프레임의 이름을 입력.
		//예 0,아니오 1
		return flag;
	}
	int buildPrompt(Place p){
		int flag = JOptionPane.showConfirmDialog(null, p.name+"등학교를 건설하시겠습니까?\n건설비:"+p.build, "학교매매", JOptionPane.YES_NO_OPTION); //리턴값이 있음
		return flag;
	}
	void noBuildPrompt(){
		JOptionPane.showMessageDialog(null, "현재 가진 돈이 부족해 건설하지 못했습니다.", "학교 건설", JOptionPane.INFORMATION_MESSAGE);
	}
	void successBuild(Place p){
		JOptionPane.showMessageDialog(null, p.name+"등학교를 건설했습니다!", "학교 건설", JOptionPane.INFORMATION_MESSAGE);
	}
	void marketPrompt(){
		JOptionPane.showMessageDialog(null, "매점이용권 100000원을 획득하셨습니다.", "매점이용권", JOptionPane.INFORMATION_MESSAGE);
	}
	void stealPrompt(){
		JOptionPane.showMessageDialog(null, "컴퓨터가 돈을 50000원을 뜯어갔습니다ㅜ3ㅜ", "갈취당함!", JOptionPane.INFORMATION_MESSAGE);
	}
	void stealedPrompt(){
		JOptionPane.showMessageDialog(null, "컴퓨터의 돈을 50000원을 뜯었습니다>_<", "갈취함!", JOptionPane.INFORMATION_MESSAGE);
	}
	void firstTurnPrompt(){
		JOptionPane.showMessageDialog(null, "선이에요. 먼저 시작하세요!", "선후공", JOptionPane.INFORMATION_MESSAGE);
	}
	void NofirstTurnPrompt(){
		JOptionPane.showMessageDialog(null, "2번째차례에요!", "선후공", JOptionPane.INFORMATION_MESSAGE);
	}
	void SalaryPrompt(){
		JOptionPane.showMessageDialog(null, "용돈 200000원 획득하였습니다!", "용돈", JOptionPane.INFORMATION_MESSAGE);
	}
	void PayPrompt(int Pay){
		String[] buttons = {"지불"};
		JOptionPane.showOptionDialog(null, "육성회비를 지불해야됩니다!\n육성회비 "+Pay+"원", "육성회비", JOptionPane.INFORMATION_MESSAGE,JOptionPane.QUESTION_MESSAGE, null, buttons, "지불");
	}
	void comPayPrompt(Place p){
		JOptionPane.showMessageDialog(null, "컴퓨터가 "+p.name+"등학교의 육성회비"+p.pay+"를 지불했습니다!", "컴퓨터 육성회비 지불 완료", JOptionPane.INFORMATION_MESSAGE);}
	void PaiedPrompt(Place p){
		JOptionPane.showMessageDialog(null, p.name+"등학교의 육성회비를 지불했습니다.", "user 육성회비 지불 완료", JOptionPane.INFORMATION_MESSAGE);
		}
	String NamePrompt(User user){
		String flag = JOptionPane.showInputDialog(null,
				"이름을 입력해주세요.", "이름 설정", JOptionPane.YES_NO_OPTION);
		user.name=flag;
		return flag;
	}
	void tripPrompt(){
		JOptionPane.showMessageDialog(null, "견학지역에 도착했습니다!\n가고싶은 지역/학교를 선택해주세요", "견학가기", JOptionPane.INFORMATION_MESSAGE);
		
	}
	void IsrandPrompt(){
		JOptionPane.showMessageDialog(null, "선도부 지역에 갖혔습니다!\n2턴동안  이동할 수 없습니다ㅠ_ㅠ!", "선도부", JOptionPane.INFORMATION_MESSAGE);
	}
	void comIsrandPrompt(){
		JOptionPane.showMessageDialog(null, "컴퓨터가 선도부 지역에 갖혔습니다!", "선도부", JOptionPane.INFORMATION_MESSAGE);
	}
	void IsrandCountPrompt(int count,String name){
		JOptionPane.showMessageDialog(null, name+"이(가) 탈출까지 남은 횟수"+(count+1)+"", "선도부", JOptionPane.INFORMATION_MESSAGE);
	}
	void IsrandEscapePrompt(){
		JOptionPane.showMessageDialog(null, "탈출성공!", "선도부", JOptionPane.INFORMATION_MESSAGE);
	}
	void TurnPrompt(){
		String[] buttons = {"선", "선"};
		int flag = JOptionPane.showOptionDialog(null, "선을 골라주세요.", "선후공 정하기", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, buttons, "선");
	}
	void DicePrompt(){
		JOptionPane.showMessageDialog(null, "컴퓨터가 주사위를 굴립니다.", "주사위", JOptionPane.INFORMATION_MESSAGE);
	}
	void GameExit(){
		JOptionPane.showMessageDialog(null, "턴이 종료되어 게임을 종료합니다.", "게임종료", JOptionPane.INFORMATION_MESSAGE);
		
	}
	void winner(User user,Com com){
		if(user.TotalMoney > com.TotalMoney){
			JOptionPane.showMessageDialog(null, ""+user.name+"님 승리!", "게임종료", JOptionPane.INFORMATION_MESSAGE);
		}else if(user.TotalMoney < com.TotalMoney){
			JOptionPane.showMessageDialog(null, "컴퓨터 의승리!ㅠ_ㅠ", "게임종료", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, "무승부!", "게임종료", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	void comForsale(){
		JOptionPane.showMessageDialog(null, "컴퓨터가 폐교를 하였습니다!", "컴퓨터 폐교 완료", JOptionPane.INFORMATION_MESSAGE);
	}
	
	void comLose(){
		JOptionPane.showMessageDialog(null, "컴퓨터가 파산을 하였습니다!", "컴퓨터 파산 완료", JOptionPane.INFORMATION_MESSAGE);
	}
	void userLose(){
		JOptionPane.showMessageDialog(null, "파산을 하였습니다!", "user 파산 완료", JOptionPane.INFORMATION_MESSAGE);
	}
	
	void NoTripPlace(){
		JOptionPane.showMessageDialog(null, "견학지역은 이동할 수 없습니다!", "견학지역은 이동할 수 없음", JOptionPane.INFORMATION_MESSAGE);
	}
	void NotFoundTripPlace(){
		JOptionPane.showMessageDialog(null, "입력하신 학교는 없는 학교입니다! \n턴이 넘어갑니다", "견학 지역 찾을 수 없음", JOptionPane.INFORMATION_MESSAGE);
	}
}