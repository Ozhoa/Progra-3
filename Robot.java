
public class Robot {
    private int id;
    private String nombre;
    private int energia;
    private int puntaje;
    private String categoria;

    public Robot(int id, String nombre, int energia, int puntaje, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.energia = energia;
        this.puntaje = puntaje;
        this.categoria = categoria;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getEnergia() { return energia; }
    public int getPuntaje() { return puntaje; }
    public String getCategoria() { return categoria; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEnergia(int energia) { this.energia = energia; }
    public void setPuntaje(int puntaje) { this.puntaje = puntaje; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Energía: " + energia +
                ", Puntaje: " + puntaje + ", Categoría: " + categoria;
    }
}
