package Clases;
import java.util.Date;

public class Entrega implements Seguimiento{
    private String metodoEntrega;
    private String estado;
    private Date fechaEntrega;
    private String direccion;

    public Entrega(String metodoEntrega, String estado, Date fechaEntrega, String direccion) {
        this.metodoEntrega = metodoEntrega;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
        this.direccion = direccion;
    }

    public void procesarPedido(Pedido pedido) {
        System.out.println("Procesando pedido: " + pedido);
        // Aquí se puede agregar la lógica para procesar el pedido
    }
    @Override
    public String getEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEstado'");
    }

    @Override
    public void setEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstado'");
    }

    @Override
    public String realizarConsultas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realizarConsultas'");
    }

}
