package cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Usuari")
public class Usuari {

	@Transient
	private static int seq = 0;

	@Id
	private Integer pk_UsuariID;

	private String numUsuari;

	private Date dataRegistre;

	private double percentatgeExit;

	public Usuari() {
		this.pk_UsuariID = seq;

		this.dataRegistre = new Date();
		percentatgeExit = 0;

		seq++;

	}

	public Usuari(Integer pk_UsuariID, String numUsuari, List<Partida> partides) {
		this.pk_UsuariID = pk_UsuariID;
		this.numUsuari = numUsuari;
		this.dataRegistre = new Date();
		percentatgeExit = 0;

	}

	public Usuari(Integer pk_UsuariID, String numUsuari, Date dataRegistre, float percentatgeExit,
			List<Partida> partides) {
		this.pk_UsuariID = pk_UsuariID;
		this.numUsuari = numUsuari;
		this.dataRegistre = dataRegistre;
		this.percentatgeExit = percentatgeExit;
	}

	public String getNumUsuari() {
		return numUsuari;
	}

	public void setNumUsuari(String numUsuari) {
		this.numUsuari = numUsuari;
	}

	public double getPercentatgeExit() {
		return percentatgeExit;
	}

	public void setPercentatgeExit(double percentatgeExit) {
		this.percentatgeExit = percentatgeExit;
	}

	public Integer getPk_UsuariID() {
		return pk_UsuariID;
	}

	public void setPk_UsuariID(Integer pk_UsuariID) {
		this.pk_UsuariID = pk_UsuariID;
	}

	public void setDataRegistre(Date dataRegistre) {
		this.dataRegistre = dataRegistre;
	}

	public Date getDataRegistre() {
		return dataRegistre;
	}

}
