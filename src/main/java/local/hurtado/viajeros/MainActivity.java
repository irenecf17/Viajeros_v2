/**
 * @author Irene Hurtado
 * @name Viajeros 2.0
 */

package local.hurtado.viajeros;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private SQLiteDatabase mDataBaseSQLite;
    String nombre, clima, cultura_religion, general, moneda, transporte;


    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //FacebookSdk.sdkInitialize(getApplicationContext());
            setContentView(R.layout.activity_main);


            mDataBaseSQLite = new DatosSQLite(getApplicationContext()).getWritableDatabase();
            String[] columns = {DatosSQLite.COLUMN_NOMBRE, DatosSQLite.COLUMN_CLIMA, DatosSQLite.COLUMN_CULTURA_RELIGION, DatosSQLite.COLUMN_GENERAL, DatosSQLite.COLUMN_ID_CHAT, DatosSQLite.COLUMN_ID_FORO, DatosSQLite.COLUMN_MONEDA, DatosSQLite.COLUMN_TRANSPORTE};
            Cursor cursor = mDataBaseSQLite.query(DatosSQLite.TABLA_PAISES, columns, null, null, null, null, null);


            while(cursor.moveToNext()) {
                int index;

                index = cursor.getColumnIndex(DatosSQLite.COLUMN_NOMBRE);
                nombre = cursor.getString(index);

                index = cursor.getColumnIndex(DatosSQLite.COLUMN_CLIMA);
                clima = cursor.getString(index);

                index = cursor.getColumnIndex(DatosSQLite.COLUMN_CULTURA_RELIGION);
                cultura_religion = cursor.getString(index);

                index = cursor.getColumnIndex(DatosSQLite.COLUMN_GENERAL);
                general = cursor.getString(index);

                index = cursor.getColumnIndex(DatosSQLite.COLUMN_MONEDA);
                moneda = cursor.getString(index);

                index = cursor.getColumnIndex(DatosSQLite.COLUMN_TRANSPORTE);
                transporte = cursor.getString(index);

                Log.i("nombre", nombre);
                Log.i("clima", clima);
                Log.i("cultura_religion", cultura_religion);
                Log.i("general", general);
                Log.i("moneda", moneda);
                Log.i("transporte", transporte);

            }

            Contenido contenidoInfo = new Contenido("Información general", general);
            SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoInfo);

            Contenido contenidoClima = new Contenido("Clima", clima);
            SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoClima);

            Contenido contenidoMoneda = new Contenido("Moneda", moneda);
            SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoMoneda);

            Contenido contenidoReligion = new Contenido("Religion", cultura_religion);
            SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoReligion);

            Contenido contenidoTransporte = new Contenido("Transporte", transporte);
            SingletonContenido.get(getApplicationContext()).getmContenido(getApplicationContext()).add(contenidoTransporte);


            mDatabase = FirebaseDatabase.getInstance().getReference("/foro/0");

                ValueEventListener listener = new ValueEventListener() {
                    String TAG;

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        GenericTypeIndicator<List<MensajeForo>> p = new GenericTypeIndicator <List<MensajeForo>>() {};
                        List<MensajeForo> foro_camboya =  dataSnapshot.getValue(p);

                        ListaMensajeForo.get(getApplicationContext()).limpiarListado();

                        for (int i = 1; i < foro_camboya.size(); i++) {
                            MensajeForo contenidoTitulo = new MensajeForo(foro_camboya.get(i).getId(), foro_camboya.get(i).getTitulo());
                            ListaMensajeForo.get(getApplicationContext()).addMensaje(contenidoTitulo);
                        }
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

