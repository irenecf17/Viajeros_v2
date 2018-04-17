package local.hurtado.viajeros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ListadoActivity extends Fragment {

    ImageView camboya, tailandia, vietnam, india, maldivas, srilanka;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listado, container, false);

        camboya = (ImageView) v.findViewById(R.id.btt_camboya);

        /**
         * Para pasar de un fragment a otro (X es igual al constructor de la clase que queremos cargar)
         * Cuando creamos un fragment, tenemos que poner un constructor, aunque esté vacío para poder luego cargarlo.
         *
         * MainActivity mainActivity = (MainActivity) getActivity();
         * Fragment fragment = new X();
         * mainActivity.cargarFragment(fragment);
         *
         */

        camboya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Viewpager_Tabs.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
