package com.generation.backend.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.generation.backend.entity.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Esta interface atua como um repositório de dados para a entidade ProductCategory.
 * Fornece métodos de acesso a dados para realizar operações CRUD (Criar, Ler, Atualizar e Excluir)
 * em instâncias de ProductCategory no banco de dados.
 *
 * @Repository Indica que esta interface é um componente de repositório gerenciado pelo Spring.
 * JpaRepository fornece métodos padrão para operações de banco de dados, como salvar, excluir e buscar.
 * Ele opera na entidade ProductCategory e utiliza Long como o tipo de dado da chave primária.
 *
 * @see ProductCategory
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    /**
     * <p>
     * Este método executa uma consulta JPQL nomeada para buscar uma categoria de produto pelo nome fornecido.
     * A consulta seleciona a categoria de produto cujo nome corresponde exatamente ao nome fornecido como parâmetro.</p>
     *<p>
     * @param name O nome a ser usado como critério de busca na categoria de produto.
     *             </p>
     *             <p>
     * @return Um objeto ProductCategory que corresponde ao nome fornecido.
     * </p>
     *<p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     * </p>
     *<p>
     * @implNote
     * Esta consulta utiliza uma consulta JPQL nomeada para buscar a categoria de produto.
     * A consulta procura por uma correspondência exata no campo "name" da categoria de produto.
     * </p>
    <p>
     * @returnCode
     * - 200 OK: A consulta é executada com sucesso e a categoria de produto correspondente ao nome é retornada.<p>
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a execução da consulta.
     * </p>
     *
     * @see ProductCategory
     */
    @Query("SELECT pc FROM productcategories AS pc " +
            "WHERE pc.name = :name")
    ProductCategory findByName(@Param("name") String name);


    /**
     * Exclui uma categoria de produtos pelo seu nome.
     *
     * @param name O nome da categoria de produtos a ser excluída.
     */
    void deleteByName(String name);

    /**
     * Verifica se uma categoria de produtos com um determinado nome existe.
     *
     * @param name O nome da categoria de produtos a ser verificado.
     * @return `true` se a categoria de produtos existe, caso contrário `false`.
     */
    boolean existsByName(String name);

    /**
     * Este método executa uma consulta JPQL nomeada para buscar categorias de produtos que correspondam à descrição fornecida.
     *
     * <p>
     * A consulta seleciona todas as categorias de produtos cujos nomes contenham a descrição fornecida,
     * ignorando o caso (case-insensitive).
     * </p>
     *
     * <p>
     *
     * @param description A descrição a ser usada como critério de busca nas categorias de produtos.
     *                    </p>
     *                    <p>
     * @return Uma lista de objetos ProductCategory que correspondem à descrição fornecida.
     * </p>
     * <p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     *                             </p>
     *                             <p>
     * @implNote Esta consulta utiliza uma consulta JPQL nomeada para buscar as categorias de produtos.
     * A consulta procura por correspondências parciais no nome da categoria, ignorando o caso (case-insensitive).
     * </p>
     *
     * <p>
     * @returnCode 200 OK: A consulta é executada com sucesso e a lista de categorias de produtos correspondentes é retornada.</p><p>
     * <p> 500 Internal Server Error: Se ocorrerem erros durante a execução da consulta.
     * </p>
     * <p>
     * @example
     * Exemplo: Buscar categorias de produtos cujos nomes contenham "eletrônicos".
     * </p>
     * <p>
     * <pre>
     * {@code
     * List<ProductCategory> categorias = repository.searchProductCategoriesByDescription("eletrônicos");
     * }
     * </pre>
     * </p>
     * Neste exemplo, a consulta busca todas as categorias de produtos que contenham "eletrônicos" em seus nomes,
     * independentemente do uso de maiúsculas ou minúsculas.</p>
     * @see ProductCategory
     */
    @Query("SELECT pc FROM productcategories AS pc " +
            "WHERE pc.name " +
            "LIKE %:description%")
    List<ProductCategory> searchProductCategoriesByDescription(@Param("description") String description);

    /**
     * Este método executa uma consulta JPQL nomeada para buscar todas as categorias de produtos e ordená-las por nome.
     *
     * <p>A consulta seleciona todas as categorias de produtos disponíveis no banco de dados e as ordena em ordem alfabética pelo nome.</p>
     *<p>
     * @return Uma lista de objetos ProductCategory contendo todas as categorias de produtos disponíveis, ordenadas por nome.
     * </p>
     * <p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     * </p>
     * <p>
     * @implNote Esta consulta utiliza uma consulta JPQL nomeada para buscar todas as categorias de produtos e ordená-las por nome.
     * </p>
     * <p>
     * @returnCode - 200 OK: A consulta é executada com sucesso e a lista de categorias de produtos ordenadas por nome é retornada.<p>
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a execução da consulta.
     * </p>
     * @see ProductCategory
     */
    @Query("SELECT pc FROM productcategories AS pc " +
            "ORDER BY pc.name")
    List<ProductCategory> getProductCategoriesSortedByName();


    /**
     * Este método executa uma consulta JPQL nomeada para buscar categorias de produtos que correspondam ao material fornecido.
     *
     * <p>A consulta seleciona todas as categorias de produtos cujo material corresponda ao material fornecido como parâmetro.</p>
     *<p>
     * @param material O material a ser usado como critério de busca nas categorias de produtos.
     * </p>
     *                 <p>
     * @return Uma lista de objetos ProductCategory que correspondem ao material fornecido.
     * </p>
     * <p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     * </p>
     * <p>
     * @implNote Esta consulta utiliza uma consulta JPQL nomeada para buscar as categorias de produtos.
     * A consulta procura por correspondências no campo "material" da categoria de produtos.
     * </p>
     * <p>
     * @returnCode - 200 OK: A consulta é executada com sucesso e a lista de categorias de produtos correspondentes ao material é retornada.<p>
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a execução da consulta.
     * </p>
     * <p>
     * @example Exemplo: Buscar categorias de produtos cujo material seja "aço inoxidável".
     * </p>
     * <p>
     * <pre>
     * {@code
     * List<ProductCategory> categorias = repository.getProductCategoriesByMaterial("aço inoxidável");
     * }
     * </pre>
     * </p>
     * Neste exemplo, a consulta busca todas as categorias de produtos cujo material seja "aço inoxidável".
     *
     * @see ProductCategory
     */
    @Query("SELECT pc FROM productcategories AS pc " +
            "WHERE pc.material = :material")
    List<ProductCategory> getProductCategoriesByMaterial(@Param("material") String material);

    /**
     * Este método executa uma consulta JPQL nomeada para buscar categorias de produtos que tenham a descrição exata fornecida.
     *
     * <p>A consulta seleciona todas as categorias de produtos cujas descrições correspondam exatamente à descrição fornecida como parâmetro.</p>
     * <p>
     *
     * @param description A descrição exata a ser usada como critério de busca nas categorias de produtos.
     *                    </p>
     *                    <p>

     * @return Uma lista de objetos ProductCategory que correspondem à descrição exata fornecida.
     * </p>
     *
     * <p>
     * @throws DataAccessException Se ocorrer algum erro ao acessar os dados no banco de dados.
     *                             </p>
     *
     *                             <p>
     * @implNote Esta consulta utiliza uma consulta JPQL nomeada para buscar as categorias de produtos.
     * A consulta procura por correspondências exatas no campo "description" da categoria de produtos.
     * </p>
     *
     * <p>
     * @returnCode - 200 OK: A consulta é executada com sucesso e a lista de categorias de produtos correspondentes à descrição exata é retornada.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a execução da consulta.
     * </p>
     * <p>
     * @example Exemplo: Buscar categorias de produtos com a descrição exata "eletrônicos".
     * <pre>
     *  </p>
     * {@code
     * List<ProductCategory> categorias = repository.getProductCategoriesByExactDescription("eletrônicos");
     * }
     * </pre>
     * Neste exemplo, a consulta busca todas as categorias de produtos com a descrição exata "eletrônicos".
     * @see ProductCategory
     */
    @Query("SELECT pc FROM productcategories AS pc " +
            "WHERE pc.description = :description")
    List<ProductCategory> getProductCategoriesByExactDescription(@Param("description") String description);
}