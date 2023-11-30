package Practice;

import java.sql.*;

public class EmployeeDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");			
		}
		catch(ClassNotFoundException ex) {
			System.out.println("Problem in loading MS Access JDBC driver");
		}
		
		try {
			String msAccDB = "Employee.accdb"; // database is in the project
			String dbURL = "jdbc:ucanaccess://" + msAccDB;
			conn = DriverManager.getConnection(dbURL);
			stat = conn.createStatement();
			
			
			String insertSQL = "INSERT INTO Emp (Name,Salary) values ('David',90000)";
			stat.executeUpdate(insertSQL);
			
			rs = stat.executeQuery("select * from Emp");
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				double sal = rs.getDouble(3);
				System.out.println(id + " " + name + " " + sal);
			}
			
			String updateSQL = "UPDATE Emp set Salary = 95000 where name = 'David'";
			stat.executeUpdate(updateSQL);
			System.out.println("after updating");
			ResultSet rs1 = stat.executeQuery("select * from Emp");
			while(rs1.next()) {
				int id = rs1.getInt(1);
				String name = rs1.getString(2);
				double sal = rs1.getDouble(3);
				System.out.println(id + " " + name + " " + sal);
			}
			
			String delet = "delete from Emp where name = 'David'";
			stat.executeUpdate(delet);
			System.out.println("after deleting");
			ResultSet rs2 = stat.executeQuery("select * from Emp");
			while(rs2.next()) {
				int id = rs2.getInt(1);
				String name = rs2.getString(2);
				double sal = rs2.getDouble(3);
				System.out.println(id + " " + name + " " + sal);
			}
			rs2.close();
			
		}
		catch(SQLException sqex) {
			sqex.printStackTrace();
		}
		finally {
			try {
				if(conn!=null) {
					rs.close();	
					stat.close();
					conn.close();
				}
			}
			catch(SQLException sqex) {
				sqex.printStackTrace();
			}
		}
	}

}
