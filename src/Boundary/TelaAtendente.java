package Boundary;

import java.sql.SQLException;

import Controller.TelaAtendenteController;
import Entities.Atendente;
import Entities.Especialidade;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.util.converter.NumberStringConverter;

public class TelaAtendente {

	private TelaAtendenteController control = new TelaAtendenteController();
	
    public Scene CadastroAtendente () throws SQLException, ClassNotFoundException {
    	  	
        Label lblUserName = new Label("Username:");
        Label lblSenha = new Label("Senha:");
        Label lblNome = new Label("Nome:");
        Label lblCodFunc = new Label("Codigo gerado:");

        TextField txtUsername = new TextField();
        TextField txtSenha = new TextField();
        TextField txtNome = new TextField();
        TextField txtCodFunc = new TextField(); 

        Bindings.bindBidirectional(txtUsername.textProperty(), control.username);
        Bindings.bindBidirectional(txtSenha.textProperty(), control.senha);
        Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
        Bindings.bindBidirectional(txtCodFunc.textProperty(), control.codFunc, new NumberStringConverter());
        
        Button btnSalvar = new Button("Salvar");
        Button btnVoltar = new Button("Voltar");

        GridPane gPane = new GridPane();
        gPane.add(lblNome,0, 0);
        gPane.add(txtNome,1, 0);
        gPane.add(lblUserName,0, 1);
        gPane.add(txtUsername,1, 1);
        gPane.add(lblSenha,0, 2);
        gPane.add(txtSenha,1, 2);
        gPane.add(lblCodFunc, 0, 3);
        gPane.add(txtCodFunc,1, 3);
        gPane.setVgap(10);
        gPane.setHgap(10);
        gPane.setAlignment(Pos.CENTER);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(btnSalvar, btnVoltar);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        
        btnSalvar.setOnAction((e) -> {
            try {
				control.adicionar();
				Principal.changedScreen("Login");
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
        });
        btnVoltar.setOnAction((e) -> {
            try {
                Principal.changedScreen("Login");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        VBox vbox = new VBox(gPane, hbox);
        vbox.setAlignment(Pos.CENTER);

        Scene scn = new Scene(vbox, 800,600);
        
        return scn;
    }
    public Pane TelaAtendente() {
    	
    	HBox hb = new HBox();
    	
    	Button btnEditar = new Button("Editar");
		Button btnPesquisar = new Button(" Pesquisar ");
		Button btnLimpar = new Button(" Limpar ");

		hb.getChildren().addAll(btnEditar, btnPesquisar, btnLimpar);
		hb.setAlignment(Pos.BASELINE_CENTER);
		hb.setSpacing(10);
		hb.setPadding(new Insets(10,10,10,10));
		
		GridPane painel = new GridPane();
		
		Label lblNome = new Label ("Nome:");
		Label lblUsername = new Label ("Username:");
		Label lblSenha = new Label("Senha");
		
		TextField txtNome = new TextField();
		TextField txtUsername = new TextField();
		TextField txtSenha = new TextField();
		
		Bindings.bindBidirectional(txtUsername.textProperty(), control.username);
        Bindings.bindBidirectional(txtSenha.textProperty(), control.senha);
        Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
		
		painel.setPadding(new Insets(10,10,10,10));
		painel.setVgap(10);
		painel.setHgap(10);
		
		painel.add(lblNome, 0, 0);
		painel.add(txtNome, 1, 0);
		painel.add(lblUsername, 0, 1);
		painel.add(txtUsername, 1, 1);
		painel.add(lblSenha, 0, 2);
		painel.add(txtSenha, 1, 2);

		txtNome.setMaxWidth(150);
		txtUsername.setMaxWidth(150);
		
		TableView table = new TableView(); //Tabela

		TableColumn<Atendente, String> col1 = new TableColumn<>("Nome");
		col1.setCellValueFactory(
				new PropertyValueFactory<Atendente, String>("Nome")
				);
		
		TableColumn<Atendente, String> col2 = new TableColumn<>("Username");
		col2.setCellValueFactory(
				new PropertyValueFactory<Atendente, String>("Username")
				);
		TableColumn<Atendente, String> col3 = new TableColumn<>("Senha");
		col3.setCellValueFactory(
				new PropertyValueFactory<Atendente, String>("senha")
				);
		TableColumn<Atendente, String> col4 = new TableColumn<>("Codigo do funcionario");
		col4.setCellValueFactory(
				new PropertyValueFactory<Atendente, String>("codFunc")
				);
		 TableColumn<Atendente, String> col5 = new TableColumn<>("Acoes");
	        col5.setCellFactory( (tbcol) -> {
	            Button btnRemover = new Button("Remover");
	            TableCell<Atendente, String> tcell = new TableCell<Atendente, String>() {
	                @Override
	                protected void updateItem(String item, boolean empty) {
	                    if (empty) {
	                        setGraphic(null);
	                        setText(null);
	                    } else {
	                        btnRemover.setOnAction( (e) -> {
	                            Atendente a = getTableView().getItems().get(getIndex());
	                            control.remover(a.getCodFunc());
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
		
		table.getColumns().addAll(col4,col1, col2, col3, col5);
		table.setItems(control.getLista());
		table.getSelectionModel().selectedItemProperty().addListener( (obs, old, novo) -> {
			control.fromEntity((Atendente) novo);
		});
		control.pesquisar();
		btnPesquisar.setOnAction( (e) -> {
			control.pesquisar();
		});
		btnEditar.setOnAction( (e) -> {
			try {
				control.salvar();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		btnLimpar.setOnAction( (e) -> {
			control.limpar();
		});
		VBox vb = new VBox(painel, hb, table);
		
		return vb;	
    }
}
