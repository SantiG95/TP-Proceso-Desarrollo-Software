package service;

import models.pedido.Pedido;
import utils.Logger;

public class Logistica implements ProcesadorPedido, Seguimiento {
    private ProcesadorPedido next;

    @Override
    public void setNext(ProcesadorPedido next) {
        this.next = next;
    }

    @Override
    public void procesarPedido(Pedido pedido) {
        Logger.chain("Logística");
        Logger.handler("Procesando la logística del vehículo: " + pedido.getVehiculo());


        pedido.setEstado("En Logística");
        
        pedido.notificarObservadores("Logística en proceso. Preparando para entrega.");

        if (next != null) {
            next.procesarPedido(pedido);
        }
    }

    @Override
    public void actualizar(Pedido pedido, String mensaje) {
        if (mensaje.contains("Venta completada")) {
            Logger.observerAction("Logística", "REACCIONA: Coordinando la entrega del vehículo.");
        } else {
        }
    }
}