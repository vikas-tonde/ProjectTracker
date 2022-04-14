package com.project.ProjectTracker.controller;


import com.project.ProjectTracker.entity.Client;
import com.project.ProjectTracker.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController {

    private ClientService clientService;

    @PostMapping("/client/add")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }
}
