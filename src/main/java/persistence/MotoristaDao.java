package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Motorista;

public class MotoristaDao implements ICrud<Motorista>{
	
	private GenericDao gDao;
	
	public MotoristaDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public void inserir(Motorista t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO motorista VALUES (?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getCodigo());
		ps.setString(2, t.getNome());
		ps.setString(3, t.getNaturalidade());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void atualizar(Motorista t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "UPDATE motorista SET nome = ?, naturalidade = ? WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getNome());
		ps.setString(2, t.getNaturalidade());
		ps.setInt(3, t.getCodigo());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void excluir(Motorista t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "DELETE motorista WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getCodigo());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public Motorista consultar(Motorista t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, nome, naturalidade FROM motorista WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			t.setCodigo(rs.getInt("codigo"));
			t.setNome(rs.getString("nome"));
			t.setNaturalidade(rs.getString("naturalidade"));
		}
		rs.close();
		ps.close();
		c.close();
		return t;
	}

	@Override
	public List<Motorista> listar() throws SQLException, ClassNotFoundException {
		List<Motorista> motoristas = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, nome, naturalidade FROM motorista";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Motorista m = new Motorista();
			m.setCodigo(rs.getInt("codigo"));
			m.setNome(rs.getString("nome"));
			m.setNaturalidade(rs.getString("naturalidade"));
			
			motoristas.add(m);
		}
		rs.close();
		ps.close();
		c.close();
		return motoristas;
	}

}
