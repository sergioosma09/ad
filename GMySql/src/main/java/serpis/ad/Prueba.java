package serpis.ad;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Prueba {
	private static Connection connection; 

	public static void main(String[] args) throws SQLException {
		//Connection connection= DriverManager.getConnection("jdbc:mysql://localhost/dbprueba?user=root&password=sistemas");
		connection= DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		insert();
		Statement statement=connection.createStatement();
		ResultSet resultSet= statement.executeQuery("select * from categoria");
		while (resultSet.next())
			System.out.printf("%s %s\n", resultSet.getObject(1), resultSet.getObject(2));
		statement.close(); //implicit resultset.close()
		connection.close();
	}
	private static void insert() throws SQLException {
		String insertSql="insert into categoria (nombre) values (?)";
		PreparedStatement preparedStatement=connection.prepareStatement(insertSql);
		preparedStatement.setObject(1, "categoria " + LocalDateTime.now());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	private static void delete() throws SQLException{
		String deleteSql="delete from {0} where {1}=@id ";
		PreparedStatement preparedStatement=connection.prepareStatement(deleteSql);
		preparedStatement.setObject(1, "id " + LocalDateTime.now());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
}
	


