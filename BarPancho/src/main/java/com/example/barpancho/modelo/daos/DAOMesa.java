package com.example.barpancho.modelo.daos;

import com.example.barpancho.modelo.objetos.Mesa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOMesa {

    private Connection c;

    public DAOMesa() {
        try {
            c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bar_pancho", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Mesa create(ResultSet rs) throws SQLException {
        return new Mesa(rs.getInt(1),
                rs.getInt(2),
                rs.getString(3));
    }


    public void update(Mesa a) {
        try {
            String update = "UPDATE mesa SET ESTADO = ?, COMENSALES = ? " +
                    "WHERE ID_MESA = ?";
            PreparedStatement st = c.prepareStatement(update);
            st.setString(1, a.getEstado());
            st.setInt(2, a.getnComensasles());
            st.setInt(3, a.getIdMesa());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Mesa read(Integer id) {
        try {
            String read = "SELECT * FROM mesa WHERE ID_MESA = ?";
            Mesa mesa = null;
            PreparedStatement st = c.prepareStatement(read);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                mesa = create(rs);
            }
            rs.close();
            st.close();
            return mesa;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Mesa> readAll(){
        try {
            String readAll = "SELECT * FROM mesa";
            List<Mesa> lista;
            PreparedStatement st = c.prepareStatement(readAll);
            ResultSet rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(create(rs));
            }
            rs.close();
            st.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
