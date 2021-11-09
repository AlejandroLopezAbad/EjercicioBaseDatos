package modelos;

public class Departamento {
    private String id;
    private String nombre;
    private double presupesto;
    private String idjefe;

    public Departamento() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPresupesto() {
        return presupesto;
    }

    public void setPresupesto(double presupesto) {
        this.presupesto = presupesto;
    }

    public String getIdjefe() {
        return idjefe;
    }

    public void setIdjefe(String idjefe) {
        this.idjefe = idjefe;
    }

    public Departamento(String id, String nombre, double presupuesto, String idJefe) {
    }
}
