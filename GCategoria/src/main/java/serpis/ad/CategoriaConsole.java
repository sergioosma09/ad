package serpis.ad;

import java.util.List;
import java.util.Scanner;

public class CategoriaConsole {
	static Scanner tcl=new Scanner(System.in);
	public static long getId() {
		return ScannerHelper.getInt("Id: ");
	}
	public static void newCategoria(Categoria categoria) {
		System.out.print("Nombre ");
		String nombre=tcl.nextLine();
		categoria.setNombre(nombre);
	}
	public static void editCategoria(Categoria categoria) {
		show(categoria);
		System.out.print("Nombre ");
		String nombre=tcl.nextLine();
		categoria.setNombre(nombre);
	}
	public static void showIdNotExists() {
		System.out.println("El id no existe");
	}
	public static boolean deleteConfirm() {
		
		System.out.println("Quieres eliminar el id: s/n");
		String resultado=tcl.nextLine();
		if(resultado.equalsIgnoreCase("s"))
			return true;
		else
			return false;
	}
	public static void show(Categoria categoria) {
		System.out.println("Id:" + categoria.getId());
		System.out.println("Nombre:" + categoria.getNombre());
	}
	public static void showList(List<Categoria> categorias) {
		for(Categoria categoria: categorias) 
			System.out.printf("%s %s\n", categoria.getId(),categoria.getNombre());
		
	}
}
