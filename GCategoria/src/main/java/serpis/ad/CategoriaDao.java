package serpis.ad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

public class CategoriaDao {
	private static int insert(Categoria categoria) throws SQLException {
	String insertSql = "insert into categoria (nombre) values (?)";
	PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(insertSql);
	preparedStatement.setObject(1, categoria.getNombre());
	return preparedStatement.executeUpdate();
}

public static int delete(long id) throws SQLException {
	String deleteSql = "delete from categoria where id=?";
	PreparedStatement preparedStatement;
	preparedStatement = App.getInstance().getConnection().prepareStatement(deleteSql);
	preparedStatement.setLong(1, id);
	return preparedStatement.executeUpdate();

}

public static Categoria load(long id) throws SQLException {

	String selectSql = "select * from categoria";
	Statement statement = App.getInstance().getConnection().createStatement();
	ResultSet resultSet = statement.executeQuery(selectSql);
	while (resultSet.next())
		System.out.printf("%s %s\n", resultSet.getObject(1), resultSet.getObject(2));
	return (Categoria) resultSet;
	

}

private static int update(Categoria categoria) throws SQLException {
	String updateSql = "update categoria set nombre=@nombre where id=@id";
	PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(updateSql);
	return preparedStatement.executeUpdate();
}

public static int save (Categoria categoria) throws SQLException{
	if(categoria.getId()==0)
		return insert(categoria);
	else
		return update(categoria);
}
public static List<Categoria> getAll() throws SQLException{
	return null;
}
	
	
}
