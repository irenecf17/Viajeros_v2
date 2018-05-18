package local.hurtado.viajeros;

/**
 * Created by pc on 15/05/2018.
 */

public class MensajeForo {
    String titulo;
    int id;

    public MensajeForo(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public MensajeForo() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
