package com.project.ProjectTracker.service;

import com.project.ProjectTracker.Dao.ClientRepository;
import com.project.ProjectTracker.entity.Client;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository clientRepository;

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClientPage(int page) {
        Pageable pageable = PageRequest.of((page - 1), 6);
        Page<Client> clientPage = clientRepository.findAll(pageable);
        return clientPage.stream().toList();
    }

    public List<String> getClientNames() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(Client::getClientName)
                .collect(Collectors.toList());
    }

    public List<Client> getClientSearch(String clientName) {
        Optional<List<Client>> clients = clientRepository.findAllByClientNameStartingWith(clientName);
        return clients.map(ArrayList::new).orElse(null);
    }

    public long getCount() {
        return clientRepository.count();
    }

    public long getCountByClientName(String clientName) {
        return clientRepository.countByClientNameStartingWith(clientName);
    }
}
