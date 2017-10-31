package BusAlarmScreen;

import java.sql.Connection; //DB�� �����ϱ� ���� Ŭ����
import java.sql.DriverManager; //JDBC����̹� Ŭ����
import java.sql.ResultSet; //DB�� ����� ��Ÿ���� Ŭ����
import java.sql.SQLException; //DB���� Ŭ����
import java.sql.Statement; //������ ���� �� ��� ���� Ŭ����

public class DBBus {
	static Connection conn = null; // DB�� �����ϱ� ���� conn��ü ����
	static Statement stmt = null; // ����� ��Ʈ�� �������� �������� �����ִ� stmt��ü ����
	static ResultSet rs = null; // ������ ���� �� ����� �������ִ� rs��ü ����

	String driverName = "com.mysql.jdbc.Driver"; // DB����̹� �˻�
	String url = "jdbc:mysql://localhost:3306/dbbus";
	String user = "root";
	String password = "apmsetup";

	static int bus_num;
	static int low_floor_bus;
	static int seat_num;
	static int cnt = 0;

	public DBBus() {
		try {
			Class.forName(driverName); // �ε�
			conn = DriverManager.getConnection(url, user, password); // ����
			System.out.println("DB Connection OK");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB Driver Error!");
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("DB Connection Error!");
		}
	} // DBBus

	public static void insertDB() {
		try {
			stmt = conn.createStatement(); // ���� ������ ���� stmt����
			double randomValue = Math.random();
			bus_num = ((int) (randomValue * 8999)) + 1000;// ������ȣ
			low_floor_bus = (int) (randomValue * 2) + 2;// ���� ����

			if (low_floor_bus == 2) {// �¼� ����
				low_floor_bus = 1;
				seat_num = 24;
			} else {
				low_floor_bus = 0;
				seat_num = 26;
			}

			String sql = "insert into bus_info (bus_num, low_floor_bus, seat_num) values(" + bus_num + ", "
					+ low_floor_bus + ", " + seat_num + ")";
			String selectsql = "SELECT * FROM dbbus.bus_info";

			stmt.executeUpdate(sql); // DB�� ����

			rs = stmt.executeQuery(selectsql); // ���� ���� stmt��ü�� �̿��Ͽ� ���� ���� ��
												// rs�� ����� ����
			if (stmt.execute(selectsql)) {
				rs = stmt.getResultSet(); // ����� ������
			}

		} catch (java.sql.SQLException e) {
			System.out.println("Db��������");
		} catch (Exception ex) {
			// handle the error
			System.out.println("getresult error");
		}
		cnt = 0;
		try {
			while (rs.next()) { // rs�� ����� �����͵��� �о� ������� ������ ����
				cnt++;

				bus_num = rs.getInt("bus_num"); // ���� ��ȣ
				low_floor_bus = rs.getInt("low_floor_bus"); // �������
				seat_num = rs.getInt("seat_num"); // �¼� ��
				// System.out.println(bus_num + " " + low_floor_bus + " " +
				// seat_num); // �������
			}
		} catch (Exception ex) {
			// handle the error
			System.out.println("print error");
		}
	}
}
