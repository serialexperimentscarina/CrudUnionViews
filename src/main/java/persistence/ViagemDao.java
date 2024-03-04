package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Motorista;
import model.Onibus;
import model.Viagem;

public class ViagemDao implements ICrud<Viagem>{
	
	private GenericDao gDao;
	
	public ViagemDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public void inserir(Viagem t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO viagem VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getCodigo());
		ps.setString(2, t.getOnibus().getPlaca());
		ps.setInt(3, t.getMotorista().getCodigo());
		ps.setInt(4, t.getHoraSaida());
		ps.setInt(5, t.getHoraChegada());
		ps.setString(6, t.getPartida());
		ps.setString(7, t.getDestino());
		ps.execute();
		ps.close();
		c.close();
		
	}

	@Override
	public void atualizar(Viagem t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "UPDATE viagem SET onibus = ?, motorista = ?, hora_saida = ?, hora_chegada = ?, partida = ?, destino = ? WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getOnibus().getPlaca());
		ps.setInt(2, t.getMotorista().getCodigo());
		ps.setInt(3, t.getHoraSaida());
		ps.setInt(4, t.getHoraChegada());
		ps.setString(5, t.getPartida());
		ps.setString(6, t.getDestino());
		ps.setInt(7, t.getCodigo());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void excluir(Viagem t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "DELETE viagem WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getCodigo());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public Viagem consultar(Viagem t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT v.codigo AS codigo_v, v.destino, v.partida, v.destino, v.hora_chegada, v.hora_saida, ");
		sql.append("m.codigo AS codigo_m, m.nome, m.naturalidade, o.ano, o.descricao, o.marca, o.placa ");
		sql.append("FROM viagem v, motorista m, onibus o ");
		sql.append("WHERE v.onibus = o.placa AND v.motorista = m.codigo AND v.codigo = ? ");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setInt(1, t.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Motorista m = new Motorista();
			m.setCodigo(rs.getInt("codigo_m"));
			m.setNome(rs.getString("nome"));
			m.setNaturalidade(rs.getString("naturalidade"));
			
			Onibus o = new Onibus();
			o.setAno(rs.getInt("ano"));
			o.setDescricao(rs.getString("descricao"));
			o.setMarca(rs.getString("marca"));
			o.setPlaca(rs.getString("placa"));
			
			t.setCodigo(rs.getInt("codigo_v"));
			t.setDestino(rs.getString("destino"));
			t.setPartida(rs.getString("partida"));
			t.setHoraChegada(rs.getInt("hora_chegada"));
			t.setHoraSaida(rs.getInt("hora_saida"));
			t.setMotorista(m);
			t.setOnibus(o);
		}
		rs.close();
		ps.close();
		c.close();
		return t;
	}

	@Override
	public List<Viagem> listar() throws SQLException, ClassNotFoundException {
		List<Viagem> viagens = new ArrayList<>();
		Connection c = gDao.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT v.codigo AS codigo_v, v.destino, v.partida, v.destino, v.hora_chegada, v.hora_saida, ");
		sql.append("m.codigo AS codigo_m, m.nome, m.naturalidade, o.ano, o.descricao, o.marca, o.placa ");
		sql.append("FROM viagem v, motorista m, onibus o ");
		sql.append("WHERE v.onibus = o.placa AND v.motorista = m.codigo");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Motorista m = new Motorista();
			m.setCodigo(rs.getInt("codigo_m"));
			m.setNome(rs.getString("nome"));
			m.setNaturalidade(rs.getString("naturalidade"));
			
			Onibus o = new Onibus();
			o.setAno(rs.getInt("ano"));
			o.setDescricao(rs.getString("descricao"));
			o.setMarca(rs.getString("marca"));
			o.setPlaca(rs.getString("placa"));
			
			Viagem t = new Viagem();
			t.setCodigo(rs.getInt("codigo_v"));
			t.setDestino(rs.getString("destino"));
			t.setPartida(rs.getString("partida"));
			t.setHoraChegada(rs.getInt("hora_chegada"));
			t.setHoraSaida(rs.getInt("hora_saida"));
			t.setMotorista(m);
			t.setOnibus(o);
			
			viagens.add(t);
		}
		rs.close();
		ps.close();
		c.close();
		return viagens;
	}
	
	public List<Viagem> listarDescViagem() throws SQLException, ClassNotFoundException {
		List<Viagem> viagens = new ArrayList<>();
		Connection c = gDao.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM v_descricao_viagem");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Motorista m = new Motorista();
			m.setNome(rs.getString("motorista"));
			
			Onibus o = new Onibus();
			o.setPlaca(rs.getString("placa_onibus"));
			
			Viagem t = new Viagem();
			t.setCodigo(rs.getInt("codigo"));
			t.setHoraSaidaFormat(rs.getString("hora_saida"));
			t.setHoraChegadaFormat(rs.getString("hora_chegada"));
			t.setPartida(rs.getString("partida"));
			t.setDestino(rs.getString("destino"));
			t.setMotorista(m);
			t.setOnibus(o);
			
			viagens.add(t);
		}
		rs.close();
		ps.close();
		c.close();
		return viagens;
	}
	
	public List<Viagem> listarDescOnibus() throws SQLException, ClassNotFoundException {
		List<Viagem> viagens = new ArrayList<>();
		Connection c = gDao.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM v_descricao_onibus");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Motorista m = new Motorista();
			m.setNome(rs.getString("motorista"));
			
			Onibus o = new Onibus();
			o.setAno(rs.getInt("ano_onibus"));
			o.setDescricao(rs.getString("descricao_onibus"));
			o.setMarca(rs.getString("marca_onibus"));
			o.setPlaca(rs.getString("placa_onibus"));
			
			Viagem t = new Viagem();
			t.setCodigo(rs.getInt("codigo"));
			t.setMotorista(m);
			t.setOnibus(o);
			
			viagens.add(t);
		}
		rs.close();
		ps.close();
		c.close();
		return viagens;
	}

}
