package Clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private double precioUnitario;
    private String estado;
    private Date fechaPedido;

    private ArrayList<Cliente> listaClientela;
    private ArrayList<Cliente> listaSeguimiento;

    // Composición: el Pedido "tiene" configuraciones adicionales
    private List<ConfiguracionAdicional> configuracionesAdicionales;

    public Pedido(double precioUnitario, Date fechaPedido) {
        this.precioUnitario = precioUnitario;
        this.fechaPedido = fechaPedido;
        this.estado = "Pendiente";
        this.listaClientela = new ArrayList<>();
        this.listaSeguimiento = new ArrayList<>();
        this.configuracionesAdicionales = new ArrayList<>();
    }

    public void agregarConfiguracion(ConfiguracionAdicional config) {
        configuracionesAdicionales.add(config);
    }

    public double calcularCostoTotal() {
        double totalAdicionales = 0;
        for (ConfiguracionAdicional config : configuracionesAdicionales) {
            totalAdicionales += config.calcularCostoAdicional();
        }
        return precioUnitario + totalAdicionales;
    }

    public void procesarPedido() {
        this.estado = "Procesado";
    }

    public String getEstado() {
        return estado;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }
}
