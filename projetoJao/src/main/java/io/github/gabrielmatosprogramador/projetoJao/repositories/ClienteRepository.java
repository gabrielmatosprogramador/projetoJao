package io.github.gabrielmatosprogramador.projetoJao.repositories;

import io.github.gabrielmatosprogramador.projetoJao.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
