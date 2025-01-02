package Boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Controller.TelaPacienteController;
import Entities.Medico;
import Entities.Paciente;
import javafx.beans.binding.Bindings;
//import Entities.Medico;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateStringConverter;

public class TelaPaciente {
	
	private TelaPacienteController control = new TelaPacienteController();
	
	public Pane TelaPaciente() {
		
		HBox hb = new HBox();
		
		Button btnAdicionar = new Button("Adicionar");
		Button btnEditar = new Button("Editar");
		Button btnPesquisar = new Button("Pesquisar");
		Button btnLimpar = new Button("Limpar");

		hb.getChildren().addAll(btnAdicionar, btnEditar, btnPesquisar, btnLimpar);
		hb.setAlignment(Pos.BASELINE_CENTER);
		hb.setSpacing(10);
		hb.setPadding(new Insets(10,10,10,10));
		
		Label lblNome = new Label("Nome:");
		Label lblCpf = new Label("CPF:");
		Label lblLogradouro = new Label("Logradouro:");
		Label lblNum = new Label("Num:");
		Label lblCidade = new Label("Cidade:");
		Label lblComp = new Label("Complemento:");
		Label lblTel = new Label (" Telefone: ");
		Label lblSexo = new Label (" Sexo: ");
		Label lblNasc = new Label (" Data de nascimento: ");
		TextField txtNome = new TextField();
		TextField txtCpf = new TextField();
		TextField txtLogradouro = new TextField();
		TextField txtNum = new TextField();
		TextField txtCidade = new TextField();
		TextField txtComp = new TextField();
		TextField txtTel = new TextField();
		TextField txtSexo = new TextField();
		TextField txtNasc = new TextField();
		
		Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
		Bindings.bindBidirectional(txtCpf.textProperty(), control.cpf);
		Bindings.bindBidirectional(txtSexo.textProperty(), control.sexo);
		Bindings.bindBidirectional(txtTel.textProperty(), control.telefone);
		Bindings.bindBidirectional(txtNasc.textProperty(), control.nascimento, new LocalDateStringConverter());
		Bindings.bindBidirectional(txtLogradouro.textProperty(), control.logradouro);
		Bindings.bindBidirectional(txtNum.textProperty(), control.num);
		Bindings.bindBidirectional(txtCidade.textProperty(), control.cidade);
		Bindings.bindBidirectional(txtComp.textProperty(), control.complemento);
		
		GridPane painel = new GridPane();
		
		painel.setAlignment(Pos.TOP_LEFT);
		
		painel.add(lblNome, 0, 0);
		painel.add(txtNome, 1, 0);
		painel.add(lblCpf, 0, 1);
		painel.add(txtCpf, 1, 1);
		painel.add(lblTel, 0, 2);
		painel.add(txtTel, 1, 2);
		painel.add(lblSexo, 2, 0);
		painel.add(txtSexo, 3, 0);
		painel.add(lblNasc, 2, 1);
		painel.add(txtNasc, 3, 1);
		painel.add(lblLogradouro, 2, 2);
		painel.add(txtLogradouro, 3, 2);
		painel.add(lblNum, 4, 0);
		painel.add(txtNum, 5, 0);
		painel.add(lblCidade, 4, 1);
		painel.add(txtCidade, 5, 1);
		painel.add(lblComp, 4, 2);
		painel.add(txtComp, 5, 2);
	
		painel.setVgap(10);
		painel.setHgap(10);
		
		painel.setPadding(new Insets(10,10,10,10));

		TableView <Paciente> table = new TableView<>();

		//para deixar o retorno do do atributo nascimento do tipo DATE do banco no formato "dd//MM/yyyy"
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		TableColumn<Paciente, String> col1 = new TableColumn<>("Nome");
		col1.setCellValueFactory(
				new PropertyValueFactory<Paciente, String>("nome")
				);
		TableColumn<Paciente, String> col2 = new TableColumn<>("CPF");
		col2.setCellValueFactory(
				new PropertyValueFactory<Paciente, String>("Cpf")
				);
		TableColumn<Paciente, String> col3 = new TableColumn<>("Telefone");
		col3.setCellValueFactory(
				new PropertyValueFactory<Paciente, String>("telefone")
				);
		TableColumn<Paciente, String> col4 = new TableColumn<>("Sexo");
		col4.setCellValueFactory(
				new PropertyValueFactory<Paciente, String>("sexo")
				);
		TableColumn<Paciente , String> col5 = new TableColumn<>("Nascimento");
		col5.setCellValueFactory( (item) ->{
						LocalDate d = item.getValue().getNascm();
						return new ReadOnlyStringWrapper(d.format(fmt));
			}
		);
		TableColumn<Paciente, String> col6 = new TableColumn<>("Logradouro");
		col6.setCellValueFactory(
			new PropertyValueFactory<Paciente, String>("logradouro")
		);
		TableColumn<Paciente, String> col7 = new TableColumn<>("Num");
		col7.setCellValueFactory(
			new PropertyValueFactory<Paciente, String>("num")
		);
		TableColumn<Paciente, String> col8 = new TableColumn<>("Cidade");
		col8.setCellValueFactory(
			new PropertyValueFactory<Paciente, String>("cidade")
		);
		TableColumn<Paciente, String> col9= new TableColumn<>("Complemento");
		col9.setCellValueFactory(
			new PropertyValueFactory<Paciente, String>("complemento")
		);
		TableColumn<Paciente, String> col10 = new TableColumn<>("Ações");
	        col10.setCellFactory( (tbcol) -> {
	            Button btnRemover = new Button("Remover");
	            TableCell<Paciente, String> tcell = new TableCell<Paciente, String>() {
	                @Override
	                protected void updateItem(String item, boolean empty) {
	                    if (empty) {
	                        setGraphic(null);
	                        setText(null);
	                    } else {
	                        btnRemover.setOnAction( (e) -> {
	                            Paciente paciente = getTableView().getItems().get(getIndex());
	                            control.excluir(paciente.getCpf());
	                            control.pesquisar();
	                        });
	                        setGraphic(btnRemover);
	                        setText(null);
	                    }
	                }
	            };
	            return tcell;
	            }
	        );
		table.getColumns().addAll(col1,col2,col3,col4,col5, col6, col7, col8, col9, col10);
		table.setItems(control.getLista());
		table.getSelectionModel().selectedItemProperty().addListener( (obs, old, novo) -> {
			if(novo != null) {
				control.fromEntity((Paciente) novo);
			}
		});
		control.pesquisar();
		btnAdicionar.setOnAction( (e) -> {
			control.adicionar();
		});
		btnEditar.setOnAction( (e) -> {
			control.atualizar();
		});
		btnPesquisar.setOnAction( (e) -> {
			control.pesquisar();
		});
		btnLimpar.setOnAction( (e) -> {
			control.limpar();
		});

		VBox vb = new VBox(painel, hb, table);
		 
		return vb;
	}

	public void findObservableListEspecialidade(){

	}
}
