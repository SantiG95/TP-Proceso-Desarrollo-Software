package models.pedido;

public abstract class ConfiguracionAdicional {
    protected String descripcion;
    
    public ConfiguracionAdicional(String descripcion) {
        this.descripcion = descripcion;
    }
    public abstract double calcularCostoAdicional();
    @Override
    public String toString() {
        return descripcion + " (+$" + String.format("%,.2f", calcularCostoAdicional()) + ")";
    }
}