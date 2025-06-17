package service;

import models.pedido.Pedido;

public interface ProcesadorPedido {
    
    void setNext(ProcesadorPedido next);
    void procesarPedido(Pedido pedido);
}