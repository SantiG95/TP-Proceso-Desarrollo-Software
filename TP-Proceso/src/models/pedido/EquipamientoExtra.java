package models.pedido;

public class EquipamientoExtra extends ConfiguracionAdicional {
    private double costoDelEquipamiento;

    public EquipamientoExtra(String descripcion, double costo) {
        this.descripcion = descripcion;
        this.costoDelEquipamiento = costo;
    }

    @Override
    public double calcularCostoAdicional() {
        return costoDelEquipamiento;
    }
}