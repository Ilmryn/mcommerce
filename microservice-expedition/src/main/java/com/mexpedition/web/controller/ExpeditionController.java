package com.mexpedition.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mexpedition.dao.ExpeditionDao;
import com.mexpedition.model.Expedition;
import com.mexpedition.web.exceptions.ExpeditionNotFoundException;
import com.mexpedition.web.exceptions.ImpossibleAjouterExpeditionException;

@RestController
public class ExpeditionController {

	@Autowired
	ExpeditionDao expeditionDao;

	@PostMapping("/expeditions")
	public ResponseEntity<Expedition> addExpedition(@RequestBody Expedition expedition) {

		Expedition newExpedition = expeditionDao.save(expedition);

		if (newExpedition == null)
			throw new ImpossibleAjouterExpeditionException("Impossible d'ajouter cette expedition");

		return new ResponseEntity<Expedition>(newExpedition, HttpStatus.CREATED);

	}

	@GetMapping(value = "/expeditions/{id}")
	public Optional<Expedition> recupererUneExpedition(@PathVariable int id) {

		Optional<Expedition> expedition = expeditionDao.findById(id);

		if (!expedition.isPresent())
			throw new ExpeditionNotFoundException("Cette expedition n'existe pas");

		return expedition;
	}

	@PutMapping(value = "/expeditions")
	public void updateExpedition(@RequestBody Expedition expedition) {

		expeditionDao.save(expedition);
	}

}
