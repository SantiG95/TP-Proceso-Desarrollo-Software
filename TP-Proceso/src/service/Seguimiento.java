package service;

import models.pedido.Pedido;

public interface Seguimiento {

    void actualizar(Pedido pedido, String mensaje);
}