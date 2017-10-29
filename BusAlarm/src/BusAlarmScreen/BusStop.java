package BusAlarmScreen;

import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BusStop extends JButton{

	Point pos; //버스 좌표
	BusStop busStop;
	int ride_passenger;//타기위해기다리는사람
	int alight_passenger;//내릴사람
	int busStopCnt=0;
	
	BusStop(int x, int y,int busStopCnt) {
		pos = new Point(x, y); //버스의 좌표를 체크
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
