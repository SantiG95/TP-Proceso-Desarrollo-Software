package models.vehiculo;

public class Moto extends Vehiculo {
    private final double impuestoNacional = 0.0; 
    private final double impuestoProvincial = 0.05;

    public Moto(String marca, double precioBase, String modelo, String color, String numeroChasis) {
        super(marca, precioBase, modelo, color, numeroChasis);
    }

    @Override
    public double calcularImpuesto() {
        return getPrecioBase() * (impuestoNacional + impuestoProvincial);
    }
}
