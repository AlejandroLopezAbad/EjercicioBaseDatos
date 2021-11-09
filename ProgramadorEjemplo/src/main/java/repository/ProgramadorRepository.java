package repository;

import database.DataBaseController;
import modelos.Programador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProgramadorRepository {

//getall getbyid save update delete
    //optional los get

    //optional List<programador> getall();{ result set "select * from Programadores"
    //return optionalel arraylist


    //save es un insert
    Programador progra = new Programador();

    public Optional<List<Programador>> getAll() throws SQLException {
        String query = "SELECT * FROM programador";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar registros de Usuarios"));
        ArrayList<Programador> list = new ArrayList<Programador>();
        while (result.next()) {
            list.add(
                    new Programador(
                            result.getString("id"),
                            result.getString("nombre"),
                            result.getDouble("salario"),
                            result.getString("idDepartamento"),
                            result.getString("lenguajes")
                    )
            );
        }
        db.close();
        return Optional.of(list);
    }

    public Programador getById(String id) throws SQLException {
        String query = "SELECT * FROM programador WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar programador con ID " + id));
        if (result.first()) {
            Programador user = new Programador(
                    result.getString("id"),
                    result.getString("nombre"),
                    result.getDouble("salario"),
                    result.getString("idDepartamento"),
                    result.getString("lenguajes")
            );
            db.close();
            return user;
        } else
            throw new SQLException("Error no existe Programador con ID: " + id);
    }

    public Programador save(Programador programador) throws SQLException {
        String query = "INSERT INTO programador VALUES (null, ?, ?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet res = db.insert(query, programador.getNombre(), programador.getSalario(),
                programador.getIdDepartamento(), programador.getLeguajes()).orElseThrow(() -> new SQLException("Error UserRepository al insertar Usuario"));
        // Para obtener su ID
        if (res.first()) {
            programador.setId(res.getString(1));
            // una vez insertado comprobamos que esta correcto para devolverlo
            db.close();
            return programador;
        } else
            throw new SQLException("Error ProgramadorRepository al insertar programdor en BD");
    }

    public Programador update(Programador programador) throws SQLException {
        String query = "UPDATE programador SET nombre = ?, email = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query, programador.getNombre(), programador.getSalario(), programador.getId());
        db.close();
        if (res > 0)
            return programador;
        else
            throw new SQLException("Error ProgramadorRepository al actualizar usuario con id: " + programador.getId());
    }


    public Programador delete(Programador programador) throws SQLException {
        String query = "DELETE FROM programador WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, programador.getId());
        db.close();
        if (res > 0)
            return programador;
        throw new SQLException("Error ProgramadorRepository al actualizar usuario con id: " + programador.getId());
    }

    public Programador getByNombre(String nombre) throws SQLException {
        String query = "SELECT * FROM programdor WHERE nombre = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, nombre).orElseThrow(() -> new SQLException("Error ProgramadorRepository no existe usuario con este nombre " + nombre));
        result.first();
        Programador programador = new Programador(
                result.getString("id"),
                result.getString("nombre"),
                result.getDouble("salario"),
                result.getString("idDepartamento"),
                result.getString("lenguajes")
        );
        ;
        db.close();
        return programador;
    }


}
