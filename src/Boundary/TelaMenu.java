package Boundary;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.sql.SQLException;

public class TelaMenu {
	public Scene Menu(){
		BorderPane bp = new BorderPane();
		
		Label bv = new Label("Bem Vindo");
	
		bv.setMinSize(350,300);
		bv.setAlignment(Pos.CENTER);
		
		bp.setCenter(bv);
		
		TelaAtendente tAtendente = new TelaAtendente();
		TelaConsulta tConsulta = new TelaConsulta();
		TelaEspecialidade tEspecialidade = new TelaEspecialidade();
		TelaMedico tMedico = new TelaMedico();
		TelaPaciente tPaciente = new TelaPaciente();
		TelaLogin tLogin = new TelaLogin();
		
		MenuBar menuBar = new MenuBar();

		Menu mnuMenu = new Menu("Menu");
		Menu mnuSair = new Menu("Sair");
		Menu mnuCodigo = new Menu("Codigo");
		
		MenuItem mnuAtendente = new MenuItem("Atendente");
		mnuAtendente.setOnAction((e) -> {
			bp.setCenter( tAtendente.TelaAtendente());
		});
		
		MenuItem mnuMedico = new MenuItem("Medico");
		mnuMedico.setOnAction((e) -> {
			bp.setCenter( tMedico.TelaMedico());
		});
		
		MenuItem mnuPaciente = new MenuItem("Paciente");
		mnuPaciente.setOnAction((e) -> {
			bp.setCenter( tPaciente.TelaPaciente());
		});
		
		MenuItem mnuEspecialidade = new MenuItem("Especialidade");
		mnuEspecialidade.setOnAction((e) -> {
			bp.setCenter( tEspecialidade.TelaEspecialidade());
		});
		
		MenuItem mnuConsulta = new MenuItem("Consulta");
		mnuConsulta.setOnAction((e) -> {
			bp.setCenter(tConsulta.TelaConsulta());
		});
		
		MenuItem mnuGerarCodigo = new MenuItem("Gerar codigo");
		mnuGerarCodigo.setOnAction((e) -> {
			bp.setCenter(tLogin.GerarCodigo());
		});
		
		MenuItem mnuLogOut = new MenuItem("Deslogar");
		mnuLogOut.setOnAction((e) -> {
			try {
				Principal.changedScreen("Login");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		});
		
		MenuItem mnuFechar = new MenuItem("Fechar");
		mnuFechar.setOnAction((e) -> {
			Platform.exit();
			System.exit(0);
		});
	
		menuBar.getMenus().addAll(mnuMenu, mnuCodigo, mnuSair);

		mnuMenu.getItems().addAll(mnuAtendente, mnuMedico, mnuPaciente, mnuEspecialidade, mnuConsulta);
		mnuCodigo.getItems().add(mnuGerarCodigo);
		mnuSair.getItems().addAll(mnuLogOut, mnuFechar);
		
		
		bp.setTop(menuBar);
	
		Scene scn = new Scene(bp, 830, 650);
		return scn;
	}
}
