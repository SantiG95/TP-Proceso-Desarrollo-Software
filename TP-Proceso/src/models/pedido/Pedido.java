package models.pedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import models.pago.FormaDePago;
import models.usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

import models.vehiculo.Vehiculo;
import service.Seguimiento;
import utils.Logger;

public class Pedido {
    private double precioTotal;
    private String estado;
    private final LocalDate fechaPedido;
    private final Usuario cliente;
    private final Vehiculo vehiculo;
    private final List<ConfiguracionAdicional> configuraciones = new ArrayList<>();
    private final List<Seguimiento> observadores = new ArrayList<>(); 
    private final Map<String, LocalDateTime> historialEstados = new LinkedHashMap<>();
    private final FormaDePago formaDePago;


    public Pedido(Usuario cliente, Vehiculo vehiculo,FormaDePago formaDePago) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaPedido = LocalDate.now();
        this.formaDePago = formaDePago;
        setEstado("Iniciado"); 

    }


    public void agregarConfiguracion(ConfiguracionAdicional config) {
        this.configuraciones.add(config);
    }

  
    public double calcularCostoTotal() {
        double costoTotal = vehiculo.getPrecioBase() + vehiculo.calcularImpuesto();
        for (ConfiguracionAdicional config : configuraciones) {
            costoTotal += config.calcularCostoAdicional();
        }
        this.precioTotal = costoTotal;
        return costoTotal;
    }


    public void agregarObservador(Seguimiento s) {
        observadores.add(s);
    }


    public void notificarObservadores(String mensaje) {
        Logger.observerEvent(mensaje);
        for (Seguimiento s : observadores) {
            s.actualizar(this, mensaje);
            }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        this.historialEstados.put(estado, LocalDateTime.now());
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public List<ConfiguracionAdicional> getConfiguraciones() {
        return new ArrayList<>(configuraciones);
    }

    public Map<String, LocalDateTime> getHistorialEstados() {
        return new LinkedHashMap<>(this.historialEstados); 
    }

    public FormaDePago getFormaDePago() {
        return formaDePago;
    }
}