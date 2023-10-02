package com.example.barpancho.modelo.daos;

import com.example.barpancho.modelo.objetos.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOPedido{
    private Connection c;

    public DAOPedido() {
        try {
            c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bar_pancho", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pedido create(ResultSet rs) throws SQLException {
        return new Pedido(rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getDate(5).toString());
    }

    public void insert(Pedido a) {
        try {
            String insert = "INSERT INTO pedidos VALUES(default, ?,?,?,?)";
            PreparedStatement st = c.prepareStatement(insert);
            st.setInt(1, a.getIdMesa());
            st.setString(2, a.getNombreUsuario());
            st.setInt(3, a.getIdCamarero());
            st.setDate(4, Date.valueOf(a.getFecha()));
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public Pedido returnPedido() {
        try {
            String sql = "SELECT * FROM `pedidos` ORDER BY `ID_PEDIDO` DESC LIMIT 1";
            Pedido pedido = null;
            PreparedStatement st = c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                pedido = create(rs);
            }
            rs.close();
            st.close();
            return pedido;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
