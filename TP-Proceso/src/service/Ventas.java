package service;

import models.pago.FormaDePago;
import models.pedido.Pedido;
import utils.Logger;

public class Ventas implements ProcesadorPedido, Seguimiento {
    private ProcesadorPedido next;

    @Override
    public void setNext(ProcesadorPedido next) {
        this.next = next;
    }

    @Override
public void procesarPedido(Pedido pedido) {
    
    Logger.chain("Ventas");
    
    
    Logger.handler("Procesando la venta del vehículo: " + pedido.getVehiculo());
    
    pedido.setEstado("Venta Realizada");
    
    double total = pedido.calcularCostoTotal();

    FormaDePago formaDePago = pedido.getFormaDePago();
    
    Logger.handler("Forma de pago: " + formaDePago.getNombre() + ". Total: $" + String.format("%,.2f", total));;
    
    formaDePago.procesarPago(total);
    
    pedido.notificarObservadores("Venta completada"); 

    if (next != null) {
        next.procesarPedido(pedido);
    }
}
    @Override
    public void actualizar(Pedido pedido, String mensaje) {
        if (mensaje.contains("entregado exitosamente")) {
            Logger.observerAction("Ventas", "REACCIONA: Marcando la venta como 'cerrada' internamente.");
        } else {
        }
    }
}