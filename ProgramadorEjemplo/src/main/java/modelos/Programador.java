package modelos;

import java.util.List;

public class Programador {

    private String id;
    private String nombre;
    private double salario;
    private String idDepartamento;
    private List<String> leguajes;

    public Programador(String id, String nombre, double salario, String idDepartamento, String lenguajes) {
    }

    public Programador() {

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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public List<String> getLeguajes() {
        return leguajes;
    }

    public void setLeguajes(List<String> leguajes) {
        this.leguajes = leguajes;
    }
}
