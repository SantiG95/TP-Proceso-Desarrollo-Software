package Clases;

public class Camioneta extends Vehiculo{
    public Camioneta(String marca, double precioBase, String modelo, String color, int numeroChasis, int numero) {
        super(marca, precioBase, modelo, color, numeroChasis, numero);
    }

    public String toString(){
        return "DATOS CAMIONETA" +
                "\nMarca: " + marca +
                "\nModelo: " + modelo +
                "\nColor: " + color +
                "\nPrecio base: " + precioBase +
                "\nImpuestos: " + calcularImpuesto() +
                "\nPrecio final: " + calcularPrecio();
    }

    @Override
    double calcularImpuesto() {
        double impuesto = 0;
        impuesto += precioBase * ImpuestoNacional.impuestoCamionetas /100;
        impuesto += precioBase * ImpuestoProvincial.impuestoCamionetas /100;
        return impuesto;
    }
}
