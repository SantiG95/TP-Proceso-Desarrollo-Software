package Clases;

public class RegistroComprador implements EstrategiaRegistro {
    @Override
    public void registrarUsuario(String documento) {
        System.out.println("Comprador registrado con documento: " + documento);
    }
}
