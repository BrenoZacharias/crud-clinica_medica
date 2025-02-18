package Controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import DAO.EspecialidadeDAO;
import DAO.MedicoDAO;
import DAOImpl.EspecialidadeDAOImpl;
import DAOImpl.MedicoDAOImpl;
import Entities.Medico;
import EntityView.MedicoEntityView;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
public class TelaMedicoController {

	public String massaDeDadosInvalida = "";

	public StringProperty nome = new SimpleStringProperty("");
	public StringProperty telefone = new SimpleStringProperty("");
	public StringProperty crm = new SimpleStringProperty("");
	public StringProperty logradouro = new SimpleStringProperty("");
	public StringProperty num = new SimpleStringProperty("");
	public StringProperty cidade = new SimpleStringProperty("");
	public StringProperty complemento = new SimpleStringProperty("");
	public SimpleObjectProperty nascimento = new SimpleObjectProperty(LocalDate.now());
	public StringProperty especialidade = new SimpleStringProperty("");
	
	private ObservableList<MedicoEntityView> medicos = FXCollections.observableArrayList();
	
	private MedicoDAO medicoDAO;
	{
		medicoDAO = new MedicoDAOImpl();
	}

	private EspecialidadeDAO especialidadeDAO;
	{
		especialidadeDAO = new EspecialidadeDAOImpl();
	}

	public void limpar() {
		nome.set("");
		telefone.set("");
		crm.set("CRM-");
		logradouro.set("");
		num.set("");
		cidade.set("");
		complemento.set("");
		nascimento.set(LocalDate.now());
		especialidade.set("");
	}

