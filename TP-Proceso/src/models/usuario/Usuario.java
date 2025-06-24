package models.usuario;

import models.pedido.Pedido;
import service.Seguimiento;

public abstract class Usuario implements Seguimiento{
    protected String nombre;
    protected String apellido;
    protected String correoElectronico;
    protected String documento;

    public Usuario(String nombre, String apellido, String correoElectronico, String documento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.documento = documento;
    }

    public abstract String getRol();

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getDocumento() {
        return documento;
    }

    public void actualizar(Pedido pedido, String nuevoEstado) {
        System.out.printf("Usuario %s: tu pedido de %s en %s cambió a «%s»%n",
            this.getNombre(),
            pedido.getVehiculo().getModelo(),
            pedido.getFechaPedido(),
            nuevoEstado);
    }
}