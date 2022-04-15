package com.project.ProjectTracker.controller;


import com.project.ProjectTracker.entity.Client;
import com.project.ProjectTracker.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ClientController {

    private ClientService clientService;

    @PostMapping("/client/add")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @GetMapping("/clients/page/{page}")
    public List<Client> getALlClientPage(@PathVariable int page)
    {
        return clientService.getAllClientPage(page);
    }


    @GetMapping("/client/names")
    public List<String> getClientNames() {
        return clientService.getClientNames();
    }

    @GetMapping("/clients/search/{clientName}")
    public List<Client> getUserSearch(@PathVariable String clientName) {
        return clientService.getClientSearch(clientName);
    }

    @GetMapping(value = {"/clients/count/{clientName}", "/clients/count"})
    public long getCount(@PathVariable(name = "clientName", required = false) String clientName) {
        System.out.println("title = " + clientName);
        if (clientName != null && !clientName.equals(""))
            return clientService.getCountByClientName(clientName);
        return clientService.getCount();
    }

}
