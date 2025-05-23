package Clases;

public class Moto extends Vehiculo{
    public Moto(String marca, double precioBase, String modelo, String color, int numeroChasis, int numero) {
        super(marca, precioBase, modelo, color, numeroChasis, numero);
    }

    public String toString(){
        return "DATOS MOTO" +
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
        impuesto += precioBase * ImpuestoNacional.impuestoMotos /100;
        impuesto += precioBase * ImpuestoProvincial.impuestoMotos /100;
        return impuesto;
    }


}

