package BusAlarmScreen;

import java.awt.Point;

import javax.swing.JButton;

public class BusStop extends JButton{

	Point pos; //버스 좌표
	BusStop busStop;

	BusStop(int x, int y) {
		pos = new Point(x, y); //버스의 좌표를 체크
	}
	
	void setPos(int x, int y)
	{
		pos.x=x;
		pos.y=y;
	}
}
