package local.hurtado.viajeros;

public class pais {

    String nombre;
    String general;
    String clima;
    String cultura_religion;
    String moneda;
    String transporte;
    int id_chat;
    int id_foro;
    String url_imagenes;
    String url_videos;

    public pais() {

    }

    public pais(String nombre, String general, String clima, String cultura_religion, String moneda, String transporte, int id_chat, int id_foro, String url_imagenes, String url_videos) {
        this.general = general;
        this.clima = clima;
        this.cultura_religion = cultura_religion;
        this.general = general;
        this.moneda = moneda;
        this.transporte = transporte;
        this.id_chat = id_chat;
        this.id_foro = id_foro;
        this.url_imagenes = url_imagenes;
        this.url_videos = url_videos;

        this.nombre = nombre;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getCultura_religion() {
        return cultura_religion;
    }

    public void setCultura_religion(String cultura_religion) {
        this.cultura_religion = cultura_religion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public int getId_chat() {
        return id_chat;
    }

    public void setId_chat(int id_chat) {
        this.id_chat = id_chat;
    }

    public int getId_foro() {
        return id_foro;
    }

    public void setId_foro(int id_foro) {
        this.id_foro = id_foro;
    }

    public String getUrl_imagenes() {
        return url_imagenes;
    }

    public void setUrl_imagenes(String url_imagenes) {
        this.url_imagenes = url_imagenes;
    }

    public String getUrl_videos() {
        return url_videos;
    }

    public void setUrl_videos(String url_videos) {
        this.url_videos = url_videos;
    }
}
