package com.example.tab_fragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new fragment1(), "POR ENTREGAR");
        vpAdapter.addFragment(new fragment2(), "POR RECOGER");
        vpAdapter.addFragment(new fragment3(), "FINALIZADOs");
        viewPager.setAdapter(vpAdapter);



        //Boton cerrar sesión
        FloatingActionButton fab = findViewById(R.id.btn_salir);
        fab.setOnClickListener(view -> Snackbar.make(view, "Cerró sesión",  Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

    }
}