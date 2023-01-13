package cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.domain.Usuari;
import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.repository.UsuariRepository;

@Service
public class UsuariServiceImpl implements UsuariService {
	
	@Autowired
	private UsuariRepository uRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Usuari> mostrarTot() {
		return uRepo.findAll();
	}

	@Override
	@Transactional
	public void guardar(Usuari usuari) {
		uRepo.save(usuari);
	}

	@Override
	public Usuari buscarPerId(Integer id) {
		return uRepo.findById(id).get();
	}

	@Override
	public List<Usuari> buscarPerNom(String nom) {
		List<Usuari> listIn = uRepo.findAll();
		List<Usuari> listOut = new ArrayList<Usuari>();
		
		for(Usuari usuari : listIn) {
			if(usuari.getNumUsuari().contains(nom)) {
				listOut.add(usuari);
			}
		}
		return listOut;
	}

	@Override
	public void eliminarPerId(Integer id) {
		uRepo.deleteById(id);
		
	}
}
