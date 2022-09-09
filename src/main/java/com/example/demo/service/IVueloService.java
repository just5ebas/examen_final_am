package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.repository.modelo.Vuelo;
import com.example.demo.repository.modelo.to.AsientosVuelo;
import com.example.demo.repository.modelo.to.VueloDisponible;

public interface IVueloService {

	public List<VueloDisponible> buscarVuelos(String origen, String destino, LocalDateTime fecha);

	public AsientosVuelo contarAsientos(String numero);

	public Vuelo buscarPorNumero(String numero);

}
