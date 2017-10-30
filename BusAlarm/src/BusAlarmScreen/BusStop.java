package BusAlarmScreen;

import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class BusStop extends JButton{

	Point pos; //���� ��ǥ
	BusStop busStop;
	int ride_passenger;//Ÿ�����ر�ٸ��»��
	int alight_passenger;//�������
	int busStopCnt=0;
	int tmp_passenger=0;
	int i=0;
	
	BusStop(int x, int y,int busStopCnt) {
		pos = new Point(x, y); //������ ��ǥ�� üũ
		this.busStopCnt=busStopCnt;
	}
	
	void setPos(int x, int y)
	{
		pos.x=x;
		pos.y=y;
	}
	
	void setBusRidePassenger(int ride_passenger)
	{
		this.ride_passenger=ride_passenger;
	}
	void setBusAlightPassenger(int alight_passenger)
	{
		this.alight_passenger=alight_passenger;
	}
	
	void increaseBusPassenger(int passenger)
	{
		ride_passenger=0;
		Timer m_timer = new Timer();
		TimerTask m_task=new TimerTask(){
			public void run(){
				ride_passenger=passenger/3;
			}
		};
		m_timer.schedule(m_task, 1000);
		
		Timer m_timer1 = new Timer();
		TimerTask m_task1=new TimerTask(){
			public void run(){
				ride_passenger=passenger/2;
			}
		};
		m_timer1.schedule(m_task1, 2000);
		
		Timer m_timer2 = new Timer();
		TimerTask m_task2=new TimerTask(){
			public void run(){
				ride_passenger=passenger;
			}
		};
		m_timer2.schedule(m_task2, 3000);
		
	}		
	

}
