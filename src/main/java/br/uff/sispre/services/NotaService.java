package br.uff.sispre.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.repositories.NotaRepository;

@Service
public class NotaService {
  @Autowired
  private NotaRepository repo;
}
