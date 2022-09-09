package com.example.demo.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IClienteRepository;
import com.example.demo.repository.ICompraPasajeRepository;
import com.example.demo.repository.modelo.CompraPasaje;
import com.example.demo.repository.modelo.to.AsientosVuelo;

@Service
public class GestorReservasServiceImpl implements IGestorReservasService {

	@Autowired
	private IVueloService iVueloService;

	@Autowired
	private ICompraPasajeRepository iCompraPasajeRepository;

	@Autowired
	private IClienteRepository iClienteRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void realizarReserva(String numeroVuelo, Integer cantidadAsientos, String cedula) {
		// TODO Auto-generated method stub

		AsientosVuelo asientosVuelo = this.iVueloService.contarAsientos(numeroVuelo);

		if (asientosVuelo.getDisponibles() >= cantidadAsientos) {
			CompraPasaje cp = new CompraPasaje();
			cp.setEstado("RES");
			cp.setCantidadAsientosComprados(cantidadAsientos);
			cp.setFechaCompra(LocalDateTime.now());
			cp.setCliente(this.iClienteRepository.buscarPorCedula(cedula));
			cp.setVuelo(this.iVueloService.buscarPorNumero(numeroVuelo));

			this.iCompraPasajeRepository.insertar(cp);

			cp.setNumero("00" + cp.getNumero());
			this.iCompraPasajeRepository.açtualizar(cp);
		}

	}

}
