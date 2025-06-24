package models.usuario;

public class Vendedor extends Usuario {
    public Vendedor(String nombre, String apellido, String correoElectronico, String documento) {
        super(nombre, apellido, correoElectronico, documento);
    }

    @Override
    public String getRol() {
        return "Vendedor";
    }

    public void verVehiculosDisponibles() {
        System.out.println("El vendedor " + getNombre() + " está viendo los vehículos disponibles.");
    }
}