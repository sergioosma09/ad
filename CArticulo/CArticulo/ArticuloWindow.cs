using System;
using Serpis.Ad.Ventas;
namespace CArticulo
{
    public partial class ArticuloWindow : Gtk.Window
    {
		public ArticuloWindow(Articulo) :
                base(Gtk.WindowType.Toplevel)
        {
            this.Build();
        }
    }
}
