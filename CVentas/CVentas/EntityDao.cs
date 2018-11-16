using System;
using System.Collections;
using System.Data;
using System.Collections.Generic;
using System.Reflection;
namespace Serpis.Ad
{
	public class EntityDao<TEntity>
	{

		protected string idPropertyName = "id";
		protected Type entityType = typeof(TEntity);
		protected List<string> entityPropertyNames = new List<string>();
		public EntityDao()
		{
			foreach (PropertyInfo propertyInfo in entityType.GetProperties())
				if (propertyInfo.Name == idPropertyName)
					entityPropertyNames.Insert(0, idPropertyName);
				else
					entityPropertyNames.Add(propertyInfo.Name);

		}


		public IEnumerable Enumerable
		{
			get
			{
				ArrayList list = new ArrayList();
				IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
				string tableName = entityType.Name.ToLower();
				string FieldNamesCsv = string.Join(", ", entityPropertyNames).ToLower();
				string selectSql = string.Format("select {0} from {1} order by {2}", FieldNamesCsv, tableName, idPropertyName.ToLower());
				dbCommand.CommandText = selectSql;
				IDataReader dataReader = dbCommand.ExecuteReader();
				while (dataReader.Read())
				{
					Activator.CreateInstance<TEntity>();
					object model = Activator.CreateInstance<TEntity>();
					foreach (string propertyName in entityPropertyNames)
					{
						object value = dataReader[propertyName];
						if(value==DBNull.Value){
							value = null;
						}
						entityType.GetProperty(propertyName).SetValue(model, value);
					}
					list.Add(model);
				}
				dataReader.Close();
				return list;
			}
		}

		protected static string selectSql = "select * from {0} where {1} = @id";
		public TEntity Load(object id)
		{
			IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
			string tableName = entityType.Name.ToLower();
			dbCommand.CommandText = string.Format(selectSql,tableName, idPropertyName.ToLower());
            DbCommandHelper.AddParameter(dbCommand, "id", id);
            IDataReader dataReader = dbCommand.ExecuteReader();
			dataReader.Read();
            Activator.CreateInstance<TEntity>();
            var model = Activator.CreateInstance<TEntity>();
            foreach (string propertyName in entityPropertyNames)
                {
				object value = dataReader[propertyName.ToLower()];
                    if (value == DBNull.Value)
                    {
                        value = null;
                    }
                    entityType.GetProperty(propertyName).SetValue(model, value);
                }
			dataReader.Close();
			return model;
		
	}

		public void Save(TEntity entity){
			object id = entityType.GetProperty(idPropertyName).GetValue(entity);
			object defaultId = Activator.CreateInstance(entityType.GetProperty(idPropertyName).PropertyType);
			if (id.Equals(defaultId))
				insert(entity);

			else
				update(entity);
			
		}
		protected static string deleteSql = "delete from {0} where {1}=@id ";
		public void Delete(object id){
			string tableName = entityType.Name.ToLower();
			IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
			dbCommand.CommandText =string.Format(deleteSql,tableName, idPropertyName.ToLower() );
            DbCommandHelper.AddParameter(dbCommand, "id", id);
            dbCommand.ExecuteNonQuery();
		}

		protected string insertSql = "insert into {0} ({1}) values ({2})";

		protected void insert(object entity)
        {
			IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();

			List<string> insertPropertyNames = entityPropertyNames.FindAll(item => item != idPropertyName);
			List<string> parametes = new List<string>();
			insertPropertyNames.ForEach(item => parametes.Add("@" + item));
			string tableName = entityType.Name.ToLower();
			string parametesCsv = string.Join(", " , parametes).ToLower();
			string insertNamesCsv = string.Join(", ", insertPropertyNames).ToLower();

			dbCommand.CommandText = string.Format(insertSql, tableName, insertPropertyNames, parametesCsv);;
			foreach(string fieldName in insertPropertyNames){
				object value = entityType.GetProperty(fieldName).GetValue(entity);
				DbCommandHelper.AddParameter(dbCommand, fieldName, value);
			}
			dbCommand.ExecuteNonQuery();
        }
        
		protected static string updateSql = "update {0} set {1} where {2}=@id";
		protected void update(object entity)
        {
			IDbCommand dbCommand = App.Instance.DbConnection.CreateCommand();
			List<string> fieldParametersPairs = new List<string>();
			List<string> propertyNames = new List<string>(new string[] { "id", "Nombre", "Precio", "Categoria" });

            
            for (int index = 1; index < propertyNames.Count; index++)
            {
				string item = propertyNames[index];
				fieldParametersPairs.Add(item + "=@" + item);

            }
			string tableName = entityType.Name.ToLower();
			string FieldNamesCsv = string.Join(", ", fieldParametersPairs).ToLower();
			dbCommand.CommandText = string.Format(updateSql, FieldNamesCsv, tableName, propertyNames);
            IDbCommand dbCommandd = App.Instance.DbConnection.CreateCommand();
			dbCommand.CommandText = updateSql;
            IDataReader dataReader = dbCommand.ExecuteReader();
            

        }


    }
}
