package BusAlarmScreen;

import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BusStop extends JButton{

	Point pos; //���� ��ǥ
	BusStop busStop;
	int ride_passenger;//Ÿ�����ر�ٸ��»��
	int alight_passenger;//�������
	int busStopCnt=0;
	
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

}
