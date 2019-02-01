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
		List<Pedido> pedidos=entityManager.createQuery("select p from Pedido p", Pedido.class).getResultList();
		List<Articulo> articulos=entityManager.createQuery("select a from Articulo a", Articulo.class).getResultList();
		
		Articulo articulo=newArticulo();
		Pedido pedido=newPedido();
		PedidoLinea pedidoLinea=newPedidoLinea();
		
		articulo.setCategoria(categorias.get(new Random().nextInt(categorias.size())));
		entityManager.persist(articulo);
		pedido.setCliente(clientes.get(new Random().nextInt(clientes.size())));
		entityManager.persist(pedido);
		pedidoLinea.setPedido(pedidos.get(new Random().nextInt(pedidos.size())));
		entityManager.persist(pedidoLinea);
		pedidoLinea.setArticulo(articulos.get(new Random().nextInt(articulos.size())));
		
		show(articulo);
		showPedido(pedido);
		showPedidoLinea(pedidoLinea);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		System.out.println("Añadido articulo. Pulsa Enter para continuar...");
		new Scanner(System.in).nextLine();
		
//		remove(articulo);
//		remove(pedido);
//		remove(pedidoLinea);
		
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
	private static void showPedidoLinea(PedidoLinea pedidoLinea) {
		System.out.printf("%4s %-30s %-30s %n", pedidoLinea.getId(), pedidoLinea.getPrecio(),pedidoLinea.getUnidades(),pedidoLinea.getImporte());
	
	}
	
	
	private static void remove(Articulo articulo) {
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		articulo=entityManager.getReference(Articulo.class, articulo.getId());
		entityManager.remove(articulo);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
private static void remove(Pedido pedido) {
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		pedido=entityManager.getReference(Pedido.class, pedido.getId());
		entityManager.remove(pedido);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
private static void remove(PedidoLinea pedidoLinea) {
	
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	entityManager.getTransaction().begin();
	
	pedidoLinea=entityManager.getReference(PedidoLinea.class, pedidoLinea.getId());
	entityManager.remove(pedidoLinea);
	
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
	private static PedidoLinea newPedidoLinea() {
		PedidoLinea pedidoLinea=new PedidoLinea();
		pedidoLinea.setPrecio(6);
		pedidoLinea.setUnidades(2);
		pedidoLinea.setImporte(9);
		return pedidoLinea;
	}

}
