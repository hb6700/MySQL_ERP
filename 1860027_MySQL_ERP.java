import java.sql.*;
import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class practice {

	// ��� ������ ��� �˻��ϱ� ���� ������
	final static String sql1 = "select * from employee";
	final static String sql2 = "insert into employee(name, jobGrade, department, email) values(?,?,?,?)";
	final static String sql3 = "delete from employee where name=?";
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		boolean run = true;
		int speed = 0;
		int keyCode = 0;
		
		while(run) {
			if(keyCode != 13 && keyCode !=10) {
				System.out.println("1.Ȯ��	2.����	3.����	4.����");
				System.out.print("���Ͻô� �޴��� �������ּ��� >> ");
			}
		keyCode = System.in.read();				//Ű������ Ű �ڵ带 �Է� ����
		
		if (keyCode == 49) {					//Ű���忡�� 1�� �Է¹�����(1. Ȯ��) 
			String driver = "com.mysql.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, "root", "0000");
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql1);
				
				System.out.println("��ȣ \t �̸� \t ��å \t �μ� \t �̸���");
				System.out.println("-------------------------------------------------");
				while(rs.next()) {
					System.out.print(rs.getInt(1)); // no
					System.out.print("\t");
					System.out.print(rs.getString(2)); // name
					System.out.print("\t");
					System.out.print(rs.getString(3)); // jobGrade
					System.out.print("\t");
					System.out.print(rs.getInt(4)); // department
					System.out.print("\t");
					System.out.println(rs.getString(5)); // email				
				}
				
			}catch(Exception e) {
				System.out.println(e);
			}finally {
				try {
					if(rs != null) {
						rs.close();
					}
					if(stmt != null) {
						stmt.close();
					}
					if(con != null) {
						con.close();
					}
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}else if(keyCode == 50){					//Ű���忡�� 2�� �Է¹�����(2. ����) 
			String driver = "com.mysql.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
			Connection con = null;
			PreparedStatement pstmt = null;	
			String sname, sjobGrade, semail, stemp;
			int ndepartment;
			
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("��� ���� �Է��ϱ�");			
				System.out.println("�̸� �Է� >> ");
				sname = br.readLine();
				sname = br.readLine();	
				System.out.println("��å�Է� >> ");
				sjobGrade = br.readLine();			
				System.out.println("�μ���ȣ �Է�(10,20,30,...) >> ");
				stemp = br.readLine();
				ndepartment = Integer.parseInt(stemp);
				System.out.println("�̸��� �Է� >> ");
				semail = br.readLine();
				
				Class.forName(driver);
				
				con = DriverManager.getConnection(url, "root", "0000");
				
				pstmt = con.prepareStatement(sql2);
				
				pstmt.setString(1, sname);
				pstmt.setString(2, sjobGrade);
				pstmt.setInt(3, ndepartment);
				pstmt.setString(4, semail);
				
				int rowCount = pstmt.executeUpdate();
				
				System.out.println(rowCount);
				
			}catch(Exception e) {
				System.out.println(e);
			} finally {
				try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(con != null) {
						con.close();
					}
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}else if(keyCode == 51) {					//Ű���忡�� 3�� �Է¹�����(3. ����) 
			String driver = "com.mysql.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
			Connection con = null;
			PreparedStatement pstmt = null;		
			
			String sname, sjobGrade, semail, stemp;
			int ndepartment;
			
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("������ ����� �̸��� �����ΰ���?");
				System.out.print("�̸� �Է� >> ");
				sname = br.readLine();
				sname = br.readLine();
				
				Class.forName(driver);
				
				con = DriverManager.getConnection(url, "root", "0000");
				
				pstmt = con.prepareStatement(sql3);
				
				pstmt.setString(1, sname);
				
				int rowCount = pstmt.executeUpdate();
				
				if(rowCount == 1) {
					System.out.println("���� ����");
				}else {
					System.out.println("���� ����");
				}
				
			}catch(Exception e) {
				System.out.println(e);
			} finally {
				try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(con != null) {
						con.close();
					}
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}else if(keyCode == 52) {					//Ű���忡�� 4�� �Է¹�����(4. ����) 
			run = false;							//while���� �����ϱ� ���ؼ� run������ false�� ����
			System.out.println("�ý��� ����");
		}
		}
	}
}
