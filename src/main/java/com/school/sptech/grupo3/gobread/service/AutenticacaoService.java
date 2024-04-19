package com.school.sptech.grupo3.gobread.service;

import com.school.sptech.grupo3.gobread.entity.Cliente;
import com.school.sptech.grupo3.gobread.entity.Comercio;
import com.school.sptech.grupo3.gobread.repository.ClienteRepository;
import com.school.sptech.grupo3.gobread.repository.ComercioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private ComercioRepository comercioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<Cliente> clienteOpt = clienteRepository.findByEmail(username);
    Optional<Comercio> comercioOpt = comercioRepository.findByEmail(username);

    if (clienteOpt.isEmpty() && comercioOpt.isEmpty()) {
      throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));
    } else if (clienteOpt.isPresent()){
      return clienteOpt.get();
    }
    return comercioOpt.get();
  }

}
