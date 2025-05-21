package Clases;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private List<Vehiculo> vehiculos = new ArrayList<>();

    public void agregarVehiculo(Vehiculo v) {
        vehiculos.add(v);
    }

    public List<Vehiculo> buscarDisponible() {
        List<Vehiculo> disponibles = new ArrayList<>();
        for (Vehiculo v : vehiculos) {
            if (v.estaDisponible()) {
                disponibles.add(v);
            }
        }
        return disponibles;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }
}

