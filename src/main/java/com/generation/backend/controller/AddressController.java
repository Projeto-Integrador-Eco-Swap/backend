package com.generation.backend.controller;

import com.generation.backend.entity.Address;
import com.generation.backend.exception.InvalidAddressIdException;
import com.generation.backend.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador para gerenciamento de endereços.
 */
@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressController {

    /**
     * O serviço para gerenciamento de endereços.
     */
    private final AddressService addressService;

    /**
     * Cria um novo controlador para gerenciamento de endereços.
     *
     * @param addressService O serviço para gerenciamento de endereços.
     */
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Cria um novo endereço.
     *
     * @param address O endereço a ser criado.
     * @return ResponseEntity contendo o endereço criado e o status HTTP OK.
     */
    @PostMapping("/create")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address createdAddress = addressService.createAddress(address);
        return ResponseEntity.status(HttpStatus.OK).body(createdAddress);
    }

    /**
     * Cria vários endereços simultaneamente.
     *
     * @param addresses Uma coleção de endereços a serem criados.
     * @return ResponseEntity contendo a coleção de endereços criados e o status HTTP OK.
     */
    @PostMapping("/create-multiple")
    public ResponseEntity<Iterable<Address>> createMultipleAddresses(@RequestBody Iterable<Address> addresses) {
        Iterable<Address> createdAddresses = addressService.createMultipleAddresses(addresses);
        return ResponseEntity.status(HttpStatus.OK).body(createdAddresses);
    }

    /**
     * Obtém uma lista de todos os endereços cadastrados.
     *
     * @return ResponseEntity contendo a lista de endereços e o status HTTP OK.
     */
    @GetMapping("/all")
    public ResponseEntity<Iterable<Address>> getAllAddresses() {
        Iterable<Address> addresses = addressService.getAllAddresses();
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    /**
     * Obtém um endereço pelo seu ID.
     *
     * @param id O ID do endereço a ser obtido.
     * @return ResponseEntity contendo o endereço obtido e o status HTTP OK.
     * @throws InvalidAddressIdException Se o ID do endereço for inválido.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) throws InvalidAddressIdException {
        Address address = addressService.getAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    /**
     * Atualiza um endereço existente.
     *
     * @param address O endereço a ser atualizado.
     * @return ResponseEntity contendo o endereço atualizado e o status HTTP OK.
     * @throws InvalidAddressIdException Se o ID do endereço for inválido.
     */
    @PutMapping("/update")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) throws InvalidAddressIdException {
        Address updatedAddress = addressService.updateAddress(address);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAddress);
    }

    /**
     * Exclui um endereço pelo seu ID.
     *
     * @param id O ID do endereço a ser excluído.
     * @return ResponseEntity contendo um mapa com informações sobre o resultado da exclusão e o status HTTP OK.
     * @throws InvalidAddressIdException Se o ID do endereço for inválido.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteAddress(@PathVariable Long id) throws InvalidAddressIdException {
        Map<String, String> deletedAddress = addressService.deleteAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedAddress);
    }

    /**
     * Exclui todos os endereços cadastrados.
     *
     * @return ResponseEntity contendo um mapa com informações sobre o resultado da exclusão e o status HTTP OK.
     */
    @DeleteMapping("/delete-all")
    public ResponseEntity<Map<String, String>> deleteAllAddresses() {
        Map<String, String> deletedAddresses = addressService.deleteAllAddresses();
        return ResponseEntity.status(HttpStatus.OK).body(deletedAddresses);
    }

    /**
     * Obtém uma lista de endereços associados a um usuário específico.
     *
     * @param userId O ID do usuário para o qual se deseja obter os endereços.
     * @return ResponseEntity contendo a lista de endereços associados ao usuário e o status HTTP OK.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Iterable<Address>> getAllAddressesByUserId(@PathVariable Long userId) {
        Iterable<Address> addresses = addressService.getAllAddressesByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }
}
