package DAO;

import java.util.List;

import Entities.Especialidade;

public interface EspecialidadeDAO {
	void adicionar(Especialidade especialidade);
	void atualizar(Especialidade especialidade);
	void excluir(String cbo);
	List<Especialidade> pesquisarTodos();
	Especialidade findEspecialidadeByNome(String nome);
	Especialidade findEspecialidadeByCbo(String cbo);
}
