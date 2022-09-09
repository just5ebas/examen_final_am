package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Avion;
import com.example.demo.repository.modelo.Vuelo;

@Repository
@Transactional
public class VueloRepositoryImpl implements IVueloRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vuelo> buscarVuelos(String origen, String destino, LocalDateTime fecha) {
		// TODO Auto-generated method stub
		TypedQuery<Vuelo> myQuery = this.entityManager.createNamedQuery("Vuelo.buscarVuelosPorDatos", Vuelo.class);
		myQuery.setParameter("origen", origen);
		myQuery.setParameter("destino", destino);
		myQuery.setParameter("fecha", fecha);
		List<Vuelo> vuelos = myQuery.getResultList();

		vuelos.forEach(v -> v.getAvion().getNombre());

		return vuelos;
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vuelo buscarPorNumero(String numero) {
		// TODO Auto-generated method stub
		TypedQuery<Vuelo> myQuery = this.entityManager.createQuery("SELECT v FROM Vuelo v WHERE v.numero=:numero",
				Vuelo.class);
		myQuery.setParameter("numero", numero);
		Vuelo v = myQuery.getSingleResult();
		v.getAvion().getCapacidadAsientos();
		return v;
	}

}
