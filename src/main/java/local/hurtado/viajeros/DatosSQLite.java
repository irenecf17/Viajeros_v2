package local.hurtado.viajeros;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pc on 08/05/2018.
 */

public class DatosSQLite extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "paises.db";

    static final String TABLA_PAISES = "paises";
    static String COLUMN_NOMBRE = "NOMBRE";
    static String COLUMN_CLIMA = "CLIMA";
    static String COLUMN_CULTURA_RELIGION = "CULTURA_RELIGION";
    static String COLUMN_GENERAL = "GENERAL";
    static String COLUMN_ID_CHAT = "ID_CHAT";
    static String COLUMN_ID_FORO = "ID_FORO";
    static String COLUMN_MONEDA = "MONEDA";
    static String COLUMN_TRANSPORTE = "TRANSPORTE";


    public DatosSQLite (Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLA_PAISES + "(" +
                COLUMN_NOMBRE+ ", " +
                COLUMN_CLIMA+ ", " +
                COLUMN_CULTURA_RELIGION+ ", " +
                COLUMN_GENERAL+ ", " +
                COLUMN_ID_CHAT+ ", " +
                COLUMN_ID_FORO+ ", " +
                COLUMN_MONEDA + ", " +
                COLUMN_TRANSPORTE +")"
        );

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOMBRE", "Camboya");
        contentValues.put("CLIMA", "Información del clima del pais. Cambiar el cont...");
        contentValues.put("CULTURA_RELIGION", "Información de la cultura y la religión del pai...");
        contentValues.put("GENERAL", "Información general del pais. Cambiar el contenido al acabar la conexión con la base de datos!");
        contentValues.put("ID_CHAT", "0");
        contentValues.put("ID_FORO","0");
        contentValues.put("MONEDA", "Información moneda del pais. Cambiar el contenido al acabar la conexión con la base de datos!");
        contentValues.put("TRANSPORTE", "Información del transporte del pais. Cambiar el contenido al finalizar. ");
        db.insert(TABLA_PAISES, null, contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}