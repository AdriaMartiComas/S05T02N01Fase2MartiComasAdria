package cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.domain.Partida;
import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.domain.Usuari;
import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.services.PartidaService;
import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.services.UsuariService;

@Controller
public class PartidaController {

	@Autowired
	private PartidaService pServ;
	
	@Autowired
	private UsuariService uServ;

	// Jugar partida
		@PostMapping("/jugarDaus")
		public ModelAndView jugarDaus(@RequestParam Integer pk_UsuariID) {
			ModelAndView mav = new ModelAndView("jugar-partida-form");
			Usuari usuari = uServ.buscarPerId(pk_UsuariID);
			Partida partida = new Partida();
			int dau1 = (int) (Math.random() * 6 + 1);
			int dau2 = (int) (Math.random() * 6 + 1);
			boolean resultat = false;

			if (dau1 + dau2 == 7) {
				resultat = true;
			}

			partida.setDau1(dau1);
			partida.setDau2(dau2);
			partida.setResultat(resultat);
			pServ.guardar(partida, usuari.getPk_UsuariID());


			// Set percentatge exit 
			List<Partida> partidesUsuari = pServ.mostrarPartidesUsuari(pk_UsuariID);
			int partidesGuanyades = 0, size = partidesUsuari.size();
			double percentatgeExit = 0;

			for (int i = 0; i < size; i++) {
				if (partidesUsuari.get(i).isResultat()) {
					partidesGuanyades = partidesGuanyades + 1;
				}
			}
			if (partidesGuanyades != 0) {
				percentatgeExit = (double) (partidesGuanyades / (double) (size + 1)) * 100;
			}
			
			usuari.setPercentatgeExit(percentatgeExit);
			uServ.guardar(usuari);


			mav.addObject("usuari", usuari);
			mav.addObject("partida", partida);

			return mav;
		}

		// Eliminar partides usuari
		@GetMapping("/eliminarPartides")
		public ModelAndView eliminarPartides(@RequestParam Integer pk_UsuariID) {
			ModelAndView mav = new ModelAndView("partides-usuari");
			Usuari usuari = uServ.buscarPerId(pk_UsuariID);
			pServ.eliminarPartides(pk_UsuariID);
			List<Partida> partidesUsuari = pServ.mostrarPartidesUsuari(pk_UsuariID);
			usuari.setPercentatgeExit(0);
			uServ.guardar(usuari);

			mav.addObject("usuari", usuari);
			mav.addObject("partidesUsuari", partidesUsuari);

			return mav;

		}

	

	

}
