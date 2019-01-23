package serpis.ad;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	
	@OneToMany(mappedBy = "cliente")
	private List<Articulo> articulo;

	public List<Articulo> getArticulo() {
		return articulo;
	}

	public void addFactura(Articulo p) {

		articulo.add(p);
	}

	public void setListaFacturas(List<Articulo> listaArticulos) {
		this.articulo = listaArticulos;
	}

	public Cliente() {
		this.nombre = nombre;
		this.id = id;
		articulo=new ArrayList<>();
	}

	public void setArticulo(List<Articulo> articulo) {
		this.articulo = articulo;
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
}