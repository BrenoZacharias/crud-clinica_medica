package DAO;

import java.util.List;

import Entities.Medico;

public interface MedicoDAO {
	void adicionar(Medico medico);
	void atualizar(String crm, Medico medico);
	void excluir(String crm);
	List<Medico> pesquisarTodos();
	Medico pesquisarUm(String crm);
}
