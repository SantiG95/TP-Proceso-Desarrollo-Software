package Clases;

public class Vehiculo {
    String marca;
    double precioBase;
    String modelo;
    String color;
    int numeroChasis;
    int numeroMotor;

    public String getMarca() {
        return marca;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public int getNumeroChasis() {
        return numeroChasis;
    }

    public int getNumeroMotor() {
        return numeroMotor;
    }

    public double calcularImpuesto(){
        return 0.0;
    }
}
