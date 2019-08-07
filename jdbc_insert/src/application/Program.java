package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {
	
	
	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			conn = DB.getConnection();
			pst = conn.prepareStatement("insert into seller (Name, Email, BirthDate, BaseSalary, DepartmentId) values (?, ?, ?, ?, ?)");
			//,Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, "Carlos Roxo");
			pst.setString(2, "carlso@gmail.com");
			pst.setDate(3, new java.sql.Date(sdf.parse("17/02/1998").getTime()));
			pst.setDouble(4, 3000.0);
			pst.setInt(5, 4);
			
//			pst = conn.prepareStatement("insert into department (Name) values ('D1'),('D2')", Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffected = 	pst.executeUpdate();
//			
//			if(rowsAffected > 0) {
//				ResultSet rs = pst.getGeneratedKeys();
//				while(rs.next()) {
//					int id = rs.getInt(1);
//					System.out.println("Sucesso! Id = " + id);
//				}
//			}else {
//				System.out.println("No rows affected! ");
//			}
//			
			System.out.println("Cadastrado com sucesso! Total de " + rowsAffected + " linhas afetadas");
		}
		catch (SQLException e) {
			e.printStackTrace();			
		}catch(ParseException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(pst);
			DB.closeConnection();
		}
		
	}
}
