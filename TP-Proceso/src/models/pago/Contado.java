package models.pago;

public class Contado extends FormaDePago {
    @Override
    public double calcularRecargo(double monto) {
        return 0;
    }

    @Override
    public String getNombre() {
        return "Contado";
    }
}