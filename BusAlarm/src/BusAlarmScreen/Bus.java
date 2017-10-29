package BusAlarmScreen;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class Bus extends JButton{

	Point pos; //���� ��ǥ
	int busPassenger=0;//���� �°���
	String degreeOfCongestion =null;//����ȥ�⵵
	String name;
	int busCnt=0;
	
	Bus(int x, int y, int busCnt) {
		pos = new Point(x, y); //������ ��ǥ�� üũ
		name=Integer.toString(busCnt);
		this.busCnt=busCnt;
	}
	
	int busDir=1;
	int line=1;
	int busSpeed=1;

	public void move() { // ���� �̵��� ���� �޼ҵ�
				
		if(pos.x>1100 || pos.x<10)
		{
			busDir=-busDir;
			pos.y+=118;	
			line++;
		}
		//pos.x+=busDir*busSpeed;
		pos.x+=busDir;
	}
	
	public void countCongestion(int busPassenger)
	{
		if(busPassenger>40){
			degreeOfCongestion="ȥ��";
		}
		else if(busPassenger>25){
			degreeOfCongestion="����";
		}else {
			degreeOfCongestion="����";
		}
	}
	
	public void arriveBus(int busStop_ride_passenger, int busStop_alight_passenger)
	{
		
			busPassenger+=busStop_ride_passenger;
			busPassenger-=busStop_alight_passenger;
		
		
		
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
	public void controllSpeed(int busType)
	{
		busSpeed=busType;
	}
	
}
