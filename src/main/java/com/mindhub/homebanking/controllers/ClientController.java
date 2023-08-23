package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    //voy a recibir yvoy a responder
    // por ejemplo, petición de que se me envíes todos los clientes
    //para obtenerlos necesito si o si ClientRepository
    @Autowired
    private ClientRepository clientRepository;

    //Empiezo a crear mis procedimientos
    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        return clientRepository.findAll()
                .stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){

        return clientRepository.findById(id).map(client -> new ClientDTO(client)).orElse(null);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestParam String firstName
                                               , @RequestParam String lastName
                                               , @RequestParam String email
                                               , @RequestParam String password) {

            if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
                return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
            }

            if (clientRepository.findByEmail(email) != null) {
                return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
            }

            clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
            return new ResponseEntity<>("Client created", HttpStatus.CREATED);
    }

    @GetMapping("/clients/current")
    public ClientDTO getCurrentClient(Authentication authentication) {
        return new ClientDTO(clientRepository.findByEmail(authentication.getName()));
    }
}
















