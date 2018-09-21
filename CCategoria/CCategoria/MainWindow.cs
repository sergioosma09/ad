using System;
using Gtk;
using MySql.Data.MySqlClient;
using System.Data;
using System.Collections.Generic;


public partial class MainWindow : Gtk.Window
{
    public MainWindow() : base(Gtk.WindowType.Toplevel)
    {
        Build();

		object obj = null;
		Console.WriteLine("" + obj);
		Console.WriteLine("Fin");		                  

		//IDbConnection dbConnection = new MySqlConnection(
  //              "server=localhost; database=dbprueba;user=root;password=sistemas;ssl-mode=none"
  //          );
  //      dbConnection.Open();
		//IDbCommand dbCommand = dbConnection.CreateCommand();
  //      dbCommand.CommandText = "select id,nombre from categoria order by id";
  //      IDataReader dataReader = dbCommand.ExecuteReader();


        


		//treeView.AppendColumn("ID", new CellRendererText(), "text", 0);
		//treeView.AppendColumn("Nombre", new CellRendererText(), "text", 1);
		//ListStore listStore = new ListStore(typeof(string), typeof(string));
		//treeView.Model = listStore;
		////listStore.AppendValues(dataReader[0], dataReader[1]);
		////listStore.AppendValues(dataReader[0], dataReader[1]);

		//while (dataReader.Read())
		//	listStore.AppendValues(dataReader["id"].ToString(), dataReader["nombre"].ToString());
  //      //    Console.WriteLine("id={0} nombre={1}", dataReader["id"], dataReader["nombre"]);
  //      //Console.WriteLine("id={0} nombre={1}", dataReader[0], dataReader[1]);

  //      dataReader.Close();

		//dbConnection.Close();

    }

    protected void OnDeleteEvent(object sender, DeleteEventArgs a)
    {
        Application.Quit();
        a.RetVal = true;
    }
}
