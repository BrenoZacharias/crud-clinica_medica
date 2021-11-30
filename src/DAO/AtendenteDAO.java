package DAO;

import java.sql.SQLException;
import java.util.List;

import Entities.Atendente;
import javafx.beans.property.IntegerProperty;

public interface AtendenteDAO {
	void adicionar(Atendente atendente) throws SQLException;
	void atualizar(int codFunc, Atendente atendente);
	void excluir(int codFunc);
	List<Atendente> pesquisarTodos() throws SQLException;
	boolean encontrarAcesso(String username, String senha) throws SQLException;

}
