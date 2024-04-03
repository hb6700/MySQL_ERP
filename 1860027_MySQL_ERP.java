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

	// 사원 정보를 모두 검색하기 위한 쿼리문
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
				System.out.println("1.확인	2.수정	3.삭제	4.종료");
				System.out.print("원하시는 메뉴를 선택해주세요 >> ");
			}
		keyCode = System.in.read();				//키보드의 키 코드를 입력 받음
		
		if (keyCode == 49) {					//키보드에서 1을 입력받을때(1. 확인) 
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
				
				System.out.println("번호 \t 이름 \t 직책 \t 부서 \t 이메일");
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
		}else if(keyCode == 50){					//키보드에서 2를 입력받을때(2. 수정) 
			String driver = "com.mysql.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
			Connection con = null;
			PreparedStatement pstmt = null;	
			String sname, sjobGrade, semail, stemp;
			int ndepartment;
			
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("사원 정보 입력하기");			
				System.out.println("이름 입력 >> ");
				sname = br.readLine();
				sname = br.readLine();	
				System.out.println("직책입력 >> ");
				sjobGrade = br.readLine();			
				System.out.println("부서번호 입력(10,20,30,...) >> ");
				stemp = br.readLine();
				ndepartment = Integer.parseInt(stemp);
				System.out.println("이메일 입력 >> ");
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
		}else if(keyCode == 51) {					//키보드에서 3을 입력받을때(3. 삭제) 
			String driver = "com.mysql.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
			Connection con = null;
			PreparedStatement pstmt = null;		
			
			String sname, sjobGrade, semail, stemp;
			int ndepartment;
			
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("삭제할 사원의 이름이 무엇인가요?");
				System.out.print("이름 입력 >> ");
				sname = br.readLine();
				sname = br.readLine();
				
				Class.forName(driver);
				
				con = DriverManager.getConnection(url, "root", "0000");
				
				pstmt = con.prepareStatement(sql3);
				
				pstmt.setString(1, sname);
				
				int rowCount = pstmt.executeUpdate();
				
				if(rowCount == 1) {
					System.out.println("삭제 성공");
				}else {
					System.out.println("삭제 실패");
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
		}else if(keyCode == 52) {					//키보드에서 4를 입력받을때(4. 종료) 
			run = false;							//while문을 종료하기 위해서 run변수에 false값 저장
			System.out.println("시스템 종료");
		}
		}
	}
}
