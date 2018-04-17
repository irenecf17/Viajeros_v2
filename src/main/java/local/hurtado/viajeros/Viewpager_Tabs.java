package local.hurtado.viajeros;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class Viewpager_Tabs extends AppCompatActivity {
    private String[] pageTitle = {"Información", "Clima", "Moneda", "Religion","Transporte"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager__tabs);
        ViewPager mPager = (ViewPager) findViewById(R.id.pager);

        FragmentManager fm = getSupportFragmentManager();

        mPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return Camboya.newInstance(position);
            }
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return pageTitle[position];
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(mPager);

    }

}
