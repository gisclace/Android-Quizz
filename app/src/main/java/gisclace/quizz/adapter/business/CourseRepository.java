package gisclace.quizz.adapter.business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import gisclace.quizz.adapter.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository extends Repository<Course> {

	public CourseRepository(Context context) {
		sqLiteOpenHelper = new CourseOpenHelper(context, null);
	}

	/**
	 * Suppression d'un produit
	 * 
	 * @param id
	 */
	public void DeleteProduit(int id) {
		maBDD.delete(CourseOpenHelper.COURSE_TABLE_NAME,
				CourseOpenHelper.COLUMN_ID + "=?",
				new String[] { String.valueOf(id) });
	}

	/**
	 * R�cup�ration de la liste de tous les produits
	 */
	@Override
	public List<Course> GetAll() {
		// R�cup�ration de la liste des courses
		Cursor cursor = maBDD.query(CourseOpenHelper.COURSE_TABLE_NAME,
				new String[] { CourseOpenHelper.COLUMN_ID,
						CourseOpenHelper.COLUMN_PRODUIT,
						CourseOpenHelper.COLUMN_QUANTITE,
						CourseOpenHelper.COLUMN_ACHETE
                }, null, null, null,
				null, null);

		return ConvertCursorToListObject(cursor);
	}

	/**
	 * Retourne un seul produit
	 */
	@Override
	public Course GetById(int id) {
		Cursor cursor = maBDD.query(CourseOpenHelper.COURSE_TABLE_NAME,
				new String[] { CourseOpenHelper.COLUMN_ID,
						CourseOpenHelper.COLUMN_PRODUIT,
						CourseOpenHelper.COLUMN_QUANTITE,
						CourseOpenHelper.COLUMN_ACHETE
                },
				CourseOpenHelper.COLUMN_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null);

		return ConvertCursorToObject(cursor);
	}

	/**
	 * Enregistre en produit dans la base
	 */
	@Override
	public void Save(Course entite) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(CourseOpenHelper.COLUMN_PRODUIT, entite.getProduit());
		contentValues.put(CourseOpenHelper.COLUMN_QUANTITE,
				entite.getQuantite());
		contentValues.put(CourseOpenHelper.COLUMN_ACHETE, entite.isAchete());

		maBDD.insert(CourseOpenHelper.COURSE_TABLE_NAME, null, contentValues);
	}

	/**
	 * Met � jour un produit
	 */
	@Override
	public void Update(Course entite) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(CourseOpenHelper.COLUMN_PRODUIT, entite.getProduit());
		contentValues.put(CourseOpenHelper.COLUMN_QUANTITE,
				entite.getQuantite());
		contentValues.put(CourseOpenHelper.COLUMN_ACHETE, entite.isAchete());

		maBDD.update(CourseOpenHelper.COURSE_TABLE_NAME, contentValues,
				CourseOpenHelper.COLUMN_ID + "=?",
				new String[] { String.valueOf(entite.getId()) });
	}

	/**
	 * Supprime un produit
	 */
	@Override
	public void Delete(int id) {
		maBDD.delete(CourseOpenHelper.COURSE_TABLE_NAME,
				CourseOpenHelper.COLUMN_ID + "=?",
				new String[] { String.valueOf(id) });
	}

	/**
	 * Converti un curseur en une liste de produits
	 */
	@Override
	public List<Course> ConvertCursorToListObject(Cursor c) {
		List<Course> liste = new ArrayList<Course>();

		// Si la liste est vide
		if (c.getCount() == 0)
			return liste;

		// position sur le premeir item
		c.moveToFirst();

		// Pour chaque item
		do {

			Course course = ConvertCursorToObject(c);

			liste.add(course);
		} while (c.moveToNext());

		// Fermeture du curseur
		c.close();

		return liste;
	}

	/**
	 * M�thode utilis�e par ConvertCursorToObject et ConvertCursorToListObject
	 */
	@Override
	public Course ConvertCursorToObject(Cursor c) {

		Course course = new Course(
				c.getString(CourseOpenHelper.NUM_COLUMN_PRODUIT),
				c.getString(CourseOpenHelper.NUM_COLUMN_QUANTITE));
		course.setId(c.getInt(CourseOpenHelper.NUM_COLUMN_ID));
		course.setAchete((c.getInt(CourseOpenHelper.NUM_COLUMN_ACHETE) != 0));

		return course;
	}

	/**
	 * Converti un curseur en un produit
	 */
	@Override
	public Course ConvertCursorToOneObject(Cursor c) {
		c.moveToFirst();

		Course course = ConvertCursorToObject(c);

		c.close();
		return course;
	}
}
