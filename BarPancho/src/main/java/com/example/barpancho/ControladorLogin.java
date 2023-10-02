package com.example.barpancho;

import com.example.barpancho.modelo.daos.DAOUsuario;
import com.example.barpancho.modelo.objetos.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ControladorLogin {
    @FXML
    TextField txtUsuario;

    @FXML
    TextField txtPassword;

    @FXML
    Button btnIniciar;
    @FXML
    Button btnCerrar;

    @FXML
    private void checkLogin() {
        if (txtUsuario.getText().isBlank() || txtPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("BAR PANCHO: Alerta");
            alert.setContentText("El usuario o la contrseña se encuentran en blanco.\nPor favor introduzca los datos e inténtelo de nuevo.");
            alert.showAndWait();
        } else {
            try {
                DAOUsuario daoUsuario = new DAOUsuario();
                List<Usuario> usuarios = daoUsuario.readAll();
                boolean usuarioValido = false;
                for (Usuario u:usuarios) {
                    if (txtUsuario.getText().equalsIgnoreCase(u.getNombreUsuario())
                            && txtPassword.getText().equalsIgnoreCase(u.getPassword())) {

                        usuarioValido = true;

                        FXMLLoader ventanaLoader = new FXMLLoader(getClass()
                                .getResource("ventana_principal.fxml"));

                        Parent root = ventanaLoader.load();

                        ControladorPrincipal controller = ventanaLoader.getController();
                        controller.setLabelUsuario(u.getNombreUsuario());

                        Scene sceneVistaPrincipal = new Scene(root, 600, 400);
                        Stage stageVistaPrincipal = new Stage();

                        stageVistaPrincipal.initModality(Modality.APPLICATION_MODAL);
                        stageVistaPrincipal.initStyle(StageStyle.UNDECORATED);
                        stageVistaPrincipal.setScene(sceneVistaPrincipal);
                        stageVistaPrincipal.showAndWait();
                    }

                }
                if (!usuarioValido) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("BAR PANCHO: Alerta");
                    alert.setContentText("El usuario o la contraseña son incorrectos.\nPor favor introduzca los datos e inténtelo de nuevo.");
                    alert.showAndWait();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    private void close() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("BAR PANCHO: Salir");
        alert.setContentText("¿Deseas cerrar el programa?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK){
            Stage stage = (Stage) btnCerrar.getScene().getWindow();
            stage.close();
        } else{
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText(null);
            alert2.setTitle("BAR PANCHO: Mensaje");
            alert2.setContentText("Operación cancelada.");
            alert2.showAndWait();
        }
    }

}