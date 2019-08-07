package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DBIntegrityException;

public class Program {
	
	
	public static void main(String[] args) {
				
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = DB.getConnection();
			pst = conn.prepareStatement("delete from department where Id=?");
			
			pst.setInt(1, 1);

			
			int rowsAffected = pst.executeUpdate();
			
			System.out.println("Sucesso! " + rowsAffected + " linhas foram afetadas!" );
			
		}catch (SQLException e){
			throw new DBIntegrityException(e.getMessage());
		}finally {
			DB.closeStatement(pst);
			DB.closeConnection();
		}

	}
}
