package Clases;

public class Transferencia extends FormaDePago{
    String banco;
    int cbu;

    public Transferencia(int cbu, String banco) {
        this.cbu = cbu;
        this.banco = banco;
    }

    public String getBanco() {
        return banco;
    }

    public int getCbu() {
        return cbu;
    }

    @Override
    public double calcularRecargo(){
        //TODO terminar la funcion
        return 0.0;
    }
}
