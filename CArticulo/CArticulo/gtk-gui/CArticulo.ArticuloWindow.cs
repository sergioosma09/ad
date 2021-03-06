
// This file has been generated by the GUI designer. Do not modify.
namespace CArticulo
{
	public partial class ArticuloWindow
	{
		private global::Gtk.VBox vbox2;

		private global::Gtk.Table table1;

		private global::Gtk.ComboBox CategoriaBox;

		private global::Gtk.Entry entryNombre;

		private global::Gtk.Label labelCategoria;

		private global::Gtk.Label labelNombre;

		private global::Gtk.Label labelPrecio;

		private global::Gtk.SpinButton precioButton;

		private global::Gtk.HButtonBox hbuttonbox1;

		private global::Gtk.Button button1;

		protected virtual void Build()
		{
			global::Stetic.Gui.Initialize(this);
			// Widget CArticulo.ArticuloWindow
			this.Name = "CArticulo.ArticuloWindow";
			this.Title = global::Mono.Unix.Catalog.GetString("ArticuloWindow");
			this.WindowPosition = ((global::Gtk.WindowPosition)(4));
			// Container child CArticulo.ArticuloWindow.Gtk.Container+ContainerChild
			this.vbox2 = new global::Gtk.VBox();
			this.vbox2.Name = "vbox2";
			this.vbox2.Spacing = 6;
			// Container child vbox2.Gtk.Box+BoxChild
			this.table1 = new global::Gtk.Table(((uint)(3)), ((uint)(2)), false);
			this.table1.Name = "table1";
			this.table1.RowSpacing = ((uint)(6));
			this.table1.ColumnSpacing = ((uint)(6));
			// Container child table1.Gtk.Table+TableChild
			this.CategoriaBox = global::Gtk.ComboBox.NewText();
			this.CategoriaBox.Name = "CategoriaBox";
			this.table1.Add(this.CategoriaBox);
			global::Gtk.Table.TableChild w1 = ((global::Gtk.Table.TableChild)(this.table1[this.CategoriaBox]));
			w1.TopAttach = ((uint)(2));
			w1.BottomAttach = ((uint)(3));
			w1.LeftAttach = ((uint)(1));
			w1.RightAttach = ((uint)(2));
			w1.XOptions = ((global::Gtk.AttachOptions)(4));
			w1.YOptions = ((global::Gtk.AttachOptions)(4));
			// Container child table1.Gtk.Table+TableChild
			this.entryNombre = new global::Gtk.Entry();
			this.entryNombre.CanFocus = true;
			this.entryNombre.Name = "entryNombre";
			this.entryNombre.IsEditable = true;
			this.entryNombre.InvisibleChar = '•';
			this.table1.Add(this.entryNombre);
			global::Gtk.Table.TableChild w2 = ((global::Gtk.Table.TableChild)(this.table1[this.entryNombre]));
			w2.LeftAttach = ((uint)(1));
			w2.RightAttach = ((uint)(2));
			w2.XOptions = ((global::Gtk.AttachOptions)(4));
			w2.YOptions = ((global::Gtk.AttachOptions)(4));
			// Container child table1.Gtk.Table+TableChild
			this.labelCategoria = new global::Gtk.Label();
			this.labelCategoria.Name = "labelCategoria";
			this.labelCategoria.Xalign = 1F;
			this.labelCategoria.LabelProp = global::Mono.Unix.Catalog.GetString("categoria");
			this.table1.Add(this.labelCategoria);
			global::Gtk.Table.TableChild w3 = ((global::Gtk.Table.TableChild)(this.table1[this.labelCategoria]));
			w3.TopAttach = ((uint)(2));
			w3.BottomAttach = ((uint)(3));
			w3.XOptions = ((global::Gtk.AttachOptions)(4));
			w3.YOptions = ((global::Gtk.AttachOptions)(4));
			// Container child table1.Gtk.Table+TableChild
			this.labelNombre = new global::Gtk.Label();
			this.labelNombre.Name = "labelNombre";
			this.labelNombre.Xalign = 1F;
			this.labelNombre.LabelProp = global::Mono.Unix.Catalog.GetString("nombre");
			this.table1.Add(this.labelNombre);
			global::Gtk.Table.TableChild w4 = ((global::Gtk.Table.TableChild)(this.table1[this.labelNombre]));
			w4.XOptions = ((global::Gtk.AttachOptions)(4));
			w4.YOptions = ((global::Gtk.AttachOptions)(4));
			// Container child table1.Gtk.Table+TableChild
			this.labelPrecio = new global::Gtk.Label();
			this.labelPrecio.Name = "labelPrecio";
			this.labelPrecio.Xalign = 1F;
			this.labelPrecio.LabelProp = global::Mono.Unix.Catalog.GetString("precio");
			this.table1.Add(this.labelPrecio);
			global::Gtk.Table.TableChild w5 = ((global::Gtk.Table.TableChild)(this.table1[this.labelPrecio]));
			w5.TopAttach = ((uint)(1));
			w5.BottomAttach = ((uint)(2));
			w5.XOptions = ((global::Gtk.AttachOptions)(4));
			w5.YOptions = ((global::Gtk.AttachOptions)(4));
			// Container child table1.Gtk.Table+TableChild
			this.precioButton = new global::Gtk.SpinButton(0D, 100D, 1D);
			this.precioButton.CanFocus = true;
			this.precioButton.Name = "precioButton";
			this.precioButton.Adjustment.PageIncrement = 10D;
			this.precioButton.ClimbRate = 1D;
			this.precioButton.Numeric = true;
			this.table1.Add(this.precioButton);
			global::Gtk.Table.TableChild w6 = ((global::Gtk.Table.TableChild)(this.table1[this.precioButton]));
			w6.TopAttach = ((uint)(1));
			w6.BottomAttach = ((uint)(2));
			w6.LeftAttach = ((uint)(1));
			w6.RightAttach = ((uint)(2));
			w6.YOptions = ((global::Gtk.AttachOptions)(4));
			this.vbox2.Add(this.table1);
			global::Gtk.Box.BoxChild w7 = ((global::Gtk.Box.BoxChild)(this.vbox2[this.table1]));
			w7.Position = 0;
			w7.Expand = false;
			w7.Fill = false;
			// Container child vbox2.Gtk.Box+BoxChild
			this.hbuttonbox1 = new global::Gtk.HButtonBox();
			this.hbuttonbox1.Name = "hbuttonbox1";
			// Container child hbuttonbox1.Gtk.ButtonBox+ButtonBoxChild
			this.button1 = new global::Gtk.Button();
			this.button1.CanFocus = true;
			this.button1.Name = "button1";
			this.button1.UseUnderline = true;
			this.button1.Label = global::Mono.Unix.Catalog.GetString("GtkButton");
			this.hbuttonbox1.Add(this.button1);
			global::Gtk.ButtonBox.ButtonBoxChild w8 = ((global::Gtk.ButtonBox.ButtonBoxChild)(this.hbuttonbox1[this.button1]));
			w8.Expand = false;
			w8.Fill = false;
			this.vbox2.Add(this.hbuttonbox1);
			global::Gtk.Box.BoxChild w9 = ((global::Gtk.Box.BoxChild)(this.vbox2[this.hbuttonbox1]));
			w9.Position = 1;
			w9.Expand = false;
			w9.Fill = false;
			this.Add(this.vbox2);
			if ((this.Child != null))
			{
				this.Child.ShowAll();
			}
			this.DefaultWidth = 400;
			this.DefaultHeight = 176;
			this.Show();
		}
	}
}
