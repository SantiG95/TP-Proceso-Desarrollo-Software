package Clases;

public abstract class Usuario {
    protected String nombre;
    protected String apellido;
    protected String correoElectronico;
    protected String direccion;
    protected int celular;
    protected int ingresoPromedio;
    protected int documento;

    protected EstrategiaRegistro estrategiaRegistro;

    public Usuario(String nombre, String apellido, String correoElectronico, String direccion,
                   int celular, int ingresoPromedio, int documento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.celular = celular;
        this.ingresoPromedio = ingresoPromedio;
        this.documento = documento;
    }

    public void setEstrategiaRegistro(EstrategiaRegistro estrategia) {
        this.estrategiaRegistro = estrategia;
    }

    public void registrarUsuario(String documento) {
        if (estrategiaRegistro != null) {
            estrategiaRegistro.registrarUsuario(documento);
        } else {
            System.out.println("Estrategia de registro no definida.");
        }
    }
}
