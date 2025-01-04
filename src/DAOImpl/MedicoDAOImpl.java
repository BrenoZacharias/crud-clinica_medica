package DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DAO.GenericDAO;
import DAO.MedicoDAO;
import Entities.Especialidade;
import Entities.Medico;

public class MedicoDAOImpl implements MedicoDAO{
		
	GenericDAO gDao = new GenericDAO();
	
	@Override
	public void adicionar(Medico medico) {
		try {
			Connection con = gDao.getConnection();
			String sql = "INSERT INTO medico(nome, especialidade, telefone, crm, logradouro, num, cidade, complemento, nascimento)" + "VALUES(?, ?, ?, ?, ?, ?, ?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			System.out.println(medico.getNome());
			st.setString(1, medico.getNome());
			st.setString(2, medico.getCboEspecialidade());
 			st.setString(3, medico.getTelefone());
			st.setString(4, medico.getCrm());
			st.setString(5, medico.getLogradouro());
			st.setString(6, medico.getNum());
			st.setString(7, medico.getCidade());
			st.setString(8, medico.getComplemento());
			st.setDate(9, java.sql.Date.valueOf(medico.getNascimento()));
			st.executeUpdate();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	@Override
	public void atualizar(String crm, Medico medico) {
		try {
			Connection con = gDao.getConnection();
			String sql = "UPDATE medico SET nome = ?, especialidade = ?, telefone = ?, logradouro = ?, num = ?, cidade = ?, complemento = ?, nascimento = ? WHERE crm = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, medico.getNome());
			st.setString(2, medico.getCboEspecialidade());
 			st.setString(3, medico.getTelefone());
			st.setString(4, medico.getLogradouro());
			st.setString(5, medico.getNum());
			st.setString(6, medico.getCidade());
			st.setString(7, medico.getComplemento());
			st.setDate(8, java.sql.Date.valueOf(medico.getNascimento()));
			st.setString(9, medico.getCrm());
			
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	@Override
	public void excluir(String crm) {
		try {
			Connection con = gDao.getConnection();
			String sql = "DELETE FROM medico WHERE crm = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, crm);
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public List<Medico> pesquisarTodos() {
		List<Medico> lista = new ArrayList<>();
        try {
        	Connection con = gDao.getConnection();
            String sql = "SELECT * FROM medico";
            System.out.println(sql);
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while( rs.next() ) {
                Medico medico = new Medico();
                medico.setNome(rs.getString("nome"));
                medico.setCrm(rs.getString("crm"));
                medico.setCboEspecialidade(rs.getString("especialidade"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setLogradouro(rs.getString("logradouro"));
                medico.setNum(rs.getString("num"));
                medico.setCidade(rs.getString("cidade"));
                medico.setComplemento(rs.getString("complemento"));
                medico.setNascimento(rs.getDate("nascimento").toLocalDate());
            	lista.add(medico);
            }
			rs.close();
			st.close();
           con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
	}

	@Override
	public Medico pesquisarUm(String crm) {
		Medico medico = new Medico();
		try{
			Connection con = gDao.getConnection();
			String sql = "SELECT * FROM medico WHERE crm LIKE ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, crm);
			ResultSet rs = st.executeQuery();
			rs.next();
			medico.setCrm(crm);
			medico.setNome(rs.getString("nome"));
			medico.setCboEspecialidade(rs.getString("especialidade"));
			medico.setTelefone(rs.getString("telefone"));
			medico.setLogradouro(rs.getString("logradouro"));
			medico.setNum(rs.getString("num"));
			medico.setCidade(rs.getString("cidade"));
			medico.setComplemento(rs.getString("complemento"));
			medico.setNascimento(rs.getDate("nascimento").toLocalDate());
			rs.close();
			st.close();
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return medico;
	}

}
