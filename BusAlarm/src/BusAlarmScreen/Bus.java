package BusAlarmScreen;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class Bus extends JButton{

	Point pos; //버스 좌표
	int busPassenger=0;//버스 승객수
	String degreeOfCongestion =null;//버스혼잡도
	String name;
	int busSpeed=0;
	
	Bus(int x, int y, int busCnt) {
		pos = new Point(x, y); //버스의 좌표를 체크
		name=Integer.toString(busCnt);
	}
	
	int busDir=1;
	int line=1;

	public void move() { // 버스 이동을 위한 메소드
				
		if(pos.x>1100 || pos.x<10)
		{
			busDir=-busDir;
			pos.y+=118;	
			line++;
		}
		pos.x+=busDir;	
		
	}
	
	public void countCongestion(int busPassenger)
	{
		if(busPassenger>40){
			degreeOfCongestion="혼잡";
		}
		else if(busPassenger>25){
			degreeOfCongestion="보통";
		}else {
			degreeOfCongestion="여유";
		}
	}
	
	public void arriveBus()
	{
		busDir=0;
		Timer m_timer = new Timer();
		TimerTask m_task=new TimerTask(){
			public void run(){
				if(line%2==0)
				{
					busDir=-1;
				}
				else
				{
					busDir=1;
				}
			}
		};
		m_timer.schedule(m_task, 1000);
		
	}
	//public void 
	
}
