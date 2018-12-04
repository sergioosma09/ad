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


	public static void main(String[] args) throws SQLException {
		App.getInstance().setConnection(DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas"));
		Menu.create("Menú Categoría").exitWhen("0 - Salir").add("1 - Nuevo", GCategoriaMain::nuevo).add("2 - Editar",
				GCategoriaMain::editar).loop();
		App.getInstance().getConnection().close();

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
		//Load();
		editar();
	}

	public static void nuevo() {
		System.out.println("Método nuevo");
	}

	public static void editar() {
		System.out.println("Método editado");
		int id = ScannerHelper.getInt("Id: ");
	}


	



}
