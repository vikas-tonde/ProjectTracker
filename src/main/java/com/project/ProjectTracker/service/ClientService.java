package com.project.ProjectTracker.service;

import com.project.ProjectTracker.Dao.ClientRepository;
import com.project.ProjectTracker.entity.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public List<String> getClientNames() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(Client::getClientName)
                .collect(Collectors.toList());
    }

}
