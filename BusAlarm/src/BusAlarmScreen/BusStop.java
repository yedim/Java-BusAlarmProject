package BusAlarmScreen;

import java.awt.Point;

import javax.swing.JButton;

public class BusStop extends JButton{

	Point pos; //���� ��ǥ
	BusStop busStop;

	BusStop(int x, int y) {
		pos = new Point(x, y); //������ ��ǥ�� üũ
	}
	
	void setPos(int x, int y)
	{
		pos.x=x;
		pos.y=y;
	}
}
