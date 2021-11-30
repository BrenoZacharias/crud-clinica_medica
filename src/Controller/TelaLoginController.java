package Controller;

import Boundary.Principal;
import DAO.AtendenteDAO;
import DAOImpl.AtendenteDAOImpl;
import Interface.Autenticavel;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.util.Random;

public class TelaLoginController  {
	AtendenteDAOImpl atendenteDAO = new AtendenteDAOImpl();
	public void VerificarAcesso(String username, String senha) throws SQLException, ClassNotFoundException {
		if(atendenteDAO.encontrarAcesso(username, senha)){
			Principal.changedScreen("Menu");
		}
		else {
			Controller.Alerts.showAlert("Login Error",null,"Usuário ou senha incorreto", Alert.AlertType.ERROR);
			Principal.changedScreen("Login");
		}
	}

	public int gerarCodigo(){
		int codigo = randomizar();
		while(atendenteDAO.verificarCodigo(codigo)){
			codigo = randomizar();
		}
		atendenteDAO.adicionarCodigo(codigo);
		return codigo;
	}
	public int randomizar() {

		Random r = new Random();
		int num = r.nextInt(1000000);
		while (num < 100000) {
			num  = r.nextInt(1000000);
		}
		return num;
	}
}
