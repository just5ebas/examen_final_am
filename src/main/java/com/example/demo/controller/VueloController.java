package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.modelo.to.VueloDisponible;
import com.example.demo.service.IVueloService;

@Controller
@RequestMapping("/vuelos")
public class VueloController {

	@Autowired
	private IVueloService iVueloService;

	@GetMapping("/disponibles")
	public String vuelosDisponibles(VueloDisponible vuelo, Model modelo) {
		List<VueloDisponible> vuelos = this.iVueloService.buscarVuelos(vuelo.getOrigen(), vuelo.getDestino(),
				LocalDateTime.parse(vuelo.getFecha().concat("T00:00:00")));
		modelo.addAttribute("vuelos", vuelos);
		return "vistaVuelosDisponibles";
	}

	// Paginas de busqueda
	@GetMapping("/buscarDisponibles")
	public String buscarDisponibles(VueloDisponible vuelo) {
		return "vistaBusquedaVuelos";
	}

}
