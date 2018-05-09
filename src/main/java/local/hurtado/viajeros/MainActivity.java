package local.hurtado.viajeros;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;

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

            /* Obtención de los datos mediante firebase. Proceso muy lento. Utilizo SQLite de momento
            con solo los datos de un país.

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
            */


            Fragment fragment = new ListadoActivity();
            cargarFragment(fragment);

        }

        public void cargarFragment(Fragment frag) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contenedor, frag).commit();
        }
}

