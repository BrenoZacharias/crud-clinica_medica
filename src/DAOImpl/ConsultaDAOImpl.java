package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ConsultaDAO;
import DAO.GenericDAO;
import Entities.Consulta;

public class ConsultaDAOImpl implements ConsultaDAO{

	private Connection con;
	
	GenericDAO gDao = new GenericDAO();
	
	@Override
	public void adicionar(Consulta consulta) {
		try {
			Connection con = gDao.getConnection();
			String sql = "INSERT INTO consulta(registro, descricao, cpf, crm, dataConsulta)" + "VALUES(null,?, ?, ?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, consulta.getDescricao());
			st.setString(2, consulta.getCpf());
			st.setString(3, consulta.getCrm());
			st.setDate(4, java.sql.Date.valueOf(consulta.getData()));
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	@Override
	public void atualizar(Consulta consulta) {
		try {
			Connection con = gDao.getConnection();
			String sql = "UPDATE consulta SET descricao = ? Where registro = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, consulta.getDescricao());
			st.setLong(2, consulta.getId());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	@Override
	public void excluir(long id) {
		try {
			Connection con = gDao.getConnection();
			String sql = "DELETE FROM consulta WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id);
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Consulta> pesquisarTodos() {
		List<Consulta> lista = new ArrayList<>();
	        try {
	        	Connection con = gDao.getConnection();
	            String sql = "SELECT * FROM consulta";
	            System.out.println(sql);
	            PreparedStatement st = con.prepareStatement(sql);
	            ResultSet rs = st.executeQuery();

	            while( rs.next() ) {
	                Consulta cons = new Consulta();
	                
	            	cons.setId(rs.getInt("registro"));
					cons.setDescricao(rs.getString("descricao"));
	            	cons.setCpf(rs.getString("cpf"));
	                cons.setCrm(rs.getString("crm"));
	                cons.setData(rs.getDate("dataConsulta").toLocalDate());
	                
	            	lista.add(cons);
	            }
	           con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return lista;
	}
}
