package gisclace.quizz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import gisclace.quizz.adapter.CourseAdapter;
import gisclace.quizz.adapter.business.CourseRepository;

public class Menu2 extends Activity {

    private TextView text1;
    private CourseAdapter adapter;
    private CourseRepository courseRepository;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        text1 = (TextView) findViewById(R.id.text1);
        courseRepository = new CourseRepository(this);

        courseRepository.Open();
        adapter = new CourseAdapter(this, courseRepository.GetAll());
        courseRepository.Close();

        text1.setText((CharSequence) adapter);

        registerForContextMenu(text1);
    }

    }

