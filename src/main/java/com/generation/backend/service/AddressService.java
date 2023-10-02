package com.generation.backend.service;

import com.generation.backend.entity.Address;
import com.generation.backend.exception.InvalidAddressIdException;

import java.util.List;
import java.util.Map;

/**
 * Interface que define os serviços relacionados a endereços.
 */
public interface AddressService {

    /**
     * Cria um novo endereço.
     *
     * @param address O endereço a ser criado.
     * @return O endereço criado.
     */
    Address createAddress(Address address);

    /**
     * Cria vários endereços simultaneamente.
     *
     * @param addresses Uma coleção de endereços a serem criados.
     * @return Uma coleção de endereços criados.
     */
    Iterable<Address> createMultipleAddresses(Iterable<Address> addresses);

    /**
     * Obtém um endereço pelo seu ID.
     *
     * @param id O ID do endereço a ser obtido.
     * @return O endereço correspondente ao ID especificado.
     */
    Address getAddressById(Long id) throws InvalidAddressIdException;

    /**
     * Obtém uma lista de todos os endereços cadastrados.
     *
     * @return Uma lista de todos os endereços.
     */
    List<Address> getAllAddresses();

    /**
     * Obtém uma lista de todos os endereços associados a um usuário específico.
     *
     * @param userId O ID do usuário para o qual se deseja obter os endereços.
     * @return Uma lista de endereços associados ao usuário especificado.
     */
    List<Address> getAllAddressesByUserId(Long userId);

    /**
     * Atualiza um endereço existente.
     *
     * @param address O endereço a ser atualizado.
     * @return O endereço atualizado.
     */
    Address updateAddress(Address address) throws InvalidAddressIdException;

    /**
     * Exclui um endereço pelo seu ID.
     *
     * @param id O ID do endereço a ser excluído.
     * @return Um mapa com informações sobre o resultado da exclusão.
     */
    Map<String, String> deleteAddressById(Long id);

    /**
     * Exclui todos os endereços cadastrados.
     *
     * @return Um mapa com informações sobre o resultado da exclusão.
     */
    Map<String, String> deleteAllAddresses();
}