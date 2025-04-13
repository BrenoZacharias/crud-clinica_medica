package DAO;

import java.sql.SQLException;
import java.util.List;

import Entities.Especialidade;

public interface EspecialidadeDAO {
	void adicionar(Especialidade especialidade) throws SQLException;
	void atualizar(Especialidade especialidade);
	void excluir(String cbo);
	List<Especialidade> pesquisarTodos();
	Especialidade findEspecialidadeByNome(String nome);
	Especialidade findEspecialidadeByCbo(String cbo);
}
