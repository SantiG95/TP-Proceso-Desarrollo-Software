package service;
import models.pedido.Pedido;
import utils.Logger;

public class Entrega implements ProcesadorPedido, Seguimiento {
    private ProcesadorPedido next;

    @Override
    public void setNext(ProcesadorPedido next) {
        this.next = next;
    }

    @Override
    public void procesarPedido(Pedido pedido) {
        Logger.chain("Entrega");
        Logger.handler("Procesando la entrega del vehículo: " + pedido.getVehiculo());
        pedido.setEstado("Entregado");
        
        pedido.notificarObservadores("Vehículo entregado exitosamente.");

    }

    @Override
    public void actualizar(Pedido pedido, String mensaje) {
        if (mensaje.contains("Logística en proceso")) {
            Logger.observerAction("Entrega", "REACCIONA: Preparando la entrega del vehículo.");
        } else {
        }
    }
}