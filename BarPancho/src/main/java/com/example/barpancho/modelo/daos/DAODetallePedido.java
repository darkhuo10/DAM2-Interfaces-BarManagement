package com.example.barpancho.modelo.daos;

import com.example.barpancho.modelo.objetos.DetallePedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAODetallePedido {
    private Connection c;

    public DAODetallePedido() {
        try {
            c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bar_pancho", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DetallePedido create(ResultSet rs) throws SQLException {
        return new DetallePedido(rs.getInt(1),
                rs.getInt(2),
                rs.getInt(3));
    }

    public void insert(DetallePedido a) {
        try {
            String insert = "INSERT INTO detalles_pedidos VALUES(?, ?, ?)";
            PreparedStatement st = c.prepareStatement(insert);
            st.setInt(1, a.getIdProducto());
            st.setInt(2, a.getIdPedido());
            st.setInt(3, a.getUnidadesProducto());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<DetallePedido> readMesaPedido(int mesa) {
        try {
            String readAll = "SELECT d.ID_PRODUCTO, d.ID_PEDIDO, d.UNIDADES_PRODUCTO, p.NOMBRE_PRODUCTO, p.PRECIO_PRODUCTO, e.ID_MESA " +
                    "FROM detalles_pedidos d, productos p, pedidos e " +
                    "WHERE d.ID_PRODUCTO = p.ID_PRODUCTO AND d.ID_PEDIDO = e.ID_PEDIDO AND e.ID_MESA = ?;";
            PreparedStatement st = c.prepareStatement(readAll);
            st.setInt(1, mesa);
            List<DetallePedido> lista;
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



