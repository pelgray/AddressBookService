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

    public ClientEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    public void add(ClientEntity newClient) {
        repository.save(newClient);
    }

    public void remove(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ClientNotFoundException(id);
        }
    }
}
