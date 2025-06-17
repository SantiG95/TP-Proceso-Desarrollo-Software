package utils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import models.pedido.Pedido;

public class InformePedido {
    private List<Pedido> listaPedidos;

    public InformePedido(List<Pedido> pedidos) {
        this.listaPedidos = pedidos;
    }

    /**
     * Muestra por consola un informe de pedidos filtrados por estado.
     * @param estado El estado por el cual filtrar (ej. "Entregado", "En Logística").
     */
    public void filtrarPorEstado(String estado) {
        System.out.println("\n--- INFORME: Pedidos con estado '" + estado + "' ---");
        List<Pedido> filtrados = listaPedidos.stream()
                .filter(p -> p.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
        
        if (filtrados.isEmpty()) {
            System.out.println("No se encontraron pedidos con ese estado.");
        } else {
            filtrados.forEach(p -> System.out.printf(
                "  - Pedido para: %s | Vehículo: %s | Costo Total: $%,.2f\n",
                p.getCliente().getNombre(),
                p.getVehiculo().toString(),
                p.getPrecioTotal()
            ));
        }
        System.out.println("------------------------------------------");
    }
    
    /**
     * Muestra por consola un informe de pedidos filtrados por fecha.
     * @param fecha La fecha a partir de la cual filtrar (inclusive).
     */
    public void filtrarPorFecha(LocalDate fecha) {
        System.out.println("\n--- INFORME: Pedidos desde la fecha '" + fecha + "' ---");
        List<Pedido> filtrados = listaPedidos.stream()
                .filter(p -> !p.getFechaPedido().isBefore(fecha))
                .collect(Collectors.toList());

        if (filtrados.isEmpty()) {
            System.out.println("No se encontraron pedidos desde esa fecha.");
        } else {
            filtrados.forEach(p -> System.out.printf(
                "  - Pedido del %s para: %s | Estado: %s\n",
                p.getFechaPedido(),
                p.getCliente().getNombre(),
                p.getEstado()
            ));
        }
        System.out.println("------------------------------------------");
    }
}



