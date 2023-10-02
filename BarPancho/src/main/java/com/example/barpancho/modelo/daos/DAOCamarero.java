package com.example.barpancho.modelo.daos;

import com.example.barpancho.modelo.objetos.Camarero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOCamarero {
    private Connection c;

    public DAOCamarero()  {
        try {
            c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bar_pancho", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Camarero create(ResultSet rs) throws SQLException {
        return new Camarero(rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5));
    }

    public void update(Camarero a){
        try {
            String update = "UPDATE camareros SET NOMBRE_CAMARERO = ?, APELLIDO1_CAMARERO = ?," +
                    "TELEFONO_CAMARERO = ?, EMAIL_CAMARERO = ? WHERE ID_CAMARERO = ?";
            PreparedStatement st = c.prepareStatement(update);
            st.setString(1, a.getNomCamarero());
            st.setString(2, a.getApellido1());
            st.setString(3, a.getTelefono());
            st.setString(4, a.getEmail());
            st.setInt(5, a.getIdCamarero());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Camarero read(Integer id) {
        try {
            String read = "SELECT * FROM camareros WHERE ID_CAMARERO = ?";
            Camarero camarero = null;
            PreparedStatement st = c.prepareStatement(read);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                camarero = create(rs);
            }
            rs.close();
            st.close();
            return camarero;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
