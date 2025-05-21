package Clases;

import java.util.Date;
import Clases.Pedido;

public class Ventas implements Seguimiento{

    private Date venta_fecha;
    private boolean esPlan;
    private double precioTotal;


    public Ventas(Date venta_fecha, boolean esPlan, double precioTotal) {
        this.venta_fecha = venta_fecha;
        this.esPlan = esPlan;
        this.precioTotal = precioTotal;
    }

    public Date getVenta_fecha() {
        return venta_fecha;
    }

    public void setVenta_fecha(Date venta_fecha) {
        this.venta_fecha = venta_fecha;
    }

    public boolean isEsPlan() {
        return esPlan;
    }

    public void setEsPlan(boolean esPlan) {
        this.esPlan = esPlan;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }


    public double calculoTotal() {
        return 0.0; 
    }

    public void procesarPedido(Pedido pedido) {
        System.out.println("Procesando pedido: " + pedido);
    }

    @Override
    public String getEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEstado'");
    }

    @Override
    public void setEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstado'");
    }

    @Override
    public String realizarConsultas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realizarConsultas'");
    }

}
