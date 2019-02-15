package serpis.ad;

import java.math.BigDecimal;
import java.sql.DriverManager;
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
import javax.persistence.PersistenceException;

import org.hibernate.cfg.JPAIndexHolder;

public class PedidoMain {
	private static EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		try {
			execute();
		} catch (PersistenceException ex) {
			PersistenceExceptionHelper.show(ex);
		}
	}

	private static void execute() {
		App.getInstance().setEntityManagerFactory(Persistence.createEntityManagerFactory("serpis.ad.hmysql"));

//		EntityManager entityManager=entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();

		List<Categoria> categorias = JpaHelper.execute(entityManager -> {
			return entityManager.createQuery("select c from Categoria c", Categoria.class).getResultList();
		});
		List<Cliente> clientes = JpaHelper.execute(entityManager -> {
			return entityManager.createQuery("select cl from Cliente cl", Cliente.class).getResultList();
		});
		List<Pedido> pedidos = JpaHelper.execute(entityManager -> {
			return entityManager.createQuery("select p from Pedido p", Pedido.class).getResultList();
		});
		List<Articulo> articulos = JpaHelper.execute(entityManager -> {
			return entityManager.createQuery("select a from Articulo a", Articulo.class).getResultList();
		});

		JpaHelper.execute(entityManager -> {
			Articulo articulo = new Articulo();
			articulo.setNombre("nuevo " + LocalDateTime.now());
			articulo.setPrecio(new BigDecimal(1.5));
			entityManager.persist(articulo);
		});

		JpaHelper.execute(entityManager -> {
			Pedido pedido = new Pedido();
			pedido.setFecha(new Date());
			pedido.setImporte(9);
		});

		JpaHelper.execute(entityManager -> {
			PedidoLinea pedidoLinea = new PedidoLinea();
			pedidoLinea.setPrecio(6);
			pedidoLinea.setUnidades(2);
			pedidoLinea.setImporte(9);
		});

		System.out.println("Añadido articulo. Pulsa Enter para continuar...");
		new Scanner(System.in).nextLine();

		Articulo articulo = JpaHelper.execute(entityManager -> {
			return entityManager.find(Articulo.class, 3L);
		});

		show(articulo);

		App.getInstance().getEntityManagerFactory().close();
	}

	private static void show(Articulo articulo) {
		System.out.printf("%4s %-30s %-30s %s %n", articulo.getId(), articulo.getNombre(), articulo.getCategoria(),
				articulo.getPrecio());

	}

	private static void showPedido(Pedido pedido) {
		System.out.printf("%4s %-30s %-30s %n", pedido.getId(), pedido.getFecha(), pedido.getImporte());

	}

	private static void showPedidoLinea(PedidoLinea pedidoLinea) {
		System.out.printf("%4s %-30s %-30s %n", pedidoLinea.getId(), pedidoLinea.getPrecio(), pedidoLinea.getUnidades(),
				pedidoLinea.getImporte());

	}

	public static void tryCatchFinally() {
		Object item = null;
		EntityManager entityManager = null;
		entityManager = App.getInstance().getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {

			entityManager.persist(item);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}

	}

}
