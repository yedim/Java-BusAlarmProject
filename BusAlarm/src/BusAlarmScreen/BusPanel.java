package BusAlarmScreen;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class BusPanel extends JPanel implements Runnable, ActionListener {

	BusAlarm busalarm;

	Image imgbusicon = new ImageIcon(this.getClass().getResource("/BusIcon.png")).getImage();
	ImageIcon icmainScreen = new ImageIcon(this.getClass().getResource("/MainScreen2.jpg"));
	ImageIcon icbusIcon = new ImageIcon(this.getClass().getResource("/BusIcon.png"));
	ImageIcon icbusRoad_green = new ImageIcon(this.getClass().getResource("/busRoad_green.png"));
	ImageIcon icbusRoad_yellow = new ImageIcon(this.getClass().getResource("/busRoad_yelllow.png"));
	ImageIcon icbusRoad_red = new ImageIcon(this.getClass().getResource("/busRoad_red.png"));
	ImageIcon half_icbusRoad_green = new ImageIcon(this.getClass().getResource("/half_busRoad_green.png"));
	ImageIcon half_icbusRoad_yellow = new ImageIcon(this.getClass().getResource("/half_busRoad_yellow.png"));
	ImageIcon half_icbusRoad_red = new ImageIcon(this.getClass().getResource("/half_busRoad_red.png"));
	ImageIcon icbusStop = new ImageIcon(this.getClass().getResource("/busStopButton.png"));
	ImageIcon icbusStopSelect = new ImageIcon(this.getClass().getResource("/busStopButtonSelect.png"));
	ImageIcon icmainScreenBar = new ImageIcon(this.getClass().getResource("/MenuBar.png"));
	ImageIcon icfold = new ImageIcon(this.getClass().getResource("/foldbutton2.png"));
	ImageIcon icbusRoad;
	ImageIcon icbusSeat= new ImageIcon(this.getClass().getResource("/BusSeat.png"));
	ImageIcon icseated= new ImageIcon(this.getClass().getResource("/seated.png"));


	JLabel lbbusInfo;
	JLabel label;
	JButton bbusStop[][] = new JButton[13][10];
	JLabel lbbusStop[][] = new JLabel[13][10];
	JLabel lbmainScreenBar = new JLabel(icmainScreenBar);
	JButton bfoldButton = new JButton(icfold);
	JLabel lbbusPassenger;

	Calendar calendar1 = Calendar.getInstance();
	int year = calendar1.get(Calendar.YEAR);
	int month = calendar1.get(Calendar.MONTH);
	int day = calendar1.get(Calendar.DAY_OF_MONTH);
	int hour = calendar1.get(Calendar.HOUR_OF_DAY);
	int min = calendar1.get(Calendar.MINUTE);
	int sec = calendar1.get(Calendar.SECOND);
	Timer timer;
	JLabel lbPresent;

	boolean menubarVisible = true;

	int i = 0, j = 0;
	int count = 0;
	BusAPI busapi = new BusAPI();

	int x, y;
	//int init_x=1100, init_y=;//초기버스
	Thread th;

	ArrayList Bus_List = new ArrayList();
	ArrayList BusStop_List = new ArrayList();
	ArrayList BusRoad_List=new ArrayList();
	ArrayList BusPassenger_List=new ArrayList();
	ArrayList BusSeat_List = new ArrayList();


	Bus bus; // 버스 접근 키
	BusStop busStop;
	BusRoad busRoad;


	static int time;
	static int busCnt;
	static int busStopCnt;
	
	int gap, linecnt1=0, bus_speed=1, many; //버스 개수, 홀/짝수줄 인식, 버스 속도, 한 줄 버스 개수
	int busgap[] = new int[3]; //버스 간 간격
	int list1_x[] = { 34, 211, 34, 211, 34, 211, 34, 211, 34, 211, 34, 80, 172, 214, 34, 211, 34, 80, 172, 214, 34, 211, 34, 80, 126, 172, 211 };
	int list1_y[] = { 103, 103, 148, 148, 194, 194, 240, 240, 286, 286, 353, 353, 353, 353, 397, 397, 397, 397, 440, 440, 484, 484, 484, 484, 484 };

	//BusAlarmScreen.DBBus dbbus = new BusAlarmScreen.DBBus();
	//JLabel lbseated[];
	
	ActionListener busStopListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			BusStop bs = (BusStop)e.getSource();
			if (e.getSource() instanceof BusStop) {
				final Frame frbusStop = new Frame("busStop");
				JPanel p= new JPanel();
				JLabel time=new JLabel("남은 시간 : ");
				JLabel waiting_passenger;

				waiting_passenger=new JLabel("기다리는 승객 수 : "+bs.ride_passenger);
				p.add(waiting_passenger);
				
				p.setBackground(Color.WHITE);
				p.add(time);
				
				frbusStop.add(p);
				frbusStop.setVisible(true);
				frbusStop.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						frbusStop.setVisible(false);
						frbusStop.dispose();
					}
				});
				frbusStop.setSize(400, 250);
				frbusStop.setLocation(200, 200);
				frbusStop.setResizable(false);
				frbusStop.setLocationRelativeTo(null);
			}
			
		}
	};
	ActionListener busListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			Bus b = (Bus)e.getSource();
		if (e.getSource() instanceof Bus) {
			final Frame frbusStop = new Frame("Bus");
//			String low_floor;
					
			JPanel p =new JPanel();
			p.setLayout(null);
			p.setBackground(Color.WHITE);
			
			
//			if(bus.busPassenger>24)
//			{
//				for(int i=0; i<24;i++)
//				{
//					JLabel jb=new JLabel();
//					jb.setIcon(icseated);
//					jb.setBounds(list1_x[i], list1_y[i],47,40);
//					p.add(jb);
//				}
//			}
//			for(int i=0; i<bus.busPassenger;i++)
//			{
//				JLabel jb=new JLabel();
//				jb.setIcon(icseated);
//				jb.setBounds(list1_x[i], list1_y[i],47,40);
//				p.add(jb);
//			}
			JLabel lbseated[]=new JLabel[b.busPassenger];
			System.out.println("버스 승객 : "+b.busPassenger);
			
			if(b.busPassenger>23)
			{
				for(int i=0; i<24;i++)
				{
					lbseated[i]=new JLabel(icseated);
					lbseated[i].setBounds(list1_x[i], list1_y[i],47,40);
					p.add(lbseated[i]);
					System.out.println("랄");
				}
			}
			else
			{
				for(int i=0; i<b.busPassenger;i++)
				{
					lbseated[i]=new JLabel(icseated);
					lbseated[i].setBounds(list1_x[b.busSeat_numbers[i]], list1_y[b.busSeat_numbers[i]], 47, 40);
					p.add(lbseated[i]);
				}
			}
			
			JLabel lbbusSeat = new JLabel(icbusSeat);
			lbbusSeat.setBounds(15,10,263,523);
			p.add(lbbusSeat);
			
			JLabel passenger=new JLabel("현재 승객수 : "+Integer.toString(b.busPassenger));//+Integer.toString(bus.busPassenger)
			passenger.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
//			JLabel number=new JLabel("버스 번호 : ");//bus.bnum
			
//			JLabel low_bus=new JLabel("저상 여부 : ");//+bus.bfloor
//			JLabel cnt_seat=new JLabel("좌석 총 개수 : ");//+ bus.bseat+"개"
//			JLabel left_seat=new JLabel("남은 좌석 수 : ");
			passenger.setBounds(19, 550, 166, 40);
//			number.setBounds(19, 530, 30, 200);
//			low_bus.setBounds(200, 530, 30, 100);
//			cnt_seat.setBounds(19, 570, 30, 100);
//			left_seat.setBounds(200, 570, 30, 100);

			p.add(passenger);
//			p.add(number);
//			p.add(low_bus);
//			p.add(cnt_seat);
//			p.add(left_seat);
			frbusStop.add(p);
			frbusStop.setVisible(true);
			
			frbusStop.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					frbusStop.setVisible(false);
					frbusStop.dispose();
				}
			});
			frbusStop.setSize(300, 700);
			frbusStop.setLocation(200, 200);
			frbusStop.setResizable(false);
			frbusStop.setLocationRelativeTo(null);
			}
		}
	};

	public BusPanel(BusAlarm busalarm) {

		super(true);
		setLayout(null);
		setBackground(new Color(255, 243, 225));
		label = new JLabel();
		label.setBackground(Color.YELLOW);
		label.setBounds(0, 0, 1280, 720);

		JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 1000, 80, 0, 1500);
		vbar.setBounds(1245, 0, 26, 686);
		vbar.addAdjustmentListener(new MyAdjustmentListener());
		add(vbar);
		add(label);

		lbPresent = new JLabel(year + "-" + month + "-" + day + "   " + hour + ":" + min + ":" + sec,
				SwingConstants.RIGHT);
		lbPresent.setVerticalAlignment(SwingConstants.TOP);
		lbPresent.setBounds(1010, 5, 220, 40);
		lbPresent.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(lbPresent);

		timer = new Timer(1000, this);
		timer.setInitialDelay(0);
		timer.start();

		busapi.GetBusPassengerInfo(hour);//현재 시각에 따른 버스 승객수를 api에서 받아오기

		for (i = 0; i < 13; i++) {
			//버스정류장 
			for (j = 0; j < 10; j++) {
				
				lbbusStop[i][j] = new JLabel();
				lbbusStop[i][j].setText("정류장"+count);// busapi.GetBusStopInfo(count)
				count++;
				if(i%2!=0){				
					lbbusStop[i][j].setBounds(115 * (9-j) + 77, 118 * i + 226, 110, 60);
					add(lbbusStop[i][j]);
					busStop=new BusStop(115 * (9-j) + 90, 118 * i + 227,busStopCnt);
					busStopCnt++;			
				}
				else{
					lbbusStop[i][j].setBounds(115 * j + 77, 118 * i + 226, 110, 60);
					add(lbbusStop[i][j]);
					busStop=new BusStop(115 * j + 90, 118 * i + 227,busStopCnt);
					busStopCnt++;
				}
				busStop.setIcon(icbusStop);
				busStop.setBounds(busStop.pos.x, busStop.pos.y,16,16);
				BusAlarm.setButton(busStop);
				BusStop_List.add(busStop);
				add(busStop);

				busStop.addActionListener(busStopListener);
				
			}
			//버스 도로 정체 정보
			for (j = -1; j < 10; j++) {
				int randomRoad = (int) (Math.random() * 3);
				
				if(j==-1){
					if(i!=0 && i%2!=0)
					{
						if (randomRoad == 0) {
							 icbusRoad = half_icbusRoad_red;
						} else if (randomRoad == 1) {
							icbusRoad = half_icbusRoad_yellow;
						} else {
							icbusRoad = half_icbusRoad_green;
						}
						busRoad = new BusRoad(10, 118 * i + 228,randomRoad+1);//1,2,3
						busRoad.setIcon(icbusRoad);
						busRoad.setBounds(10, 118 * i + 228, 120, 12);
						BusAlarm.setButton(busRoad);
						add(busRoad);
						BusRoad_List.add(busRoad);
						
						busRoad = new BusRoad(10, 118 *(i+1) + 228,randomRoad+1);//1,2,3
						busRoad.setIcon(icbusRoad);
						busRoad.setBounds(10, 118 *(i+1) + 228, 120, 12);
						BusAlarm.setButton(busRoad);
						add(busRoad);
						BusRoad_List.add(busRoad);
					}
				}
				else if(j==9){
					if(i!=12 && i%2==0)
					{
						if (randomRoad == 0) {
							 icbusRoad = half_icbusRoad_red;
						} else if (randomRoad == 1) {
							icbusRoad = half_icbusRoad_yellow;
						} else {
							icbusRoad = half_icbusRoad_green;
						}
						busRoad = new BusRoad(1105, 118 * i + 228,randomRoad+1);//1,2,3
						busRoad.setIcon(icbusRoad);
						busRoad.setBounds(1105, 118 * i + 228, 120, 12);
						BusAlarm.setButton(busRoad);
						add(busRoad);
						BusRoad_List.add(busRoad);
						
						busRoad = new BusRoad(1105, 118 * (i+1) + 228,randomRoad+1);//1,2,3
						busRoad.setIcon(icbusRoad);
						busRoad.setBounds(1105, 118 * (i+1) + 228, 120, 12);
						BusAlarm.setButton(busRoad);
						add(busRoad);
						BusRoad_List.add(busRoad);
					}
				}
				else{
					if (randomRoad == 0) {
						 icbusRoad = icbusRoad_red;
					} else if (randomRoad == 1) {
						icbusRoad = icbusRoad_yellow;
					} else {
						icbusRoad = icbusRoad_green;
					}
					busRoad = new BusRoad(115 * j + 95, 118 * i + 228,randomRoad+1);//1,2,3
					busRoad.setIcon(icbusRoad);
					busRoad.setBounds(115 * j + 95, 118 * i + 228, 120, 12);
					BusAlarm.setButton(busRoad);
					add(busRoad);
					BusRoad_List.add(busRoad);
				}
				
			}				

		}
		addMenu();

		
		for(int i=0; i<busapi.BusPassengerRide_List.size(); i++)
		{			
			busStop= (BusStop)BusStop_List.get(i);
			busStop.setBusRidePassenger(busapi.BusPassengerRide_List.get(i));
			busStop.setBusAlightPassenger(busapi.BusPassengerAlight_List.get(i));
		}

		init();
		start();
	}

	class MyAdjustmentListener implements AdjustmentListener {
		public void adjustmentValueChanged(AdjustmentEvent e) {

			for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 10; j++) {
					if(i%2!=0){				
						lbbusStop[i][j].setLocation(115 * (9-j) + 77, e.getValue() - (-118 * i + 774));
						add(lbbusStop[i][j]);
					}
					else{
						lbbusStop[i][j].setLocation(115 * j + 77, e.getValue() - (-118 * i + 774));
						add(lbbusStop[i][j]);
					}
				}
			}
			for(int i=0; i<BusStop_List.size();++i)
			{
				busStop=(BusStop)(BusStop_List.get(i));
				busStop.setBounds(busStop.pos.x,busStop.pos.y+ lbbusStop[1][1].getY() - 345,16,16);
				//System.out.println(busStop.pos.x+" "+busStop.pos.y);//90,205,320,435,550 ..227,345,463,581,699
				add(busStop);
			}
			for(int i=0; i<BusRoad_List.size();++i)
			{
				busRoad=(BusRoad)(BusRoad_List.get(i));
				busRoad.setBounds(busRoad.pos.x, busRoad.pos.y+lbbusStop[1][1].getY()-345, 120, 12);	
				//System.out.println(busRoad.pos.x +" "+busRoad.pos.y);//95,210,325,440....228,346,464
				add(busRoad);
			}
			
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

	public void init() { 
		x = 50;//버스 초기위치
		y = 130;
	}

	public void start() {
		th = new Thread(this);
		th.start();
	}

	public void BusGap() { //시간대별로 버스 총 개수 정하기
		if((7<=hour && hour<=9) || (12<=hour && hour<=14) || (18<=hour && hour<=20)) { //버스 많을 시간(아침, 점심, 저녁)
			gap=40;
		}
		else if((5<=hour&& hour<=6) || (22<=hour && hour<=24)) { //없음 
			gap=20;
		}
		else { //평균
			gap=30;
		}
	}
	
	public void changexy() { //x, y좌표 주기
		x=10;
		y+=118;

		many = (int)((Math.random()*2)+2);
		//System.out.println(many);
		for(i=0; i<many; i++) { //한 줄에 버스 2, 3개정도
			if(many == 3) {
				busgap[0]=(int)((Math.random()*300)+100); 
				busgap[1]=(int)((Math.random()*200)+400); 
				busgap[2]=(int)((Math.random()*300)+700); 
			}
			else if(many == 2) {
				busgap[0]=(int)((Math.random()*300)+100); 
				busgap[1]=(int)((Math.random()*500)+500); 
			}
			x=busgap[i];
			
			BusProcess();
			if(linecnt1%2==0) { //뒤로 가기
				bus.busDir=-bus_speed;
			}
		}
		linecnt1++;
	}
		
	
	public void run() {
		try {
			BusGap(); //버스 몇 대 세팅해놀지 시간별로 정해줌	
			for(int i=0; i<gap;i++)
			{
				changexy(); //좌표값 주기
			}

			x = 50;//버스 초기위치
			y = 130;
			while (true) {
				BusProcess();
				for (int i = 0; i < Bus_List.size(); ++i) {
					bus = (Bus) (Bus_List.get(i));
					lbbusPassenger=(JLabel)(BusPassenger_List.get(i));
					
					bus.move();
					bus.setBounds(bus.pos.x, bus.pos.y +lbbusStop[1][1].getY() - 315, 48, 65);
					lbbusPassenger.setBounds(bus.pos.x+20, bus.pos.y + lbbusStop[1][1].getY() - 300, 50, 20);
					
					//System.out.println(bus.pos.x+" "+bus.pos.y);
					//130 248 366 118씩
					
					for(int j=0; j<BusRoad_List.size();j++)
					{
						busRoad=(BusRoad)(BusRoad_List.get(j));
						
						if(bus.line%2==0){//짝수줄
							if((bus.pos.x-115==busRoad.pos.x)&& (bus.pos.y+98==busRoad.pos.y)){
								//System.out.println("2  "+busRoad.busType);
								//bus.busSpeed=busRoad.busType;
							}
						}
						else
						{
							if((bus.pos.x+10==busRoad.pos.x)&& (bus.pos.y+98==busRoad.pos.y)){
								//System.out.println(bus.pos.x+10+", "+busRoad.pos.x);

								//System.out.println(busRoad.busType);
								//bus.busSpeed=busRoad.busType;
							}
//							if((bus.pos.x-40>=busRoad.pos.x && bus.pos.x-45<=busRoad.pos.x) && (bus.pos.y+98==busRoad.pos.y))
//							{
								//System.out.println("얍");
								//bus.busSpeed=1;
								//System.out.println(bus.busSpeed);
//							}
						}
						
						
					}
					for(int j=0; j<BusStop_List.size();j++)
					{
						busStop=(BusStop)(BusStop_List.get(j));
						if((bus.pos.x+10==busStop.pos.x)  && bus.pos.y+97==busStop.pos.y )
						{
							if(bus.flag==true)
							{
								bus.flag=false;
								bus.arriveBus(busStop.ride_passenger,busStop.alight_passenger);//버스 도착하면 정류장에 있던 사람들은 버스에 타고, 버스에서는 승객이 하차한다.
								lbbusPassenger.setText(""+bus.busPassenger);						
								bus.seat(bus.busPassenger);
								
								//System.out.println(bus.pos.x+10+", "+busStop.pos.x);

								if(busStop.ride_passenger!=0)
								{
									//busStop.ride_passenger =(int)( Math.random()*5)+(busStop.ride_passenger-2);//시간에 따른 busStop의 탑승객수를 random으로 발생시켜 현실성 반영.
									busStop.increaseBusPassenger(busStop.ride_passenger);
								}
								
							}
						}
						if(bus.pos.x==busStop.pos.x)
						{
							bus.flag=true;
						}
					}
					add(bus);
				}
				Thread.sleep(50);
				time++;

			}
		} catch (Exception e) {
		}
	}

	public void BusProcess() { // 버스 처리 메소드

		if (time % 1000 == 0) {
			//DBBus.insertDB();
			bus = new Bus(x, y,busCnt); // 좌표 체크하여 넘기기
			busCnt++;
//			bus.bnum=dbbus.bus_num;
//			bus.bfloor=dbbus.low_floor_bus;
//			bus.bseat=dbbus.seat_num;
			
			Bus_List.add(bus); // 버스 추가
			bus.setIcon(icbusIcon);
			bus.setBounds(bus.pos.x, bus.pos.y + lbbusStop[1][1].getY() - 315, 48, 65);
			BusAlarm.setButton(bus);
			bus.addActionListener(busListener);
			add(bus);
			
			lbbusPassenger=new JLabel(""+bus.busPassenger);
			lbbusPassenger.setBounds(bus.pos.x+20, bus.pos.y + lbbusStop[1][1].getY() - 290, 50, 20);
			lbbusPassenger.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			add(lbbusPassenger);
			BusPassenger_List.add(lbbusPassenger);

		}
	}

	public void addMenu() {
		// 메뉴바
		lbbusInfo = new JLabel();
		lbbusInfo.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		lbbusInfo.setText("버스정보");// busapi.GetBusInfo()
		lbbusInfo.setSize(500, 100);
		lbbusInfo.setLocation(0, 0);
		add(lbbusInfo);

		lbmainScreenBar.setSize(1280, 120);
		lbmainScreenBar.setLocation(0, 0);
		add(lbmainScreenBar);

		bfoldButton.setIcon(icfold);
		bfoldButton.setSize(90, 26);
		bfoldButton.setLocation(BusAlarm.SCREEN_W / 2 - 45, 120);
		BusAlarm.setButton(bfoldButton);
		bfoldButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				if (menubarVisible == false) {
					lbmainScreenBar.setVisible(true);
					lbbusInfo.setVisible(true);
					menubarVisible = true;
					bfoldButton.setLocation(BusAlarm.SCREEN_W / 2 - 45, 120);
				} else {
					lbmainScreenBar.setVisible(false);
					lbbusInfo.setVisible(false);
					menubarVisible = false;
					bfoldButton.setLocation(BusAlarm.SCREEN_W / 2 - 45, 0);
				}

			}
		});
		add(bfoldButton);
	}

}
