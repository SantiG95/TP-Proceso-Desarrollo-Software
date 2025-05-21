package Clases;

public class GarantiaExtendida extends ConfiguracionAdicional{

	private int duracionEnMeses;
	private double costoGarantiaExtendida;
	
	public GarantiaExtendida(String descripcion, int duracionEnMeses, double costoGarantiaExtendida) {
		super(descripcion);
		this.duracionEnMeses = duracionEnMeses;
		this.costoGarantiaExtendida = costoGarantiaExtendida;
	}
	
	public double calcularCostoAdicional() {
		return duracionEnMeses * costoGarantiaExtendida;
	}
	
}
