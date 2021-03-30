package com.jefferson.restapijavaalgaworks.api.controller;

import com.jefferson.restapijavaalgaworks.domain.model.Client;
import com.jefferson.restapijavaalgaworks.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> index() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> show(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent())
            return ResponseEntity.ok(client.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@Valid @RequestBody Client client){
        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@Valid @PathVariable Long id, @RequestBody Client client) {
        if (!clientRepository.existsById(id))
            return ResponseEntity.notFound().build();


        client.setId(id);
        client = clientRepository.save(client);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id) {
        if (!clientRepository.existsById(id))
            return ResponseEntity.notFound().build();

        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
