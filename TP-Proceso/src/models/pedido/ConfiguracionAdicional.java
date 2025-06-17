package models.pedido;


public abstract class ConfiguracionAdicional {
    protected String descripcion;


    public abstract double calcularCostoAdicional();

    public String getDescripcion() {
        return descripcion;
    }
}