package local.hurtado.viajeros;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    static int posicion = 0;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //FacebookSdk.sdkInitialize(getApplicationContext());
            setContentView(R.layout.activity_main);

            mDatabase = FirebaseDatabase.getInstance().getReference("/pais");

            ValueEventListener listener = new ValueEventListener() {
                String TAG;
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<List<pais>> p = new GenericTypeIndicator <List<pais>>() {};
                    List<pais> camboya =  dataSnapshot.getValue(p);

                    Contenido contenidoInfo = new Contenido("Información general", camboya.get(0).getGeneral());
                    SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoInfo);

                    Contenido contenidoClima = new Contenido("Clima", camboya.get(0).getClima());
                    SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoClima);

                    Contenido contenidoMoneda = new Contenido("Moneda", camboya.get(0).getMoneda());
                    SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoMoneda);

                    Contenido contenidoReligion = new Contenido("Religion", camboya.get(0).getCultura_religion());
                    SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoReligion);

                    Contenido contenidoTransporte = new Contenido("Transporte", camboya.get(0).getTransporte());
                    SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoTransporte);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                }
            };

            mDatabase.addValueEventListener(listener);

            Fragment fragment = new ListadoActivity();
            cargarFragment(fragment);

        }

        public void cargarFragment(Fragment frag) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contenedor, frag).commit();
        }
}

