package com.example.barpancho;

import com.example.barpancho.modelo.daos.*;
import com.example.barpancho.modelo.objetos.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ControladorPrincipal {

    // General
    @FXML
    private Button cerrar;
    @FXML
    private Label labelUsuario;
    @FXML
    private TabPane tabPane;

    // Tab usuario
    @FXML
    private TextField usuarioNombre;
    @FXML
    private TextField usuarioApellido;
    @FXML
    private TextField usuarioUsuario;
    @FXML
    private TextField usuarioPassword;
    @FXML
    private TextField usuarioEmail;
    @FXML
    private TextField usuarioTelefono;

    // Tab productos
    @FXML
    private ListView<Producto> productosLista;
    @FXML
    private TextField productosNombre;
    @FXML
    private TextField productosPrecio;

    // Tab pedido
    @FXML
    private ListView<Producto> pedidoProductos;
    @FXML
    private ListView<DetallePedido> pedidoDetalle;
    @FXML
    private Button pedidoAdd;
    @FXML
    private Button pedidoFinalizar;
    @FXML
    private TextField pedidoNombre;

    @FXML
    private Button pedidoCancelar;

    @FXML
    private Label pedidoMesa;
    @FXML
    private Label pedidoComensales;
    @FXML
    private TextField pedidoUnidades;

    // Tab mesas
    @FXML
    private Button btnMesa1;
    @FXML
    private Button btnMesa2;
    @FXML
    private Button btnMesa3;
    @FXML
    private Button btnMesa4;
    @FXML
    private Button btnMesa5;
    @FXML
    private Button btnMesa6;
    @FXML
    private Button btnMesa7;
    @FXML
    private Button btnMesa8;
    @FXML
    private Button btnMesa9;
    @FXML
    private Button btnMesa10;
    @FXML
    private Button btnMesa11;
    @FXML
    private Button btnMesa12;
    @FXML
    private Button btnMesa13;
    @FXML
    private Button btnMesa14;
    @FXML
    private Button btnMesa15;

    List<Button> buttons;


    // General
    @FXML
    private void close() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("BAR PANCHO: Volver");
        alert.setContentText("¿Desea volver a la pantalla de inicio?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Stage stage = (Stage) cerrar.getScene().getWindow();
            stage.close();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText(null);
            alert2.setTitle("BAR PANCHO: Mensaje");
            alert2.setContentText("Operación cancelada.");
            alert2.showAndWait();
        }

    }

    @FXML
    public void setLabelUsuario(String user) {
        labelUsuario.setText(user);
    }


    // Tab usuario
    @FXML
    public void setDatosUsuario() {
        DAOUsuario daoUsuario = new DAOUsuario();
        DAOCamarero daoCamarero = new DAOCamarero();
        Usuario u = daoUsuario.read(labelUsuario.getText());
        Camarero c = daoCamarero.read(u.getIdCamarero());
        usuarioUsuario.setText(u.getNombreUsuario());
        usuarioPassword.setText(u.getPassword());
        usuarioNombre.setText(c.getNomCamarero());
        usuarioApellido.setText(c.getApellido1());
        usuarioEmail.setText(c.getEmail());
        usuarioTelefono.setText(c.getTelefono());
        usuarioTelefono.setText(c.getTelefono());
        usuarioTelefono.setText(c.getTelefono());

    }

    @FXML
    public void updateUser() {
        DAOCamarero daoCamarero = new DAOCamarero();
        DAOUsuario daoUsuario = new DAOUsuario();
        Usuario u = daoUsuario.read(usuarioUsuario.getText().trim());
        Camarero c = daoCamarero.read(u.getIdCamarero());
        u.setNombreUsuario(u.getNombreUsuario());
        u.setIdCamarero(u.getIdCamarero());
        u.setPassword(usuarioPassword.getText());
        c.setIdCamarero(u.getIdCamarero());
        c.setNomCamarero(usuarioNombre.getText());
        c.setApellido1(usuarioApellido.getText());
        c.setEmail(usuarioEmail.getText());
        c.setTelefono(usuarioTelefono.getText());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("BAR PANCHO: Guardar");
        alert.setContentText("¿Desea guardar los cambios realizados?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            daoUsuario.update(u);
            daoCamarero.update(c);
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText(null);
            alert2.setTitle("BAR PANCHO: Mensaje");
            alert2.setContentText("Se han guardado los cambios.");
            alert2.showAndWait();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText(null);
            alert2.setTitle("BAR PANCHO: Mensaje");
            alert2.setContentText("Operación cancelada.\nNo se han guardado los cambios.");
            alert2.showAndWait();
        }

    }

    // Tab productos
    @FXML
    public void setListaProductos() {
            productosNombre.setText("");
            productosPrecio.setText("");
            DAOProducto daoProducto = new DAOProducto();
            List<Producto> productosList = daoProducto.readAll();
            ObservableList<Producto> productos = FXCollections.observableArrayList(productosList);
            productosLista.setItems(productos);
            setDatosProductos();
    }

    @FXML
    public void setDatosProductos() {
        productosLista.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Producto selectedItem = productosLista.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                productosNombre.setText(selectedItem.getNombreProd());
                productosPrecio.setText(String.valueOf(selectedItem.getPrecio()));
            }
        });
    }

    @FXML
    public void insertProducto() {
        DAOProducto daoProducto = new DAOProducto();
        Producto p = new Producto(0, productosNombre.getText(), Double.parseDouble(productosPrecio.getText().trim()));
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("BAR PANCHO: Insertar");
        alert.setContentText("¿Desea añadir el producto?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            daoProducto.insert(p);
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText(null);
            alert2.setTitle("BAR PANCHO: Mensaje");
            alert2.setContentText("Se ha registrado el producto.");
            alert2.showAndWait();
            setListaProductos();
        } else {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText(null);
            alert2.setTitle("BAR PANCHO: Mensaje");
            alert2.setContentText("Operación cancelada.\nNo se ha registrado el producto");
            alert2.showAndWait();
        }


    }

    @FXML
    public void readProducto() {
        DAOProducto daoProducto = new DAOProducto();
        for (Producto p : daoProducto.readAll()) {
            if (p.getNombreProd().equalsIgnoreCase(productosNombre.getText())) {
                productosPrecio.setText(String.valueOf(p.getPrecio()));
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("BAR PANCHO: Alerta");
                alert.setContentText("El producto que busca no existe.\nSi lo desea lo puede añadir a la base de datos.");
                alert.showAndWait();
                break;
            }
        }


    }

    @FXML
    public void updateProducto() {
        DAOProducto daoProducto = new DAOProducto();
        Producto p = productosLista.getSelectionModel().getSelectedItem();
        if (p != null) {
            p.setIdProducto(p.getIdProducto());
            p.setNombreProd(productosNombre.getText());
            p.setPrecio(Double.parseDouble(productosPrecio.getText()));
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("BAR PANCHO: Actualizar");
        alert.setContentText("¿Desea modificar el producto?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if (p == null) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setHeaderText(null);
                alert2.setTitle("BAR PANCHO: Alerta");
                alert2.setContentText("No hay ningún producto seleccionado.\nSi lo desea lo puede añadir a la base de datos.");
                alert2.showAndWait();
            } else {
                daoProducto.update(p);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setHeaderText(null);
                alert2.setTitle("BAR PANCHO: Mensaje");
                alert2.setContentText("Se ha actualizado el producto.");
                alert2.showAndWait();
                setListaProductos();
            }

        }


    }

    @FXML
    public void deleteProducto() {
        DAOProducto daoProducto = new DAOProducto();
        String p = productosNombre.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("BAR PANCHO: Borrar");
        alert.setContentText("¿Desea eliminar el producto?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            daoProducto.delete(p);
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setHeaderText(null);
            alert2.setTitle("BAR PANCHO: Mensaje");
            alert2.setContentText("Se ha eliminado el producto.");
            alert2.showAndWait();
            setListaProductos();
        }
    }

    // Tab pedido
    @FXML
    public void setPedidoProductos() {
        DAOProducto daoProducto = new DAOProducto();
        List<Producto> productosList = daoProducto.readAll();
        ObservableList<Producto> productos = FXCollections.observableArrayList(productosList);
        pedidoProductos.setItems(productos);
        setLabelAdd();
    }

    @FXML
    public void setLabelAdd() {
        pedidoProductos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Producto selectedItem = pedidoProductos.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                pedidoNombre.setText(selectedItem.getNombreProd());
            }
        });
    }

    @FXML
    public Pedido returnPedido() {
        DAOPedido daoPedido = new DAOPedido();
        return daoPedido.returnPedido();
    }

    @FXML
    public void addToPedido() {
            Date date = new Date();
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(date);
            DAOUsuario daoUsuario = new DAOUsuario();
            int idCamarero = daoUsuario.read(labelUsuario.getText()).getIdCamarero();
            Pedido pedido = new Pedido(Integer.parseInt(pedidoMesa.getText().trim()), labelUsuario.getText(), idCamarero, fecha);
            DAOPedido daoPedido = new DAOPedido();
            daoPedido.insert(pedido);
    }

    @FXML
    public void addToDetalles() {
            DAODetallePedido daoDetalle = new DAODetallePedido();
            DAOProducto daoProducto = new DAOProducto();
            pedidoCancelar.setDisable(true);
            addToPedido();
            Pedido p = returnPedido();
            Producto pro = daoProducto.read(pedidoNombre.getText());
            DetallePedido dp = new DetallePedido(pro.getIdProducto(), p.getIdPedido(), Integer.parseInt(pedidoUnidades.getText().trim()));
            System.out.println(dp);

            daoDetalle.insert(dp);
            List<DetallePedido> detalles = daoDetalle.readMesaPedido(p.getIdMesa());
            ObservableList<DetallePedido> detallePedido = FXCollections.observableArrayList(detalles);
            pedidoDetalle.setItems(detallePedido);
    }


    @FXML
    public void cancelarPedido() {
            DAOMesa daoMesa = new DAOMesa();
            Mesa m = daoMesa.read(Integer.valueOf(pedidoMesa.getText()));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("BAR PANCHO: Cancelar");
            alert.setContentText("¿Desea cancelar el pedido?");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {
                m.setEstado("V");
                daoMesa.update(m);

            }
            tabPane.getSelectionModel().select(0);

    }

    // Tab mesas
    @FXML
    public void colorMesas() {
            DAOMesa daoMesa = new DAOMesa();
            List<Mesa> mesas = daoMesa.readAll();
            buttons = new ArrayList<>(List.of(btnMesa1, btnMesa2, btnMesa3, btnMesa4, btnMesa5, btnMesa6,
                    btnMesa7, btnMesa8, btnMesa9, btnMesa10, btnMesa11, btnMesa12, btnMesa13, btnMesa14, btnMesa15));
            for (Mesa mesa : mesas) {
                int mesaId = mesa.getIdMesa();
                Button button = null;
                switch (mesaId) {
                    case 1:
                        button = btnMesa1;
                        break;
                    case 2:
                        button = btnMesa2;
                        break;
                    case 3:
                        button = btnMesa3;
                        break;
                    case 4:
                        button = btnMesa4;
                        break;
                    case 5:
                        button = btnMesa5;
                        break;
                    case 6:
                        button = btnMesa6;
                        break;
                    case 7:
                        button = btnMesa7;
                        break;
                    case 8:
                        button = btnMesa8;
                        break;
                    case 9:
                        button = btnMesa9;
                        break;
                    case 10:
                        button = btnMesa10;
                        break;
                    case 11:
                        button = btnMesa11;
                        break;
                    case 12:
                        button = btnMesa12;
                        break;
                    case 13:
                        button = btnMesa13;
                        break;
                    case 14:
                        button = btnMesa14;
                        break;
                    case 15:
                        button = btnMesa15;
                        break;
                }
                if (button != null) {
                    if (mesa.getEstado().equalsIgnoreCase("O")) {
                        button.setStyle("-fx-background: null; -fx-background-color: #FF5349;");
                    } else {
                        button.setStyle("-fx-background: null; -fx-background-color:  #9ACD32;");
                    }
                }
            }
    }

    @FXML
    public void selectMesa(ActionEvent e) {
            DAOMesa daoMesa = new DAOMesa();
            Button button = (Button) e.getSource();
            Mesa m = daoMesa.read(Integer.parseInt(button.getText()));
            m.setEstado("O");
            daoMesa.update(m);
            pedidoMesa.setText(String.valueOf(m.getIdMesa()));
            pedidoComensales.setText("Comensales: " + m.getnComensasles());
            tabPane.getSelectionModel().select(1);
    }
}
