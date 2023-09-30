package com.generation.backend.repository;

import com.generation.backend.entity.User;
import com.generation.backend.entity.UserLogin;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Esta interface atua como um repositório de dados para a entidade User.
 * Fornece métodos de acesso a dados para realizar operações CRUD (Criar, Ler, Atualizar e Excluir)
 * em instâncias de User no banco de dados.
 *
 * @Repository Indica que esta interface é um componente de repositório gerenciado pelo Spring.
 * JpaRepository fornece métodos padrão para operações de banco de dados, como salvar, excluir e buscar.
 * Ele opera na entidade User e utiliza Long como o tipo de dado da chave primária.
 * @see User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Este método executa uma consulta JPQL para buscar um usuário com base no seu primeiro nome.
     *
     * <p>Esta consulta seleciona um usuário cujo primeiro nome corresponda exatamente ao nome fornecido como parâmetro.</p>
     * <p>
     *
     * @param name O primeiro nome a ser usado como critério de busca para encontrar o usuário.
     *             </p>
     *             <p>
     * @return Um objeto User que corresponde ao primeiro nome fornecido, ou null se nenhum usuário for encontrado.
     * </p>
     * <p>
     * @implNote Esta consulta utiliza uma consulta JPQL para buscar usuários.
     * A consulta procura por correspondências exatas no campo "firstName" do usuário.
     * </p>
     * <p>
     * @returnCode - 200 OK: A consulta é executada com sucesso e o usuário correspondente ao primeiro nome é retornado.<p>
     * <p> - 404 Not Found: Se nenhum usuário for encontrado com o primeiro nome fornecido.</p>
     * </p>
     * @see User
     */
    @Query("SELECT u FROM users AS u " +
            "WHERE u.firstName = :name")
    User findByFirstName(String name);

    /**
     * Este método executa uma consulta JPQL para buscar um usuário com base no seu sobrenome.
     *
     * <p>Esta consulta seleciona um usuário cujo sobrenome corresponda exatamente ao sobrenome fornecido como parâmetro.</p>
     * <p>
     *
     * @param lastName O sobrenome a ser usado como critério de busca para encontrar o usuário.
     *                 </p>
     *                 <p>
     * @return Um objeto User que corresponde ao sobrenome fornecido, ou null se nenhum usuário for encontrado.
     * </p>
     * <p>
     * @implNote Esta consulta utiliza uma consulta JPQL para buscar usuários.
     * A consulta procura por correspondências exatas no campo "lastName" do usuário.
     * </p>
     * <p>
     * @returnCode - 200 OK: A consulta é executada com sucesso e o usuário correspondente ao sobrenome é retornado.<p>
     * <p>   - 404 Not Found: Se nenhum usuário for encontrado com o sobrenome fornecido.</p>
     * </p>
     * @see User
     */
    @Query("SELECT u FROM users AS u " +
            "WHERE u.lastName = :lastName")
    User findUserByLastName(String lastName);

    /**
     * Este método executa uma consulta JPQL para buscar um usuário com base na sua data de nascimento.
     *
     * <p>Esta consulta seleciona um usuário cuja data de nascimento corresponda exatamente à data fornecida como parâmetro.</p>
     * <p>
     *
     * @param birthDate A data de nascimento a ser usada como critério de busca para encontrar o usuário.
     *                  <p>
     * @param birthDate A data de nascimento a ser usada como critério de busca para encontrar o usuário.
     *                  <p>
     * @return Um objeto User que corresponde à data de nascimento fornecida, ou null se nenhum usuário for encontrado.
     * <p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     *                             <p>
     * @implNote Esta consulta utiliza uma consulta JPQL para buscar usuários.
     * A consulta procura por correspondências exatas no campo "birthDate" do usuário.
     * <p>
     * @returnCode - 200 OK: A consulta é executada com sucesso e o usuário correspondente à data de nascimento é retornado.<p>
     * <p>- 404 Not Found: Se nenhum usuário for encontrado com a data de nascimento fornecida.<p>
     * @see User
     */
    @Query("SELECT u FROM users AS u " +
            "WHERE u.birthDate = :birthDate")
    User findByBirthDate(@Param("birthDate") LocalDate birthDate);

    /**
     * Este método executa uma consulta JPQL para buscar um usuário com base no seu endereço de e-mail.
     *
     * <p>Esta consulta seleciona um usuário cujo endereço de e-mail corresponda exatamente ao e-mail fornecido como parâmetro.</p>
     * <p>
     *
     * @param email O endereço de e-mail a ser usado como critério de busca para encontrar o usuário.
     *
     *              <p>
     * @return Um objeto User que corresponde ao endereço de e-mail fornecido, ou null se nenhum usuário for encontrado.
     * <p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     *                             <p>
     * @implNote Esta consulta utiliza uma consulta JPQL para buscar usuários.
     * A consulta procura por correspondências exatas no campo "email" do usuário.
     * <p>
     * @returnCode - 200 OK: A consulta é executada com sucesso e o usuário correspondente ao endereço de e-mail é retornado.<p>
     * <p>         - 404 Not Found: Se nenhum usuário for encontrado com o endereço de e-mail fornecido.
     * @see User
     */
    @Query("SELECT u FROM users AS u " +
            "WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u FROM users AS u " +
            "WHERE u.firstName = :userName")
    Optional<User> findByUserName(String userName);
}
