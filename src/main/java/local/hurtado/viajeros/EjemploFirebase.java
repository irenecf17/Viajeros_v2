/*
package local.hurtado.viajeros;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class EjemploFirebase extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference("/pais/1");
        //Si queremos escribir en la base de datos lo hacemos con el setValue
        pais pais_new = new pais("Camboya", "1", "2", "3", "4", "5", 6, 7, "8", "9");
        mDatabase.setValue(pais_new);

        mDatabase = FirebaseDatabase.getInstance().getReference("/pais/1");

        ValueEventListener paisListener = new ValueEventListener() {
            String TAG;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pais pais = dataSnapshot.getValue(pais.class);
                Log.i("LEER", String.valueOf(pais.getNombre()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        mDatabase.addValueEventListener(paisListener);


        mDatabase = FirebaseDatabase.getInstance().getReference("/pais");

        ValueEventListener listener = new ValueEventListener() {
            String TAG;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator <List<pais>> p = new GenericTypeIndicator <List<pais>>() {};
                List<pais>  paises =  dataSnapshot.getValue(p);
                Log.i("LEER PAISES: ", paises.get(0).getNombre());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        mDatabase.addValueEventListener(listener);


    }

}

*/
