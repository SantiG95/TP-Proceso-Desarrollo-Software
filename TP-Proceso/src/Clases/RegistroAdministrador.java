package Clases;

public class RegistroAdministrador implements EstrategiaRegistro {
    @Override
    public void registrarUsuario(String documento) {
        System.out.println("Administrador registrado con documento: " + documento);
    }
}
