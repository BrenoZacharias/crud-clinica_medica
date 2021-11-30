package Boundary;

import java.time.LocalDate;

import Controller.TelaMedicoController;
import Entities.Especialidade;
import Entities.Medico;
import javafx.beans.binding.Bindings;
//import Entities.Medico;
import javafx.beans.property.StringProperty;
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
		Button btnPesquisar = new Button(" Pesquisar ");
		
		hb.getChildren().addAll(btnAdicionar, btnEditar, btnPesquisar);
		hb.setAlignment(Pos.BASELINE_CENTER);
		hb.setSpacing(10);
		hb.setPadding(new Insets(10,10,10,10));
		
		//------------------------------------------- área de Edição Label/TextField
		GridPane painel = new GridPane();
		
		Label lblNome = new Label("Nome:");
		Label lblEspec = new Label("Especialização:");
		Label lblTelefone = new Label ("Telefone:");
		Label lblCrm = new Label("CRM:");
		Label lblRua = new Label("Rua");
		Label lblNum = new Label("Num:");
		Label lblCidade = new Label("Cidade:");
		Label lblComp = new Label("Complemento:");
		Label lblNasc = new Label ("Data de nascimento:");
		TextField txtNome = new TextField();
		TextField txtEspec = new TextField();
		TextField txtTel = new TextField();
		TextField txtCrm = new TextField();
		TextField txtRua = new TextField();
		TextField txtNum = new TextField();
		TextField txtCidade = new TextField();
		TextField txtComp = new TextField();
		TextField txtNasc = new TextField();

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
		painel.add(lblRua, 2, 2);
		painel.add(txtRua, 3, 2);
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
		Bindings.bindBidirectional(txtRua.textProperty(), control.rua);
		Bindings.bindBidirectional(txtNum.textProperty(), control.num);
		Bindings.bindBidirectional(txtCidade.textProperty(), control.cidade);
		Bindings.bindBidirectional(txtComp.textProperty(), control.complemento);
		//Bindings.bindBidirectional(cbxNomeEspecialidade.getValue(), (StringProperty) control.nome_Especialidade);

		painel.setVgap(10);
		painel.setHgap(10);
		
		painel.setPadding(new Insets(10,10,10,10));
		
		//-------------------------------------------------------- Table
		
		TableView <Medico> table = new TableView<>();

		TableColumn<Medico, String> col1 = new TableColumn<>("Nome");
		col1.setCellValueFactory(
			new PropertyValueFactory<Medico, String>("nome")
		);
		TableColumn<Medico, String> col2 = new TableColumn<>("Especialidade");
		col2.setCellValueFactory(
			new PropertyValueFactory<Medico, String>("getCboEspecialidade()")
		);
		TableColumn<Medico, String> col3 = new TableColumn<>("CRM");
		col3.setCellValueFactory(
			new PropertyValueFactory<Medico, String>("crm")
		);
		TableColumn<Medico, String> col4 = new TableColumn<>("Telefone");
		col4.setCellValueFactory(
			new PropertyValueFactory<Medico, String>("telefone")
		);	
		
		TableColumn<Medico, LocalDate> col5= new TableColumn<>("Nascimento");
		col5.setCellValueFactory(
			new PropertyValueFactory<Medico, LocalDate>("nascimento")
		);	
		TableColumn<Medico, String> col6 = new TableColumn<>("Rua");
		col6.setCellValueFactory(
			new PropertyValueFactory<Medico, String>("rua")
		);
		TableColumn<Medico, String> col7 = new TableColumn<>("Num");
		col7.setCellValueFactory(
			new PropertyValueFactory<Medico, String>("num")
		);
		TableColumn<Medico, String> col8 = new TableColumn<>("Cidade");
		col8.setCellValueFactory(
			new PropertyValueFactory<Medico, String>("cidade")
		);
		TableColumn<Medico, String> col9= new TableColumn<>("Complemento");
		col9.setCellValueFactory(
			new PropertyValueFactory<Medico, String>("complemento")
		);
		
		 TableColumn<Medico, String> col10 = new TableColumn<>("Ações");
	        col10.setCellFactory( (tbcol) -> {
	            Button btnRemover = new Button("Remover");
	            TableCell<Medico, String> tcell = new TableCell<Medico, String>() {
	                @Override
	                protected void updateItem(String item, boolean empty) {
	                    if (empty) {
	                        setGraphic(null);
	                        setText(null);
	                    } else {
	                        btnRemover.setOnAction( (e) -> {
	                            Medico medico = getTableView().getItems().get(getIndex());
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
			control.fromEntity((Medico) novo);
		});

		btnAdicionar.setOnAction( (e) -> {
			control.adicionar(cbxNomeEspecialidade.getValue());
		});
		
		btnEditar.setOnAction( (e) -> {
			control.atualizar(cbxNomeEspecialidade.getValue());
		});
		
		btnPesquisar.setOnAction( (e) -> {
			control.pesquisar();
		});
				
		VBox painelP = new VBox(painel, hb, table);
		
		return painelP;
	}
}
