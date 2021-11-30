package Boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Controller.TelaConsultaController;
import Entities.Consulta;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateStringConverter;

public class TelaConsulta {
	
	private TelaConsultaController control = new TelaConsultaController();
		
	public Pane TelaConsulta() {
	
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		HBox hb = new HBox();
		
		Button btnAdicionar = new Button(" Adicionar ");
		Button btnEditar = new Button("Editar");
		Button btnPesquisar = new Button(" Pesquisar ");
		
		hb.getChildren().addAll(btnAdicionar, btnEditar, btnPesquisar);
		hb.setAlignment(Pos.BASELINE_CENTER);
		hb.setSpacing(10);
		hb.setPadding(new Insets(10,10,10,10));
		
		GridPane painel = new GridPane();
	
		Label lblCPF = new Label ("CPF:");
		Label lblCRM = new Label ("CRM:");
		Label lblData = new Label("Data da consulta");

		TextField txtCPF = new TextField();
		TextField txtCRM = new TextField();
		TextField txtData = new TextField();
		
		Label lblDesc = new Label("Descricao:");
		TextArea descr = new TextArea();
		
        Bindings.bindBidirectional(txtCPF.textProperty(), control.cpf);
        Bindings.bindBidirectional(txtCRM.textProperty(), control.crm);
        Bindings.bindBidirectional(txtData.textProperty(), control.dataConsult, new LocalDateStringConverter());
        Bindings.bindBidirectional(descr.textProperty(), control.descricao);
        
		painel.setPadding(new Insets(10,10,10,10));
		painel.setVgap(10);
		painel.setHgap(10);
		
		painel.add(lblCPF, 0, 0);
		painel.add(txtCPF, 1, 0);
		painel.add(lblCRM, 0, 1);
		painel.add(txtCRM, 1, 1);
		painel.add(lblData, 0, 2);
		painel.add(txtData, 1,2);
		painel.add(lblDesc, 0, 3);
		painel.add(descr, 1, 3);

		txtCPF.setMaxWidth(150);
		txtCRM.setMaxWidth(150);

		
		TableView table = new TableView(); //Tabela
		
		TableColumn<Consulta, Long> col1 = new TableColumn<>("Id");
		col1.setCellValueFactory(
				new PropertyValueFactory<Consulta, Long>("id")
				);
		
		TableColumn<Consulta, String> col2 = new TableColumn<>("CPF");
		col2.setCellValueFactory(
				new PropertyValueFactory<Consulta, String>("cpf")
				);
		
		TableColumn<Consulta, String> col3 = new TableColumn<>("CRM");
		col3.setCellValueFactory(
				new PropertyValueFactory<Consulta, String>("crm")
				);
		TableColumn<Consulta, String> col4 = new TableColumn<>("Data");
		col4.setCellValueFactory( (item) -> {
				LocalDate d = item.getValue().getData();
                return new ReadOnlyStringWrapper(d.format(fmt));
                }
		);
		
		TableColumn<Consulta, String> col5 = new TableColumn<>("Descrição");
		col5.setCellValueFactory(
				new PropertyValueFactory<Consulta, String>("descricao")
				);
		TableColumn<Consulta, String> col6 = new TableColumn<>("Ações");
	    col6.setCellFactory( (tbcol) -> {
            Button btnRemover = new Button("Remover");
            TableCell<Consulta, String> tcell = new TableCell<Consulta, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btnRemover.setOnAction( (e) -> {
                            Consulta c = getTableView().getItems().get(getIndex());
                            control.excluir(c.getId());
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
	
		table.getColumns().addAll(col1,col2, col3, col4, col5, col6);
		table.setItems(control.getLista());
		table.getSelectionModel().selectedItemProperty().addListener( (obs, old, novo) -> {
			control.fromEntity((Consulta) novo);
		});
		
		btnAdicionar.setOnAction( (e) -> {
			control.adicionar();
			new Alert(Alert.AlertType.INFORMATION, "Consulta salva com sucesso").showAndWait();
			control.limparCampos();
			control.pesquisar();
		});
		
		btnEditar.setOnAction( (e) -> {
			control.atualizar();
			control.pesquisar();
		});
		
		btnPesquisar.setOnAction( (e) -> {
			control.pesquisar();
		});
	
		VBox vb = new VBox(painel, hb, table);
		
		return vb;
	}

}
