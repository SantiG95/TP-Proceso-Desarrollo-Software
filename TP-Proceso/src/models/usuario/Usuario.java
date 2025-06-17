package models.usuario;

public abstract class Usuario {
    protected String nombre;
    protected String apellido;
    protected String correoElectronico;
    protected String documento;

    public Usuario(String nombre, String apellido, String correoElectronico, String documento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.documento = documento;
    }

    public abstract String getRol();

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getDocumento() {
        return documento;
    }
}