package Clases;

public class EquipamientoExtra extends ConfiguracionAdicional{

	private String equipamientoAgregado;
	private double costoDelEquipamiento;
	
	public EquipamientoExtra (String descripcion, String equipamientoAgregado, double costoDelEquipamiento) {
		super(descripcion);
		this.equipamientoAgregado =equipamientoAgregado;
		this.costoDelEquipamiento = costoDelEquipamiento;
	}
	
	public double calcularCostoAdicional() {
        return costoDelEquipamiento;
    }
	
}
