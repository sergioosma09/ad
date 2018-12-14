package serpis.ad;

import java.util.List;
import java.util.Scanner;

public class CategoriaConsole {
	static Scanner tcl=new Scanner(System.in);
	public static long getId() {
		return -1;
	}
	public static void newCategoria(Categoria categoria) {
		
	}
	public static void editCategoria(Categoria categoria) {
		
	}
	public static void idNotExists() {
		long id= CategoriaConsole.getId();
		if(id==0)
			System.out.println("El id no existe");
	}
	public static boolean deleteConfirm() {
		
		System.out.println("Quieres eliminar el id: s/n");
		String resultado=tcl.nextLine();
		if(resultado.equalsIgnoreCase("s"))
			return false;
		else
			return true;
	}
	public static void show(Categoria categoria) {
		System.out.printf("%s %s\n", categoria.getId(),categoria.getNombre());
	}
	public static void showList(List<Categoria> categorias) {
		for(Categoria categoria: categorias) 
			System.out.printf("%s %s\n", categoria.getId(),categoria.getNombre());
		
	}
}
