package com.pelgray.controller;

import com.pelgray.domain.AddressEntity;
import com.pelgray.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private static final Logger LOG = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    /**
     * Добавление нового Address для заданного id Client
     */
    @PostMapping
    void addAddress(@RequestBody AddressEntity newAddress) {
        LOG.debug("addAddress: {}", newAddress.toString());
        addressService.add(newAddress);
    }

    /**
     * Удаление Address
     *
     * @param id идентификатор удаляемого {@link AddressEntity}
     */
    @DeleteMapping("/{id}")
    void removeAddress(@PathVariable Integer id) {
        LOG.debug("removeAddress: id={}", id);
        addressService.remove(id);
    }
}
