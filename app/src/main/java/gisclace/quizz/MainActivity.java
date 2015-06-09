package gisclace.quizz;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        // Tab du premier menu
        TabSpec m1 = tabHost.newTabSpec("Ajouter un mot");
        // Titre
        m1.setIndicator("Ajouter un mot");
        Intent m1Intent = new Intent(this, Menu1.class);
        m1.setContent(m1Intent);

        // Tab du second menu
        TabSpec m2 = tabHost.newTabSpec("Partie quizz");
        m2.setIndicator("Partie quizz");
        Intent m2Intent = new Intent(this, Menu2.class);
        m2.setContent(m2Intent);



        // TabSpec --> TabHost
        tabHost.addTab(m1); // Tab menu 1
        tabHost.addTab(m2); // Tab menu 2


    }
}