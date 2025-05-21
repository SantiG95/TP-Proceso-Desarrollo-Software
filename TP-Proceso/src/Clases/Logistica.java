package Clases;

import java.util.Date;

public class Logistica implements Seguimiento {
    private String tipoTransporte;
    private Date fechaInicio;
    private Date fechaFinEstimada;
    private String estado;

    public Logistica(String tipoTransporte, Date fechaInicio, Date fechaFinEstimada, String estado) {
        this.tipoTransporte = tipoTransporte;
        this.fechaInicio = fechaInicio;
        this.fechaFinEstimada = fechaFinEstimada;
        this.estado = estado;
    }

    public void procesarPedido(Pedido pedido) {
        System.out.println("Procesando pedido: " + pedido);
        // Aquí se puede agregar la lógica para procesar el pedido
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

    @Override
    public String getEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEstado'");
    }
}