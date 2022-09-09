package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IVueloRepository;
import com.example.demo.repository.modelo.Vuelo;
import com.example.demo.repository.modelo.to.AsientosVuelo;
import com.example.demo.repository.modelo.to.VueloDisponible;

@Service
public class VueloServiceImpl implements IVueloService {

	@Autowired
	private IVueloRepository iVueloRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public List<VueloDisponible> buscarVuelos(String origen, String destino, LocalDateTime fecha) {
		// TODO Auto-generated method stub
		List<Vuelo> vuelos = this.iVueloRepository.buscarVuelos(origen, destino, fecha);

		List<VueloDisponible> vuelosDisponibles = vuelos.stream().filter(v -> v.getEstado().equals("DIS")).map(v -> {
			VueloDisponible vd = new VueloDisponible();
			vd.setNumeroVuelo(v.getNumero());
			vd.setOrigen(v.getOrigen());
			vd.setDestino(v.getDestino());
			vd.setValorAsiento(v.getValorPorAsiento());
			vd.setNombreAvion(v.getAvion().getNombre());
			return vd;
		}).collect(Collectors.toList());

		return vuelosDisponibles;
	}

	@Override
	public AsientosVuelo contarAsientos(String numero) {
		// TODO Auto-generated method stub
		Vuelo v = this.iVueloRepository.buscarPorNumero(numero);

		AsientosVuelo av = new AsientosVuelo();

		if (v.getEstado().equals("DIS")) {
			av.setDisponibles(v.getAsientosDisponibles());
			av.setOcupados(v.getAvion().getCapacidadAsientos() - av.getDisponibles());
		}

		return av;
	}

	@Override
	public Vuelo buscarPorNumero(String numero) {
		// TODO Auto-generated method stub
		return this.iVueloRepository.buscarPorNumero(numero);
	}

}
