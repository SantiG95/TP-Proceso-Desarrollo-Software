package Clases;

import java.util.ArrayList;
import java.util.List;

public class Contexto {
    private Catalogo catalogo;
    private List<Pedido> pedidos; // Composición

    public Contexto(Catalogo catalogo) {
        this.catalogo = catalogo;
        this.pedidos = new ArrayList<>();
    }

    public boolean registrarUsuario() {
        // lógica real pendiente
        return true;
    }

    public List<Vehiculo> verCatalogoVehiculos() {
        return catalogo.getVehiculos();
    }

    public boolean realizarCompra(double precioUnitario) {
        Pedido nuevoPedido = new Pedido(precioUnitario, new java.util.Date());
        pedidos.add(nuevoPedido);
        nuevoPedido.procesarPedido();
        return true;
    }

    public String verEstado(Pedido pedido) {
        return pedido.getEstado();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}

