package serpis.ad;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PedidoMain {
	private static EntityManagerFactory entityManagerFactory;
	public static void main(String[] args) {
		entityManagerFactory= Persistence.createEntityManagerFactory("serpis.ad.hmysql");
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Categoria> categorias=entityManager.createQuery("select c from Categoria c", Categoria.class).getResultList();
		List<Cliente> clientes=entityManager.createQuery("select cl from Cliente cl", Cliente.class).getResultList();

		
		Articulo articulo=newArticulo();
		//articulo=entityManager.find(Articulo.class, 4L);
		Pedido pedido=newPedido();
		
		articulo.setCategoria(categorias.get(new Random().nextInt(categorias.size())));
		entityManager.persist(articulo);
		pedido.setCliente(clientes.get(new Random().nextInt(clientes.size())));
		entityManager.persist(pedido);
		
		show(articulo);
		showPedido(pedido);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		System.out.println("Añadido articulo. Pulsa Enter para continuar...");
		new Scanner(System.in).nextLine();
		
		//remove(articulo);
		
		doInJPA(entityManagerFactory, entityManager2 ->  {
			Articulo articulo2=entityManager2.getReference(Articulo.class, articulo.getId());
			entityManager2.remove(articulo2);
		});
		Articulo articulo3= doInJPA(entityManagerFactory, entityManager2 ->{
			
			return entityManager2.find(Articulo.class, 5L);	
		});
		show(articulo3);
		entityManagerFactory.close();

	}
	private static void show(Articulo articulo) {
		System.out.printf("%4s %-30s %-30s %s %n", articulo.getId(), articulo.getNombre(), articulo.getCategoria(), articulo.getPrecio());
	
	}
	private static void showPedido(Pedido pedido) {
		System.out.printf("%4s %-30s %-30s %n", pedido.getId(), pedido.getFecha(),pedido.getImporte());
	
	}
	
	private static void remove(Articulo articulo) {
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		articulo=entityManager.getReference(Articulo.class, articulo.getId());
		entityManager.remove(articulo);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	private static void doInJPA(EntityManagerFactory entityManagerFactory, Consumer<EntityManager> consumer) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		consumer.accept(entityManager);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	private static <R> R doInJPA(EntityManagerFactory entityManagerFactory, Function<EntityManager, R> function) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		R result= function.apply(entityManager);
		entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}
	
	public static Articulo newArticulo() {
		Articulo articulo=new Articulo();
		articulo.setNombre("nuevo " + LocalDateTime.now());
		articulo.setPrecio(new BigDecimal(1.5));
		return articulo;
	}
	
	private static Pedido newPedido() {
		Pedido pedido=new Pedido();
		pedido.setFecha(new Date());
		pedido.setImporte(9);
		return pedido;
	}

}
