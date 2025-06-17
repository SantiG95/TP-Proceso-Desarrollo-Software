package models.pedido;

public class Accesorio extends ConfiguracionAdicional {
    private double costoDelAccesorio;

    public Accesorio(String descripcion, double costo) {
        this.descripcion = descripcion;
        this.costoDelAccesorio = costo;
    }

    @Override
    public double calcularCostoAdicional() {
        return costoDelAccesorio;
    }
}