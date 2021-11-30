package Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DAO.EspecialidadeDAO;
import DAO.MedicoDAO;
import DAOImpl.EspecialidadeDAOImpl;
import DAOImpl.MedicoDAOImpl;
import Entities.Especialidade;
import Entities.Medico;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaMedicoController {
	
	public StringProperty nome = new SimpleStringProperty("");
	public StringProperty telefone = new SimpleStringProperty("");
	public StringProperty crm = new SimpleStringProperty("");
	public StringProperty rua = new SimpleStringProperty("");
	public StringProperty num = new SimpleStringProperty("");
	public StringProperty cidade = new SimpleStringProperty("");
	public StringProperty complemento = new SimpleStringProperty("");
	public SimpleObjectProperty nascimento = new SimpleObjectProperty(LocalDate.now());
	
	private ObservableList<Medico> medicos = FXCollections.observableArrayList();
	
	private MedicoDAO medicoDAO;
	{
		medicoDAO = new MedicoDAOImpl();
	}

	private EspecialidadeDAO especialidadeDAO;
	{
		especialidadeDAO = new EspecialidadeDAOImpl();
	}

	public void adicionar(String valorComboboxNomeEspecialidade) {
		Medico medico = toEntity(valorComboboxNomeEspecialidade);
		medicoDAO.adicionar(medico);
		//medicos.addAll(medicoDAO.pesquisarTodos());
		System.out.println("valor cbo: " + medico.getCboEspecialidade());
		medico = medicoDAO.pesquisarUm(medico.getCrm());
		medico.setCboEspecialidade("1");
		medicos.add(medico);
	}
	
	public void atualizar(String valorComboboxNomeEspecialidade) {
		Medico  medico = toEntity(valorComboboxNomeEspecialidade);
		if(medico.getCrm() == "") {
			medicoDAO.adicionar(medico);
			medicoDAO.pesquisarTodos();
		}else {
			medicoDAO.atualizar(crm.get(), medico);
			medicoDAO.pesquisarTodos();
		}
	}
	public void pesquisar() {
		medicos.clear();
		List<Medico> encontrados = medicoDAO.pesquisarTodos();
		medicos.addAll(encontrados);
		if(!medicos.isEmpty()) {
			fromEntity(medicos.get(0));
		}

	}
	public void salvar(String valorComboboxNomeEspecialidade) {
		medicos.clear();
		Medico medico = toEntity(valorComboboxNomeEspecialidade);
		if(medico.getCrm() == "") {
			medicoDAO.adicionar(medico);
		}else {
			medicoDAO.atualizar(crm.get(), medico);
		}
		medicoDAO.pesquisarTodos();
	}

	public void excluir(String crm) {
		medicos.clear();
		medicoDAO.excluir(crm);
		medicos.addAll(medicoDAO.pesquisarTodos());
	}
	
	public Medico toEntity(String valorComboboxNomeEspecialidade) {
		Medico m = new Medico();
		
		m.setNome(nome.get());
		m.setTelefone(telefone.get());
		m.setCrm(crm.get());
		m.setRua(rua.get());
		m.setNum(num.get());
		m.setCidade(cidade.get());
		m.setComplemento(complemento.get());
		m.setNascimento((LocalDate) nascimento.get());
		m.setCboEspecialidade(especialidadeDAO.findEspecialidadeByNome(valorComboboxNomeEspecialidade).getCbo());

		return m;
	}
	
	public void fromEntity(Medico medico) {
		nome.set(medico.getNome());
		telefone.set(medico.getTelefone());
		crm.set(medico.getCrm());
		rua.set(medico.getRua());
		num.set(medico.getNum());
		cidade.set(medico.getCidade());
		complemento.set(medico.getComplemento());
		nascimento.set(medico.getNascimento());
		//nome_Especialidade.set(especialidadeDAO.findEspecialidadeByCbo(medico.getCboEspecialidade()).getNome());
	}
	
	public ObservableList<Medico> getLista(){
		return medicos;
	}

	public ObservableList<String> getObservableListOfNomeEspecialidades() {
			List<String> nomeEspecialidades = new ArrayList<>();
			especialidadeDAO.pesquisarTodos().forEach(e -> nomeEspecialidades.add(e.getNome()));
			return FXCollections.observableList(nomeEspecialidades);
	}
}
