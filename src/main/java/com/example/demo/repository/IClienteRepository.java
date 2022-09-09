package com.example.demo.repository;

import com.example.demo.repository.modelo.Cliente;

public interface IClienteRepository {

	public Cliente buscarPorCedula(String cedula);

}
