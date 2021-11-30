package DAO;

import java.util.List;

import Entities.Paciente;

public interface PacienteDAO {
	void adicionar(Paciente paciente);
	void atualizar(String string, Paciente p);
	void excluir(String cpf);
	List<Paciente> pesquisarTodos();
}