package cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.domain.Partida;
import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.domain.Usuari;
import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.services.PartidaService;
import cat.itacademy.barcelonactiva.fase2.marti_comas.adria.s05.t02.n01.model.services.UsuariService;


@Controller
public class UsuariController {
	@Autowired
	private UsuariService uServ;

	@Autowired
	private PartidaService pServ;

	// Mostrar llista usuaris / index
	@GetMapping({ "/mostrarUsuaris", "/", "/list" })
	public ModelAndView mostrarUsuaris() {
		ModelAndView mav = new ModelAndView("list-usuaris");
		List<Usuari> list = uServ.mostrarTot();
		mav.addObject("usuaris", list);
		return mav;
	}

	// Formulari Afegir usuari
	@GetMapping("/addUsuariForm")
	public ModelAndView afegirUsuariForm() {
		ModelAndView mav = new ModelAndView("add-usuari-form");
		Usuari usuari = new Usuari();
		mav.addObject("usuari", usuari);
		return mav;
	}

	// Guardar Usuari
	@PostMapping("/saveUsuari")
	public String guardarUsuari(@ModelAttribute Usuari usuari) {
		List<Usuari> list = uServ.mostrarTot();

		if (usuari.getNumUsuari().isBlank()) {
			usuari.setNumUsuari("ANONIM");
			uServ.guardar(usuari);
			return "redirect:/list";

		}

		if (list.isEmpty()) {
			uServ.guardar(usuari);
			return "redirect:/list";
		}

		for (Usuari usuarif : list) {
			if (usuari.getNumUsuari().equals(usuarif.getNumUsuari())) {
				return "redirect:/errorUsuari";
			}
		}
		uServ.guardar(usuari);
		return "redirect:/list";
	}

	// Mostrar formulari actualitzacio usuari
	@GetMapping("/showUpdateForm")
	public ModelAndView mostrarUpdateForm(@RequestParam Integer pk_UsuariID) {
		ModelAndView mav = new ModelAndView("add-usuari-form");
		Usuari usuari = uServ.buscarPerId(pk_UsuariID);
		mav.addObject("usuari", usuari);

		return mav;
	}

	// Eliminar usuari
	@GetMapping("/deleteUsuari")
	public String eliminarUsuari(@RequestParam Integer pk_UsuariID) {
		uServ.eliminarPerId(pk_UsuariID);
		return "redirect:/list";

	}

	// Mostrar formulari Busqueda usuari
	@GetMapping("/searchUsuariForm")
	public ModelAndView buscarUsuariForm() {
		ModelAndView mav = new ModelAndView("search-usuari-form");
		Usuari usuari = new Usuari();
		mav.addObject("usuari", usuari);
		return mav;
	}

	// Buscar usuari
	@GetMapping("/searchUsuari")
	public ModelAndView searchUsuari(@RequestParam String numUsuari) {
		ModelAndView mav = new ModelAndView("show-usuari");
		List<Usuari> list = uServ.buscarPerNom(numUsuari);
		mav.addObject("usuaris", list);
		return mav;
	}

	// Error usuari ja existeix
	@GetMapping("/errorUsuari")
	public ModelAndView errorUsuari() {
		ModelAndView mav = new ModelAndView("error-usuari");
		return mav;

	}

	// Mostrar partides usuari
	@GetMapping("/mostrarPartidesUsuari")
	public ModelAndView mostrarPartidesUsuari(@RequestParam Integer pk_UsuariID) {
		ModelAndView mav = new ModelAndView("partides-usuari");
		Usuari usuari = uServ.buscarPerId(pk_UsuariID);
		List<Partida> partidesUsuari = pServ.mostrarPartidesUsuari(pk_UsuariID);
		mav.addObject("usuari", usuari);
		mav.addObject("partidesUsuari", partidesUsuari);
		return mav;

	}

	// formulari Jugar partida
	@GetMapping("/jugarPartidaForm")
	public ModelAndView jugarPartidaForm(@RequestParam Integer pk_UsuariID) {
		ModelAndView mav = new ModelAndView("jugar-partida-form");

		mav.addObject("usuari", uServ.buscarPerId(pk_UsuariID));
		mav.addObject("partida", new Partida());
		return mav;

	}

	// Ranquing perdedor
	@GetMapping("/ranquingPerdedor")
	public ModelAndView ranquingPerdedor() {
		ModelAndView mav = new ModelAndView("ranquing-perdedor");
		List<Usuari> list = uServ.mostrarTot();
		List<Usuari> sList = list.stream().sorted(Comparator.comparingDouble(Usuari::getPercentatgeExit))
				.collect(Collectors.toList());

		List<Usuari> outList = new ArrayList<Usuari>();
		outList.add(sList.get(0));
		mav.addObject("usuaris", outList);

		return mav;
	}

	// Ranquing perdedor
	@GetMapping("/ranquingGuanyador")
	public ModelAndView ranquingGuanyador() {
		ModelAndView mav = new ModelAndView("ranquing-guanyador");
		List<Usuari> list = uServ.mostrarTot();

		List<Usuari> sList = list.stream().sorted(Comparator.comparingDouble(Usuari::getPercentatgeExit))
				.collect(Collectors.toList());

		List<Usuari> outList = new ArrayList<Usuari>();
		outList.add(sList.get(sList.size() - 1));
		mav.addObject("usuaris", outList);

		return mav;
	}

}
