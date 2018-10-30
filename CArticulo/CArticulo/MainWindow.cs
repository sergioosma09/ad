using System;
using Gtk;
using Serpis.Ad;
using Serpis.Ad.Ventas;

public partial class MainWindow : Gtk.Window
{
	public MainWindow() : base(Gtk.WindowType.Toplevel)
	{
		Build();

		//var list = new[]{
		//	new{Id=1, Nombre ="Articulo 1"},
		//	new{Id=2, Nombre = "Articulo 2"},
		//	new{Id=3, Nombre = "Articulo 3"}

		//};

		EntityDao<Articulo> articuloDao = new EntityDao<Articulo>();
		TreeViewHelper.Fill(treeView, new string[] { "Id", "Nombre","Precio"},articuloDao.Enumerable);
    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }
}
