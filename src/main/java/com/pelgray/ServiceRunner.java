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

@SpringBootApplication
public class ServiceRunner {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceRunner.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceRunner.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(ClientRepository clientRepository, AddressRepository addressRepository) {
        return args -> {
            LOG.info("Preloading data");
            ClientEntity client = clientRepository.save(new ClientEntity("client0"));
            LOG.info(client.toString());
            AddressEntity address = addressRepository.save(new AddressEntity("client0_adr0", client));
            LOG.info(address.toString());
            address = addressRepository.save(new AddressEntity("client0_adr1", client));
            LOG.info(address.toString());
            address = addressRepository.save(new AddressEntity("client0_adr2", client));
            LOG.info(address.toString());

            client = clientRepository.save(new ClientEntity("client1"));
            LOG.info(client.toString());
            address = addressRepository.save(new AddressEntity("client1_adr0", client));
            LOG.info(address.toString());
            address = addressRepository.save(new AddressEntity("client1_adr1", client));
            LOG.info(address.toString());

            client = clientRepository.save(new ClientEntity("client2"));
            LOG.info(client.toString());
            address = addressRepository.save(new AddressEntity("client2_adr0", client));
            LOG.info(address.toString());

            LOG.info(clientRepository.save(new ClientEntity("client3")).toString());
            LOG.info(clientRepository.save(new ClientEntity("abc")).toString());

            client = clientRepository.save(new ClientEntity("abcd"));
            LOG.info(client.toString());
            address = addressRepository.save(new AddressEntity("abcd_adr0", client));
            LOG.info(address.toString());

            LOG.info(clientRepository.save(new ClientEntity("abcda")).toString());
            LOG.info(clientRepository.save(new ClientEntity("abcdac")).toString());
            LOG.info("End of preloading data");
        };
    }
}
