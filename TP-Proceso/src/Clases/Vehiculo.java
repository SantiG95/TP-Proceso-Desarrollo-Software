package Clases;

import java.util.List;


abstract class Vehiculo {
    String marca;
    double precioBase;
    String modelo;
    String color;
    int numeroChasis;
    int numero;
    List<ConfiguracionAdicional> configuracionesAdicionales = new List<ConfiguracionAdicional>();

    public Vehiculo(String marca, double precioBase, String modelo, String color, int numeroChasis, int numero) {
        this.marca = marca;
        this.precioBase = precioBase;
        this.modelo = modelo;
        this.color = color;
        this.numeroChasis = numeroChasis;
        this.numero = numero;
    }

    public double calcularPrecio(){
        return precioBase + calcularImpuesto() + calcularCostosAdicionales();
    }

    private double calcularCostosAdicionales() {
        double costo = 0;
        for (int i = 0; i < configuracionesAdicionales.size(); i++) {
            costo += configuracionesAdicionales.calcularCostoAdicional();
        }
        return costo;
    }

    abstract double calcularImpuesto();

    public Vehiculo getVehiculo(){
        return this;
    }

    public void agregarCostoAdicional(ConfiguracionAdicional configuracionAdicional){
        configuracionesAdicionales.add(configuracionAdicional);
    }
}
