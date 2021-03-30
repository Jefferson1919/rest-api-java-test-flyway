package com.jefferson.restapijavaalgaworks.domain.repository;

import com.jefferson.restapijavaalgaworks.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
