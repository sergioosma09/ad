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
	@FunctionalInterface
	interface DaoAction {
		void execute() throws SQLException;
	}

	public static void main(String[] args) throws SQLException {
		App.getInstance().setConnection(DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas"));
		Menu.create("Menú Categoría")
		.add("\t1 - Nuevo", () -> tryAction(GCategoriaMain::nuevo, "No se ha podido insertar."))
		.add("\t2 - Editar", () -> tryAction(GCategoriaMain::editar, "No se ha podido modificar"))
		.add("\t3 - Eliminar", () -> tryAction(GCategoriaMain::eliminar, "No se ha podido eliminar"))
		.add("\t4 - Consultar", () -> tryAction(GCategoriaMain::consultar, "No se ha podido realizar la consulta"))
		.add("\t5 - Listar", () -> tryAction(GCategoriaMain::listar, "No se ha podido realizar la consulta"))
		.exitWhen("\t0 - Salir")
		.loop();		
		App.getInstance().getConnection().close();
	}
	
	public static void tryAction(DaoAction daoAction, String errorMessage) {
		try {
			daoAction.execute();
		} catch (SQLException ex) {
			System.out.println(errorMessage);
		}
	}

	public static void nuevo() {
		Categoria categoria= new Categoria();
		CategoriaConsole.newCategoria(categoria);
		try{
			
			CategoriaDao.save(categoria);
		}catch(Exception e) {
			
		}
	}

	public static void editar(){	
		long id= CategoriaConsole.getId();
		
		try {
			Categoria categoria=CategoriaDao.load(id);
			if(categoria==null) {
				CategoriaConsole.idNotExists();
				return;
			}
			CategoriaConsole.editCategoria(categoria);		
			CategoriaDao.save(categoria);
				
		}catch(Exception e) {
			
		}
		
	}
	
	public static void eliminar() throws SQLException {
		long id= CategoriaConsole.getId();
		if(CategoriaConsole.deleteConfirm())
			CategoriaDao.delete(id);

	}
	
	public static void consultar() throws SQLException{
		long id= CategoriaConsole.getId();
		Categoria categoria=CategoriaDao.load(id);
		if(categoria==null) {
			CategoriaConsole.idNotExists();
			return;
		}
		CategoriaConsole.show(categoria);
	}
	
	public static void listar() throws SQLException{
		List<Categoria> categorias=CategoriaDao.getAll();
		CategoriaConsole.showList(categorias);
	}

}
