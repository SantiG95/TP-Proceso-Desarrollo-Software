package Clases;
import java.util.Date;
public class Embarque implements Seguimiento {
    private String tipoEntrega;
    private String estado;
    private String direccionEntrega;
    private Date fechaEmbarque;

    public Embarque(String tipoEntrega, String estado, String direccionEntrega, Date fechaEmbarque) {
        this.tipoEntrega = tipoEntrega;
        this.estado = estado;
        this.direccionEntrega = direccionEntrega;
        this.fechaEmbarque = fechaEmbarque;
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