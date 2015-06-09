package gisclace.quizz.adapter.business;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseOpenHelper extends SQLiteOpenHelper {

	// Version de la base de donn�es
	private static final int DATABASE_VERSION = 1;

	// Nom de la base
	private static final String COURSE_BASE_NAME = "course.db";

	// Nom de la table
	public static final String COURSE_TABLE_NAME = "Course";

	// Description des colonnes
	public static final String COLUMN_ID = "ID";
	public static final int NUM_COLUMN_ID = 0;
	public static final String COLUMN_PRODUIT = "PRODUIT";
	public static final int NUM_COLUMN_PRODUIT = 1;
	public static final String COLUMN_QUANTITE = "QUANTITE";
	public static final int NUM_COLUMN_QUANTITE = 2;
	public static final String COLUMN_ACHETE = "ACHETE";
	public static final int NUM_COLUMN_ACHETE = 3;

	// Requ�te SQL pour la cr�ation da la base
	private static final String REQUETE_CREATION_BDD = "CREATE TABLE "
			+ COURSE_TABLE_NAME + " (" + COLUMN_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUIT
			+ " TEXT NOT NULL, " + COLUMN_QUANTITE + " TEXT NOT NULL, "
			+ COLUMN_ACHETE + " INTEGER NOT NULL);";


	/**
	 * Constructeur
	 * 
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public CourseOpenHelper(Context context, CursorFactory factory) {
		super(context, COURSE_BASE_NAME, factory, DATABASE_VERSION);
	}

	/**
	 * Cr�ation de la base
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(REQUETE_CREATION_BDD);
	}

	/**
	 * Mise � jour de la base
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Lorsque l'on change le num�ro de version de la base on supprime la
		// table puis on la recr�e
		if (newVersion > DATABASE_VERSION) {
			db.execSQL("DROP TABLE " + COURSE_TABLE_NAME + ";");
			onCreate(db);
		}
	}

}
