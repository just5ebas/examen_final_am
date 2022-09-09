package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.modelo.to.Reserva;
import com.example.demo.service.IGestorReservasService;

@Controller
@RequestMapping("/reservas")
public class CompraPasajeController {

	@Autowired
	private IGestorReservasService gestorReservasService;

	@PostMapping("/realizarReserva")
	public String reservar(Reserva reserva) {
		this.gestorReservasService.realizarReserva(reserva.getNumeroVuelo(), reserva.getCantidadAsientos(),
				reserva.getCedula());

		return "redirect:/reservas/todas";
	}

	// Pagina de busqueda
	@GetMapping("/reservar")
	public String disponible(Reserva reserva) {
		return "vistaCrearReserva";
	}

}
