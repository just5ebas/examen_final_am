package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.repository.modelo.Vuelo;
import com.example.demo.repository.modelo.to.VueloDisponible;

public interface IVueloRepository {

	public List<Vuelo> buscarVuelos(String origen, String destino, LocalDateTime fecha);

	public Vuelo buscarPorNumero(String numero);

}
