package pcrn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Ponto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private Data data;
	private String horaEntrada;
	private String horaSaida;
	private String horasTrabalhadas;
	private Boolean pontoEntrada;
	private Boolean pontoSaida;
	private String situacaoPonto;
	private Pessoa pessoa;
	
	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}	
	
	@ManyToOne
	@JoinColumn(name="codigo_data", referencedColumnName="codigo")
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	
	@Column(name="hora_entrada")
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	
	@Column(name="hora_saida")
	public String getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}
	
	@Column(name="horas_trabalhadas")
	public String getHorasTrabalhadas() {
		return horasTrabalhadas;
	}
	public void setHorasTrabalhadas(String horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}
	@Column(name="ponto_entrada")
	public Boolean getPontoEntrada() {
		return pontoEntrada;
	}
	public void setPontoEntrada(Boolean pontoEntrada) {
		this.pontoEntrada = pontoEntrada;
	}
	
	@Column(name="ponto_saida")
	public Boolean getPontoSaida() {
		return pontoSaida;
	}
	public void setPontoSaida(Boolean pontoSaida) {
		this.pontoSaida = pontoSaida;
	}
	
	@Column(name="situacao_ponto")
	public String getSituacaoPonto() {
		return situacaoPonto;
	}
	public void setSituacaoPonto(String situacaoPonto) {
		this.situacaoPonto = situacaoPonto;
	}
	
	@ManyToOne
	@JoinColumn(name="codigo_pessoa", referencedColumnName="codigo")
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((horaEntrada == null) ? 0 : horaEntrada.hashCode());
		result = prime * result + ((horaSaida == null) ? 0 : horaSaida.hashCode());
		result = prime * result + ((horasTrabalhadas == null) ? 0 : horasTrabalhadas.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((pontoEntrada == null) ? 0 : pontoEntrada.hashCode());
		result = prime * result + ((pontoSaida == null) ? 0 : pontoSaida.hashCode());
		result = prime * result + ((situacaoPonto == null) ? 0 : situacaoPonto.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ponto other = (Ponto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (horaEntrada == null) {
			if (other.horaEntrada != null)
				return false;
		} else if (!horaEntrada.equals(other.horaEntrada))
			return false;
		if (horaSaida == null) {
			if (other.horaSaida != null)
				return false;
		} else if (!horaSaida.equals(other.horaSaida))
			return false;
		if (horasTrabalhadas == null) {
			if (other.horasTrabalhadas != null)
				return false;
		} else if (!horasTrabalhadas.equals(other.horasTrabalhadas))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (pontoEntrada == null) {
			if (other.pontoEntrada != null)
				return false;
		} else if (!pontoEntrada.equals(other.pontoEntrada))
			return false;
		if (pontoSaida == null) {
			if (other.pontoSaida != null)
				return false;
		} else if (!pontoSaida.equals(other.pontoSaida))
			return false;
		if (situacaoPonto == null) {
			if (other.situacaoPonto != null)
				return false;
		} else if (!situacaoPonto.equals(other.situacaoPonto))
			return false;
		return true;
	}
}