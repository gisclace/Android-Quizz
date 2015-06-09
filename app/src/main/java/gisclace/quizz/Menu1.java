package gisclace.quizz;

import android.app.Activity;
import android.os.Bundle;
import android.app.Dialog;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import gisclace.quizz.adapter.Course;
import gisclace.quizz.adapter.CourseAdapter;
import gisclace.quizz.adapter.business.CourseRepository;

public class Menu1 extends Activity {
    private ListView listeViewCourse;
    private Button boutonAjouter;
    private CourseAdapter adapter;
    private CourseRepository courseRepository;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);

        // Bouton
        boutonAjouter = (Button) findViewById(R.id.buttonAjouter);

        boutonAjouter.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                AjouterItem();
            }
        });

        // Listview
        listeViewCourse = (ListView) findViewById(R.id.listViewCourse);
        courseRepository = new CourseRepository(this);

        courseRepository.Open();
        adapter = new CourseAdapter(this, courseRepository.GetAll());
        courseRepository.Close();

        listeViewCourse.setAdapter(adapter);

        registerForContextMenu(listeViewCourse);
    }

    /**
     * Ajout d'une produit
     */
    private void AjouterItem() {

        // Cr�ation de la boite de dialogue
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogadd);
        dialog.setTitle("          Ajouter un mot          ");

        final Button buttonAdd = (Button) dialog.findViewById(R.id.buttonAdd);
        final Button buttonCancel = (Button) dialog
                .findViewById(R.id.buttonCancel);

        buttonAdd.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // Insertion du produit
                courseRepository.Open();

                String nomProduit = ((EditText) dialog
                        .findViewById(R.id.editTextProduitAdd)).getText()
                        .toString();
                String quantiteProduit = (((EditText) dialog
                        .findViewById(R.id.editTextQuantiteAdd)).getText()
                        .toString());

                courseRepository.Save(new Course(nomProduit, quantiteProduit));
                courseRepository.Close();

                UpdateAdapter();

                dialog.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // Clic sur le bouton annuler
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void UpdateAdapter() {
        courseRepository.Open();
        adapter.setCourses(courseRepository.GetAll());
        courseRepository.Close();
        adapter.notifyDataSetChanged();
    }

    public void UpdateChecked(Course course) {
        courseRepository.Open();
        courseRepository.Update(course);
        courseRepository.Close();
    }

    public void DeleteItem(int id) {
        courseRepository.Open();
        courseRepository.Delete(id);
        courseRepository.Close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_course, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
                .getMenuInfo();

        switch (item.getItemId()) {
            case R.id.itemDelete:
                DeleteItem((int) info.id);
                UpdateAdapter();
                return true;

            default:
                return super.onContextItemSelected(item);
        }

    }

    }

