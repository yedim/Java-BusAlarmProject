package BusAlarmScreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BusRoad extends JButton {

	Point pos; //���� ��ǥ
	int busType;

	BusRoad(int x, int y,int busType) {
		pos = new Point(x, y); //������ ��ǥ�� üũ
		this.busType=busType;
	}

}
