package models.pedido;

public class GarantiaExtendida extends ConfiguracionAdicional {
    private double costoDeGarantia;

    public GarantiaExtendida(String descripcion, double costo) {
        this.descripcion = descripcion;
        this.costoDeGarantia = costo;
    }

    @Override
    public double calcularCostoAdicional() {
        return costoDeGarantia;
    }
}
