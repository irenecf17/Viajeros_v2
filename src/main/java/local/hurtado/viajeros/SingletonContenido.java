package local.hurtado.viajeros;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 12/04/2018.
 */

public class SingletonContenido {


    //Declaro la variable tipo singleton
    private static SingletonContenido sSingletonContenido;
    private static List<Contenido> mContenido;

    private SingletonContenido() {
        mContenido = new ArrayList<>();
    }


    public static SingletonContenido get(Context context) {
        if (sSingletonContenido == null) {
            sSingletonContenido = new SingletonContenido();
        }
        return sSingletonContenido;
    }

    public List<Contenido> getmContenido(Context context) {
        return mContenido;
    }
}
