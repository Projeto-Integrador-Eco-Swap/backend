package com.generation.backend.controller;

import com.generation.backend.entity.ProductCategory;
import com.generation.backend.exception.InvalidIdProductCategoryException;
import com.generation.backend.exception.InvalidNameProductCategoryException;
import com.generation.backend.exception.InvalidProductCategoryException;
import com.generation.backend.service.ProductCategoryService;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Esta classe é um controlador que lida com endpoints relacionados a categorias de produtos.
 * <p>
 *
 * @RestController - Indica que esta classe é um controlador Spring MVC.
 * <p>
 * @RequestMapping("/category") - Define o mapeamento de URL de base para todos os endpoints deste controlador como "/category".
 * <p>
 * @CrossOrigin(origins="*",allowedHeaders="*") - Permite solicitações de qualquer origem e com qualquer cabeçalho,
 * permitindo o acesso a este controlador a partir de diferentes domínios.
 * <p>
 * @apiNote - Esta classe é responsável por lidar com as operações relacionadas a categorias de produtos, como criação, busca e atualização.
 * Ela define os endpoints e utiliza o serviço ProductCategoryService para executar as operações.
 * <p>
 * @see ProductCategoryService
 */
@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductCategoryController {

    /**
     * Este é um campo final que armazena uma instância do serviço de categorias de produtos.
     * <p>
     *
     * @apiNote - Este campo é utilizado para manter uma referência à instância do serviço ProductCategoryService
     * no controlador ProductCategoryController.
     * A injeção de dependência permite que o controlador acesse e utilize os métodos do serviço.
     * <p>
     * @see ProductCategoryService
     */
    private final ProductCategoryService productCategoryService;

    /**
     * Este construtor é responsável por injetar uma instância de ProductCategoryService no controlador.
     * <p>
     *
     * @param productCategoryService O serviço de categorias de produtos a ser injetado no controlador.
     *                               <p>
     * @apiNote - Este construtor é utilizado para injetar a dependência do serviço ProductCategoryService no controlador.
     * A anotação @Autowired é usada para realizar a injeção de dependência.

     * @example Exemplo de uso:

     * <p>
     * @Autowired public ProductCategoryController(ProductCategoryService productCategoryService) { </p>
     * <p>this.productCategoryService = productCategoryService;</p>
     * <p>}</p>
     *  <p>
     * <p>
     * Neste exemplo, uma instância de ProductCategoryService é injetada no controlador para que ele possa acessar e utilizar os métodos do serviço.
     * <p>
     * @see ProductCategoryService
     */
    @Autowired
    @Contract(pure = true)
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    /**
     * Este método é um endpoint REST que permite criar uma nova categoria de produto.
     *<p>
     * @param productCategory O objeto de categoria de produto a ser criado.
     *                        <p>
     * @return Uma ResponseEntity contendo a categoria de produto recém-criada.
     * <p>
     * @throws InvalidNameProductCategoryException Se o nome da categoria de produto fornecido for inválido.
     * <p>
     * @apiNote - Endpoint: POST /create
     * - Este endpoint permite a criação de uma nova categoria de produto.
     * - O corpo da solicitação (RequestBody) deve conter um objeto de categoria de produto a ser criado.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.createProductCategory(productCategory)
     * para criar a categoria de produto.
     * <p>
     * @returnCode - 200 OK: A operação de criação da categoria de produto é realizada com sucesso e a resposta contém a categoria de produto recém-criada.
     * <p>- 400 Bad Request: Se o nome da categoria de produto fornecido for inválido ou se ocorrerem outros erros durante a operação de criação.
     * <p>- 500 Internal Server Error: Se ocorrerem erros internos durante a operação.
     * <p>
     * @example Exemplo de uso:
     * POST /create
     * Corpo da Solicitação (RequestBody):
     * <p>{ </p>
     * <p>"name": "Nova Categoria",</p>
     * <p>"description": "Descrição da Nova Categoria"</p>
     * <p>}</p>
     * Neste exemplo, uma nova categoria de produto será criada com os dados fornecidos.
     * <p>
     * @see ProductCategoryService#createProductCategory(ProductCategory)
     */
    @PostMapping("/create")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) throws InvalidNameProductCategoryException {
        ProductCategory createdProductCategory = productCategoryService.createProductCategory(productCategory);
        return ResponseEntity.status(HttpStatus.OK).body(createdProductCategory);
    }

    /**
     * Este método é um endpoint REST que permite criar várias categorias de produtos de uma vez.
     *<p>
     * @param productCategories Uma lista de objetos de categoria de produto a serem criados.
     *                          <p>
     * @return Uma ResponseEntity contendo uma lista das categorias de produtos recém-criadas.
     * <p>
     * @throws InvalidProductCategoryException Se ocorrerem erros durante a criação das categorias de produtos.
     * <p>
     * @apiNote - Endpoint: POST /create-multiple
     * - Este endpoint permite a criação de várias categorias de produtos de uma vez.
     * - O corpo da solicitação (RequestBody) deve conter uma lista de objetos de categoria de produto a serem criados.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.createMultipleProductCategories(productCategories)
     * para criar várias categorias de produtos ao mesmo tempo.
     * <p>
     * @returnCode - 200 OK: A operação de criação das categorias de produtos é realizada com sucesso e a resposta contém a lista das categorias recém-criadas.
     * <p> - 400 Bad Request: Se ocorrerem erros durante a operação de criação das categorias de produtos.
     * <p> - 500 Internal Server Error: Se ocorrerem erros internos durante a operação.
     * <p>
     * @example Exemplo de uso:
     * POST /create-multiple
     * Corpo da Solicitação (RequestBody):
     * <p>[
     * <p>  {
     * <p>      "name": "Categoria1",
     * <p>      "description": "Descrição1"
     * <p>  },
     * <p>  {
     * <p>      "name": "Categoria2",
     * <p>      "description": "Descrição2"
     * <p>  }
     * <p>]
     * <p>
     * Neste exemplo, duas categorias de produtos serão criadas com os dados fornecidos.
     * <p>
     * @see ProductCategoryService#createMultipleProductCategories(List)
     */
    @PostMapping("/create-multiple")
    public ResponseEntity<Iterable<ProductCategory>> createMultipleProductCategories(@RequestBody List<ProductCategory> productCategories) throws InvalidProductCategoryException {
        Iterable<ProductCategory> createdProductCategories = productCategoryService.createMultipleProductCategories(productCategories);
        return ResponseEntity.status(HttpStatus.OK).body(createdProductCategories);
    }

    /**
     * Este método é um endpoint REST que permite obter todas as categorias de produtos existentes.
     *<p>
     * @return Uma ResponseEntity contendo uma lista de todas as categorias de produtos.
     * <p>
     * @apiNote - Endpoint: GET /all
     * - Este endpoint permite buscar todas as categorias de produtos cadastradas no sistema.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.getAllProductCategories()
     * para buscar todas as categorias de produtos.
     * <p>
     * @returnCode - 200 OK: A operação de busca de todas as categorias de produtos é realizada com sucesso e a resposta contém a lista de categorias.
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a operação de busca de todas as categorias de produtos.
     * <p>
     * @example Exemplo de uso:
     * GET /all
     * Neste exemplo, o endpoint buscará e retornará todas as categorias de produtos cadastradas no sistema.
     * <p>
     * @see ProductCategoryService#getAllProductCategories()
     */
    @GetMapping("/all")
    public ResponseEntity<Iterable<ProductCategory>> getAllProductCategories() {
        Iterable<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        return ResponseEntity.status(HttpStatus.OK).body(productCategories);
    }

    /**
     * Este método é um endpoint REST que permite obter uma categoria de produto pelo ID.
     *<p>
     * @param id O ID da categoria de produto a ser buscada.
     *           <p>
     * @return Uma ResponseEntity contendo a categoria de produto encontrada.
     * <p>
     * @throws InvalidIdProductCategoryException Se o ID da categoria de produto fornecido for inválido.
     * <p>
     * @apiNote - Endpoint: GET /{id}
     * - Este endpoint permite buscar uma categoria de produto com base no ID fornecido como parâmetro na URL.
     * - O parâmetro {id} na URL deve ser preenchido com o ID da categoria de produto desejada.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.getProductCategoryById(id)
     * para buscar a categoria de produto pelo ID.
     * <p>
     * @returnCode - 200 OK: A operação de busca da categoria de produto é realizada com sucesso e a resposta contém a categoria de produto encontrada.
     * <p> - 400 Bad Request: Se o ID da categoria de produto fornecido for inválido.
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a operação de busca da categoria de produto.
     * <p>
     * @example Exemplo de uso:
     * GET /1
     * Neste exemplo, o parâmetro {id} na URL é "1".
     * Isso resultará na busca da categoria de produto com o ID igual a 1.
     * <p>
     * @see ProductCategoryService#getProductCategoryById(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Long id) throws InvalidIdProductCategoryException {
        try {
            ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(productCategory);
        } catch (InvalidIdProductCategoryException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Este método é um endpoint REST que permite obter uma categoria de produto pelo nome.
     *<p>
     * @param name O nome da categoria de produto a ser buscada.
     *             <p>
     * @return Uma ResponseEntity contendo a categoria de produto encontrada.
     * <p>
     * @apiNote - Endpoint: GET /name/{name}
     *      - Este endpoint permite buscar uma categoria de produto com base no nome fornecido como parâmetro na URL.<p>
     * <p>  - O parâmetro {name} na URL deve ser preenchido com o nome da categoria de produto desejada.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.getProductCategoryByName(name)
     * para buscar a categoria de produto pelo nome.
     * <p>
     * @returnCode - 200 OK: A operação de busca da categoria de produto é realizada com sucesso e a resposta contém a categoria de produto encontrada.<p>
     * <p> - 404 Not Found: Se nenhuma categoria de produto com o nome fornecido for encontrada.<p>
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a operação de busca da categoria de produto.
     * <p>
     * @example Exemplo de uso:
     * GET /name/CategoriaX
     * Neste exemplo, o parâmetro {name} na URL é "CategoriaX".
     * Isso resultará na busca da categoria de produto com o nome "CategoriaX".
     * <p>
     * @see ProductCategoryService#getProductCategoryByName(String)
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductCategory> getProductCategoryByName(@PathVariable String name) {
        ProductCategory productCategory = productCategoryService.getProductCategoryByName(name);
        if (productCategory != null) {
            return ResponseEntity.status(HttpStatus.OK).body(productCategory);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Este método é um endpoint REST que permite atualizar uma categoria de produto existente.
     *<p>
     * @param productCategory O objeto de categoria de produto contendo os dados a serem atualizados.
     *                        <p>
     * @return Uma ResponseEntity contendo a categoria de produto atualizada.
     * <p>
     * @throws InvalidIdProductCategoryException Se o ID da categoria de produto fornecido for inválido.
     * <p>
     * @apiNote - Endpoint: PUT /update
     * - Este endpoint permite atualizar os dados de uma categoria de produto existente.
     * - O corpo da solicitação (RequestBody) deve conter um objeto de categoria de produto com os dados a serem atualizados.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.updateProductCategory(productCategory)
     * para atualizar a categoria de produto.
     * A transação é gerenciada automaticamente pela anotação @Transactional.
     * <p>
     * @returnCode - 200 OK: A operação de atualização da categoria de produto é realizada com sucesso e a resposta contém a categoria de produto atualizada.
     * <p> - 400 Bad Request: Se o ID da categoria de produto fornecido for inválido.
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a operação de atualização da categoria de produto.
     * <p>
     * @example Exemplo de uso:
     * PUT /update
     * Corpo da Solicitação (RequestBody):
     * <p>
     * <p>{
     * <p>  "id": 1,
     * <p>  "name": "Nova Categoria",
     * <p>  "description": "Nova Descrição"
     * <p>}<p>
     *     <p>
     * Neste exemplo, os dados da categoria de produto com ID 1 serão atualizados com o novo nome e a nova descrição.
     * <p>
     * @see ProductCategoryService#updateProductCategory(ProductCategory)
     */
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategory) throws InvalidIdProductCategoryException {
        ProductCategory updatedProductCategory = productCategoryService.updateProductCategory(productCategory);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProductCategory);
    }

    /**
     * Este método é um endpoint REST que permite atualizar a descrição de uma categoria de produto existente.
     *<p>
     * @param productCategory O objeto de categoria de produto contendo o ID e a nova descrição.
     *                        <p>
     * @return Uma ResponseEntity contendo a categoria de produto atualizada.
     * <p>
     * @throws InvalidIdProductCategoryException Se o ID da categoria de produto fornecido for inválido.
     * <p>
     * @apiNote - Endpoint: PUT /update-description
     * <p>- Este endpoint permite atualizar a descrição de uma categoria de produto existente.<p>
     * <p>- O corpo da solicitação (RequestBody) deve conter um objeto de categoria de produto com o ID e a nova descrição.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.updateProductCategoryDescription(productCategory)
     * para atualizar a descrição da categoria de produto.
     * A transação é gerenciada automaticamente pela anotação @Transactional.
     * <p>
     * @returnCode - 200 OK: A operação de atualização da descrição da categoria de produto é realizada com sucesso e a resposta contém a categoria de produto atualizada.<p>
     * <p>- 400 Bad Request: Se o ID da categoria de produto fornecido for inválido.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de atualização da descrição da categoria de produto.
     * <p>
     * @example Exemplo de uso:
     * PUT /update-description
     * Corpo da Solicitação (RequestBody):
     * <p>{
     * <p>"id": 1,
     * <p>"description": "Nova Descrição"
     * <p>}
     *  <p>
     * Neste exemplo, a descrição da categoria de produto com ID 1 será atualizada para "Nova Descrição".
     * <p>
     * @see ProductCategoryService#updateProductCategoryDescription(ProductCategory)
     */
    @PutMapping("/update-description")
    @Transactional
    public ResponseEntity<ProductCategory> updateProductCategoryDescription(@RequestBody ProductCategory productCategory) throws InvalidIdProductCategoryException {
        ProductCategory updatedProductCategory = productCategoryService.updateProductCategoryDescription(productCategory);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProductCategory);
    }

    /**
     * Este método é um endpoint REST que permite excluir uma categoria de produto com base em seu ID.
     *<p>
     * @param id O ID da categoria de produto a ser excluída.
     *           <p>
     * @return Uma ResponseEntity contendo uma mensagem de sucesso ou erro após a exclusão da categoria de produto.
     * <p>
     * @apiNote - Endpoint: DELETE /delete/{id}
     * <p>- Este endpoint permite a exclusão de uma categoria de produto com base no ID fornecido como parâmetro na URL.<p>
     * <p>- O parâmetro {id} na URL deve ser preenchido com o ID da categoria de produto a ser excluída.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.deleteProductCategoryById(id)
     * para excluir a categoria de produto com o ID fornecido.
     * A transação é gerenciada automaticamente pela anotação @Transactional.
     * <p>
     * @returnCode - 200 OK: A operação de exclusão da categoria de produto é realizada com sucesso e a resposta contém uma mensagem de sucesso.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de exclusão da categoria de produto.
     * <p>
     * @example Exemplo de uso:
     * DELETE /delete/1
     * Neste exemplo, o parâmetro {id} na URL é "1".
     * Isso resultará na exclusão da categoria de produto com o ID igual a 1.
     * <p>
     * @see ProductCategoryService#deleteProductCategoryById(Long)
     */
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteProductCategory(@PathVariable Long id) {
        Map<String, String> response = productCategoryService.deleteProductCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Este método é um endpoint REST que permite excluir todas as categorias de produtos.
     *<p>
     * @return Uma ResponseEntity contendo uma mensagem de sucesso ou erro após a exclusão de todas as categorias de produtos.
     * <p>
     * @apiNote - Endpoint: DELETE /delete-all<p>
     * <p>- Este endpoint permite a exclusão de todas as categorias de produtos no sistema.<p>
     * <p>- Todas as categorias de produtos serão removidas permanentemente.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.deleteAllProductCategories()
     * para excluir todas as categorias de produtos.
     * A transação é gerenciada automaticamente pela anotação @Transactional.
     * <p>
     * @returnCode - 200 OK: A operação de exclusão de todas as categorias de produtos é realizada com sucesso e a resposta contém uma mensagem de sucesso.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de exclusão de todas as categorias de produtos.
     * <p>
     * @example Exemplo de uso:
     * DELETE /delete-all
     * Neste exemplo, todas as categorias de produtos existentes serão excluídas do sistema.
     * <p>
     * @see ProductCategoryService#deleteAllProductCategories()
     */
    @DeleteMapping("/delete-all")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteAllProductCategories() {
        Map<String, String> response = productCategoryService.deleteAllProductCategories();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Este método é um endpoint REST que permite excluir uma categoria de produto com base no nome.
     *<p>
     * @param name O nome da categoria de produto a ser excluída.
     *             <p>
     * @return Uma ResponseEntity contendo uma mensagem de sucesso ou erro após a exclusão.
     * <p>
     * @apiNote - Endpoint: DELETE /delete-by-name/{name}
     * <p>- Este endpoint permite a exclusão de uma categoria de produto com base no nome fornecido como parâmetro na URL.<p>
     * <p>- O parâmetro {name} na URL deve ser preenchido com o nome da categoria de produto a ser excluída.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.deleteProductCategoryByName(name)
     * para excluir a categoria de produto com base no nome fornecido.
     * A transação é gerenciada automaticamente pela anotação @Transactional.
     * <p>
     * @returnCode - 200 OK: A operação de exclusão da categoria de produto é realizada com sucesso e a resposta contém uma mensagem de sucesso.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de exclusão da categoria de produto.
     * <p>
     * @example Exemplo de uso:
     * DELETE /delete-by-name/CategoriaX
     * Neste exemplo, o parâmetro {name} na URL é "CategoriaX".
     * Isso resultará na exclusão da categoria de produto com o nome "CategoriaX".
     * <p>
     * @see ProductCategoryService#deleteProductCategoryByName(String)
     */
    @DeleteMapping("/delete-by-name/{name}")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteProductCategoryByName(@PathVariable String name) {
        Map<String, String> response = productCategoryService.deleteProductCategoryByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Este método é um endpoint REST que permite buscar categorias de produtos com base em uma descrição parcial.
     *<p>
     * @param description A descrição parcial a ser usada para buscar categorias de produtos.
     *                    <p>
     * @return Uma ResponseEntity contendo uma lista de categorias de produtos que correspondem à descrição parcial.
     * <p>
     * @apiNote - Endpoint: GET /search-description/{description}
     * <p>- Este endpoint busca categorias de produtos com base na descrição parcial fornecida como parâmetro na URL.<p>
     * <p>- O parâmetro {description} na URL deve ser preenchido com a descrição parcial desejada.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.searchProductCategoriesByDescription(description)
     * para buscar categorias de produtos com base na descrição parcial.
     * <p>
     * @returnCode - 200 OK: A operação de busca de categorias de produtos é realizada com sucesso e a resposta contém a lista de categorias.<p>
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a operação de busca de categorias de produtos.
     * <p>
     * @example Exemplo de uso:
     * GET /search-description/Produto Parcial
     * Neste exemplo, o parâmetro {description} na URL é "Produto Parcial".
     * Isso resultará na busca de categorias de produtos que contenham a descrição parcial "Produto Parcial".
     * <p>
     * @see ProductCategoryService#searchProductCategoriesByDescription(String)
     */
    @GetMapping("/search-description/{description}")
    public ResponseEntity<List<ProductCategory>> searchProductCategoriesByDescription(@PathVariable String description) {
        List<ProductCategory> productCategories = productCategoryService.searchProductCategoriesByDescription(description);
        return ResponseEntity.status(HttpStatus.OK).body(productCategories);
    }

    /**
     * Este método é um endpoint REST que permite obter categorias de produtos ordenadas por nome.
     *<p>
     * @return Uma ResponseEntity contendo uma lista de categorias de produtos ordenadas alfabeticamente por nome.
     * <p>
     * @apiNote - Endpoint: GET /sort-by-name
     * - Este endpoint busca categorias de produtos e as retorna ordenadas por nome em ordem alfabética.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.getProductCategoriesSortedByName()
     * para buscar e ordenar as categorias de produtos por nome.
     * <p>
     * @returnCode - 200 OK: A operação de busca e ordenação de categorias de produtos é realizada com sucesso e a resposta contém a lista ordenada.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de busca e ordenação de categorias de produtos.
     * <p>
     * @example Exemplo de uso:
     * GET /sort-by-name
     * Neste exemplo, o endpoint buscará todas as categorias de produtos e as retornará ordenadas por nome.
     * <p>
     * @see ProductCategoryService#getProductCategoriesSortedByName()
     */
    @GetMapping("/sort-by-name")
    public ResponseEntity<List<ProductCategory>> getProductCategoriesSortedByName() {
        List<ProductCategory> sortedCategories = productCategoryService.getProductCategoriesSortedByName();
        return ResponseEntity.status(HttpStatus.OK).body(sortedCategories);
    }

    /**
     * Este método é um endpoint REST que permite obter categorias de produtos com base em um material específico.
     *<p>
     * @param material O material específico a ser usado para buscar categorias de produtos.
     *                 <p>
     * @return Uma ResponseEntity contendo uma lista de categorias de produtos que correspondem ao material fornecido.
     * <p>
     * @apiNote - Endpoint: GET /material/{material}
     * <p>- Este endpoint busca categorias de produtos com base no material fornecido como parâmetro na URL.<p>
     * <p>- O parâmetro {material} na URL deve ser preenchido com o material desejado.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.getProductCategoriesByMaterial(material)
     * para buscar categorias de produtos com base no material específico.
     * <p>
     * @returnCode - 200 OK: A operação de busca de categorias de produtos é realizada com sucesso e a resposta contém a lista de categorias.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de busca de categorias de produtos.
     * <p>
     * @example Exemplo de uso:
     * GET /material/MaterialXYZ
     * Neste exemplo, o parâmetro {material} na URL é "MaterialXYZ".
     * Isso resultará na busca de categorias de produtos com base no material "MaterialXYZ".
     * <p>
     * @see ProductCategoryService#getProductCategoriesByMaterial(String)
     */
    @GetMapping("/material/{material}")
    public ResponseEntity<List<ProductCategory>> getProductCategoriesByMaterial(@PathVariable String material) {
        List<ProductCategory> categoriesByMaterial = productCategoryService.getProductCategoriesByMaterial(material);
        return ResponseEntity.status(HttpStatus.OK).body(categoriesByMaterial);
    }

    /**
     * Este método é um endpoint REST que permite obter categorias de produtos com base em uma descrição exata.
     *<p>
     * @param description A descrição exata a ser usada para buscar categorias de produtos.
     *                    <p>
     * @return Uma ResponseEntity contendo uma lista de categorias de produtos que correspondem à descrição exata.
     * <p>
     * @apiNote - Endpoint: GET /description-exact/{description}
     * <p>- Este endpoint busca categorias de produtos com base na descrição exata fornecida como parâmetro na URL.<p>
     * <p>- O parâmetro {description} na URL deve ser preenchido com a descrição exata desejada.
     * <p>
     * @implNote - Este método chama o serviço productCategoryService.getProductCategoriesByExactDescription(description)
     * para buscar categorias de produtos com base na descrição exata.
     * <p>
     * @returnCode - 200 OK: A operação de busca de categorias de produtos é realizada com sucesso e a resposta contém a lista de categorias.<p>
     *<p> - 500 Internal Server Error: Se ocorrerem erros durante a operação de busca de categorias de produtos.
     * <p>
     * @example Exemplo de uso:
     * GET /description-exact/Produto Exato
     * Neste exemplo, o parâmetro {description} na URL é "Produto Exato".
     * Isso resultará na busca de categorias de produtos com a descrição exata "Produto Exato".
     * <p>
     * @see ProductCategoryService#getProductCategoriesByExactDescription(String)
     */
    @GetMapping("/description-exact/{description}")
    public ResponseEntity<List<ProductCategory>> getProductCategoriesByExactDescription(@PathVariable String description) {
        List<ProductCategory> categoriesByExactDescription = productCategoryService.getProductCategoriesByExactDescription(description);
        return ResponseEntity.status(HttpStatus.OK).body(categoriesByExactDescription);
    }}