package com.pelgray;

import com.pelgray.domain.AddressEntity;
import com.pelgray.domain.ClientEntity;
import com.pelgray.repository.AddressRepository;
import com.pelgray.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ServiceRunner {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceRunner.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceRunner.class, args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner initDatabase(ClientRepository clientRepository, AddressRepository addressRepository) {
        return args -> {
            LOG.info("Preloading data");
            List<String> clientNames = Arrays.asList("client0", "client1", "client2", "client3",
                    "abc", "abcd", "abcda", "abcdac");
            List<ClientEntity> clients = clientNames.stream().map(ClientEntity::new).collect(Collectors.toList());

            List<String> addressNames = Arrays.asList("client0_adr0", "client0_adr1", "client0_adr2");
            clients.get(0).setAddresses(addressNames.stream().map(AddressEntity::new).collect(Collectors.toList()));

            addressNames = Arrays.asList("client1_adr0", "client1_adr1");
            clients.get(1).setAddresses(addressNames.stream().map(AddressEntity::new).collect(Collectors.toList()));

            clients.get(2).setAddresses(Collections.singletonList(new AddressEntity("client2_adr0")));

            clients.get(5).setAddresses(Collections.singletonList(new AddressEntity("abcd_adr0")));

            clientRepository.saveAll(clients);
            LOG.info("End of preloading data");
        };
    }
}
