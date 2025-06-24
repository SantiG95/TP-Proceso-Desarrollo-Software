package models.pago;

public class TarjetaDeCredito extends FormaDePago {
    private final double recargoPorcentual = 0.05; 

    @Override
    public double calcularRecargo(double monto) {
        return monto * recargoPorcentual;
    }

    @Override
    public String getNombre() {
        return "Tarjeta de Crédito";
    }
}
