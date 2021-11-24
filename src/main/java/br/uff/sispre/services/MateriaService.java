package br.uff.sispre.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.repositories.MateriaRepository;

@Service
public class MateriaService {
  @Autowired
  private MateriaRepository repo;
}
