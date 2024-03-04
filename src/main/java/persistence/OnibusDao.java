package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Onibus;

public class OnibusDao implements ICrud<Onibus>{ 
	
	private GenericDao gDao;
	
	public OnibusDao(GenericDao gDao) {
		this.gDao = gDao;
	}
	
	@Override
	public void inserir(Onibus t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO onibus VALUES (?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getPlaca());
		ps.setString(2, t.getMarca());
		ps.setInt(3, t.getAno());
		ps.setString(4, t.getDescricao());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void atualizar(Onibus t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "UPDATE onibus SET marca = ?, ano = ?, descricao = ? WHERE placa = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getMarca());
		ps.setInt(2, t.getAno());
		ps.setString(3, t.getDescricao());
		ps.setString(4, t.getPlaca());
		ps.execute();
		ps.close();
		c.close();
		
	}

	@Override
	public void excluir(Onibus t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "DELETE onibus WHERE placa = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getPlaca());
		ps.execute();
		ps.close();
		c.close();
		
	}

	@Override
	public Onibus consultar(Onibus t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT placa, marca, ano, descricao FROM onibus WHERE placa = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getPlaca());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			t.setMarca(rs.getString("marca"));
			t.setAno(rs.getInt("ano"));
			t.setDescricao(rs.getString("descricao"));
		}
		rs.close();
		ps.close();
		c.close();
		return t;
	}

	@Override
	public List<Onibus> listar() throws SQLException, ClassNotFoundException {
		List<Onibus> onibus = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT placa, marca, ano, descricao FROM onibus";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Onibus o = new Onibus();
			o.setPlaca(rs.getString("placa"));
			o.setMarca(rs.getString("marca"));
			o.setAno(rs.getInt("ano"));
			o.setDescricao(rs.getString("descricao"));
			
			onibus.add(o);
		}
		rs.close();
		ps.close();
		c.close();
		return onibus;
	}

}
