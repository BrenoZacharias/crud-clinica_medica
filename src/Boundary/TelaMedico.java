package Boundary;

import java.time.LocalDate;
import java.util.function.UnaryOperator;
import Controller.TelaMedicoController;
import EntityView.MedicoEntityView;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateStringConverter;

public class TelaMedico {
	
	private TelaMedicoController control = new TelaMedicoController();
	
	public Pane TelaMedico() {
		
		//--------------------------------------------Botoes
		HBox hb = new HBox();
		
		Button btnAdicionar = new Button("Adicionar");
		Button btnEditar = new Button("Editar");
		Button btnListar = new Button(" Listar");
		Button btnPesquisarPorCrm = new Button(" Pesquisar Por CRM");
		Button btnLimpar = new Button(" Limpar ");
		
		hb.getChildren().addAll(btnAdicionar, btnEditar, btnPesquisarPorCrm, btnListar, btnLimpar);
		hb.setAlignment(Pos.BASELINE_CENTER);
		hb.setSpacing(10);
		hb.setPadding(new Insets(10,10,10,10));
		
		//------------------------------------------- �rea de Edi��o Label/TextField
		GridPane painel = new GridPane();
		
		Label lblNome = new Label("Nome:");
		Label lblEspec = new Label("Especialização:");
		Label lblTelefone = new Label ("Telefone:");
		Label lblCrm = new Label("CRM:");
		Label lblLogradouro = new Label("Logradouro:");
		Label lblNum = new Label("Num:");
		Label lblCidade = new Label("Cidade:");
		Label lblComp = new Label("Complemento:");
		Label lblNasc = new Label ("Data de nascimento:");
		TextField txtNome = new TextField();
		TextField txtTel = new TextField();
		TextField txtCrm = new TextField();
		TextField txtLogradouro = new TextField();
		TextField txtNum = new TextField();
		TextField txtCidade = new TextField();
		TextField txtComp = new TextField();
		TextField txtNasc = new TextField();


		// Define um UnaryOperator para formatar a entrada
		UnaryOperator<TextFormatter.Change> phoneFormatter = change -> {
			String oldText = change.getControlText();
			String newText = change.getControlNewText();

			// Remove tudo o que não for número
			newText = newText.replaceAll("[^\\d]", "");

			// Aplica a máscara no formato (XX) XXXXX-XXXX
			String formattedText = newText;

			if (formattedText.length() > 11) {
				formattedText = formattedText.substring(0, 11);
			}































































































































































































































































			if (formattedText.length() > 6) {
				formattedText = "(" + formattedText.substring(0, 2) + ") " + formattedText.substring(2, 7) + "-" + formattedText.substring(7);
			} else if (formattedText.length() > 2) {
				formattedText = "(" + formattedText.substring(0, 2) + ") " + formattedText.substring(2);
			} else if (formattedText.length() == 5) {
				formattedText = "(" + formattedText.substring(0, 2) + ") " + formattedText.substring(2, 6);
			}

			// Calcula a diferença de caracteres e ajusta o cursor
			int diff = formattedText.length() - newText.length();
			int caretPosition = change.getCaretPosition() + diff;
			caretPosition = Math.max(0, Math.min(formattedText.length(), caretPosition));

			// Atualiza o texto e a posição do cursor
			change.setText(formattedText);
			change.setRange(0, oldText.length());  // Define o intervalo de alteração do texto
			change.setCaretPosition(caretPosition);  // Define a posição do cursor após a alteração
			return change;
		};
		// Aplica o TextFormatter com a máscara
		TextFormatter<String> textFormatter = new TextFormatter<>(phoneFormatter);
		txtTel.setTextFormatter(textFormatter);

		ComboBox<String> cbxNomeEspecialidade;
		cbxNomeEspecialidade = new ComboBox<>();
		cbxNomeEspecialidade.setItems(control.getObservableListOfNomeEspecialidades());
		cbxNomeEspecialidade.setPromptText("Especialização");
		
		painel.setAlignment(Pos.TOP_LEFT);
		
		painel.add(lblNome, 0, 0);
		painel.add(txtNome, 1, 0);
		painel.add(lblEspec, 0, 1);
		painel.add(cbxNomeEspecialidade, 1, 1);
		painel.add(lblTelefone, 0, 2);
		painel.add(txtTel, 1, 2);
		
		painel.add(lblCrm, 2, 0);
		painel.add(txtCrm, 3, 0);
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
		
		Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
		Bindings.bindBidirectional(txtCrm.textProperty(), control.crm);
		Bindings.bindBidirectional(txtTel.textProperty(), control.telefone);
		Bindings.bindBidirectional(txtNasc.textProperty(), control.nascimento, new LocalDateStringConverter());
		Bindings.bindBidirectional(txtLogradouro.textProperty(), control.logradouro);
		Bindings.bindBidirectional(txtNum.textProperty(), control.num);
		Bindings.bindBidirectional(txtCidade.textProperty(), control.cidade);
		Bindings.bindBidirectional(txtComp.textProperty(), control.complemento);
		Bindings.bindBidirectional(cbxNomeEspecialidade.valueProperty(), control.especialidade);
		painel.setVgap(10);
		painel.setHgap(10);
		
		painel.setPadding(new Insets(10,10,10,10));
		
		//-------------------------------------------------------- Table
		
		TableView <MedicoEntityView> table = new TableView<>();

		TableColumn<MedicoEntityView, String> col1 = new TableColumn<>("Nome");
		col1.setCellValueFactory(
			new PropertyValueFactory<MedicoEntityView, String>("nome")
		);
		TableColumn<MedicoEntityView, String> col2 = new TableColumn<>("especialidade");
		col2.setCellValueFactory(
			new PropertyValueFactory<MedicoEntityView, String>("NomeEspecialidade")
		);
		TableColumn<MedicoEntityView, String> col3 = new TableColumn<>("CRM");
		col3.setCellValueFactory(
			new PropertyValueFactory<MedicoEntityView, String>("crm")
		);
		TableColumn<MedicoEntityView, String> col4 = new TableColumn<>("Telefone");
		col4.setCellValueFactory(
			new PropertyValueFactory<MedicoEntityView, String>("telefone")
		);	
		
		TableColumn<MedicoEntityView, LocalDate> col5= new TableColumn<>("Nascimento");
		col5.setCellValueFactory(
			new PropertyValueFactory<MedicoEntityView, LocalDate>("nascimento")
		);	
		TableColumn<MedicoEntityView, String> col6 = new TableColumn<>("Logradouro");
		col6.setCellValueFactory(
			new PropertyValueFactory<MedicoEntityView, String>("logradouro")
		);
		TableColumn<MedicoEntityView, String> col7 = new TableColumn<>("Num");
		col7.setCellValueFactory(
			new PropertyValueFactory<MedicoEntityView, String>("num")
		);
		TableColumn<MedicoEntityView, String> col8 = new TableColumn<>("Cidade");
		col8.setCellValueFactory(
			new PropertyValueFactory<MedicoEntityView, String>("cidade")
		);
		TableColumn<MedicoEntityView, String> col9= new TableColumn<>("Complemento");
		col9.setCellValueFactory(
			new PropertyValueFactory<MedicoEntityView, String>("complemento")
		);
		
		 TableColumn<MedicoEntityView, String> col10 = new TableColumn<>("Ações");
	        col10.setCellFactory( (tbcol) -> {
	            Button btnRemover = new Button("Remover");
	            TableCell<MedicoEntityView, String> tcell = new TableCell<MedicoEntityView, String>() {
	                @Override
	                protected void updateItem(String item, boolean empty) {
	                    if (empty) {
	                        setGraphic(null);
	                        setText(null);
	                    } else {
	                        btnRemover.setOnAction( (e) -> {
	                            MedicoEntityView medico = getTableView().getItems().get(getIndex());
	                            control.excluir(medico.getCrm());
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
				control.fromEntity(control.medicoEntityViewToMedico(novo));
			}
		});
		control.listar();
		btnAdicionar.setOnAction( (e) -> {
			control.adicionar(cbxNomeEspecialidade.getValue());
		});
		
		btnEditar.setOnAction( (e) -> {
			control.atualizar(cbxNomeEspecialidade.getValue());
		});

		btnListar.setOnAction( (e) -> {
			control.listar();
		});

		btnPesquisarPorCrm.setOnAction( (e) -> {
			control.pesquisarPorCrm();
		});

		btnLimpar.setOnAction( (e) -> {
			control.limpar();
		});
		VBox painelP = new VBox(painel, hb, table);

		txtCrm.setText("CRM-");

		return painelP;
	}
}
