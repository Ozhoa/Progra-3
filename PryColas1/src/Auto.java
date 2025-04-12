public class Auto {
    private String modelo;
    private int anio;

    public Auto(String modelo, int anio) {
        this.modelo = modelo;
        this.anio = anio;
    }
    public int calcularPrecio() {
        int aniosDesde2025 = 2025 - anio+1;
        return aniosDesde2025 * 50;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Auto modelo: "+modelo+" año "+anio+"precio: $"+calcularPrecio()+ "\n";
    }
}
