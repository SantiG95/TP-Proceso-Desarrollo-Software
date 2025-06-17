package Clases;

import java.util.ArrayList;


abstract class Vehiculo {
    String marca;
    double precioBase;
    String modelo;
    String color;
    int numeroChasis;
    int numero;
    boolean disponible;
    ArrayList<ConfiguracionAdicional> configuracionesAdicionales = new ArrayList<ConfiguracionAdicional>();

    public Vehiculo(String marca, double precioBase, String modelo, String color, int numeroChasis, int numero) {
        this.marca = marca;
        this.precioBase = precioBase;
        this.modelo = modelo;
        this.color = color;
        this.numeroChasis = numeroChasis;
        this.numero = numero;
        this.disponible = true;
    }

    public double calcularPrecio(){
        return precioBase + calcularImpuesto() + calcularCostosAdicionales();
    }

    private double calcularCostosAdicionales() {
        double costo = 0;
        for (int i = 0; i < configuracionesAdicionales.size(); i++) {
            costo += configuracionesAdicionales.get(i).calcularCostoAdicional();
        }
        return costo;
    }

    abstract double calcularImpuesto();

    public Vehiculo getVehiculo(){
        return this;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void agregarCostoAdicional(ConfiguracionAdicional configuracionAdicional){
        configuracionesAdicionales.add(configuracionAdicional);
    }
}
