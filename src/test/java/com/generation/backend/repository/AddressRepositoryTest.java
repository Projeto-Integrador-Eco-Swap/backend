package com.generation.backend.repository;

import com.generation.backend.entity.Address;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Transactional
    void saveMethod() {
        Address address = new Address();
        address.setCep("13082-205");
        address.setLogradouro("Rua Orlando de Oliveira");
        address.setBairro("Jardim São Marcos");
        address.setLocalidade("Campinas");
        address.setUf("SP");
        address.setIbge("3509502");
        address.setGia("2446");
        address.setDdd("19");
        address.setSiafi("6291");
        address.setComplemento("Casa após a lombada");

        addressRepository.save(address);

        System.out.println(address);
    }

    @Test
    @Transactional
    void saveAllMethod() {
        Address address1 = new Address();
        Address address2 = new Address();
        Address address3 = new Address();

        address1.setCep("13082-206");
        address1.setLogradouro("Rua Orlando de Oliveira");
        address1.setBairro("Jardim São Marcos");
        address1.setLocalidade("Campinas");
        address1.setUf("SP");
        address1.setIbge("3509502");
        address1.setGia("2446");
        address1.setDdd("19");
        address1.setSiafi("6291");
        address1.setComplemento("Casa após a lombada");

        address2.setCep("13082-207");
        address2.setLogradouro("Rua Orlando de Oliveira");
        address2.setBairro("Jardim São Marcos");
        address2.setLocalidade("Campinas");
        address2.setUf("SP");
        address2.setIbge("3509502");
        address2.setGia("2446");
        address2.setDdd("19");
        address2.setSiafi("6291");
        address2.setComplemento("Casa após a lombada");

        address3.setCep("13082-208");
        address3.setLogradouro("Rua Orlando de Oliveira");
        address3.setBairro("Jardim São Marcos");
        address3.setLocalidade("Campinas");
        address3.setUf("SP");
        address3.setIbge("3509502");
        address3.setGia("2446");
        address3.setDdd("19");
        address3.setSiafi("6291");
        address3.setComplemento("Casa após a lombada");

        addressRepository.saveAll(List.of(address1, address2, address3));

        System.out.println(address1);
        System.out.println(address2);
        System.out.println(address3);
    }

    @Test
    @Transactional
    void updateMethod() {
        Address address = addressRepository.findById(1L).orElse(null);

        assert address != null;
        address.setCep("13082-205");
        address.setLogradouro("Rua Orlando de Oliveira");
        address.setBairro("Jardim São Marcos");
        address.setLocalidade("Campinas");
        address.setUf("SP");
        address.setIbge("3509502");
        address.setGia("2446");
        address.setDdd("19");
        address.setSiafi("6291");
        address.setComplemento("Casa após a lombada");

        addressRepository.save(address);

        System.out.println(address);
    }

    @Test
    void findAllMethod() {
        List<Address> addressList = addressRepository.findAll();

        System.out.println("[");
        boolean isFirst = true;
        for (Address address : addressList) {
            if (!isFirst) {
                System.out.print(",");
            } else {
                isFirst = false;
            }
            System.out.println(address);
        }
        System.out.println("]");
    }

    @Test
    void findByIdMethod() {
        Address address = addressRepository.findById(1L).orElse(null);

        System.out.println(address);
    }

    @Test
    void findByCepMethod() {
        List<Address> addressList = addressRepository.findByCep("13082-205");

        System.out.println("[");
        boolean isFirst = true;
        for (Address address : addressList) {
            if (!isFirst) {
                System.out.print(",");
            } else {
                isFirst = false;
            }
            System.out.println(address);
        }
    }

    @Test
    void findByLogradouroMethod() {
        List<Address> addressList = addressRepository.findByLogradouro("Rua Orlando de Oliveira");

        System.out.println("[");
        boolean isFirst = true;
        for (Address address : addressList) {
            if (!isFirst) {
                System.out.print(",");
            } else {
                isFirst = false;
            }
            System.out.println(address);
        }

    }

    @Test
    void findByBairroMethod() {

        List<Address> addressList = addressRepository.findByBairro("Jardim São Marcos");

        System.out.println("[");
        boolean isFirst = true;
        for (Address address : addressList) {
            if (!isFirst) {
                System.out.print(",");
            } else {
                isFirst = false;
            }
            System.out.println(address);
        }

    }

    @Test
    void findByLocalidadeMethod() {

        List<Address> addressList = addressRepository.findByLocalidade("Campinas");

        System.out.println("[");
        boolean isFirst = true;
        for (Address address : addressList) {
            if (!isFirst) {
                System.out.print(",");
            } else {
                isFirst = false;
            }
            System.out.println(address);
        }

    }

    @Test
    void findByUfMethod() {

        List<Address> addressList = addressRepository.findByUf("SP");

        System.out.println("[");
        boolean isFirst = true;
        for (Address address : addressList) {
            if (!isFirst) {
                System.out.print(",");
            } else {
                isFirst = false;
            }
            System.out.println(address);
        }
    }

    @Test
    void countMethod() {
        System.out.println(addressRepository.count());
    }

    @Test
    void existsByIdMethod() {
        System.out.println(addressRepository.existsById(1L));
    }

    @Test
    @Transactional
    void deleteByIdMethod() {
        try {
            addressRepository.deleteById(1L);
            System.out.println("Deletado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao deletar!");
        }
    }

    @Test
    @Transactional
    void deleteAllMethod() {
        addressRepository.deleteAll();
    }
}
