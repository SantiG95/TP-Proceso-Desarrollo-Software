package service;

import models.pedido.Pedido;
import utils.Logger;

public class Cobranza implements Seguimiento {

    @Override
    public void actualizar(Pedido pedido, String mensaje) {
        if (mensaje.contains("Venta completada")) {
            Logger.observerAction("Cobranza", "REACCIONA: Iniciando facturación.");;
        } else {
            Logger.observerAction("Cobranza", "IGNORA: Evento no relevante.");
        }
    
    }
}