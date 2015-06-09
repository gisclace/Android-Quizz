package gisclace.quizz.adapter.business;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class Repository<T> implements IRepository<T> {
	// Base de donn�es
	protected SQLiteDatabase maBDD;
	
	protected SQLiteOpenHelper sqLiteOpenHelper;
	
	/**
	 * Constructeur par d�faut
	 */
	public Repository() {
		
	}
	
	/**
	 * Ouverture de la connection
	 */
	public void Open() {
		maBDD = sqLiteOpenHelper.getWritableDatabase();
	}

	/**
	 * Fermeture de la connection
	 */
	public void Close() {
		maBDD.close();
	}
}
