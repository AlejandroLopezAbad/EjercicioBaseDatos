package repository;

import database.DataBaseController;
import modelos.Departamento;
import modelos.Programador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoRepository {
    Departamento derpa = new Departamento();


    public List<Departamento> getAll() throws SQLException {
        String query = "SELECT * FROM departamento";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar registros de Departamentos"));
        ArrayList<Departamento> list = new ArrayList<Departamento>();
        while (result.next()) {
            list.add(
                    new Departamento(
                            result.getString("id"),
                            result.getString("nombre"),
                            result.getDouble("presupuesto"),
                            result.getString("idJefe")
                    )
            );
        }
        db.close();
        return list;
    }


    public Departamento getById(String id) throws SQLException {
        String query = "SELECT * FROM departamento WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar el departamento con ID " + id));
        if (result.first()) {
            Departamento depa =  new Departamento(
                    result.getString("id"),
                    result.getString("nombre"),
                    result.getDouble("presupuesto"),
                    result.getString("idJefe")
            );
            db.close();
            return depa ;
        } else
            throw new SQLException("Error no existe este departamento con este ID: " + id);
    }

    public Departamento save(Departamento departamento) throws SQLException {
        String query = "INSERT INTO departamento VALUES (null, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet res = db.insert(query, departamento.getNombre(), departamento.getPresupesto()
                , departamento.getIdjefe()).orElseThrow(() -> new SQLException("Error DepartamentoRepository al insertar departamento"));
        // Para obtener su ID
        if (res.first()) {
            departamento.setId(res.getString(1));
            // una vez insertado comprobamos que esta correcto para devolverlo
            db.close();
            return departamento;
        } else
            throw new SQLException("Error  DepartamentoRepository al insertar departamento en BD");
    }

    public Departamento update(Departamento departamento) throws SQLException {
        String query = "UPDATE departamento SET nombre = ?, presupuesto = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query, departamento.getNombre(), departamento.getPresupesto(), departamento.getId());
        db.close();
        if (res > 0)
            return departamento;
        else
            throw new SQLException("Error UserRepository al actualizar usuario con id: " + departamento.getId());
    }


    public Programador delete(Programador programador) throws SQLException {
        String query = "DELETE FROM programador WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, programador.getId());
        db.close();
        if (res > 0)
            return programador;
        throw new SQLException("Error UserRepository al actualizar usuario con id: " + programador.getId());
    }

    public Departamento getByNombre(String nombre) throws SQLException {
        String query = "SELECT * FROM departamento WHERE nombre = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, nombre).orElseThrow(() -> new SQLException("Error DepartamentoRepository no existe departamento con este nombre " + nombre));
        result.first();
        Departamento depa =  new Departamento(
                result.getString("id"),
                result.getString("nombre"),
                result.getDouble("presupuesto"),
                result.getString("idJefe")
        );
        ;
        db.close();
        return depa;
    }






}
