package Clases;


public abstract class ConfiguracionAdicional {
	
	protected String descripcion;
	
	
	public ConfiguracionAdicional(String descripcion2) {
		// TODO Auto-generated constructor stub
	}
	public void configuracionAdicional(String descripcion) {
		this.descripcion = descripcion;
	}
	public void cambiarConfiguracion(String nuevaDescripcion) {
		this.descripcion = nuevaDescripcion;	
	}
	
	
	public abstract double calcularCostoAdicional();

}
