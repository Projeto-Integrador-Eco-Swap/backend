package com.generation.backend.service.implementation;

import com.generation.backend.entity.Address;
import com.generation.backend.exception.InvalidAddressIdException;
import com.generation.backend.repository.AddressRepository;
import com.generation.backend.repository.ProductCategoryRepository;
import com.generation.backend.service.AddressService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementação do serviço de endereços.
 */
@Service
public class AddressServiceImpl implements AddressService {
    /**
     * O repositório de endereços.
     */
    private final AddressRepository addressRepository;
    private final ProductCategoryRepository productCategoryRepository;

    /**
     * Cria uma nova instância do serviço de endereços.
     *
     * @param addressRepository O repositório de endereços.
     */
    public AddressServiceImpl(AddressRepository addressRepository,
                              ProductCategoryRepository productCategoryRepository) {
        this.addressRepository = addressRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    /**
     * Cria um novo endereço.
     *
     * @param address O endereço a ser criado.
     * @return O endereço criado.
     */
    @Override
    public Address createAddress(@NotNull Address address) {

        if (address.getId() != null) {
            throw new IllegalArgumentException("Address ID must be null");
        }

        return addressRepository.saveAndFlush(address);
    }

    /**
     * Cria vários endereços simultaneamente.
     *
     * @param addresses Uma coleção de endereços a serem criados.
     * @return Uma coleção de endereços criados.
     */
    @Override
    public Iterable<Address> createMultipleAddresses(Iterable<Address> addresses) {
        return addressRepository.saveAll(addresses);
    }

    /**
     * Obtém um endereço pelo seu ID.
     *
     * @param id O ID do endereço a ser obtido.
     * @return O endereço correspondente ao ID especificado.
     */
    @Override
    public Address getAddressById(Long id) throws InvalidAddressIdException {
        Optional<Address> address = addressRepository.findById(id);
        return address
                .orElseThrow(() -> new InvalidAddressIdException("Invalid address ID: " + id));
    }

    /**
     * Obtém uma lista de todos os endereços cadastrados.
     *
     * @return Uma lista de todos os endereços.
     */
    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    /**
     * Obtém uma lista de todos os endereços associados a um usuário específico.
     *
     * @param userId O ID do usuário para o qual se deseja obter os endereços.
     * @return Uma lista de endereços associados ao usuário especificado.
     */
    @Override
    public List<Address> getAllAddressesByUserId(Long userId) {
        return null;
    }

    /**
     * Atualiza um endereço existente.
     *
     * @param address O endereço a ser atualizado.
     * @return O endereço atualizado.
     */
    @Override
    public Address updateAddress(@NotNull Address address) throws InvalidAddressIdException {
        if (address.getId() == null) {
            throw new IllegalArgumentException("Address ID cannot be null");
        }

        Address existingAddress = addressRepository.findById(address.getId())
                .orElseThrow(() -> new InvalidAddressIdException("Invalid address ID: " + address.getId()));

        existingAddress.setCep(address.getCep());
        existingAddress.setLogradouro(address.getLogradouro());
        existingAddress.setBairro(address.getBairro());
        existingAddress.setLocalidade(address.getLocalidade());
        existingAddress.setUf(address.getUf());
        existingAddress.setIbge(address.getIbge());
        existingAddress.setGia(address.getGia());
        existingAddress.setDdd(address.getDdd());
        existingAddress.setSiafi(address.getSiafi());
        existingAddress.setComplemento(address.getComplemento());

        return addressRepository.saveAndFlush(existingAddress);
    }

    /**
     * Exclui um endereço pelo seu ID.
     *
     * @param id O ID do endereço a ser excluído.
     * @return Um mapa com informações sobre o resultado da exclusão.
     */
    @Override
    public Map<String, String> deleteAddressById(Long id) {
        addressRepository.deleteById(id);
        return Map.of("message", "Address deleted successfully");
    }

    /**
     * Exclui todos os endereços cadastrados.
     *
     * @return Um mapa com informações sobre o resultado da exclusão.
     */
    @Override
    public Map<String, String> deleteAllAddresses() {
        productCategoryRepository.deleteAll();
        return Map.of("message", "All addresses deleted successfully");
    }
}