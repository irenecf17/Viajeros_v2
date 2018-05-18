package local.hurtado.viajeros;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 15/05/2018.
 */

public class ListaMensajeForo {


        private static ListaMensajeForo sListaMensajeForo;
        private static List<MensajeForo> mMensajeForo;

        private ListaMensajeForo() {
            mMensajeForo = new ArrayList<>();
        }


        public static ListaMensajeForo get(Context context) {
            if (sListaMensajeForo == null) {
                sListaMensajeForo = new ListaMensajeForo();
            }
            return sListaMensajeForo;
        }

        public List<MensajeForo> getmMensajeForo(Context context) {
            return mMensajeForo;
        }

        public void addMensaje (MensajeForo mensaje) {
            mMensajeForo.add(mensaje);
        }

}
