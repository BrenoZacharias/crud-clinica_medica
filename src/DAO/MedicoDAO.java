package DAO;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import Entities.Medico;

public interface MedicoDAO {
	void adicionar(Medico medico) throws SQLException;
	void atualizar(String crm, Medico medico);
	void excluir(String crm);
	List<Medico> pesquisarTodos();
	Medico pesquisarUm(String crm);
}
