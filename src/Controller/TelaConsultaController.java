package Controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import DAO.ConsultaDAO;
import DAOImpl.ConsultaDAOImpl;
import Entities.Consulta;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaConsultaController{
	
	public LongProperty id = new SimpleLongProperty(0);
	public StringProperty cpf = new SimpleStringProperty("");
	public StringProperty crm = new SimpleStringProperty("");
	public StringProperty descricao = new SimpleStringProperty("");
	public ObjectProperty dataConsult = new SimpleObjectProperty(LocalDate.now());
	
	
	private ObservableList<Consulta> consultas = FXCollections.observableArrayList();
	
	private ConsultaDAO consultaDAO;
	{
		consultaDAO = new ConsultaDAOImpl();
	}
	public void limpar() {
		id.set(0);
		cpf.set("");
		crm.set("");
		descricao.set("");
		dataConsult.set(LocalDate.now());
	}
	public void adicionar() {
		Consulta consulta = toEntity();
		consultaDAO.adicionar(consulta);
		limparCampos();
		consultaDAO.pesquisarTodos();
	}
	public void atualizar() {
		Consulta consulta = toEntity();
		consultaDAO.atualizar(consulta);
		consultaDAO.pesquisarTodos();
	}
	public void excluir(long id) {
		consultaDAO.excluir(id);
		consultaDAO.pesquisarTodos();
	}
	public void pesquisar() {
		consultas.clear();
		List<Consulta> lista = consultaDAO.pesquisarTodos();
		consultas.addAll(lista);
		if(!consultas.isEmpty()) {
			fromEntity(consultas.get(0));
		}
	}
	public Consulta toEntity() {
		Consulta consulta = new Consulta();
		
		consulta.setId((int) id.get());
		consulta.setCpf(cpf.get());
		consulta.setCrm(crm.get());
		consulta.setData((LocalDate) dataConsult.get());
		consulta.setDescricao(descricao.get());
		return consulta;
	}

	public void fromEntity(Consulta consulta) {
		id.set(consulta.getId());
		cpf.set(consulta.getCpf());
		crm.set(consulta.getCrm());
		dataConsult.set(consulta.getData());
		descricao.set(consulta.getDescricao());
	}
	public void limparCampos() {
		cpf.set("");
		crm.set("");
		dataConsult.set(LocalDate.now());
		descricao.set("");
	}

	public ObservableList<Consulta> getLista(){
		return consultas;
	}
	
}
