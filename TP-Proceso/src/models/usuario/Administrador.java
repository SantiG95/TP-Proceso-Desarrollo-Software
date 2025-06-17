package models.usuario;

public class Administrador extends Usuario {
    public Administrador(String nombre, String apellido, String correoElectronico, String documento) {
        super(nombre, apellido, correoElectronico, documento);
    }

    @Override
    public String getRol() {
        return "Administrador";
    }

    public void gestionarSistema() {
        System.out.println("El administrador " + getNombre() + " está gestionando el sistema.");
    }
}