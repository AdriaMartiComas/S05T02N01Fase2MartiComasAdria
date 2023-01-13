package cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.services;

import java.util.List;

import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.domain.Usuari;


public interface UsuariService {
	public List<Usuari> mostrarTot();

	public void guardar(Usuari usuari);

	public Usuari buscarPerId(Integer id);

	public List<Usuari> buscarPerNom(String nom);

	public void eliminarPerId(Integer id);
}
