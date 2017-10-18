package BusAlarmScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Main.BusAlarm;

public class BusPanel extends JPanel implements Runnable, ActionListener{

	BusAlarm busalarm;
	
	Image imgbusicon=new ImageIcon(this.getClass().getResource("/BusIcon.png")).getImage();
	ImageIcon icmainScreen = new ImageIcon(this.getClass().getResource("/MainScreen2.jpg"));
	ImageIcon icbusIcon = new ImageIcon(this.getClass().getResource("/BusIcon.png"));
	ImageIcon icbusRoad_green = new ImageIcon(this.getClass().getResource("/busRoad_green.png"));
	ImageIcon icbusRoad_yellow = new ImageIcon(this.getClass().getResource("/busRoad_yelllow.png"));
	ImageIcon icbusRoad_red = new ImageIcon(this.getClass().getResource("/busRoad_red.png"));
	ImageIcon icbusStop = new ImageIcon(this.getClass().getResource("/busStopButton.png"));
	ImageIcon icbusStopSelect = new ImageIcon(this.getClass().getResource("/busStopButtonSelect.png"));

	JLabel label;
	JLabel lbbusroad_green;
	JLabel lbbusroad_yellow;
	JButton bbusStop[][] = new JButton[13][10];
	JLabel lbbusStop[][] = new JLabel[13][10];
	JLabel jlabel;

	Calendar calendar1 = Calendar.getInstance();
	int year = calendar1.get(Calendar.YEAR);
	int month = calendar1.get(Calendar.MONTH);
	int day = calendar1.get(Calendar.DAY_OF_MONTH);
	int hour = calendar1.get(Calendar.HOUR_OF_DAY);
	int min = calendar1.get(Calendar.MINUTE);
	int sec = calendar1.get(Calendar.SECOND);
	Timer timer;
	JLabel lbPresent;

	int i = 0, j = 0;
	int count = 0;
	BusAPI busapi = new BusAPI();

	int x, y;
	Thread th;

	ArrayList Bus_List = new ArrayList();

	Bus bus; // ���� ���� Ű
	
	static int time;
	
	 
	public BusPanel(BusAlarm busalarm) {
		
		super(true);
		setLayout(null);
		
		setBackground(new Color(255, 243, 225));
		label = new JLabel();
		label.setBackground(Color.YELLOW);
		label.setBounds(0, 0, 1280, 720);

		lbbusroad_green = new JLabel(icbusRoad_green);
		lbbusroad_green.setBounds(95, 221, 120, 12);

		JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 1000, 80, 0, 1500);
		vbar.setBounds(1245, 0, 26, 686);
		vbar.addAdjustmentListener(new MyAdjustmentListener());

		add(vbar);
		add(label);
		
		lbPresent = new JLabel(year + "-" + month + "-" + day + "   " + hour + ":" + min + ":" + sec,SwingConstants.RIGHT);
		lbPresent.setVerticalAlignment(SwingConstants.TOP);
		lbPresent.setBounds(1010, 5, 220, 40);
		lbPresent.setFont(new Font("���� ���", Font.BOLD, 20));
		add(lbPresent);

		timer = new Timer(1000, this);
		timer.setInitialDelay(0);
		timer.start();

		for (i = 0; i < 13; i++) {
			for (j = 0; j < 10; j++) {
				lbbusStop[i][j] = new JLabel();
				lbbusStop[i][j].setText("����");//busapi.GetBusStopInfo(count)
				count++;
				lbbusStop[i][j].setBounds(115 * j + 77, 140 * i + 226, 110, 50);
				add(lbbusStop[i][j]);

				bbusStop[i][j] = new JButton(icbusStop);
				bbusStop[i][j].setBounds(115 * j + 93, 140 * i + 220, 16, 16);
				BusAlarm.setButton(bbusStop[i][j]);
				add(bbusStop[i][j]);
			}
		}

		add(lbbusroad_green);
		
		init();
		start();
	}
	class MyAdjustmentListener implements AdjustmentListener {
		public void adjustmentValueChanged(AdjustmentEvent e) {

			for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 10; j++) {
					lbbusStop[i][j].setLocation(115 * j + 77, e.getValue() - (-140 * i + 774));
					add(lbbusStop[i][j]);
					bbusStop[i][j].setLocation(115 * j + 93, e.getValue() - (-140 * i +780));
					BusAlarm.setButton(bbusStop[i][j]);
					add(bbusStop[i][j]);
				}
			}

			lbbusroad_green.setLocation(95, e.getValue() - 779);
			add(lbbusroad_green);

			label.setText(" New Value is " + e.getValue() + " ");
			repaint();
			
		}
	}

	public void actionPerformed(ActionEvent e) {
		++sec;
		Calendar calendar2 = Calendar.getInstance();
		year = calendar2.get(Calendar.YEAR);
		month = calendar2.get(Calendar.MONTH);
		day = calendar2.get(Calendar.DAY_OF_MONTH);
		hour = calendar2.get(Calendar.HOUR_OF_DAY);
		min = calendar2.get(Calendar.MINUTE);
		sec = calendar2.get(Calendar.SECOND);
		lbPresent.setText(year + "-" + month + "-" + day + "   " + hour + ":" + min + ":" + sec);
	}
	
	public void init() { // ���Ǹ� ���� init ���� �⺻���� ������ �մϴ�.
		x = 100;
		y = 100;
	}
	public void start() {
		th = new Thread(this);
		th.start();
	}

	public void run() {
		try {
			while (true) {
				BusProcess(); 

				repaint();
				Thread.sleep(40);
				time++;
			}
		} catch (Exception e) {
		}
	}
	public void BusProcess() { //���� ó�� �޼ҵ�
		if (time%500==0) { 
			bus = new Bus(x, y); // ��ǥ üũ�Ͽ� �ѱ��
			Bus_List.add(bus); // ���� �߰�
		}
	}
	public void paint(Graphics g) {
		super.paint(g);
		
		for (int i = 0; i < Bus_List.size(); ++i) {
			bus = (Bus) (Bus_List.get(i));
			g.drawImage(imgbusicon, bus.pos.x, bus.pos.y+15 +bbusStop[1][1].getY()-330 , this);
			bus.move();
		}

	}
	
	public void MouseEvent(int i, int j)
	{
		bbusStop[i][j].addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				bbusStop[i][j].setIcon(icbusStopSelect);
			}

			public void mouseExited(MouseEvent e) {
				bbusStop[i][j].setIcon(icbusStop);
			}

			public void mousePressed(MouseEvent e) {

			}
		});
		add(bbusStop[i][j]);
	}

	
}

