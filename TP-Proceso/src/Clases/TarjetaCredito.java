package Clases;

public class TarjetaCredito extends FormaDePago{
    String nombreTarjeta;
    int numeroTarjeta;
    int numeroCuotas;

    public TarjetaCredito(String nombreTarjeta, int numeroTarjeta, int numeroCuotas) {
        this.nombreTarjeta = nombreTarjeta;
        this.numeroTarjeta = numeroTarjeta;
        this.numeroCuotas = numeroCuotas;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    @Override
    public double calcularRecargo(){
        //TODO terminar la funcion
        return 0.0;
    }
}
