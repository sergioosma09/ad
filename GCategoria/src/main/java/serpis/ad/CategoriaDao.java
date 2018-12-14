package serpis.ad;

import java.math.BigInteger;
import java.sql.PreparedStatement;  
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
	private static int insert(Categoria categoria) throws SQLException {
	String insertSql = "insert into categoria (nombre) values (?)";
	PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(insertSql);
	preparedStatement.setObject(1, categoria.getNombre());
	int rowCount=preparedStatement.executeUpdate();
	preparedStatement.close();
	return rowCount;
}

public static int delete(long id) throws SQLException {
	String deleteSql = "delete from categoria where id=?";
	PreparedStatement preparedStatement;
	preparedStatement = App.getInstance().getConnection().prepareStatement(deleteSql);
	preparedStatement.setLong(1, id);
	return preparedStatement.executeUpdate();

}

public static Categoria load(long id) throws SQLException {
	Categoria categoria=new Categoria();
	String selectSql = "select id,nombre from categoria where id=?";
	PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(selectSql);
	preparedStatement.setObject(1, id);
	ResultSet resultSet= preparedStatement.executeQuery(selectSql);
	if (resultSet.next()==false) 
		return null;
	else
		categoria.setId( ((BigInteger)resultSet.getObject("id")).longValue());
		categoria.setNombre((String) resultSet.getObject("nombre"));
		return categoria;
	
}

private static int update(Categoria categoria) throws SQLException {
	String updateSql = "update categoria set nombre=? where id=?";
	PreparedStatement preparedStatement = App.getInstance().getConnection().prepareStatement(updateSql);
	preparedStatement.setObject(1, categoria.getNombre());
	preparedStatement.setObject(2, categoria.getId());
	int rowCount=preparedStatement.executeUpdate();
	preparedStatement.close();
	return rowCount;
}

public static int save (Categoria categoria) throws SQLException{
	if(categoria.getId()==0)
		return insert(categoria);
	else
		return update(categoria);
}
private static String selectAll = "select id, nombre from categoria order by id";
public static List<Categoria> getAll() throws SQLException{	
		List<Categoria> categorias=new ArrayList<>();
		Statement statement = App.getInstance().getConnection().createStatement();
		ResultSet resultSet = statement.executeQuery(selectAll);
		while(resultSet.next()) {
			Categoria categoria=new Categoria();
			categoria.setId( ((BigInteger)resultSet.getObject("id")).longValue());
			categoria.setNombre((String) resultSet.getObject("nombre"));
			categorias.add(categoria);
		}
		statement.close();
		return categorias;

	}
}
