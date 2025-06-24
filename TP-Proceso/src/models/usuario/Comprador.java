package models.usuario;

public class Comprador extends Usuario {
    public Comprador(String nombre, String apellido, String correoElectronico, String documento) {
        super(nombre, apellido, correoElectronico, documento);
    }

    @Override
    public String getRol() {
        return "Comprador";
    }

    public void verPedidos() {
        System.out.println("El usuario " + getNombre() + " está viendo sus pedidos.");
    }
}