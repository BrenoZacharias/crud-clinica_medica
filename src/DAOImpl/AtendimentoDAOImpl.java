package DAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.AtendimentoDAO;
import Entities.Atendimento;
import Entities.Consulta;

public class AtendimentoDAOImpl implements AtendimentoDAO{
	private static final String URIDB = "jdbc:mariadb://localhost:3306/dbclinicamedica";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	public AtendimentoDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URIDB, USER, PASSWORD);
	}

	@Override
	public void adicionar(Atendimento atendimento) {
		try {
			Connection con = getConnection();
			String sql = "INSERT INTO atendimento(cpf, data, codFunc)" + "VALUES(?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, atendimento.getCpf());
			st.setDate(2, java.sql.Date.valueOf(atendimento.getData()));
			st.setInt(3, atendimento.getCodFunc());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	@Override
	public void atualizar(Atendimento atendimento) {
		try {
			Connection con = getConnection();
			String sql = "UPDATE atendimento SET cpf = ?, data = ? where codFunc = ?" + "VALUES(?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, atendimento.getCpf());
			st.setDate(2, java.sql.Date.valueOf(atendimento.getData()));
			st.setInt(3, atendimento.getCodFunc());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void excluir(int id) {
		try {
			Connection con = getConnection();
			String sql = "DELETE FROM atendimento WHERE codFunc = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	@Override
	public List<Atendimento> mostrarTodos() {
		List<Atendimento> lista = new ArrayList<>();
        try {
        	Connection con = getConnection();
            String sql = "SELECT * FROM atendimento";
            System.out.println(sql);
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while( rs.next() ) {
               Atendimento atend = new Atendimento();
               
               atend.setCpf(rs.getString("cpf"));
               atend.setData(rs.getDate("Data").toLocalDate());
               atend.setCodFunc(rs.getInt("codFunc")); 
               lista.add(atend);
            }
           con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
	}
}