	public void adicionar(String valorComboboxNomeEspecialidade) {
		try {
			if(validaMassaDeDadosMedico()) {
				Medico medico = toEntity(valorComboboxNomeEspecialidade);
				medicoDAO.adicionar(medico);
				medicos.add(medicoToMedicoEntityView(medico));
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			Controller.Alerts.showAlert("Erro ao adicionar médico",null,"CRM já cadastrado no sistema!", Alert.AlertType.ERROR);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public void atualizar(String valorComboboxNomeEspecialidade) {
		if(validaMassaDeDadosMedico()) {
			Medico medico = toEntity(valorComboboxNomeEspecialidade);
			medicoDAO.atualizar(crm.get(), medico);
			medicos.removeIf(e->e.getCrm()==medico.getCrm());
			medicos.add(medicoToMedicoEntityView(medico));
		}
	}
	public void listar() {
		medicos.clear();
		ArrayList<Medico> medicosEncontrados = (ArrayList<Medico>) medicoDAO.pesquisarTodos();
		ArrayList<MedicoEntityView> medicosEntityView = medicosToMedicosEntityView(medicosEncontrados);
		medicos.addAll(medicosEntityView);
		if(!medicos.isEmpty()) {
			fromEntity(medicoDAO.pesquisarUm(medicos.get(0).getCrm()));
		}
	}
	public void pesquisarPorCrm() {
			medicos.clear();
			Medico medico = medicoDAO.pesquisarUm(crm.get());
			if(medico.getCrm() != null) {
				MedicoEntityView medicoEntityView = medicoToMedicoEntityView(medico);
				medicos.addAll(medicoEntityView);
				if(!medicos.isEmpty()) {
					fromEntity(medico);
				}
			} else {
				limpar();
				Controller.Alerts.showAlert("Erro na pesquisa",null,"Não existe médico com o CRM informado!", Alert.AlertType.ERROR);
			}
	}

	public void excluir(String crm) {
		try {
			medicos.clear();
			medicoDAO.excluir(crm);
			medicos.removeIf(e -> e.getCrm() == crm);
			limpar();
		} catch (SQLIntegrityConstraintViolationException e) {
			if(e.getMessage().contains("consulta")){
				Alerts.showAlert("Erro na exclusão", null, "O médico possui consultas a fazer, elas precisam ser realizadas ou excluidas para que o médico possa ser excluído", Alert.AlertType.ERROR);
			};
			listar();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Medico toEntity(String valorComboboxNomeEspecialidade) {
		Medico m = new Medico();
		
		m.setNome(nome.get());
		m.setTelefone(telefone.get());
		m.setCrm(crm.get());
		m.setLogradouro(logradouro.get());
		m.setNum(num.get());
		m.setCidade(cidade.get());
		m.setComplemento(complemento.get());
		m.setNascimento((LocalDate) nascimento.get());
		m.setCboEspecialidade(especialidadeDAO.findEspecialidadeByNome(valorComboboxNomeEspecialidade).getCbo());
		return m;
	}

	private boolean validaMassaDeDadosMedico() {
		massaDeDadosInvalida="";
		if (!nome.getValue().matches("^[^0-9!@#$%&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?`´\\^~§±\\\\]{3,100}$")){
			massaDeDadosInvalida+=("nome inválido\n");
		}
		if (especialidade.getValue()==""){
			massaDeDadosInvalida+=("uma especialidade deve ser selecionada\n");
		}
		if (telefone.getValue().length()!=15){
			massaDeDadosInvalida+=("telefone deve conter 11 digitos\n");
		}
		if (!crm.getValue().matches("^[cC][rR][mM]-[a-zA-Z]{2} \\d{6}$")){
			massaDeDadosInvalida+=("crm inválido. exemplo: CRM-SP 123456. CRM-[sigla do estado] [6 números do crm ]\n");
		} else {
			String siglaEstados[] = {"ac", "al", "ap", "am", "ba", "ce", "df", "es", "go", "ma", "mt", "ms", "mg", "pa",
					"pb", "pr", "pe", "pi", "rr", "ro", "rj", "rn", "rs", "sc", "sp", "se", "to"};
			boolean ehSiglaEstado = false;
			for (String siglaEstado :
					siglaEstados) {
				if(crm.getValue().substring(4,6).equalsIgnoreCase(siglaEstado)){
					ehSiglaEstado = true;
				}
			}
			if(!ehSiglaEstado){
				massaDeDadosInvalida+=("crm inválido. exemplo: CRM-SP 123456. CRM-[sigla do estado] [6 números do crm]\n");
			}
		}
		if (!logradouro.getValue().matches("^(?=.*[a-zA-Z])[^^!@#$%&*()_+\\-=\\[\\]{};:\\\"\\|<>\\/?`´^~§±\\\\]{3,50}$")){
			massaDeDadosInvalida+=("logradouro inválido\n");
		}
		if (!num.getValue().matches("[0-9]")){
			massaDeDadosInvalida+=("número inválido\n");
		}
		if (!cidade.getValue().matches("^(?=.*[a-zA-Z])[^0-9^!@#$%&*()_+\\-=\\[\\]{};:\\\"\\|<>\\/?`´^~§±\\\\]{3,100}$")){
			massaDeDadosInvalida+=("cidade inválida\n");
		}
		if (!complemento.getValue().matches("^.{0,100}$")){
			massaDeDadosInvalida+=("complemento inválido\n");
		}
		if(!massaDeDadosInvalida.isEmpty()){
			Controller.Alerts.showAlert("Dados inválidos",null, massaDeDadosInvalida, Alert.AlertType.ERROR);
			return false;
		}

		return true;
	}

	public void fromEntity(Medico medico) {
		nome.set(medico.getNome());
		telefone.set(medico.getTelefone());
		crm.set(medico.getCrm());
		logradouro.set(medico.getLogradouro());
		num.set(medico.getNum());
		cidade.set(medico.getCidade());
		complemento.set(medico.getComplemento());
		nascimento.set(medico.getNascimento());
		especialidade.set(especialidadeDAO.findEspecialidadeByCbo(medico.getCboEspecialidade()).getNome());
	}
	
	public ObservableList<MedicoEntityView> getLista(){
		return medicos;
	}

	public ObservableList<String> getObservableListOfNomeEspecialidades() {
			List<String> nomeEspecialidades = new ArrayList<>();
			especialidadeDAO.pesquisarTodos().forEach(e -> nomeEspecialidades.add(e.getNome()));
		return FXCollections.observableList(nomeEspecialidades);
	}

	private MedicoEntityView medicoToMedicoEntityView (Medico medico) {
		MedicoEntityView medicoEntityView =  new MedicoEntityView();
		medicoEntityView.setNome(medico.getNome());
		medicoEntityView.setCrm(medico.getCrm());
		medicoEntityView.setNomeEspecialidade(especialidadeDAO.findEspecialidadeByCbo(medico.getCboEspecialidade()).getNome());
		medicoEntityView.setCidade(medico.getCidade());
		medicoEntityView.setComplemento(medico.getComplemento());
		medicoEntityView.setNascimento(medico.getNascimento());
		medicoEntityView.setTelefone(medico.getTelefone());
		medicoEntityView.setNum(medico.getNum());
		medicoEntityView.setLogradouro(medico.getLogradouro());
		return medicoEntityView;
	}

	private ArrayList<MedicoEntityView> medicosToMedicosEntityView (ArrayList<Medico> medicos) {
		ArrayList<MedicoEntityView> medicosEntityView =  new ArrayList<MedicoEntityView>();
		for (Medico medico :
			 medicos) {
			MedicoEntityView medicoEntityView =  medicoToMedicoEntityView(medico);
			medicosEntityView.add(medicoEntityView);
		}
		return medicosEntityView;
	}

	public Medico medicoEntityViewToMedico (MedicoEntityView medicoEntityView) {
		Medico medico =  new Medico();
		medico.setNome(medicoEntityView.getNome());
		medico.setCrm(medicoEntityView.getCrm());
		medico.setCboEspecialidade(especialidadeDAO.findEspecialidadeByNome(medicoEntityView.getNomeEspecialidade()).getCbo());
		medico.setCidade(medicoEntityView.getCidade());
		medico.setComplemento(medicoEntityView.getComplemento());
		medico.setNascimento(medicoEntityView.getNascimento());
		medico.setTelefone(medicoEntityView.getTelefone());
		medico.setNum(medicoEntityView.getNum());
		medico.setLogradouro(medicoEntityView.getLogradouro());
		return medico;
	}
}
