package Clases;

public class Administrador extends Usuario {
    public Administrador(String nombre, String apellido, String correoElectronico, String direccion,
                         int celular, int ingresoPromedio, int documento) {
        super(nombre, apellido, correoElectronico, direccion, celular, ingresoPromedio, documento);
        this.estrategiaRegistro = new RegistroAdministrador();
    }

    public void gestionarSistema() {
        System.out.println("Gestionando el sistema...");
    }
}
