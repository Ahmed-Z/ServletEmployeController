package pack1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EmployéDbUtil {
	
	private static DataSource dataSource;
	
	public EmployéDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	public static List<Employé> getEmployé() throws Exception {
		List<Employé> Employés = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;
		try {
			
			myConn = dataSource.getConnection();
			String sql = "select * from employé order by nom";
			myStmt = myConn.createStatement();
			myRes = myStmt.executeQuery(sql);
			while(myRes.next()) {
				int id = myRes.getInt("id");

				String nom = myRes.getString("nom");
				String prenom = myRes.getString("prenom");
				String email = myRes.getString("email");
				Employé tempemployé = new Employé(id,nom,prenom,email);
				Employés.add(tempemployé);
			}
			return Employés;	
		}
		finally {
			close(myConn,myStmt,myRes);
		}
		
	}

	public static void addEmployé(Employé employé) throws Exception {
		try {
			Connection myConn = null;
			PreparedStatement myStmt = null;
			myConn = dataSource.getConnection();
			String sql = "insert into employé " + "(nom, prenom, email)" + "values(?,?,?)";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, employé.getNom());
			myStmt.setString(2, employé.getPrenom());
			myStmt.setString(3, employé.getEmail());
			
			myStmt.execute();
			
		}
		finally {
			//close(myConn,myStmt,null);
		}
	}
	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRes) {
		try {
			if (myRes!=null) {
				myRes.close();
			}
			if (myStmt!=null) {
				myStmt.close();
			}
			if (myConn!=null) {
				myConn.close();
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	public static Employé getEmployé(String employeId) throws Exception {
		Employé employé = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;
		int employeID;
		try {
			//convert id to int
			employeID = Integer.parseInt(employeId);
			//get connection to db
			myConn = dataSource.getConnection();
			//create sql to get employe
			String sql = "select * from employé where id=?";
			//crete stmt
			myStmt = myConn.prepareStatement(sql);
			//set param
			myStmt.setInt(1, employeID);
			//exec stmt
			myRes = myStmt.executeQuery();
			// retrieve data
			if (myRes.next()) {
				String nom = myRes.getString("nom");
				String prenom = myRes.getString("prenom");
				String email = myRes.getString("email");
				
				employé = new Employé(employeID,nom,prenom,email);
			}
			else {
				throw new Exception("Could not find Employé ID : "+ employeId);
			}
			return employé;
		}
		finally {
			close(myConn,myStmt,myRes);
		}
		
		
	}
	public static void updateEmploye(Employé employe) throws Exception {
		try {
			Connection myConn = null;
			PreparedStatement myStmt = null;
			myConn = dataSource.getConnection();
			
			String sql = "update employé " + "set nom=?, prenom=?, email=? " + "where id=?";
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, employe.getNom());
			myStmt.setString(2, employe.getPrenom());
			myStmt.setString(3, employe.getEmail());
			myStmt.setInt(4, employe.getId());
			myStmt.execute();
		}
		finally {
			//close(myConn,myStmt,null);
		}
		
	}
	public static void deleteEmploye(int id)throws Exception {
		try {
			String ID = String.valueOf(id);
			Connection myConn = null;
			PreparedStatement myStmt = null;
			myConn = dataSource.getConnection();
			String sql = "delete from employé where id=" + ID;
			myStmt = myConn.prepareStatement(sql);
			//myStmt.setString(1,ID);
			myStmt = myConn.prepareStatement(sql);
			myStmt.execute();
		}
		finally {
			
		}
		
	}
	

}
