package cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.domain.Partida;
import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.repository.PartidaRepository;

@Service
public class PartidaServiceImpl implements PartidaService{
	@Autowired
	private PartidaRepository pRepo;


	@Override
	@Transactional(readOnly = true)
	public List<Partida> mostrarPartidesUsuari(Integer pk_UsuariID) {
		List<Partida> partidesUsuari = pRepo.findAll().stream()
				.filter(u -> pk_UsuariID == u.getIdUsuari()).collect(Collectors.toList());

		return partidesUsuari;
	}

	@Override
	@Transactional
	public void guardar(Partida partida, Integer pk_UsuariID) {
		partida.setIdUsuari(pk_UsuariID);
		pRepo.save(partida);
	}

	@Override
	@Transactional
	public void eliminarPartides(Integer pk_UsuariID) {
		List<Partida> partidesUsuari = pRepo.findAll().stream()
				.filter(u -> pk_UsuariID == u.getIdUsuari()).collect(Collectors.toList());
		pRepo.deleteAll(partidesUsuari);

	}

}
