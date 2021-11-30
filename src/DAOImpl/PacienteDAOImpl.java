package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.GenericDAO;
import DAO.PacienteDAO;
import Entities.Paciente;

public class PacienteDAOImpl implements PacienteDAO {

	
	GenericDAO gDao = new GenericDAO();
	
	@Override
	public void adicionar(Paciente paciente) {
		try {
			Connection con = gDao.getConnection();
			String sql = "INSERT INTO paciente(nome, cpf, sexo, nascimento,telefone, rua, num, cidade, complemento)" + "VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, paciente.getNome());
			st.setString(2, paciente.getCpf());
			st.setString(3, paciente.getSexo());
			st.setDate(4, java.sql.Date.valueOf(paciente.getNascm()));
 			st.setString(5, paciente.getTelefone());
			st.setString(6, paciente.getRua());
			st.setString(7, paciente.getNum());
			st.setString(8, paciente.getCidade());
			st.setString(9, paciente.getComplemento());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	@Override
	public void atualizar(String cpf, Paciente paciente) {
		try {
			Connection con = gDao.getConnection();
			String sql = "UPDATE paciente SET nome = ?, sexo = ?, telefone = ?, rua = ?, num = ?, cidade = ?, complemento = ?, nascimento = ? WHERE cpf like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, paciente.getNome());
			st.setString(2, paciente.getSexo()); 
 			st.setString(3, paciente.getTelefone());
			st.setString(4, paciente.getRua());
			st.setString(5, paciente.getNum());
			st.setString(6, paciente.getCidade());
			st.setString(7, paciente.getComplemento());
			st.setDate(8, java.sql.Date.valueOf(paciente.getNascm()));
			st.setString(9, cpf);
			st.executeUpdate();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	@Override
	public void excluir(String cpf) {
		try {
			Connection con = gDao.getConnection();
			String sql = "DELETE FROM paciente WHERE cpf = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, cpf);
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Paciente> pesquisarTodos() {
		List<Paciente> lista = new ArrayList<>();
        try {
        	Connection con = gDao.getConnection();
            String sql = "SELECT * FROM paciente";
            System.out.println(sql);
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while( rs.next() ) {
                Paciente paciente = new Paciente();
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setRua(rs.getString("rua"));
                paciente.setNum(rs.getString("num"));
                paciente.setCidade(rs.getString("cidade"));
                paciente.setComplemento(rs.getString("complemento"));
                //paciente.setNascm(rs.getDate("data").toLocalDate());
				paciente.setNascm(rs.getDate("nascimento").toLocalDate());
            	lista.add(paciente);
            }
           con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
	}
	
}
