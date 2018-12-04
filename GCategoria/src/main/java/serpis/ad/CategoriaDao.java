package serpis.ad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class CategoriaDao {
	private static void insert(Categoria categoria) throws SQLException {
	String insertSql = "insert into categoria (nombre) values (?)";
	PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(insertSql);
	preparedStatement.setObject(1, categoria.getNombre());
	preparedStatement.executeUpdate();
	preparedStatement.close();
}

private static void delete(int id) {
	String deleteSql = "delete from categoria where id=?";
	PreparedStatement preparedStatement;
	try {
		preparedStatement = App.getInstance().getConnection().prepareStatement(deleteSql);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	} catch (SQLException e) {
		System.out.println(e);
		System.out.println("No se puede por el foreign key");

	}
}

private static void Load() throws SQLException {

	String selectSql = "select * from categoria";
	Statement statement = App.getInstance().getConnection().createStatement();
	ResultSet resultSet = statement.executeQuery(selectSql);
	while (resultSet.next())
		System.out.printf("%s %s\n", resultSet.getObject(1), resultSet.getObject(2));
	statement.close(); // implicit resultset.close()

}

private static void Update() throws SQLException {
	String updateSql = "update categoria set nombre=@nombre where id=@id";
	PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(updateSql);
	preparedStatement.executeUpdate();
	preparedStatement.close();
}
	
	
}
