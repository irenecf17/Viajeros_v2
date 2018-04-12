package local.hurtado.viajeros;

/**
 * Created by pc on 11/04/2018.
 */

public class Contenido {
    String titulo, texto;

    public Contenido() {

    }

    public Contenido(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
