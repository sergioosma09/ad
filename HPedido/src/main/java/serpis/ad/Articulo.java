package serpis.ad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Articulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private float precio;
	private Categoria categoria;
	
	
	@OneToMany(mappedBy = "articulo")
	private List<PedidoLinea> pedidoLinea;

	public List<PedidoLinea> getPedidoLinea() {
		return pedidoLinea;
	}

	public void addFactura(PedidoLinea p) {

		pedidoLinea.add(p);
	}

	public void setListaFacturas(List<PedidoLinea> listaFacturas) {
		this.pedidoLinea = listaFacturas;
	}

	public Articulo() {
		this.nombre = nombre;
		this.id = id;
		pedidoLinea = new ArrayList<>();

	}

	public void setPedidoLinea(List<PedidoLinea> pedidoLinea) {
		this.pedidoLinea = pedidoLinea;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
