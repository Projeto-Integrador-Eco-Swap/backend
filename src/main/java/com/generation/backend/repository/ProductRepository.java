package com.generation.backend.repository;

import com.generation.backend.entity.ProductCategory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.generation.backend.entity.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Esta interface atua como um repositório de dados para a entidade Product.
 * Fornece métodos de acesso a dados para realizar operações CRUD (Criar, Ler, Atualizar e Excluir)
 * em instâncias de Product no banco de dados.
 *
 * @Repository Indica que esta interface é um componente de repositório gerenciado pelo Spring.
 * JpaRepository fornece métodos padrão para operações de banco de dados, como salvar, excluir e buscar.
 * Ele opera na entidade Product e utiliza Long como o tipo de dado da chave primária.
 * @see Product
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Este método executa uma consulta JPQL para buscar um produto pelo seu nome.
     *
     * <p>Esta consulta seleciona um único produto cujo nome corresponda exatamente ao nome fornecido como parâmetro.</p>
     *<p>
     * @param name O nome do produto a ser usado como critério de busca para encontrar o produto.
     * <p>
     * @return Um objeto Product que corresponde ao nome fornecido, ou null se nenhum produto for encontrado.
     * <p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     * <p>
     * @implNote Esta consulta utiliza uma consulta JPQL para buscar produtos pelo nome.
     * A consulta verifica se o nome de cada produto é igual ao nome fornecido como parâmetro.
     * <p>
     * @returnCode - 200 OK: A consulta é executada com sucesso e retorna o produto correspondente ao nome.<p>
     * <p> - 404 Not Found: Se nenhum produto for encontrado com o nome fornecido.
     * @see Product
     */
    @Query("SELECT p FROM products AS p " +
            "WHERE p.name = :name")
    Product findByName(@Param("name") String name);

    /**
     * Exclui um produto com base em seu nome.
     * <p>
     *
     * @param name O nome do produto a ser excluído.
     *             <p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     *                             <p>
     * @implNote Este método executa a exclusão de um produto com base em seu nome.
     * A exclusão é realizada pelo nome do produto fornecido como parâmetro.
     * <p>
     * @returnCode - 204 No Content: A exclusão é executada com sucesso e nenhum conteúdo é retornado.<p>
     * <p>
     * @see Product
     */
    void deleteByName(String name);

    /**
     * Obtém uma lista de produtos pertencentes a uma categoria específica com base no ID da categoria.
     * <p>
     *
     * @param category A instância de ProductCategory pela qual os produtos serão filtrados.
     *                 <p>
     * @return Uma lista de produtos que pertencem à categoria especificada.
     * <p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     *                             <p>
     * @implNote Este método executa uma consulta JPQL para buscar produtos pertencentes a uma categoria específica.
     * A consulta verifica se a categoria de cada produto é igual à categoria fornecida como parâmetro.
     * <p>
     * @returnCode - 200 OK: A consulta é executada com sucesso e retorna uma lista de produtos que pertencem à categoria especificada.<p>
     * <p>
     * @see Product
     * @see ProductCategory
     */
    @Query("SELECT p FROM products AS p " +
            "WHERE p.category = :category")
    List<Product> findByCategory(@Param("category") ProductCategory category);

    /**
     * Este método executa uma consulta JPQL para buscar produtos com base no seu status de ativação.
     *
     * <p>Esta consulta seleciona todos os produtos cujo status de ativação corresponde ao valor fornecido pelo parâmetro isActivated.</p>
     * <p>
     *
     * @param isActivated O status de ativação a ser usado como critério de busca para encontrar os produtos.
     *                    <p>
     * @return Uma lista de objetos Product que correspondem ao status de ativação fornecido.
     *
     * <p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     * @implNote Esta consulta utiliza uma consulta JPQL para buscar produtos pelo status de ativação.
     * A consulta verifica se o status de ativação de cada produto é igual ao valor fornecido.
     * <p>
     * @returnCode - 200 OK: A consulta é executada com sucesso e retorna uma lista de produtos com o status de ativação correspondente.
     * <p>
     * @see Product
     */
    @Query("SELECT p FROM products AS p " +
            "WHERE p.isActivated = :isActivated")
    List<Product> findProductsByActive(@Param("isActivated") Boolean isActivated);

    /**
     *<p> Este método executa uma consulta JPQL para buscar produtos dentro de um intervalo de preços.
     *
     * <p>Esta consulta seleciona todos os produtos cujos preços estão dentro do intervalo especificado pelos parâmetros minPrice e maxPrice.</p>
     *<p>
     * @param minPrice O preço mínimo do intervalo.
     *                 <p>
     * @param maxPrice O preço máximo do intervalo.

    <p>
     * @return Uma lista de objetos Product que estão dentro do intervalo de preços especificado.
     * <p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     * <p>
     * @implNote Esta consulta utiliza uma consulta JPQL para buscar produtos pelo intervalo de preços.
     * A consulta verifica se o preço de cada produto está entre o preço mínimo e o preço máximo fornecidos.
     * <p>
     * @returnCode - 200 OK: A consulta é executada com sucesso e retorna uma lista de produtos dentro do intervalo de preços.
     * <p>
     * @see Product
     */
    @Query("SELECT p FROM products AS p " +
            "WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findProductsByPriceRange(
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );

    /**
     * Este método executa uma consulta JPQL para verificar se um produto com o nome especificado existe.
     *
     * <p>Esta consulta retorna verdadeiro se um produto com o nome fornecido como parâmetro existir e falso caso contrário.</p>
     *<p>
     * @param name O nome do produto a ser verificado.
    <p>
     * @return true se um produto com o nome especificado existe, caso contrário, false.
     * <p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     * <p>
     * @implNote Esta consulta utiliza uma consulta JPQL para verificar a existência de produtos pelo nome.
     * A consulta conta o número de produtos com o nome fornecido e retorna verdadeiro se o número for maior que zero, caso contrário, retorna falso.
     * <p>
     * @returnCode - 200 OK: A consulta é executada com sucesso e retorna verdadeiro ou falso com base na existência do produto.
     * @see Product
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 " +
            "THEN true " +
            "ELSE false END " +
            "FROM products p " +
            "WHERE p.name = :name")
    boolean existsProductByName(@Param("name") String name);
}