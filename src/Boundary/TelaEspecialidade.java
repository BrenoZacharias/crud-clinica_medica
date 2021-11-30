package Boundary;

import Controller.TelaEspecialidadeController;
import Entities.Consulta;
import Entities.Especialidade;
import javafx.beans.binding.Bindings;
//import Entities.Medico;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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

public class TelaEspecialidade {
	
	private TelaEspecialidadeController control = new TelaEspecialidadeController();
	
	public Pane TelaEspecialidade() {
		
		HBox hb = new HBox();
		
		Button btnAdicionar = new Button("Adicionar");
		Button btnEditar = new Button("Editar");
		Button btnPesquisar = new Button("Pesquisar");
				
		hb.setAlignment(Pos.BASELINE_CENTER);
		hb.setSpacing(10);
		hb.setPadding(new Insets(10,10,10,10));
		
		hb.getChildren().addAll(btnAdicionar, btnEditar, btnPesquisar);
		
		GridPane painel = new GridPane();
		
		Label lblCbo = new Label(" Código CBO: ");
		Label lblNome = new Label(" Nome da especialidade: ");
		Label lblAviso = new Label("O CAMPO CBO NÃO É EDITAVEL");
		
		lblAviso.setStyle("-fx-text-fill: red; -fx-font-size: 10px;");

		TextField txtCbo = new TextField();
		TextField txtNome = new TextField();
		
		Bindings.bindBidirectional(txtCbo.textProperty(), control.cbo );
		Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
		
		painel.add(lblCbo, 0, 0);
		painel.add(txtCbo, 1, 0);
		painel.add(lblAviso, 2, 0);
		painel.add(lblNome, 0, 1);
		painel.add(txtNome, 1, 1);
		
		painel.setAlignment(Pos.CENTER_LEFT);
		painel.setVgap(10);
		painel.setHgap(10);
		
		painel.setPadding(new Insets(10,10,10,10));

		btnAdicionar.setOnAction( (e) -> {
			control.adicionar();
			new Alert(Alert.AlertType.INFORMATION, "Especialidade Adicionada com sucesso").showAndWait();
		});
		
		btnEditar.setOnAction( (e) -> {
			control.atualizar();
			new Alert(Alert.AlertType.INFORMATION, "Especialidade Atualizada com sucesso").showAndWait();
		});
		
		btnPesquisar.setOnAction( (e) -> {
			control.pesquisar();
		});
		
		TableView table = new TableView(); 
		
		TableColumn<Especialidade, String> col1 = new TableColumn<>("CBO");
		col1.setCellValueFactory(
				new PropertyValueFactory<Especialidade, String>("cbo")
				);
		TableColumn<Especialidade, String> col2 = new TableColumn<>("Nome da especialidade");
		col2.setCellValueFactory(
				new PropertyValueFactory<Especialidade, String>("nome")
				);
		TableColumn<Especialidade, String> col3 = new TableColumn<>("Ações");
	    col3.setCellFactory( (tbcol) -> {
            Button btnRemover = new Button("Remover");
            TableCell<Especialidade, String> tcell = new TableCell<Especialidade, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btnRemover.setOnAction( (e) -> {
                            Especialidade especialidade = getTableView().getItems().get(getIndex());
                            control.excluir(especialidade.getCbo());
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
	    table.getColumns().addAll(col1,col2, col3);
	    table.setItems(control.getLista());
		table.getSelectionModel().selectedItemProperty().addListener( (obs, old, novo) -> {
			control.fromEntity((Especialidade) novo);
		});
		
		VBox vb = new VBox(painel, hb, table);
		
		return vb;

	}
}
