package models.vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Catalogo {
    private List<Vehiculo> vehiculos;

    public Catalogo() {
        this.vehiculos = new ArrayList<>();
    }


    public void agregarVehiculo(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
    }


    public List<Vehiculo> buscarDisponible(String marca) {
        return vehiculos.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase(marca))
                .collect(Collectors.toList());
    }


    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos);
    }
}