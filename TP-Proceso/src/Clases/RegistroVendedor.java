package Clases;

public class RegistroVendedor implements EstrategiaRegistro {
    @Override
    public void registrarUsuario(String documento) {
        System.out.println("Vendedor registrado con documento: " + documento);
    }
}
