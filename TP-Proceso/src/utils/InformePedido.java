package utils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import models.pedido.Pedido;

public class InformePedido {
    private final List<Pedido> listaPedidos;

    public InformePedido(List<Pedido> pedidos) {
        this.listaPedidos = pedidos;
    }

    /** Devuelve la lista filtrada por estado */
    public List<Pedido> filtrarPorEstado(String estado) {
        return listaPedidos.stream()
            .filter(p -> p.getEstado().equalsIgnoreCase(estado))
            .collect(Collectors.toList());
    }

    /** Imprime un informe enumerado sin usar getId() */
    public void mostrarPorEstado(String estado) {
        System.out.println("\n--- INFORME: Pedidos estado='" + estado + "' ---");
        List<Pedido> filtrados = filtrarPorEstado(estado);

        if (filtrados.isEmpty()) {
            System.out.println("No se encontraron pedidos con ese estado.");
        } else {
            int contador = 1;
            for (Pedido p : filtrados) {
                System.out.printf("%2d) Cliente: %s %s | Vehículo: %s | Total: $%,.2f%n",
                    contador++,
                    p.getCliente().getNombre(),
                    p.getCliente().getApellido(),
                    p.getVehiculo(),
                    p.getPrecioTotal()
                );
            }
        }
        System.out.println("------------------------------------------");
    }

    /** Devuelve la lista filtrada por fecha */
    public List<Pedido> filtrarPorFecha(LocalDate fecha) {
        return listaPedidos.stream()
            .filter(p -> !p.getFechaPedido().isBefore(fecha))
            .collect(Collectors.toList());
    }

    /** Imprime un informe enumerado de pedidos desde fecha dada */
    public void mostrarPorFecha(LocalDate fecha) {
        System.out.println("\n--- INFORME: Pedidos desde '" + fecha + "' ---");
        List<Pedido> filtrados = filtrarPorFecha(fecha);

        if (filtrados.isEmpty()) {
            System.out.println("No se encontraron pedidos desde esa fecha.");
        } else {
            int contador = 1;
            for (Pedido p : filtrados) {
                System.out.printf("%2d) Fecha: %s | Cliente: %s %s | Estado: %s%n",
                    contador++,
                    p.getFechaPedido(),
                    p.getCliente().getNombre(),
                    p.getCliente().getApellido(),
                    p.getEstado()
                );
            }
        }
        System.out.println("------------------------------------------");
    }
}
