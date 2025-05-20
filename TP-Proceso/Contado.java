package Clases;

public class Contado extends FormaDePago {
    String tipoMoneda;

    public Contado(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    @Override
    public double calcularRecargo(){
        //TODO terminar la funcion
        return 0.0;
    }
}
