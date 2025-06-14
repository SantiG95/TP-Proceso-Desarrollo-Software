package Clases;

public class Vendedor extends Usuario {
    public Vendedor(String nombre, String apellido, String correoElectronico, String direccion,
                    int celular, int ingresoPromedio, int documento) {
        super(nombre, apellido, correoElectronico, direccion, celular, ingresoPromedio, documento);
        this.estrategiaRegistro = new RegistroVendedor();
    }

    public void verVehiculosDisponibles() {
        System.out.println("Mostrando vehículos disponibles...");
    }
}
