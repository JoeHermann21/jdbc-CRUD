package application;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import db.DB;
import db.DBIntegrityException;
import db.DbExcepiton;

public class Program {
	
	
	public static void main(String[] args) {
				
		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("update seller set BaseSalary = 2090 where DepartmentId = 1");
			
//			boolean x = true;
//			if (x = true) {
//				throw new SQLException("Fake error");
//			}
			
			int rows2 = st.executeUpdate("update seller set BaseSalary = 3090 where DepartmentId = 2");
			
			conn.commit();

			System.out.println("rows1 " + rows1);
			System.out.println("rows2 " + rows2);
			
			
		}catch (SQLException e){
			try {
				conn.rollback();
				throw new DbExcepiton("Transação não autorizada! Causada pelo erro " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbExcepiton("Erro ao voltar transação! Causada pelo erro " + e.getMessage());
			}
		}finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
}
