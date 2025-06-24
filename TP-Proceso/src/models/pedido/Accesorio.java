package models.pedido;

public class Accesorio extends ConfiguracionAdicional {
    private final double costoAccesorio;
    public Accesorio(String descripcion, double costoAccesorio) {
        super(descripcion);
        this.costoAccesorio = costoAccesorio;
    }
    @Override
    public double calcularCostoAdicional() {
        return costoAccesorio;
    }
}