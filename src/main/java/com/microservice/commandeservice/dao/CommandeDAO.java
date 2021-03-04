package com.microservice.commandeservice.dao;

import com.microservice.commandeservice.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeDAO extends JpaRepository<Commande, Integer>  {
	public List<Commande> findAll();
	public Commande findById(int id);
	public Commande save(Commande Commande);
}
