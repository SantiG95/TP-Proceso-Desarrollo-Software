package Clases;

public class Comprador extends Usuario {
    public Comprador(String nombre, String apellido, String correoElectronico, String direccion,
                     int celular, int ingresoPromedio, int documento) {
        super(nombre, apellido, correoElectronico, direccion, celular, ingresoPromedio, documento);
        this.estrategiaRegistro = new RegistroComprador();
    }

    public void verPedidos() {
        System.out.println("Visualizando pedidos...");
    }
}
