package Controller;

import java.sql.SQLException;
import java.util.List;

import DAO.AtendenteDAO;
import DAOImpl.AtendenteDAOImpl;
import Entities.Atendente;
import Interface.Autenticavel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class TelaAtendenteController implements Autenticavel {

	public String massaDeDadosInvalida = "";

	public StringProperty nome = new SimpleStringProperty("");
		public StringProperty username = new SimpleStringProperty("");
		public StringProperty senha = new SimpleStringProperty("");
		public IntegerProperty codFunc = new SimpleIntegerProperty(0);

	private AtendenteDAO atendenteDAO;
	{
		atendenteDAO = new AtendenteDAOImpl();
	}

	private ObservableList<Atendente> atendentes = FXCollections.observableArrayList();

	public void adicionar() throws SQLException {
		if(validaMassaDeDadosParaCadastrarAtendente()){
			Atendente atendente = toEntity();
			atendenteDAO.adicionar(atendente);
			atendenteDAO.pesquisarTodos();
		}
	}
	public void salvar() throws SQLException {
		if (validaMassaDeDadosParaAlterarAtendente()) {
			Atendente atendente = toEntity();
			try {
				atendenteDAO.atualizar(codFunc.get(), atendente);
				atendenteDAO.pesquisarTodos();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void pesquisarTodos() {
		atendentes.clear();
		try {
			List<Atendente> lista = atendenteDAO.pesquisarTodos();
			atendentes.addAll(lista);
			if(!atendentes.isEmpty()) {
				fromEntity(atendentes.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void pesquisarPorNome() {
		atendentes.clear();
		try {
			List<Atendente> lista = atendenteDAO.pesquisarPorNome(nome.get());
			atendentes.addAll(lista);
			if(!atendentes.isEmpty()) {
				fromEntity(atendentes.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remover(int codFunc){
		atendenteDAO.excluir(codFunc);
	}
	
	public Atendente toEntity() {
		Atendente atendente = new Atendente();
		atendente.setCodFunc(codFunc.get());
		atendente.setNome(nome.get());
		atendente.setUsername(username.get());
		atendente.setSenha(senha.get());
		return atendente;
	}

	public void fromEntity(Atendente atendente) {
		codFunc.set(atendente.getCodFunc());
		nome.set(atendente.getNome());
		username.set(atendente.getUsername());
		senha.set(atendente.getSenha());
	}
	@Override
	public void autenticar(String username, String senha) {
		boolean autenticado = false;
		try {
			autenticado = atendenteDAO.encontrarAcesso(username, senha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!autenticado){
			System.out.println("Usuário ou senha inválidos!!!");;
		}
	}
	public ObservableList<Atendente> getLista(){
		return atendentes;
	}

	private boolean validaNome(){
		if (!nome.getValue().matches("^[^0-9!@#$%&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?`´\\^~§±\\\\]{3,100}$")){
			return false;
		}
		return true;
	}

	private boolean validaUsername(){
		if (!username.getValue().matches("^[a-zA-Z0-9.]{2,30}$")){
			return false;
		}
		return true;
	}

	private boolean validaSenha(){
		if (!senha.getValue().matches("^.{8,20}$")){
			return false;
		}
		return true;
	}

	private boolean validaMassaDeDadosParaAlterarAtendente() throws SQLException {
		massaDeDadosInvalida="";
		if(!validaNome()){
			massaDeDadosInvalida+=("nome inválido\n");
		}
		if(!validaUsername()){
			massaDeDadosInvalida+=("username inválido. Deve conter de 2 a 30 caracteres. Caracteres permitidos: a-z, A-Z, 0-9 e .\n");
		}
		if (!validaSenha()){
			massaDeDadosInvalida+=("senha deve conter de 8 a 20 caracteres\n");
		}
/*		if(atendenteDAO.pesquisarPorCodigoFuncionario(codFunc.getValue())==null){
			massaDeDadosInvalida+=("código inválido\n");
		}*/
		if(!massaDeDadosInvalida.isEmpty()){
			Controller.Alerts.showAlert("Dados inválidos",null, massaDeDadosInvalida, Alert.AlertType.ERROR);
			return false;
		}
		return true;
	}

	private boolean validaMassaDeDadosParaCadastrarAtendente() throws SQLException {
		massaDeDadosInvalida="";
		if(!validaNome()){
			massaDeDadosInvalida+=("nome inválido\n");
		}
		if(!validaUsername()){
			massaDeDadosInvalida+=("username inválido. Deve conter de 2 a 30 caracteres. Caracteres permitidos: a-z, A-Z, 0-9 e .\n");
		}
		if (!validaSenha()){
			massaDeDadosInvalida+=("senha deve conter de 8 a 20 caracteres\n");
		}
		if(!atendenteDAO.verificarCodigo(codFunc.getValue())){
			massaDeDadosInvalida+=("código inválido\n");
		}
		if(!massaDeDadosInvalida.isEmpty()){
			Controller.Alerts.showAlert("Dados inválidos",null, massaDeDadosInvalida, Alert.AlertType.ERROR);
			return false;
		}
		return true;
	}
}
