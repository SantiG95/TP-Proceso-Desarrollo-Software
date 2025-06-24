import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import models.pago.*;
import models.pedido.Pedido;
import models.usuario.*;
import models.vehiculo.*;
import system.Contexto;
import utils.InformePedido;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Contexto sistema = Contexto.getInstancia();
    private static Usuario usuarioLogueado;

    public static void main(String[] args) {
        cargarDatosIniciales();
        boolean salir = false;
        while (!salir) {
            iniciarSesion();
            if (usuarioLogueado == null) {
                salir = true;
                continue;
            }
            if (usuarioLogueado instanceof Administrador) {
                menuAdministrador();
            } else if (usuarioLogueado instanceof Vendedor) {
                menuVendedor();
            } else {  // Comprador
                menuComprador();
            }
            System.out.println("\nCerrando sesión de " +
                (usuarioLogueado != null ? usuarioLogueado.getNombre() : "") + "...\n");
            usuarioLogueado = null;
        }
        scanner.close();
        System.out.println("Gracias por utilizar el sistema. ¡Hasta pronto!");
    }

    private static void iniciarSesion() {
        System.out.println("\n#############################################");
        System.out.println(  "##       BIENVENIDO A LA CONCESIONARIA     ##");
        System.out.println(  "#############################################");
        while (true) {
            System.out.print("Ingrese su documento (o salir): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("salir")) {
                usuarioLogueado = null;
                return;
            }
            if (!input.matches("\\d+")) {
                System.out.println("⚠️ Formato inválido: sólo dígitos. Intente de nuevo.");
                continue;
            }
            Optional<Usuario> opt = sistema.buscarUsuarioPorDocumento(input);
            if (opt.isEmpty()) {
                System.out.println("❌ Usuario no encontrado. Verifique el documento.");
                continue;
            }
            usuarioLogueado = opt.get();
            System.out.printf("✅ ¡Bienvenido, %s! Rol: %s%n",
                usuarioLogueado.getNombre(),
                usuarioLogueado.getRol());
            return;
        }
    }

    private static void menuAdministrador() {
        int opcion;
        do {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Realizar Nueva Venta (Acceso Total)");
            System.out.println("2. Ver Catálogo de Vehículos");
            System.out.println("3. Agregar vehiculo al catalogo");
            System.out.println("4. Generar Informes de Pedidos");
            System.out.println("5. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            opcion = leerOpcion();
            switch (opcion) {
                case 1 -> realizarNuevaVenta();
                case 2 -> verCatalogo();
                case 3 -> agregarAuto();
                case 4 -> generarInformes();
                case 5 -> System.out.println("Volviendo al inicio de sesión...");
                default -> System.out.println("Opción inválida.");
            }
            if (opcion != 4) pausa();
        } while (opcion != 4);
    }

    private static void menuVendedor() {
        int opcion;
        do {
            System.out.println("\n--- Menú Vendedor ---");
            System.out.println("1. Realizar Nueva Venta");
            System.out.println("2. Ver Catálogo de Vehículos Disponibles");
            System.out.println("3. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            opcion = leerOpcion();
            switch (opcion) {
                case 1 -> realizarNuevaVenta();
                case 2 -> verCatalogo();
                case 3 -> System.out.println("Volviendo al inicio de sesión...");
                default -> System.out.println("Opción inválida.");
            }
            if (opcion != 3) pausa();
        } while (opcion != 3);
    }

    private static void menuComprador() {
        int opcion;
        do {
            System.out.println("\n--- Menú Comprador: " + usuarioLogueado.getNombre() + " ---");
            System.out.println("1. Ver mis pedidos");
            System.out.println("2. Ver Catálogo de Vehículos Disponibles");
            System.out.println("3. Consultar Seguimiento de un Pedido");
            System.out.println("4. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            opcion = leerOpcion();
            switch (opcion) {
                case 1 -> verMisPedidos();
                case 2 -> verCatalogo();
                case 3 -> verSeguimientoDePedido();
                case 4 -> System.out.println("Volviendo al inicio de sesión...");
                default -> System.out.println("Opción inválida.");
            }
            if (opcion != 4) pausa();
        } while (opcion != 4);
    }

    private static void realizarNuevaVenta() {
        System.out.println("\n--- Realizar Nueva Venta ---");
        List<Vehiculo> catalogo = sistema.getCatalogo().getVehiculos();
        if (catalogo.isEmpty()) {
            System.out.println("No hay vehículos disponibles.");
            return;
        }
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, catalogo.get(i));
        }
        System.out.print("Seleccione el número del vehículo: ");
        int idxVeh = leerOpcion() - 1;
        if (idxVeh < 0 || idxVeh >= catalogo.size()) {
            System.out.println("Selección inválida.");
            return;
        }
        Vehiculo veh = catalogo.get(idxVeh);

        Usuario cliente;
        if (usuarioLogueado instanceof Comprador) {
            cliente = usuarioLogueado;
        } else {
            System.out.print("Documento del cliente: ");
            String doc = scanner.nextLine().trim();
            System.out.print("Nombre del cliente: ");
            String nombre = scanner.nextLine().trim();
            cliente = new Comprador(nombre, "", "", doc);
            sistema.registrarUsuario(cliente);
        }

        System.out.println("Seleccione forma de pago:");
        System.out.println("1. Contado");
        System.out.println("2. Transferencia");
        System.out.println("3. Tarjeta de Crédito");
        System.out.print("Opción: ");
        int opPago = leerOpcion();
        FormaDePago pago = switch (opPago) {
            case 2 -> new Transferencia();
            case 3 -> new TarjetaDeCredito();
            default -> new Contado();
        };

        System.out.print("¿Confirmar compra? (s/n): ");
        String resp = scanner.nextLine().trim();
        if (resp.equalsIgnoreCase("s")) {
            sistema.realizarCompra(cliente, veh, pago);
            System.out.println("✅ Venta procesada con éxito.");
        } else {
            System.out.println("❌ Venta cancelada.");
        }
    }

    private static void verCatalogo() {
        System.out.println("\n--- Catálogo de Vehículos Disponibles ---");
        sistema.getCatalogo().getVehiculos()
               .forEach(v -> System.out.println(v));
    }

    private static void agregarAuto() {
        System.out.println("\n--- AGREGAR VEHICULO ---");
        System.out.println("Seleccione el tipo de vehiculo ");
        System.out.println("1. Auto");
        System.out.println("2. Camion");
        System.out.println("3. Moto");
        System.out.println("4. Camioneta");
        System.out.print("Opción: ");
        int opVehiculo = leerOpcion();

        System.out.print("Ingrese nombre de marca: ");
        String marca = scanner.nextLine().trim();

        System.out.print("Ingrese el modelo del vehiculo: ");
        String modelo = scanner.nextLine().trim();

        System.out.print("Ingrese el color del vehiculo: ");
        String color = scanner.nextLine().trim();

        System.out.print("Ingrese el numero de chasis del vehiculo: ");
        String chasis = scanner.nextLine().trim();

        int precio = 0;
        while(precio == 0){
            try {
                System.out.print("Ingrese el precio: ");
                precio = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
        

        System.out.println(marca + " " + modelo + " " + color +
        "\nChasis numero " + chasis +
        "\nPrecio: " + precio);

        System.out.println("¿Desea agregar este vehiculo?");
        System.out.println("1. Si");
        System.out.println("2. No");
        System.out.print("Opcion: ");
        int opDecision = leerOpcion();

        if(opDecision == 1){
            Vehiculo vehiculoNuevo;
            switch (opVehiculo) {
                case 1:
                    vehiculoNuevo = new Auto(marca, precio, modelo, color, chasis);
                    break;
                case 2:
                    vehiculoNuevo = new Camion(marca, precio, modelo, color, chasis);
                    break;
                case 3:
                    vehiculoNuevo = new Moto(marca, precio, modelo, color, chasis);
                        break;
                case 4:
                    vehiculoNuevo = new Camioneta(marca, precio, modelo, color, chasis);
                        break;
        
                default:
                    return;
            }
            sistema.agregarVehiculo(vehiculoNuevo);
            System.out.println("Vehiculo agregado con exito");
        } 
        else{
            System.out.println("Operacion cancelada");
        }
            
    }

    private static void generarInformes() {
        System.out.println("\n--- Informe: Pedidos ENTREGADOS ---");
        InformePedido inf = new InformePedido(sistema.getPedidosRegistrados());
        List<Pedido> entregados = inf.filtrarPorEstado("Entregado");
        if (entregados.isEmpty()) {
            System.out.println("No hay pedidos entregados aún.");
            return;
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (int i = 0; i < entregados.size(); i++) {
            Pedido p = entregados.get(i);
            System.out.printf(
                "Pedido %d | Cliente: %s %s | Vehículo: %s | Fecha: %s | Total: $%,.2f%n",
                i + 1,
                p.getCliente().getNombre(),
                p.getCliente().getApellido(),
                p.getVehiculo(),
                p.getFechaPedido().format(fmt),
                p.getPrecioTotal()
            );
        }
    }

    private static void verMisPedidos() {
        System.out.println("\n--- Mis Pedidos ---");
        List<Pedido> mis = sistema.getPedidosRegistrados().stream()
            .filter(p -> p.getCliente().getDocumento()
                          .equals(usuarioLogueado.getDocumento()))
            .toList();
        if (mis.isEmpty()) {
            System.out.println("No tienes pedidos registrados.");
        } else {
            for (int i = 0; i < mis.size(); i++) {
                Pedido p = mis.get(i);
                System.out.printf(
                    "%d) %s | Estado: %s | Costo: $%,.2f%n",
                    i + 1,
                    p.getVehiculo(),
                    p.getEstado(),
                    p.getPrecioTotal()
                );
            }
        }
    }

private static void verSeguimientoDePedido() {
    System.out.println("\n--- Consultar Seguimiento de Pedido ---");
    List<Pedido> misPedidos = sistema.getPedidosRegistrados().stream()
        .filter(p -> p.getCliente().getDocumento()
                      .equals(usuarioLogueado.getDocumento()))
        .toList();

    if (misPedidos.isEmpty()) {
        System.out.println("No tienes pedidos registrados.");
        return;
    }

    for (int i = 0; i < misPedidos.size(); i++) {
        Pedido p = misPedidos.get(i);
        System.out.printf("%d) %s — Estado: %s%n",
            i + 1, p.getVehiculo(), p.getEstado());
    }

    System.out.print("Selecciona el número del pedido para ver el historial: ");
    int idx = leerOpcion() - 1;
    if (idx < 0 || idx >= misPedidos.size()) {
        System.out.println("Selección inválida.");
        return;
    }

    mostrarHistorial(misPedidos.get(idx));
    }

    private static void mostrarHistorial(Pedido pedido) {
        System.out.println("\n--- Historial de " + pedido.getVehiculo() + " ---");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy 'a las' HH:mm:ss");
        String[] flujo = {"Iniciado", "Venta Realizada", "En Logística", "Entregado"};
        Map<String, LocalDateTime> hist = pedido.getHistorialEstados();
        for (String estado : flujo) {
            if (hist.containsKey(estado)) {
                String fecha = hist.get(estado).format(fmt);
                System.out.printf(" [X] %-20s completado el %s%n", estado, fecha);
            } else {
                System.out.printf(" [ ] %-20s (pendiente)%n", estado);
            }
        }
        System.out.println("→ Estado actual: " + pedido.getEstado());
    }

    private static void cargarDatosIniciales() {
        System.out.println("Cargando datos iniciales...");
        sistema.registrarUsuario(new Administrador("Admin", "Root", "admin@concesionaria.com", "1"));
        sistema.registrarUsuario(new Vendedor("Carlos", "Ventas", "carlos@concesionaria.com", "2"));
        sistema.registrarUsuario(new Comprador("Nico", "Perez", "nico@email.com", "12345678"));
        sistema.registrarUsuario(new Comprador("Ana", "Gomez", "ana@email.com", "87654321"));
        sistema.getCatalogo().agregarVehiculo(new Auto("Toyota", 25000.0, "Corolla", "Gris", "ABC111"));
        sistema.getCatalogo().agregarVehiculo(new Camioneta("Ford", 40000.0, "Ranger", "Azul", "DEF222"));
        System.out.println("Datos cargados.\n");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void pausa() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
