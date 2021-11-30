package Boundary;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Principal extends Application {

	private static Stage stage;

	@Override
	public void start(Stage stg) throws Exception {
		stage = stg;
		
		TelaLogin tLogin = new TelaLogin();
		
		stg.setTitle("Clinica Medica");
		stg.setScene(tLogin.logar());
		stg.show();

	}

	public static void main(String[] args) {
		Application.launch(Principal.class, args);

	}
	
	public static void changedScreen(String nome) throws SQLException, ClassNotFoundException {
		TelaMenu tMenu = new TelaMenu();
		TelaLogin tLogin = new TelaLogin();
		TelaAtendente tAtendente = new TelaAtendente();
		
		switch(nome){
			case "Menu":
				stage.setTitle("Clinica Medica");
				stage.setScene(tMenu.Menu());
				break;
			case "Login":
				stage.setTitle("Clinica Medica: Login");
				stage.setScene(tLogin.logar());
				break;
			case "Cadastrar-se":
				stage.setTitle("Clinica Medica: Cadastro do Atendente");
				stage.setScene(tAtendente.CadastroAtendente());
		}
	}
}
