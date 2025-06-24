package system;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import utils.Logger;
import models.pago.FormaDePago;
import models.pedido.*;
import models.usuario.Usuario;
import models.vehiculo.Catalogo;
import models.vehiculo.Vehiculo;
import service.*;

public class Contexto {

    private static Contexto instancia;

    private final List<Usuario> usuariosRegistrados = new ArrayList<>();
    private final Catalogo catalogo = new Catalogo();
    private final List<Pedido> pedidosRegistrados = new ArrayList<>();

    // Servicios y observadores
    private final Ventas ventas;
    private final Logistica logistica;
    private final Entrega entrega;
    private final Cobranza cobranza;
    private final Embarque embarque;

    // Cabeza de la cadena
    private final ProcesadorPedido chain;

    private Contexto() {
        // Instancio cada servicio/observador una sola vez
        ventas    = new Ventas();
        logistica = new Logistica();
        entrega   = new Entrega();
        cobranza  = new Cobranza();
        embarque  = new Embarque();

        // Armo la cadena de responsabilidad
        ventas.setNext(logistica);
        logistica.setNext(entrega);
        chain = ventas;

        System.out.println("Sistema 'Contexto' inicializado (Singleton).");
    }

    public static synchronized Contexto getInstancia() {
        if (instancia == null) {
            instancia = new Contexto();
        }
        return instancia;
    }

    public Pedido realizarCompra(Usuario cliente, Vehiculo vehiculo, FormaDePago formaDePago) {
        Logger.system("Iniciando flujo de compra para: " + cliente.getNombre());

        // Creo el pedido (el constructor ya registra al cliente y pone estado "Iniciado")
        Pedido pedido = new Pedido(cliente, vehiculo, formaDePago);

        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Configuraciones Adicionales ---");
        System.out.println("1) Garantía Extendida (12 meses)");
        System.out.println("2) Asientos de Cuero ($2.000)");
        System.out.println("3) Cámara de Retroceso ($800)");
        System.out.println("0) Ninguna");
        System.out.print("Elegí opciones separadas por coma: ");
        String entrada = sc.nextLine().trim();

        if (!entrada.equals("0")) {
            for (String opt : entrada.split(",")) {
                switch (opt.trim()) {
                    case "1" -> pedido.agregarConfiguracion(new GarantiaExtendida(12));
                    case "2" -> pedido.agregarConfiguracion(
                                    new EquipamientoExtra("Asientos de Cuero", 2000));
                    case "3" -> pedido.agregarConfiguracion(
                                    new Accesorio("Cámara de Retroceso", 800));
                    default  -> System.out.println("⚠ Opción inválida: " + opt);
                }
            }
        }        

        double costoFinal = pedido.calcularCostoTotal();
        System.out.printf("Costo total con extras: $%,.2f%n", costoFinal);
        // Registro todos los observadores
        pedido.agregarObservador(ventas);
        pedido.agregarObservador(logistica);
        pedido.agregarObservador(entrega);
        pedido.agregarObservador(cobranza);
        pedido.agregarObservador(embarque);

        // Disparo la cadena de responsabilidad
        chain.procesarPedido(pedido);

        // Guardo el pedido y retorno
        pedidosRegistrados.add(pedido);
        Logger.system("Flujo de compra finalizado. Estado final: " + pedido.getEstado());
        return pedido;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public List<Pedido> getPedidosRegistrados() {
        return new ArrayList<>(pedidosRegistrados);
    }

    public void registrarUsuario(Usuario usuario) {
        usuariosRegistrados.add(usuario);
    }

    public void agregarVehiculo(Vehiculo vehiculoNuevo) {
        catalogo.agregarVehiculo(vehiculoNuevo);
    }

    public Optional<Usuario> buscarUsuarioPorDocumento(String documento) {
        return usuariosRegistrados.stream()
            .filter(u -> u.getDocumento().equalsIgnoreCase(documento))
            .findFirst();
    }
}
