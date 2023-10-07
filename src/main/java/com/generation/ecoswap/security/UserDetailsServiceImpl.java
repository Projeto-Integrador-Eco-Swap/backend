package com.generation.ecoswap.security;

import java.util.List;
import java.util.Optional;

import com.generation.ecoswap.entity.Usuario;
import com.generation.ecoswap.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * A classe {@code UserDetailsServiceImpl} implementa a interface {@code UserDetailsService} do Spring Security
 * e é responsável por carregar os detalhes de um usuário com base no nome de usuário fornecido durante a autenticação.
 *
 * Essa classe atua como um serviço que busca um usuário no repositório de usuários com base no nome de usuário
 * fornecido e, se encontrado, cria um objeto {@code UserDetailsImpl} contendo as informações do usuário para serem usadas
 * pelo Spring Security durante a autenticação e autorização.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carrega os detalhes do usuário com base no nome de usuário fornecido.
     *
     * @param userName O nome de usuário fornecido durante a autenticação
     * @return Um objeto {@code UserDetails} que encapsula os detalhes do usuário encontrado
     * @throws UsernameNotFoundException Se o nome de usuário não for encontrado no repositório de usuários
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);

        if (usuario.isPresent())
            return new UserDetailsImpl(usuario.get());
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
}