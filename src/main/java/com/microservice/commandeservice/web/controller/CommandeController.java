package com.microservice.commandeservice.web.controller;

import com.microservice.commandeservice.dao.CommandeDAO;
import com.microservice.commandeservice.model.Commande;
import com.microservice.commandeservice.web.exceptions.CommandeIntrouvableException;
import com.microservice.commandeservice.web.exceptions.PrixCommandeIncorrectException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

@RestController
@Api(description = "API pour les opérations CRUD sur les commandes")
public class CommandeController {

	@Autowired
	private CommandeDAO commandeDAO;
  private static final Logger logger = LoggerFactory.getLogger (CommandeController.class);
  @Autowired
  private HttpServletRequest requestContext ;

	@RequestMapping(value = "/Commandes", method = RequestMethod.GET)
	public List<Commande> listeCommandes() {
	  logger.info("Début d'appel au service Commande pour la requête : " + requestContext.getHeader("req-id"));
	  List<Commande> commandes = commandeDAO.findAll();
	  return commandes;
	}

	@ApiOperation(value = "Récupère une commande grâce à son ID")
	@RequestMapping(value = "/Commandes/{id}", method = RequestMethod.GET)
	public Commande afficherUneCommande(@PathVariable int id) {
	  logger.info("Début d'appel au service Commandes pour la requête : " + requestContext.getHeader("req-id"));
    Commande commandes = commandeDAO.findById(id);
			if (commandes == null)
				throw new CommandeIntrouvableException("Commande introuvable");
		 return commandes;

	}

	@DeleteMapping (value = "/Commandes/{id}")
	public void supprimerCommande(@PathVariable int id) {
    commandeDAO.deleteById(id);
	}

	@PutMapping (value = "/Commandes")
	public void updateCommande(@RequestBody Commande commande) {
    commandeDAO.save(commande);
	}

	@PostMapping(value = "/Commandes")
	public ResponseEntity<Void> ajouterComande(@RequestBody Commande commande) {
		if (commande.getPrix() == 0)
			throw new PrixCommandeIncorrectException("Commande introuvable");
    Commande commandeAdded = commandeDAO.save(commande);
		 if (commandeAdded == null)
			 return ResponseEntity.noContent().build();
				 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						 .path("/{id}").buildAndExpand(commandeAdded.getId()).toUri();
		 return ResponseEntity.created(location).build();
	 }

}
