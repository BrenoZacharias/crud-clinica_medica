package Boundary;

import Controller.TelaAtendenteController;
import Controller.TelaLoginController;
import DAO.AtendenteDAO;
import DAOImpl.AtendenteDAOImpl;
import Interface.Autenticavel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;


public class TelaLogin {
    TelaLoginController loginController = new TelaLoginController();
    public Scene logar() {

        AtendenteDAO aDao = new AtendenteDAOImpl();
        Label lblUserName = new Label("Username:");
        Label lblSenha = new Label("Senha:");

        TextField txtUsername = new TextField();
        PasswordField txtSenha = new PasswordField();

        Button btnLogin = new Button("Login");
        Button btnCadastro = new Button("Cadastrar-se");

        GridPane gPane = new GridPane();
        gPane.add(lblUserName,0, 0);
        gPane.add(txtUsername,1, 0);
        gPane.add(lblSenha,0, 1);
        gPane.add(txtSenha,1, 1);
        lblSenha.setAlignment(Pos.BASELINE_RIGHT);
        gPane.setVgap(10);
        gPane.setHgap(10);
        gPane.setAlignment(Pos.CENTER);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(btnLogin, btnCadastro);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.BASELINE_CENTER);

        VBox vbox = new VBox(gPane, hbox);
        vbox.setAlignment(Pos.CENTER);

        btnLogin.setOnAction((e) -> {
            try {
                loginController.VerificarAcesso(txtUsername.getText(),txtSenha.getText());
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        btnCadastro.setOnAction((e) -> {
            try {
                Principal.changedScreen("Cadastrar-se");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
       
        Scene scn = new Scene(vbox, 600, 400);
        
        return scn;
    }

	public Pane GerarCodigo() {
		Label lblCodigo = new Label("CODIGO");
		Button btnGerarCodigo = new Button("Gerar Codigo");
        Button btnVoltar = new Button("Voltar");
		//lblCodigo.setText(TelaLoginController.gerarCodigo());
        btnGerarCodigo.setOnAction((e)->{
            int codigo = loginController.gerarCodigo();
            lblCodigo.setText(""+codigo);
        });

        btnVoltar.setOnAction((e)->{
            try {
                Principal.changedScreen("Menu");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

		VBox vb = new VBox(lblCodigo, btnGerarCodigo, btnVoltar);

        vb.setSpacing(10);
		vb.setAlignment(Pos.CENTER);
		return vb;
	}
}
