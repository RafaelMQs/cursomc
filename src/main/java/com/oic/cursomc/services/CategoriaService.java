package com.oic.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oic.cursomc.domain.Categoria;
import com.oic.cursomc.repositories.CategoriaRepository;
import com.oic.cursomc.services.exeptions.ObjectNotFoundExeption;

@Service	
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id); 
		return obj.orElseThrow( () -> new ObjectNotFoundExeption(
				"Objeto não encontrado! Id: " +id+ ", Tipo: " +Categoria.class.getName()));
	}
}
