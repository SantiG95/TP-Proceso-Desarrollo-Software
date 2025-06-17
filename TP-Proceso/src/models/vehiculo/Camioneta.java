package models.vehiculo;

public class Camioneta extends Vehiculo {
    private final double impuestoNacional = 0.10;
    private final double impuestoProvincial = 0.05;

    public Camioneta(String marca, double precioBase, String modelo, String color, String numeroChasis) {
        super(marca, precioBase, modelo, color, numeroChasis);
    }

    @Override
    public double calcularImpuesto() {
        return getPrecioBase() * (impuestoNacional + impuestoProvincial);
    }
}