package com.example.barpancho.modelo.daos;

import com.example.barpancho.modelo.objetos.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOProducto {
    private Connection c;

    public DAOProducto() {
        try {
            c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bar_pancho", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Producto create(ResultSet rs) throws SQLException {
        return new Producto(rs.getInt(1),
                rs.getString(2),
                rs.getDouble(3));
    }

    public void insert(Producto a) {
        try {
            String insert = "INSERT INTO productos VALUES(?,?,?)";
            PreparedStatement st = c.prepareStatement(insert);
            st.setInt(1, a.getIdProducto());
            st.setString(2, a.getNombreProd());
            st.setDouble(3, a.getPrecio());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(String nombre) {
        try {
            String delete = "DELETE FROM productos WHERE NOMBRE_PRODUCTO = ?";
            PreparedStatement st = c.prepareStatement(delete);
            st.setString(1, nombre);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Producto a) {
        try {
            String update = "UPDATE productos SET NOMBRE_PRODUCTO = ?, PRECIO_PRODUCTO = ?" +
                    " WHERE ID_PRODUCTO = ?";
            PreparedStatement st = c.prepareStatement(update);
            st.setString(1, a.getNombreProd());
            st.setDouble(2, a.getPrecio());
            st.setInt(3, a.getIdProducto());

            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Producto read(String nombre){
        try {
            String read = "SELECT * FROM productos WHERE NOMBRE_PRODUCTO = ?";
            Producto producto = null;
            PreparedStatement st = c.prepareStatement(read);
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                producto = create(rs);
            }
            rs.close();
            st.close();
            return producto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Producto> readAll() {
        try {
            String readAll = "SELECT * FROM productos ORDER BY NOMBRE_PRODUCTO";
            List<Producto> lista;
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
