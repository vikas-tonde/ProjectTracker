package com.project.ProjectTracker.service;

import com.project.ProjectTracker.Dao.ClientRepository;
import com.project.ProjectTracker.entity.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

}
