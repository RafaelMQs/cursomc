package com.oic.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.oic.cursomc.domain.Categoria;
import com.oic.cursomc.repositories.CategoriaRepository;
import com.oic.cursomc.services.exeptions.DataIntegrityExeption;
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
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void deleteById(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityExeption	("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
}
