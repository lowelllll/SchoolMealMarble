package Marble;

public class Place{
	String name = null;
	int build = 0;
	String owner=null;
	int pay = 0;
	Prompt pr = new Prompt();
	public Place(String name,int build){
		this.name = name;//지역의 이름
		this.build = build;//지역의 땅값
	}
	public void Owner(String owner){
		this.owner = owner;//땅의 주인
	}
	public void setPay(){
		this.pay = build*2;//통행료 = 건설비 *2
	}
//	public void build(Com com,Place p){//컴퓨터가 건물을 지을때
//		if(com.Money > (p.build+200000)){ // 현재 가진돈이 해당 지역의 매매가보다 20만원이 많다면ㅇ
//			com.Money -=p.build;//건물을 산다.
//			com.TotalMoney += p.build; // 총 재산 +=건설비
//			p.Owner(com.name);//건물을 산다.
//		}
//	}
	
//	public void build(User user,Place p){//유저가 건물을 지을때
//			if(pr.buildPrompt()==0){ //건물을 산다고 하면
//				user.Money -= p.build; 
//				user.TotalMoney += p.build;
//				p.Owner(user.name);
//			}
//		}
	
	static public Place[] placearr(){
		Place[] p = new Place[28];
		
		p[0] = new Place("시작점!", 0); //특수
		p[1] = new Place("전자고", 50000);
		p[2] = new Place("영상고", 70000);
		p[3] = new Place("방송고", 110000);
		p[4] = new Place("관광고", 130000);
		p[5] = new Place("은메고", 170000);
		p[6] = new Place("염여메고", 200000);
		
		p[7] = new Place("선도부", 0); //특수
		p[8] = new Place("유헬스고", 230000);
		p[9] = new Place("리라고", 260000);
		p[10] = new Place("홍디고", 300000);
		p[11] = new Place("예디고", 340000);
		p[12] = new Place("대세고", 370000);
		p[13] = new Place("과기고", 400000);
		
		p[14] = new Place("매점", 0); //특수
		p[15] = new Place("무역고", 450000);
		p[16] = new Place("컨벤션고", 480000);
		p[17] = new Place("글로벌고", 520000);
		p[18] = new Place("설여상고", 560000);
		p[19] = new Place("서울공고", 580000);
		p[20] = new Place("신과기고", 630000);
		
		p[21] = new Place("견학가기", 0); //특수
		p[22] = new Place("용산공고", 670000);
		p[23] = new Place("상미고", 710000);
		p[24] = new Place("한세고", 780000);
		p[25] = new Place("선린인고", 830000);
		p[26] = new Place("세명컴고", 870000);
		p[27] = new Place("디지텍고", 950000);
		
		for(int i=0; i<28; i++){
			p[i].setPay();
		}
		return p;
	}
	/*int trip(int index,int location,User user){//user만 세계여행 가능
		if (location < index)
		{
			user.move(index);
			return 0;
		}
		else if(location > index)
		{
			user.move(28 - location + index);
			return 0;
		}
		else if(location == index)
		{
			return 0;
		}else return 0;
	}*/
	
	//도착했을때
	 
	
}
	