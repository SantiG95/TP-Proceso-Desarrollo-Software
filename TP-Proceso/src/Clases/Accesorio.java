package Clases;

public class Accesorio extends ConfiguracionAdicional {
    private double costoDelAccesorio;

    public Accesorio(String descripcion, double costoDelAccesorio) {
        super(descripcion);
        this.costoDelAccesorio = costoDelAccesorio;
    }

    @Override
    public double calcularCostoAdicional() {
        return costoDelAccesorio;
    }
}
