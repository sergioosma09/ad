package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GCategoriaMain {

	private static Scanner scanner = new Scanner(System.in);
	private static Connection connection;



	public static void main(String[] args) throws SQLException {
		connection= DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		Menu.create("Menú Categoría").exitWhen("0 - Salir").add("1 - Nuevo", GCategoriaMain::nuevo).add("2 - Editar",
				GCategoriaMain::editar).loop();
		

//	List<Action> actions = new ArrayList<>();
//	actions.add( () -> exit = true );
//	actions.add( CategoriaMain::nuevo );
//	actions.add( CategoriaMain::editar );
//	
//	while (!exit) {
//	System.out.print("0 - Salir\n1 - Nuevo\n2 - Editar\nElige opción: ");
//	int option = Integer.parseInt(scanner.nextLine());
//	actions.get(option).execute();
//	}
		Load();
		editar();
	}

	public static void nuevo() {
		System.out.println("Método nuevo");
	}

	public static void editar() {
		System.out.println("Método editado");
		int id = ScannerHelper.getInt("Id: ");
	}


	

	private static void insert() throws SQLException {
		String insertSql = "insert into categoria (nombre) values (?)";
		PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
		preparedStatement.setObject(1, "categoria " + LocalDateTime.now());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	private static void delete(int id) {
		String deleteSql = "delete from categoria where id=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(deleteSql);
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
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(selectSql);
		while (resultSet.next())
			System.out.printf("%s %s\n", resultSet.getObject(1), resultSet.getObject(2));
		statement.close(); // implicit resultset.close()

	}

	private static void Update() throws SQLException {
		String updateSql = "update categoria set nombre=@nombre where id=@id";
		PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

}
