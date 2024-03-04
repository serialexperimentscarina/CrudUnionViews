package model;

public class Viagem {
	
	private int codigo;
	private Onibus onibus;
	private Motorista motorista;
	private int horaSaida;
	private int horaChegada;
	private String horaSaidaFormat;
	private String horaChegadaFormat;
	private String partida;
	private String destino;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Onibus getOnibus() {
		return onibus;
	}
	public void setOnibus(Onibus onibus) {
		this.onibus = onibus;
	}
	public Motorista getMotorista() {
		return motorista;
	}
	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}
	public int getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(int hora_saida) {
		this.horaSaida = hora_saida;
	}
	public int getHoraChegada() {
		return horaChegada;
	}
	public void setHoraChegada(int hora_chegada) {
		this.horaChegada = hora_chegada;
	}
	public String getPartida() {
		return partida;
	}
	public void setPartida(String partida) {
		this.partida = partida;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getHoraSaidaFormat() {
		return horaSaidaFormat;
	}
	public void setHoraSaidaFormat(String horaSaidaFormat) {
		this.horaSaidaFormat = horaSaidaFormat;
	}
	public String getHoraChegadaFormat() {
		return horaChegadaFormat;
	}
	public void setHoraChegadaFormat(String horaChegadaFormat) {
		this.horaChegadaFormat = horaChegadaFormat;
	}
	@Override
	public String toString() {
		return codigo + " - " + onibus + " - " + motorista;
	}
	
	

}
