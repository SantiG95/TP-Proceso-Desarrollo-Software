package models.pedido;

public class GarantiaExtendida extends ConfiguracionAdicional {
    private final int meses;
    private final double costoPorMes = 125.0;  // ejemplo
    public GarantiaExtendida(int meses) {
        super("Garantía Extendida (" + meses + " meses)");
        this.meses = meses;
    }
    @Override
    public double calcularCostoAdicional() {
        return meses * costoPorMes;
    }
}