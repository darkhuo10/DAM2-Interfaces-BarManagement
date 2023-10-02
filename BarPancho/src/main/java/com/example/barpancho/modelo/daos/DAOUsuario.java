package com.example.barpancho.modelo.daos;

import com.example.barpancho.modelo.objetos.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUsuario {
    private Connection c;

    public DAOUsuario() {
        try {
            c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bar_pancho", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario create(ResultSet rs) throws SQLException {
        return new Usuario(rs.getString(1),
                rs.getInt(2),
                rs.getString(3));

    }

    public void update(Usuario a) {
        try {
            String update = "UPDATE usuarios SET PASSWORD_USUARIO = ? WHERE ID_CAMARERO = ? AND NOMBRE_USUARIO = ?";
            PreparedStatement st = c.prepareStatement(update);
            st.setString(1, a.getPassword());
            st.setInt(2, a.getIdCamarero());
            st.setString(3, a.getNombreUsuario());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public Usuario read(String user) {
        try {
            String read = "SELECT * FROM usuarios WHERE NOMBRE_USUARIO = ?";
            Usuario usuario = null;
            PreparedStatement st = c.prepareStatement(read);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                usuario = create(rs);
            }
            rs.close();
            st.close();
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Usuario> readAll() {
        try{
            String readAll = "SELECT * FROM usuarios";
            List<Usuario> lista;
            PreparedStatement st = c.prepareStatement(readAll);
            ResultSet rs = st.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()){
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
