package Controller;

import java.time.LocalDate;
import java.util.List;

import DAO.PacienteDAO;
import DAOImpl.PacienteDAOImpl;
import Entities.Paciente;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaPacienteController{
	
	public StringProperty nome = new SimpleStringProperty("");
	public StringProperty cpf = new SimpleStringProperty("");
	public StringProperty sexo = new SimpleStringProperty("");
	public ObjectProperty nascimento = new SimpleObjectProperty(LocalDate.now());
	public StringProperty telefone = new SimpleStringProperty("");
	public StringProperty logradouro = new SimpleStringProperty("");
	public StringProperty num = new SimpleStringProperty("");
	public StringProperty cidade = new SimpleStringProperty("");
	public StringProperty complemento = new SimpleStringProperty("");
	
	
	private ObservableList<Paciente> pacientes = FXCollections.observableArrayList();
	
	private PacienteDAO pacienteDAO;
	{
		pacienteDAO = new PacienteDAOImpl();
	}
	public void adicionar() {
		pacientes.clear();
		Paciente paciente = toEntity();
		pacienteDAO.adicionar(paciente);
		pacientes.addAll(pacienteDAO.pesquisarTodos());
	}
	public void atualizar() {
		pacientes.clear();
		Paciente paciente = toEntity();
		pacienteDAO.atualizar(cpf.get(), paciente);
		pacientes.addAll(pacienteDAO.pesquisarTodos());
	}
	public void pesquisar() {
		pacientes.clear();
		List<Paciente> encontrados = pacienteDAO.pesquisarTodos();
		pacientes.addAll(encontrados);
		if(!pacientes.isEmpty()) {
			fromEntity(pacientes.get(0));
		}
	}
	public void excluir(String cpf) {
		pacientes.clear();
		pacienteDAO.excluir(cpf);
		pacientes.addAll(pacienteDAO.pesquisarTodos());
	}
	public void limpar() {
		nome.set("");
		cpf.set("");
		sexo.set("");
		nascimento.set(LocalDate.now());
		telefone.set("");
		logradouro.set("");
		num.set("");
		cidade.set("");
		complemento.set("");
	}
	public Paciente toEntity() {
		Paciente paciente = new Paciente();
		
		paciente.setNome(nome.get());
		paciente.setCpf(cpf.get());
		paciente.setSexo(sexo.get());
		paciente.setNascm((LocalDate) nascimento.get());
		paciente.setTelefone(telefone.get());
		paciente.setLogradouro(logradouro.get());
		paciente.setNum(num.get());
		paciente.setCidade(cidade.get());
		paciente.setComplemento(complemento.get());
		
		return paciente;
	}
	public void fromEntity(Paciente paciente) {
		
		nome.set(paciente.getNome());
		cpf.set(paciente.getCpf());
		sexo.set(paciente.getSexo());
		nascimento.set(paciente.getNascm());
		telefone.set(paciente.getTelefone());
		logradouro.set(paciente.getLogradouro());
		num.set(paciente.getNum());
		cidade.set(paciente.getCidade());
		complemento.set(paciente.getComplemento());
	}
	public ObservableList<Paciente> getLista(){
		return pacientes;
	}
}

