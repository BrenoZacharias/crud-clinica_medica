package DAO;

import java.util.List;

import Entities.Atendimento;

public interface AtendimentoDAO {
	void adicionar(Atendimento atendimento);
	void atualizar(Atendimento atendimento);
	void excluir(int id);
	List<Atendimento> mostrarTodos();
}
