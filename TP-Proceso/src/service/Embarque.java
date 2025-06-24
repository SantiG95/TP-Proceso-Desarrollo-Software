package service;
import models.pedido.Pedido;
import utils.Logger;
public class Embarque implements Seguimiento {

    @Override
    public void actualizar(Pedido pedido, String mensaje) {
        if (mensaje.contains("Logística en proceso")) {
            Logger.observerAction("Embarque", "REACCIONA: Preparando documentación de embarque.");
        } else {
        }
    }
}