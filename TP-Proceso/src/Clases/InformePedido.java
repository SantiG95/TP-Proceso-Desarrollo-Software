package Clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InformePedido {
    
    private List<Pedido> listaPedidos;

    public InformePedido() {
        this.listaPedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        listaPedidos.add(pedido);
    }

    public List<Pedido> filtrarPorFecha(Date fecha) {
        List<Pedido> filtrados = new ArrayList<>();
        for (Pedido p : listaPedidos) {
            if (p.getFechaPedido().equals(fecha)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    public List<Pedido> filtrarPorEstado(String estado) {
        List<Pedido> filtrados = new ArrayList<>();
        for (Pedido p : listaPedidos) {
            if (p.getEstado().equalsIgnoreCase(estado)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }
}



