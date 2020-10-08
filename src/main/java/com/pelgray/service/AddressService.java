package com.pelgray.service;

import com.pelgray.domain.AddressEntity;
import com.pelgray.exceptions.AddressNotFoundException;
import com.pelgray.exceptions.ClientNotFoundException;
import com.pelgray.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientService clientService;

    public void add(AddressEntity newAddress) {
        Integer clientId = newAddress.getClient().getId();
        if (!clientService.existsById(clientId)) {
            throw new ClientNotFoundException(clientId);
        }
        addressRepository.save(newAddress);
    }

    public void remove(Integer id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        } else {
            throw new AddressNotFoundException(id);
        }
    }
}
