package com.pelgray.service;

import com.pelgray.domain.ClientEntity;
import com.pelgray.exceptions.ClientNotFoundException;
import com.pelgray.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientService {
    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);
    @Autowired
    private ClientRepository repository;

    public List<ClientEntity> findAll() {
        LOG.debug("findAll()");
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<ClientEntity> findAllBySubstringInName(String query) {
        LOG.debug("findAllBySubstringInName()");
        return repository.findBySubstringInName(query);
    }

    public ClientEntity find(Integer clientId) {
        return repository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
    }

    public boolean exists(Integer clientId) {
        return repository.existsById(clientId);
    }

    public void add(ClientEntity newClient) {
        repository.save(newClient);
    }

    public void remove(Integer clientId) {
        if (repository.existsById(clientId)) {
            repository.deleteById(clientId);
        } else {
            throw new ClientNotFoundException(clientId);
        }
    }
}
