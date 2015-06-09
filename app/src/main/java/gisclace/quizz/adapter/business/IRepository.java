package gisclace.quizz.adapter.business;

import android.database.Cursor;

import java.util.List;

public interface IRepository<T> {

	public List<T> GetAll();
	public T GetById(int id);
	
	public void Save(T entite);
	public void Update(T entite);
	public void Delete(int id);
	
	public List<T> ConvertCursorToListObject(Cursor c);
	public T ConvertCursorToObject(Cursor c);
	public T ConvertCursorToOneObject(Cursor c);
}
