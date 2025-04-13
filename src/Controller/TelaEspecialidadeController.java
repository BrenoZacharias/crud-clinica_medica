package Controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import DAO.EspecialidadeDAO;
import DAOImpl.EspecialidadeDAOImpl;
import Entities.Especialidade;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class TelaEspecialidadeController {
	public StringProperty nome = new SimpleStringProperty("");
	public StringProperty cbo = new SimpleStringProperty("");
	
	private ObservableList<Especialidade> especialidades = FXCollections.observableArrayList();
	
	private EspecialidadeDAO especialidadeDAO;
	{
		especialidadeDAO = new EspecialidadeDAOImpl();
		pesquisar();
	}
	public void adicionar() {
		try{
			especialidades.clear();
			Especialidade especialidade = toEntity();
			especialidadeDAO.adicionar(especialidade);
			especialidades.addAll(especialidadeDAO.pesquisarTodos());
			Controller.Alerts.showAlert( "Especialidade Adicionada com sucesso", null, "Especialidade Adicionada com sucesso", Alert.AlertType.INFORMATION);
		} catch (SQLIntegrityConstraintViolationException e) {
			Controller.Alerts.showAlert("Erro ao adicionar especialidade",null,"código da especialidade já cadastrado no sistema!", Alert.AlertType.ERROR);
			pesquisar();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void atualizar() {
		especialidades.clear();
		Especialidade especialidade = toEntity();
		especialidadeDAO.atualizar(especialidade);
		especialidadeDAO.pesquisarTodos();
		especialidades.addAll(especialidadeDAO.pesquisarTodos());
	}
	public void excluir(String cbo) {
		especialidadeDAO.excluir(cbo);
		especialidadeDAO.pesquisarTodos();
	}
	public void pesquisar() {
		especialidades.clear();
		List<Especialidade> lista = especialidadeDAO.pesquisarTodos();
		especialidades.addAll(lista);
		if(!especialidades.isEmpty()) {
			fromEntity(especialidades.get(0));
		}
	}
	public void limpar() {
		nome.set("");
		cbo.set("");
	}
	public Especialidade toEntity() {
		Especialidade especialidade = new Especialidade();
		especialidade.setCbo(cbo.get());
		especialidade.setNome(nome.get());
		return especialidade;
	}
	public void fromEntity(Especialidade especialidade) {
		cbo.set(especialidade.getCbo());
		nome.set(especialidade.getNome());
	}
	public void limparCampos() {
		cbo.set("");
		nome.set("");
	}
	public ObservableList<Especialidade> getLista(){
		return especialidades;
	}
	
}
