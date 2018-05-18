package local.hurtado.viajeros;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ForoActivyPasoIntermedio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro_activy_paso_intermedio);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new Foro();
        fm.beginTransaction().replace(R.id.frameForo, fragment).commit();

    }
}
