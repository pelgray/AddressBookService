package com.pelgray.controller;

import com.pelgray.domain.AddressEntity;
import com.pelgray.domain.ClientEntity;
import com.pelgray.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {
    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    /**
     * Просмотр списка всех Client
     *
     * @param query фильтр, необязательный параметр. Если указан, то возвращаемый список будет состоять
     *              из {@link ClientEntity}, в поле {@code name} которых присутствует указанное значение
     */
    @GetMapping("/clients")
    List<ClientEntity> getClients(@RequestParam(required = false) String query) {
        LOG.debug("getClients: query={}", query);
        return query == null ? clientService.findAll() : clientService.findAllBySubstringInName(query);
    }

    /**
     * Добавление нового Client
     */
    @PostMapping("/clients")
    void addClient(@RequestBody ClientEntity newClient) {
        LOG.debug("addClient: {}", newClient.toString());
        clientService.add(newClient);
    }

    /**
     * Удаление Client
     *
     * @param id идентификатор удаляемого {@link ClientEntity}
     */
    @DeleteMapping("/clients/{id}")
    void removeClient(@PathVariable Integer id) {
        LOG.debug("removeClient: id={}", id);
        clientService.remove(id);
    }

    /**
     * Получение списка Address для заданного id Client
     *
     * @param clientId идентификатор {@link ClientEntity}
     */
    @GetMapping("/clients/{clientId}/addresses")
    List<AddressEntity> getAddressesByClientId(@PathVariable Integer clientId) {
        LOG.debug("getAddressesByClientId: id={}", clientId);
        return clientService.findById(clientId).getAddresses();
    }
}
