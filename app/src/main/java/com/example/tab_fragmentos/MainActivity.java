package com.example.tab_fragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
        vpAdapter.addFragment(new fragment3(), "FINALIZADOS");
        viewPager.setAdapter(vpAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                String action = "";

                if (position == 0) {
                    action = "TAG_POR_ENTREGAR";
                } else if (position == 1) {
                    action = "TAG_POR_RECOGER";
                } else if (position == 2) {
                    action = "TAG_FINALIZADOS";
                }

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getApplicationContext());
                Intent i = new Intent(action);
                lbm.sendBroadcast(i);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });


        //Boton cerrar sesión
        FloatingActionButton fab = findViewById(R.id.btn_salir);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, Login.class ));
            finish();
        });

    }
}