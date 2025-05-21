package Clases;

import java.util.Date;

public class Cobranza {
    private Date fechaCobro;
    private double montoPagado;
    private String estado;

    public Cobranza(Date fechaCobro, double montoPagado, String estado) {
        this.fechaCobro = fechaCobro;
        this.montoPagado = montoPagado;
        this.estado = estado;
    }

    public void procesarCobro(Pedido pedido) {
        System.out.println("Procesando cobro para el pedido: " + pedido);
        // Aquí se puede agregar la lógica para procesar el cobro Forma de pago)
    }
}
