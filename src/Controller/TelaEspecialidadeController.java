package Controller;

import java.sql.SQLException;
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

public class TelaEspecialidadeController {
	public StringProperty nome = new SimpleStringProperty("");
	public StringProperty cbo = new SimpleStringProperty("");
	
private ObservableList<Especialidade> especialidades = FXCollections.observableArrayList();
	
	private EspecialidadeDAO especialidadeDAO;
	{
		especialidadeDAO = new EspecialidadeDAOImpl();
	}
	public void adicionar() {
		Especialidade especialidade = toEntity(); 
		especialidadeDAO.adicionar(especialidade);
		especialidadeDAO.pesquisarTodos();
	}
	public void atualizar() {
		Especialidade especialidade = toEntity();
		especialidadeDAO.atualizar(especialidade);
		especialidadeDAO.pesquisarTodos();
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
