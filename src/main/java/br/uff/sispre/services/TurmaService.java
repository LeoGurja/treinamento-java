package br.uff.sispre.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uff.sispre.repositories.TurmaRepository;

@Service
public class TurmaService {
  @Autowired
  private TurmaRepository repo;
}
