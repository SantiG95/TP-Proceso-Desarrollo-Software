package system;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import utils.Logger;
import models.pago.FormaDePago;
import models.pedido.*;
import models.usuario.*;
import models.vehiculo.*;
import service.*;

public class Contexto {

    private static Contexto instancia;
    private final List<Usuario> usuariosRegistrados;
    private final Catalogo catalogo;
    private final List<Pedido> pedidosRegistrados;


    private Contexto() {
        this.usuariosRegistrados = new ArrayList<>();
        this.catalogo = new Catalogo();
        this.pedidosRegistrados = new ArrayList<>();
        System.out.println("Sistema 'Contexto' inicializado (Singleton).");
    }


    public static synchronized Contexto getInstancia() {
        if (instancia == null) {
            instancia = new Contexto();
        }
        return instancia;
    }

  
    public Pedido realizarCompra(Usuario cliente, Vehiculo vehiculo,FormaDePago formaDePago) {

        Logger.system("Iniciando flujo de compra para: " + cliente.getNombre());             
        Pedido pedido = new Pedido(cliente, vehiculo, formaDePago);

        Ventas ventas = new Ventas();
        Logistica logistica = new Logistica();
        Entrega entrega = new Entrega();
        Cobranza cobranza = new Cobranza();
        Embarque embarque = new Embarque();

        pedido.agregarObservador(ventas);
        pedido.agregarObservador(logistica);
        pedido.agregarObservador(entrega);
        pedido.agregarObservador(cobranza);
        pedido.agregarObservador(embarque);
        
        ProcesadorPedido chain = ventas;
        ventas.setNext(logistica);
        logistica.setNext(entrega);

        chain.procesarPedido(pedido);

        this.pedidosRegistrados.add(pedido);

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
    this.usuariosRegistrados.add(usuario);
    }
    public Optional<Usuario> buscarUsuarioPorDocumento(String documento) {
    return usuariosRegistrados.stream()
            .filter(u -> u.getDocumento().equalsIgnoreCase(documento))
            .findFirst();
    }
}