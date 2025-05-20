package Clases;

public class Camion extends Vehiculo{
    public Camion(String marca, double precioBase, String modelo, String color, int numeroChasis, int numero) {
        super(marca, precioBase, modelo, color, numeroChasis, numero);
    }

    public String toString(){
        return "DATOS CAMION" +
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
        impuesto += precioBase * ImpuestoNacional.impuestoCamiones /100;
        impuesto += precioBase * ImpuestoProvincial.impuestoCamiones /100;
        return impuesto;
    }


}
