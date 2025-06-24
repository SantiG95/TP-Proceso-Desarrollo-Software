package models.pago;

public abstract class FormaDePago {
    
    public abstract double calcularRecargo(double monto);
    public abstract String getNombre();

    public void procesarPago(double monto) {
        System.out.printf("[PAGO] Procesando pago de $%,.2f vía %s...\n", monto, getNombre());
    }
}