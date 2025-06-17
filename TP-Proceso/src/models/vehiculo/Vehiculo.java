package models.vehiculo;

public abstract class Vehiculo {
    protected String marca;
    protected double precioBase;
    protected String modelo;
    protected String color;
    protected String numeroChasis;

    public Vehiculo(String marca, double precioBase, String modelo, String color, String numeroChasis) {
        this.marca = marca;
        this.precioBase = precioBase;
        this.modelo = modelo;
        this.color = color;
        this.numeroChasis = numeroChasis;
    }

    public abstract double calcularImpuesto();

    public double getPrecioBase() {
        return precioBase;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    @Override
    public String toString() {
        return marca + " " + modelo + " (" + color + ")";
    }
}