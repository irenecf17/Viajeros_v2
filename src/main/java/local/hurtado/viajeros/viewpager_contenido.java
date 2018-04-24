package local.hurtado.viajeros;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


/**
 * NO SIRVE!!!!
 *
 */
public class viewpager_contenido extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager mPager;
    int pos;
    private TabLayout tabLayout;
    private String[] pageTitle = {"Fragment 1", "Fragment 2", "Fragment 3", "Fragment 4","Fragment 5"};
    private FirebaseAuth AuthUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pos = 0;
        //setContentView(R.layout.activity_viewpager_contenido);
        setContentView(R.layout.activity_viewpager__tabs);
        mPager = (ViewPager) findViewById(R.id.pager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //final ActionBar actionBar = getActionBar();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //tabLayout = (TabLayout) findViewById(R.id.tab);
        //for (int i = 0; i < 5; i++) {
        //    tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        //}

        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        });

        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //change ViewPager page when tab selected
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mPager.setCurrentItem(pos);

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mPager.getCurrentItem() == 0) {
                super.onBackPressed();
            } else {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_informacion) {
            Toast.makeText(getApplicationContext(), "Botón Info" ,Toast.LENGTH_SHORT);
            Intent intent = new Intent(getApplicationContext(), viewpager_contenido.class);
            startActivity(intent);
        } else if (id == R.id.nav_foro) {

        } else if (id == R.id.nav_chat) {
            Toast.makeText(getApplicationContext(), "Botón Chat" ,Toast.LENGTH_SHORT);
            Intent intent = new Intent(getApplicationContext(), Chat.class);
            startActivity(intent);
        } else if (id == R.id.nav_return) {
            Toast.makeText(getApplicationContext(), "Botón Volver" ,Toast.LENGTH_SHORT);
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment_listado = new ListadoActivity();
            fm.beginTransaction().replace(R.id.contenedor, fragment_listado).commit();

        } else if (id == R.id.nav_ajustes) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_singout) {
            AuthUI.getInstance().signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
