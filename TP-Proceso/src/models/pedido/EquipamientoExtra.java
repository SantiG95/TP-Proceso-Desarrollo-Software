package models.pedido;

public class EquipamientoExtra extends ConfiguracionAdicional {
    private final double costoEquipo;
    public EquipamientoExtra(String descripcion, double costoEquipo) {
        super(descripcion);
        this.costoEquipo = costoEquipo;
    }
    @Override
    public double calcularCostoAdicional() {
        return costoEquipo;
    }
}