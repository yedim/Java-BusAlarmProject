package BusAlarmScreen;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import Main.BusAlarm;

public class BusPanel extends JPanel {

	BusAlarm busalarm;

	ImageIcon icmainScreen = new ImageIcon(this.getClass().getResource("/MainScreen2.png"));
	ImageIcon icbusIcon = new ImageIcon(this.getClass().getResource("/BusIcon.png"));

	ImageIcon icbusRoad_green = new ImageIcon(this.getClass().getResource("/busRoad_green.png"));
	ImageIcon icbusRoad_yellow = new ImageIcon(this.getClass().getResource("/busRoad_yelllow.png"));
	ImageIcon icbusRoad_red = new ImageIcon(this.getClass().getResource("/busRoad_red.png"));
	ImageIcon icbusStop = new ImageIcon(this.getClass().getResource("/busStopButton.png"));
	ImageIcon icbusStopSelect = new ImageIcon(this.getClass().getResource("/busStopButtonSelect.png"));
	
	JLabel label;
	JLabel bbusroad_green;
	
	public BusPanel(BusAlarm busalarm) {
		super(true);
		label = new JLabel();
		label.setBounds(0, 0, 1280, 720);

		bbusroad_green=new JLabel(icbusRoad_green);
		bbusroad_green.setBounds(10,50,120,12);

		JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 0, 40, 0, 400);
		vbar.setBounds(1245, 0, 26, 686);
		vbar.addAdjustmentListener(new MyAdjustmentListener());
		setLayout(null);

		add(vbar);
		add(label);
		add(bbusroad_green);

	}

	class MyAdjustmentListener implements AdjustmentListener {
		public void adjustmentValueChanged(AdjustmentEvent e) {
			bbusroad_green.setLocation(10,e.getValue()+50);
			label.setText(" New Value is " + e.getValue() + " ");
			repaint();
		}
	}


}
