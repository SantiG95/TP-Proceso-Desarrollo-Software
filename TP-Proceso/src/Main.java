import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import models.pago.Contado;
import models.pago.FormaDePago;
import models.pago.TarjetaDeCredito;
import models.pago.Transferencia;
import models.pedido.Pedido;
import models.usuario.*;
import models.vehiculo.*;
import system.Contexto;
import utils.InformePedido;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Contexto sistema = Contexto.getInstancia();
    private static Usuario usuarioLogueado = null; 

    public static void main(String[] args) {
        cargarDatosIniciales();
        
 boolean salirDelSistema = false;
        while (!salirDelSistema) {
            
            iniciarSesion(); 
            
            if (usuarioLogueado == null) {
                salirDelSistema = true;
                continue; 
            }

            if (usuarioLogueado instanceof Administrador) {
                menuAdministrador();
            } else if (usuarioLogueado instanceof Vendedor) {
                menuVendedor();
            } else if (usuarioLogueado instanceof Comprador) {
                menuComprador();
            }

            System.out.println("Cerrando sesión de " + usuarioLogueado.getNombre() + "...");
            usuarioLogueado = null;
        }

        scanner.close();
        System.out.println("\nGracias por utilizar el sistema. ¡Hasta pronto!");
    }
    private static void iniciarSesion() {
        System.out.println("\n#############################################");
        System.out.println("##       BIENVENIDO A LA CONCESIONARIA       ##");
        System.out.println("#############################################");
        System.out.print("Ingrese su número de documento para iniciar sesión (o escriba 'salir' para terminar): ");
        String documento = scanner.nextLine();

        if (documento.equalsIgnoreCase("salir")) {
            usuarioLogueado = null; 
            return;
        }
        
        Optional<Usuario> usuarioOpt = sistema.buscarUsuarioPorDocumento(documento);
        if (usuarioOpt.isPresent()) {
            usuarioLogueado = usuarioOpt.get();
            System.out.println("\n¡Bienvenido, " + usuarioLogueado.getNombre() + "! Rol: " + usuarioLogueado.getRol());
        } else {
            System.out.println("Usuario no encontrado. Por favor, intente de nuevo.");
            pausa();
            iniciarSesion();
        }
    }


    private static void menuAdministrador() {
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Realizar Nueva Venta (Acceso Total)");
            System.out.println("2. Ver Catálogo de Vehículos");
            System.out.println("3. Generar Informes de Pedidos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerOpcion();

            switch (opcion) {
                case 1: realizarNuevaVenta(); break;
                case 2: verCatalogo(); break;
                case 3: generarInformes(); break;
            }
            pausa();
        }
    }
    
    private static void menuVendedor() {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("\n--- Menú Vendedor ---");
            System.out.println("1. Realizar Nueva Venta");
            System.out.println("2. Ver Catálogo de Vehículos Disponibles");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerOpcion();

            switch (opcion) {
                case 1: realizarNuevaVenta(); break;
                case 2: verCatalogo(); break;
            }
            pausa();
        }
    }

    private static void menuComprador() {
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("\n--- Menú Comprador: " + usuarioLogueado.getNombre() + " ---");
            System.out.println("1. Ver mis pedidos"); // Opción 1
            System.out.println("2. Ver Catálogo de Vehículos Disponibles"); // Opción 2
            System.out.println("3. Consultar Seguimiento de un Pedido");
            System.out.println("4. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            opcion = leerOpcion();

            // CORRECCIÓN AQUÍ:
            switch (opcion) {
                case 1: verMisPedidos(); break; // La opción 1 ahora llama al método correcto.
                case 2: verCatalogo(); break;   // La opción 2 ahora llama al método correcto.
                case 3: verSeguimientoDePedido(); break;
                case 4: 
                    break; 
                default: 
                    System.out.println("Opción no válida.");
            }
            if (opcion != 4) pausa();
        }
    }


    private static void realizarNuevaVenta() {
        System.out.println("\n--- Realizar Nueva Venta ---");
        List<Vehiculo> catalogo = sistema.getCatalogo().getVehiculos();
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, catalogo.get(i));
        }
        System.out.print("Seleccione el número del vehículo: ");
        int vehiculoIndex = leerOpcion() - 1;

        if (vehiculoIndex < 0 || vehiculoIndex >= catalogo.size()) {
            System.out.println("Selección inválida."); return;
        }
        Vehiculo vehiculoSeleccionado = catalogo.get(vehiculoIndex);
        
        Usuario cliente;
        if (usuarioLogueado instanceof Comprador) {
            cliente = usuarioLogueado;
        } else {
            System.out.print("Ingrese documento del cliente: "); String doc = scanner.nextLine();
            System.out.print("Ingrese nombre del cliente: "); String nombre = scanner.nextLine();
            cliente = new Comprador(nombre, "N/A", "N/A", doc);
        }

        System.out.println("Seleccione la forma de pago:");
        System.out.println("1. Contado");
        System.out.println("2. Transferencia");
        System.out.println("3. Tarjeta de Crédito");
        System.out.print("Opción: ");
        int opcionPago = leerOpcion();

        FormaDePago formaDePagoSeleccionada;
        switch (opcionPago) {
            case 1: formaDePagoSeleccionada = new Contado(); break;
            case 2: formaDePagoSeleccionada = new Transferencia(); break;
            case 3: formaDePagoSeleccionada = new TarjetaDeCredito(); break;
            default:
                System.out.println("Opción inválida, se usará Contado por defecto.");
                formaDePagoSeleccionada = new Contado();
        }

        System.out.print("¿Confirmar compra? (s/n): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            sistema.realizarCompra(cliente, vehiculoSeleccionado,formaDePagoSeleccionada);
            System.out.println("\n¡Venta procesada con éxito!");
        } else {
            System.out.println("\nVenta cancelada.");
        }
    }

    private static void verCatalogo() {
        System.out.println("\n--- Catálogo de Vehículos Disponibles ---");
        sistema.getCatalogo().getVehiculos().forEach(System.out::println);
    }
    
    private static void generarInformes() {
        System.out.println("\n--- Generación de Informes ---");
        InformePedido informe = new InformePedido(sistema.getPedidosRegistrados());
        informe.filtrarPorEstado("Entregado");
    }
    
    private static void verMisPedidos() {
        System.out.println("\n--- Mis Pedidos ---");
        List<Pedido> misPedidos = sistema.getPedidosRegistrados().stream()
                .filter(p -> p.getCliente().getDocumento().equals(usuarioLogueado.getDocumento()))
                .toList();

        if (misPedidos.isEmpty()) {
            System.out.println("No tienes pedidos registrados.");
        } else {
            misPedidos.forEach(p -> System.out.printf("- Vehículo: %s | Estado: %s | Costo: $%,.2f\n",
                    p.getVehiculo(), p.getEstado(), p.getPrecioTotal()));
        }
    }
    

    private static void cargarDatosIniciales() {
        System.out.println("Cargando datos iniciales del sistema...");
        // Usuarios
        sistema.registrarUsuario(new Administrador("Admin", "Root", "admin@concesionaria.com", "1"));
        sistema.registrarUsuario(new Vendedor("Carlos", "Ventas", "carlos@concesionaria.com", "2"));
        sistema.registrarUsuario(new Comprador("Nico", "Perez", "nico@email.com", "12345678"));
        sistema.registrarUsuario(new Comprador("Ana", "Gomez", "ana@email.com", "87654321"));
        
        // Vehículos
        sistema.getCatalogo().agregarVehiculo(new Auto("Toyota", 25000.0, "Corolla", "Gris", "ABC111"));
        sistema.getCatalogo().agregarVehiculo(new Camioneta("Ford", 40000.0, "Ranger", "Azul", "DEF222"));
        System.out.println("Datos cargados.");
    }
    
    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido.");
            return -1;
        }
    }
    
    private static void pausa() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    private static void verSeguimientoDePedido() {
    System.out.println("\n--- Consultar Seguimiento de Pedido ---");
    List<Pedido> misPedidos = sistema.getPedidosRegistrados().stream()
            .filter(p -> p.getCliente().getDocumento().equals(usuarioLogueado.getDocumento()))
            .toList();

    if (misPedidos.isEmpty()) {
        System.out.println("No tienes pedidos para consultar.");
        return;
    }

    System.out.println("Tus pedidos:");

    List<Pedido> pedidosActivos = misPedidos.stream()
        .filter(p -> !p.getEstado().equalsIgnoreCase("Entregado"))
        .toList();

    for (int i = 0; i < misPedidos.size(); i++) {
        Pedido p = misPedidos.get(i);
        System.out.printf("%d. Vehículo: %s | Estado Actual: %s\n", i + 1, p.getVehiculo(), p.getEstado());
    }

    System.out.print("Selecciona el número del pedido del que deseas ver el seguimiento: ");
    int pedidoIndex = leerOpcion() - 1;

    if (pedidoIndex < 0 || pedidoIndex >= misPedidos.size()) {
        System.out.println("Selección inválida.");
        return;
    }

    Pedido pedidoSeleccionado = misPedidos.get(pedidoIndex);

    System.out.println("\n==================================================================");
    System.out.println(" DETALLE DE SEGUIMIENTO PARA EL PEDIDO DEL VEHÍCULO: " + pedidoSeleccionado.getVehiculo());
    System.out.println("==================================================================");
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy 'a las' HH:mm:ss");
    
    Map<String, LocalDateTime> historial = pedidoSeleccionado.getHistorialEstados();

    String[] flujoDeEstados = {"Iniciado", "Venta Realizada", "En Logística", "Entregado"};

    
    for (String estadoDelFlujo : flujoDeEstados) {
        if (historial.containsKey(estadoDelFlujo)) {
            String fecha = historial.get(estadoDelFlujo).format(formatter);
            System.out.printf("  [X] %-20s (Completado el %s)\n", estadoDelFlujo, fecha);
        } else {
            System.out.printf("  [ ] %-20s (Pendiente)\n", estadoDelFlujo);
        }
    }
    
    System.out.println("\n------------------------------------------------------------------");
    System.out.println("  ESTADO ACTUAL: " + pedidoSeleccionado.getEstado());
    System.out.println("------------------------------------------------------------------");
    }
}