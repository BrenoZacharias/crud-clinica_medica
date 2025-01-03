package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.AtendenteDAO;
import DAO.GenericDAO;
import Entities.Atendente;

public class AtendenteDAOImpl implements AtendenteDAO{

	GenericDAO gDao = new GenericDAO();
	
	@Override
	public void adicionar(Atendente atendente) {
		try {
			Connection con = gDao.getConnection();
			String sql = "INSERT INTO atendente(codFunc,nome, username, senha)" + "VALUES(?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,atendente.getCodFunc());
			st.setString(2, atendente.getNome());
			st.setString(3, atendente.getUsername());
			st.setString(4, atendente.getSenha());
			st.execute();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void adicionarCodigo(int codigo) {
		try {
			Connection con = gDao.getConnection();
			String sql = "INSERT INTO Codigo(codGerado)" + "VALUES(?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, codigo);
			st.execute();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean verificarCodigo(int codigo){
		boolean x;
		try{
			Connection con = gDao.getConnection();
			String sql = "SELECT * FROM Codigo WHERE codGerado = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, codigo);
			ResultSet rs = st.executeQuery();

			con.close();
			if(!rs.first()){
				x = false;
			} else { x = true; }
		} catch (SQLException e){
			e.printStackTrace();
			x = false;
		}
		return x;
	}

	@Override
	public void atualizar(int codFunc, Atendente atendente) {
		try {
			Connection con = gDao.getConnection();
			String sql = "UPDATE atendente SET nome = ?, username = ?, senha = ? where codFunc = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, atendente.getNome());
			st.setString(2, atendente.getUsername());
			st.setString(3, atendente.getSenha());
			st.setInt(4, atendente.getCodFunc());
			st.execute();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(int codFunc) {
		try {
			Connection con = gDao.getConnection();
			String sql = "DELETE FROM atendente WHERE codFunc = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, codFunc);
			System.out.println(sql);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	@Override
	public boolean encontrarAcesso(String username, String senha) {
		boolean x;
		try{
			Connection con = gDao.getConnection();
			String sql = "SELECT * FROM atendente WHERE username = ? AND senha = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, senha);
			ResultSet rs = st.executeQuery();
			
			con.close();
			if(!rs.first()){
				System.out.println("Username ou Senha incorretos!!!");
				x = false;
			} else { x = true; }
		} catch (SQLException e){
			e.printStackTrace();
			x = false;
		}
		System.out.println(x);
		return x;
	}

	@Override
	public List<Atendente> pesquisarTodos() {
		 List<Atendente> lista = new ArrayList<>();
	        try {
	        	Connection con = gDao.getConnection();
	            String sql = "SELECT * FROM atendente";
	            System.out.println(sql);
	            PreparedStatement stmt = con.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();

	            while( rs.next() ) {
	                Atendente a = new Atendente();
					a.setCodFunc(rs.getInt("codFunc"));
	                a.setNome(rs.getString("nome"));
	                a.setUsername(rs.getString("username"));
	                a.setSenha(rs.getString("senha"));
	                lista.add(a);
	            }
	          	con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return lista;
	}

	@Override
	public List<Atendente> pesquisarPorNome(String nome) {
		List<Atendente> lista = new ArrayList<>();
		try {
			Connection con = gDao.getConnection();
			String sql = "SELECT * FROM atendente WHERE nome like ?";

			System.out.println(sql);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + nome + "%");
			ResultSet rs = st.executeQuery();

			while( rs.next() ) {
				Atendente a = new Atendente();
				a.setCodFunc(rs.getInt("codFunc"));
				a.setNome(rs.getString("nome"));
				a.setUsername(rs.getString("username"));
				a.setSenha(rs.getString("senha"));
				lista.add(a);
			}
			st.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
