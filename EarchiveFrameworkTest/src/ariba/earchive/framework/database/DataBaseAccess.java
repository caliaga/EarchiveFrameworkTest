package ariba.earchive.framework.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DataBaseAccess {
	
	private static Connection conn = null;
	
	private static void connectToDB(){
		
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "test";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "root";
		
		try {
			// Connect to BD
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			System.out.println("Conectado a la base de datos de konakart");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void disconnect(){
		try {
			// Cerrar la conexion
			conn.close();
			System.out.println("Desconectado de la base de datos de konakart");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<List<String>> getDataFromDB(String query, int numCols) {

		connectToDB();
		
		List<List<String>> rowsList = new ArrayList<List<String>>();
		int columns = numCols;
		
		// Prepare the Query
		Statement stmt;
		ResultSet rs;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query) ;

			while(rs.next()){
				List<String> l = new ArrayList<String>();
				for(int i=1; i<=columns; i++){
					l.add(rs.getString(i));
					//System.out.println(l.get(i-1));
				}
				//System.out.println("------------------------------");
				rowsList.add(l);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		disconnect();
		
		return rowsList;
	}
	
	public static void main(String args[]){
		
		connectToDB();
		List<List<String>> rows = new ArrayList<List<String>>();
		List<String> columns = new ArrayList<String>();
		String query = 	"select customers_email_address, customers_id " +
						"from customers "+
						"order by customers_id " +
						//"where customers_email_address = 'admin@konakart.com' " +
						"limit 5;";
		rows = getDataFromDB(query,2);
		
		
		for(int r=0; r<rows.size();r++){
			System.out.println("Fila # " + (r+1));
			columns = rows.get(r);
			for(int c=0; c<columns.size(); c++){
				System.out.println("Columna # " + (c+1)+ ": " + rows.get(r).get(c));
			}
		}
		
		disconnect();
	}

}
