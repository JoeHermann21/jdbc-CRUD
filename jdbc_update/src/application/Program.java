package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {
	
	
	public static void main(String[] args) {
				
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = DB.getConnection();
			pst = conn.prepareStatement("update seller set BaseSalary = BaseSalary + ? where DepartmentId=?");
			
			pst.setDouble(1, 200);
			pst.setInt(2, 2);
			
			int rowsAffected = pst.executeUpdate();
			
			System.out.println("Sucesso! " + rowsAffected + " linhas foram afetadas!" );
			
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			DB.closeStatement(pst);
		}

	}
}
