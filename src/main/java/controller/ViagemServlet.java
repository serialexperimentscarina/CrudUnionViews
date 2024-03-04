package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Motorista;
import model.Onibus;
import model.Viagem;
import persistence.GenericDao;
import persistence.MotoristaDao;
import persistence.OnibusDao;
import persistence.ViagemDao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/viagem")
public class ViagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViagemServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String erro = "";
		List<Motorista> motoristas = new ArrayList<>();
		List<Onibus> onibusLista = new ArrayList<>();

		GenericDao gDao = new GenericDao();
		MotoristaDao mDao = new MotoristaDao(gDao);
		OnibusDao oDao = new OnibusDao(gDao);
		
		try {
			motoristas = mDao.listar();
			onibusLista = oDao.listar();
		} catch (Exception e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("erro", erro);
			request.setAttribute("onibusLista", onibusLista);
			request.setAttribute("motoristas", motoristas);
			
			RequestDispatcher rd = request.getRequestDispatcher("viagem.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("botao");
		String codigo = request.getParameter("codigo");
		String onibus = request.getParameter("onibus");
		String motorista = request.getParameter("motorista");
		String horaSaida = request.getParameter("hora_saida");
		String horaChegada = request.getParameter("hora_chegada");
		String partida = request.getParameter("partida");
		String destino = request.getParameter("destino");
		
		String saida = "";
		String erro = "";
		Viagem v = new Viagem();
		List<Viagem> viagens = new ArrayList<>();
		List<Onibus> onibusLista = new ArrayList<>();
		List<Motorista> motoristas = new ArrayList<>();
		List<Viagem> descOnibus = new ArrayList<>();
		List<Viagem> descViagens = new ArrayList<>();
		
		if (!cmd.contains("Listar") && !cmd.contains("Desc")) {
			v.setCodigo(Integer.parseInt(codigo));
		}
		try {
			onibusLista = listarOnibus();
			motoristas = listarMotoristas();
			
			if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
				Motorista m = new Motorista();
				m.setCodigo(Integer.parseInt(motorista));
				v.setMotorista(buscarMotorista(m));
				
				Onibus o = new Onibus();
				o.setPlaca(onibus);
				v.setOnibus(buscarOnibus(o));
				
				v.setCodigo(Integer.parseInt(codigo));
				v.setDestino(destino);
				v.setPartida(partida);
				v.setHoraChegada(Integer.parseInt(horaChegada));
				v.setHoraSaida(Integer.parseInt(horaSaida));
			}
			
			if (cmd.contains("Cadastrar")) {
				cadastrarViagem(v);
				saida = "Viagem cadastrada com sucesso";
				v = null;
			}
			if (cmd.contains("Alterar")) {
				alterarViagem(v);
				saida = "Viagem alterada com sucesso";
				v = null;
			}
			if (cmd.contains("Excluir")) {
				excluirViagem(v);
				saida = "Viagem excluida com sucesso";
				v = null;
			}
			if (cmd.contains("Buscar")) {
				v = buscarViagem(v);
			}
			if (cmd.contains("Listar")) {
				viagens = listarViagens();
			}
			if (cmd.contains("Desc do Onibus")) {
				descOnibus = listarDescOnibus();
			}
			if (cmd.contains("Desc da Viagem")) {
				descViagens = listarDescViagem();
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("viagem", v);
			request.setAttribute("viagens", viagens);
			request.setAttribute("onibusLista", onibusLista);
			request.setAttribute("motoristas", motoristas);
			request.setAttribute("descOnibus", descOnibus);
			request.setAttribute("descViagens", descViagens);
			
			RequestDispatcher rd = request.getRequestDispatcher("viagem.jsp");
			rd.forward(request, response);
		}
	}
	
	private List<Viagem> listarDescViagem() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		return vDao.listarDescViagem();
	}

	private List<Viagem> listarDescOnibus() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		return vDao.listarDescOnibus();
	}

	private List<Viagem> listarViagens() throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		List<Viagem> viagens = new ArrayList<>();
		viagens = vDao.listar();
		return viagens;
	}

	private Viagem buscarViagem(Viagem v) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		v = vDao.consultar(v);
		return v;
	}

	private void excluirViagem(Viagem v) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		vDao.excluir(v);
	}

	private void alterarViagem(Viagem v) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		vDao.atualizar(v);
	}

	private void cadastrarViagem(Viagem v) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		vDao.inserir(v);
	}

	private List<Onibus> listarOnibus() throws SQLException, ClassNotFoundException{
		GenericDao gDao = new GenericDao();
		OnibusDao oDao = new OnibusDao(gDao);
		List<Onibus> onibus = oDao.listar();
		return onibus;
	}
	
	private List<Motorista> listarMotoristas() throws SQLException, ClassNotFoundException{
		GenericDao gDao = new GenericDao();
		MotoristaDao mDao = new MotoristaDao(gDao);
		List<Motorista> motoristas = mDao.listar();
		return motoristas;
	}
	

	private Motorista buscarMotorista(Motorista m) throws SQLException, ClassNotFoundException{
		GenericDao gDao = new GenericDao();
		MotoristaDao mDao = new MotoristaDao(gDao);
		m = mDao.consultar(m);
		return m;
	}
	
	private Onibus buscarOnibus(Onibus o) throws SQLException, ClassNotFoundException{
		GenericDao gDao = new GenericDao();
		OnibusDao oDao = new OnibusDao(gDao);
		o = oDao.consultar(o);
		return o;
	}

}
