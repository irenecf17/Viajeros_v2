package local.hurtado.viajeros;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Camboya extends Fragment {
    static int posicion;
    String tituloPantalla, contenidoPantalla;
    TextView titulo_pantalla, contenido_pantalla;
    private OnFragmentInteractionListener mListener;

    public Camboya() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            posicion = getArguments().getInt("POSICION");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_camboya, container, false);
        titulo_pantalla = (TextView) v.findViewById(R.id.txt_titulo_camboya);
        contenido_pantalla = (TextView) v.findViewById(R.id.txt_contenido_camboya);
        Log.i("SINGLETON", SingletonContenido.get(getContext()).getmContenido(getContext()).toString());
        tituloPantalla = SingletonContenido.get(getContext()).getmContenido(getContext()).get(posicion).getTitulo();
        contenidoPantalla = SingletonContenido.get(getContext()).getmContenido(getContext()).get(posicion).getTexto();
        titulo_pantalla.setText(""+tituloPantalla);
        contenido_pantalla.setText(""+contenidoPantalla);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public static Camboya newInstance(int position) {
        Bundle args = new Bundle();
        Camboya fragment = new Camboya();
        args.putInt("POSICION", position);
        fragment.setArguments(args);
        return fragment;
    }
}
