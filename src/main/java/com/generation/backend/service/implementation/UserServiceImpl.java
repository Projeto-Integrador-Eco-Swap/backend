package com.generation.backend.service.implementation;

import com.generation.backend.entity.User;
import com.generation.backend.entity.UserLogin;
import com.generation.backend.repository.UserRepository;
import com.generation.backend.security.JwtService;
import com.generation.backend.service.UserService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A implementação do serviço de usuário.
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * O repositório de usuários.
     */
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Cria um novo serviço de usuário.
     *
     * @param userRepository        O repositório de usuários.
     * @param jwtService
     * @param authenticationManager
     */
    @Contract(pure = true)
    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Cadastra um novo usuário com base nas informações fornecidas.
     *
     * @param user Um objeto Optional contendo as informações do usuário a ser cadastrado.
     * @return Um objeto Optional contendo os detalhes do usuário cadastrado, se o cadastro for bem-sucedido.
     * @throws ResponseStatusException Se o usuário já existir, uma exceção com código de status HTTP 400 (BAD_REQUEST) será lançada.
     */
    @Override
    public Optional<User> cadastrarUsuario(@NotNull Optional<UserLogin> user) {

        Optional<User> buscaUsuario = userRepository.findByUserName(user.get().getUser());
        if (buscaUsuario.isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

        User usuario = new User();
        usuario.setFirstName(user.get().getUser());
        usuario.setPassword(criptografarSenha(user.get().getPassword()));

        return Optional.of(userRepository.save(usuario));

    }

    /**
     * Atualiza as informações de um usuário com base nas informações fornecidas.
     *
     * @param user Um objeto Optional contendo as informações do usuário a ser atualizado.
     * @return Um objeto Optional contendo os detalhes do usuário atualizado, se a atualização for bem-sucedida.
     * @throws ResponseStatusException Se o usuário não existir, uma exceção com código de status HTTP 400 (BAD_REQUEST) será lançada.
     */
    @Override
    public Optional<User> atualizarUsuario(@NotNull Optional<UserLogin> user) {

        Optional<User> buscaUsuario = userRepository.findByUserName(user.get().getUser());
        if (buscaUsuario.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não existe!", null);

        User usuario = new User();
        usuario.setFirstName(user.get().getUser());
        usuario.setPassword(criptografarSenha(user.get().getPassword()));

        return Optional.of(userRepository.save(usuario));
    }

    /**
     * Autentica um usuário com base nas credenciais fornecidas.
     *
     * @param usuarioLogin Um objeto Optional contendo as credenciais do usuário para autenticação.
     * @return Um objeto Optional contendo os detalhes do usuário autenticado, se a autenticação for bem-sucedida.
     */
    @Override
    public Optional<UserLogin> autenticarUsuario(@NotNull Optional<UserLogin> usuarioLogin) {

        // Gera o Objeto de autenticação
        var credenciais = new UsernamePasswordAuthenticationToken(usuarioLogin.get().getUser(), usuarioLogin.get().getPassword());

        // Autentica o Usuario
        Authentication authentication = authenticationManager.authenticate(credenciais);

        // Se a autenticação foi efetuada com sucesso
        if (authentication.isAuthenticated()) {

            // Busca os dados do usuário
            Optional<User> usuario = userRepository.findByUserName(usuarioLogin.get().getUser());

            // Se o usuário foi encontrado
            if (usuario.isPresent()) {

                usuarioLogin.get().setId(usuario.get().getId());
                usuarioLogin.get().setFirstName(usuario.get().getFirstName());
                //Todo: criar
                //usuarioLogin.get().setFoto(usuario.get().getFoto());

                usuarioLogin.get().setToken(gerarToken(usuarioLogin.get().getUser()));
                usuarioLogin.get().setPassword("");

                // Retorna o Objeto preenchido
                return usuarioLogin;

            }
        }
        return Optional.empty();
    }

    /**
     * Criptografa a senha do usuário usando o algoritmo BCrypt.
     *
     * @param senha A senha a ser criptografada.
     * @return A senha criptografada.
     */
    private String criptografarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    /**
     * Gera um token de autenticação JWT (JSON Web Token) para o usuário especificado.
     *
     * @param usuario O nome de usuário para o qual o token JWT será gerado.
     * @return Uma string contendo o token JWT no formato "Bearer {token}".
     */
    private @NotNull String gerarToken(String usuario) {
        return "Bearer " + jwtService.generateToken(usuario);
    }

    /**
     * Obtém todos os usuários cadastrados no sistema.
     *
     * @return Uma lista de todos os usuários.
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Cria um novo usuário no sistema.
     *
     * @param user O usuário a ser criado.
     * @return O usuário criado.
     * @throws IllegalArgumentException Se o usuário já possui um ID.
     */
    @Override
    public User createUser(User user) {
        validateUserForCreation(user);

        if (user.getId() != null) {
            throw new IllegalArgumentException("O usuário a ser criado não deve ter um ID.");
        }
        return userRepository.saveAndFlush(user);
    }

    /**
     * Obtém um usuário pelo seu ID.
     *
     * @param id O ID do usuário a ser obtido.
     * @return O usuário com o ID especificado, ou nulo se não encontrado.
     */
    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    /**
     * Obtém um usuário pelo seu firstName.
     *
     * @param firstName O nome do usuário a ser obtido.
     * @return O usuário com o nome especificado, ou nulo se não encontrado.
     */
    @Override
    public User getUserByName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param user O usuário atualizado.
     * @return O usuário atualizado.
     * @throws IllegalArgumentException Se o usuário não possui um ID ou não for encontrado.
     */
    public User updateUser(@NotNull User user) {
        validateUserIdForUpdate(user);

        User existingUser = findUserById(user.getId());

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPicture(user.getPicture());

        return userRepository.saveAndFlush(existingUser);
    }

    /**
     * Atualiza a data de nascimento de um usuário existente.
     *
     * @param user O usuário com a nova data de nascimento.
     * @return O usuário atualizado com a nova data de nascimento.
     * @throws IllegalArgumentException Se o usuário não tiver um ID válido ou não for encontrado.
     */
    @Override
    public User updateUserBirthDate(@NotNull User user) {
        validateUserIdForUpdate(user);
        User existingUser = findUserById(user.getId());
        existingUser.setBirthDate(user.getBirthDate());
        return userRepository.saveAndFlush(existingUser);
    }

    /**
     * Valida se o usuário possui um ID válido para atualização.
     *
     * @param user O usuário a ser validado.
     * @throws IllegalArgumentException Se o usuário não possuir um ID válido.
     */
    private void validateUserIdForUpdate(@NotNull User user) {
        if (user.getId() == null || user.getId() <= 0) {
            throw new IllegalArgumentException("O usuário a ser atualizado deve ter um ID válido.");
        }
    }

    /**
     * Exclui um usuário pelo seu ID.
     *
     * @param id O ID do usuário a ser excluído.
     * @throws IllegalArgumentException Se o usuário não for encontrado.
     */
    @Override
    public void deleteUserById(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
        createSuccessResponse();
    }

    /**
     * Exclui um usuário pelo seu nome.
     *
     * @param firstName O nome do usuário a ser excluído.
     * @throws IllegalArgumentException Se o usuário não for encontrado.
     */
    @Override
    public void deleteUserByName(String firstName) {
        User user = findUserByName(firstName);
        userRepository.delete(user);
        createSuccessResponse();
    }

    /**
     * Atualiza a senha de um usuário existente.
     * <p>
     * Este método permite atualizar a senha de um usuário existente com base nas informações
     * fornecidas no objeto User. O usuário atualizado é retornado como resultado.
     *
     * @param user O objeto User com a senha atualizada.
     * @return O usuário atualizado.
     * @throws IllegalArgumentException Se o usuário não for encontrado com o ID especificado.
     */
    @Override
    public User updateUserPassword(@NotNull User user) {
        User existingUser = findUserById(user.getId());
        existingUser.setPassword(user.getPassword());
        return userRepository.saveAndFlush(existingUser);
    }

    /**
     * Atualiza o endereço de e-mail de um usuário existente.
     * <p>
     * Este método permite atualizar o endereço de e-mail de um usuário existente com base
     * nas informações fornecidas no objeto User. O usuário atualizado é retornado como resultado.
     *
     * @param user O objeto User com o endereço de e-mail atualizado.
     * @return O usuário atualizado.
     * @throws IllegalArgumentException Se o usuário não for encontrado com o ID especificado.
     */
    @Override
    public User updateUserEmail(@NotNull User user) {
        User existingUser = findUserById(user.getId());
        existingUser.setEmail(user.getEmail());
        return userRepository.saveAndFlush(existingUser);
    }

    /**
     * Obtém um usuário pela sua data de nascimento.
     *
     * @param birthDate A data de nascimento do usuário a ser obtido.
     * @return O usuário com a data de nascimento especificada, ou null se não encontrado.
     */
    @Override
    public User getUserByBirthDay(String birthDate) {
        return userRepository.findByBirthDate(LocalDate.parse(birthDate));
    }

    /**
     * Exclui um usuário pela sua data de nascimento.
     *
     * @param birthDate A data de nascimento do usuário a ser excluído.
     * @return Um ResponseEntity vazio (sem corpo) indicando sucesso.
     */
    public void deleteUserByBirthDay(String birthDate) {
        User userToDelete = userRepository.findByBirthDate(LocalDate.parse(birthDate));

        if (userToDelete != null) {
            userRepository.delete(userToDelete);
        } else {
            throw new IllegalArgumentException("Usuário com a data de nascimento especificada não encontrado.");
        }
    }

    /**
     * Exclui todos os usuários.
     * <p>
     * Este método exclui todos os registros de usuários do repositório, resultando
     * na exclusão de todos os usuários da aplicação.
     */
    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }


    /**
     * Procura um usuário pelo ID e lança uma exceção se não for encontrado.
     *
     * @param id O ID do usuário a ser encontrado.
     * @return O usuário encontrado.
     * @throws IllegalArgumentException Se o usuário não for encontrado.
     */
    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuário não encontrado com o ID " + id + ". " +
                                "Não foi possível excluí-lo."));
    }

    /**
     * Procura um usuário pelo nome e lança uma exceção se não for encontrado.
     *
     * @param firstName O nome do usuário a ser encontrado.
     * @return O usuário encontrado.
     * @throws IllegalArgumentException Se o usuário não for encontrado.
     */
    private User findUserByName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    /**
     * Cria um mapa de resposta com uma mensagem de sucesso.
     *
     * @return Um mapa de resposta com a mensagem.
     */
    private @NotNull Map<String, String> createSuccessResponse() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário excluído com sucesso.");
        return response;
    }

    /**
     * Valida se os dados de um usuário são válidos para a criação.
     *
     * @param user O usuário a ser validado.
     * @throws IllegalArgumentException Se algum dado do usuário for inválido.
     */
    @Contract("null -> fail")
    private void validateUserForCreation(User user) {
        if (user == null) {
            throw new IllegalArgumentException("O usuário a ser criado não deve ser nulo.");
        }

        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode estar em branco.");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email do usuário não pode estar em branco.");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("A senha do usuário não pode estar em branco.");
        }
    }
}
