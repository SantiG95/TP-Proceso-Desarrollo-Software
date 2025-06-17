package models.pago;

public class Transferencia extends FormaDePago {
    @Override
    public double calcularRecargo(double monto) {
        return 0;
    }

    @Override
    public String getNombre() {
        return "Transferencia Bancaria";
    }
}