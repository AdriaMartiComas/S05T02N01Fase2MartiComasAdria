package cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="Partida")
public class Partida {
	@Transient
	private static int seq = 0;

	@Id
	private Integer pk_partidaID;

	private int dau1;

	private int dau2;

	private boolean resultat;

	private int idUsuari ;

	public Partida() {
		this.pk_partidaID = seq;
		seq++;

	}

	public Partida(Integer pk_partidaID, int dau1, int dau2, boolean resultat, int idUsuari) {
		this.pk_partidaID = pk_partidaID;
		this.dau1 = dau1;
		this.dau2 = dau2;
		this.resultat = resultat;
		this.idUsuari = idUsuari;
	}

	public Integer getPk_partidaID() {
		return pk_partidaID;
	}

	public void setPk_partidaID(Integer pk_partidaID) {
		this.pk_partidaID = pk_partidaID;
	}

	public int getDau1() {
		return dau1;
	}

	public void setDau1(int dau1) {
		this.dau1 = dau1;
	}

	public int getDau2() {
		return dau2;
	}

	public void setDau2(int dau2) {
		this.dau2 = dau2;
	}

	public boolean isResultat() {
		return resultat;
	}

	public void setResultat(boolean resultat) {
		this.resultat = resultat;
	}

	public int getIdUsuari() {
		return idUsuari;
	}

	public void setIdUsuari(int idUsuari) {
		this.idUsuari = idUsuari;
	}


}
