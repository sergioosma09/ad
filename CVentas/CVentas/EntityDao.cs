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
						entityType.GetProperty(propertyName).SetValue(model, value);
					}
					list.Add(model);
				}
				dataReader.Close();
				return list;
			}
		}


		public TEntity Load(object id)
		{
			
			return default(TEntity);


		
	}

		public void Save(TEntity entity){
			object id = entityType.GetProperty(idPropertyName).GetValue(entity);
			object defaultId = Activator.CreateInstance(entityType.GetProperty(idPropertyName).PropertyType);
			if (id.Equals(defaultId))
				insert(entity);

			else
				update(entity);
			
		}

		public void Delete(object id){
			
		}
		public void insert(object entity)
        {

        }
		public void update(object entity)
        {

        }


    }
}
