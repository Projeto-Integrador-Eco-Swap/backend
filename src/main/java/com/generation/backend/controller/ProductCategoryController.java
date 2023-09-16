package com.generation.backend.controller;

import com.generation.backend.entity.ProductCategory;
import com.generation.backend.exception.InvalidIdProductCategoryException;
import com.generation.backend.exception.InvalidNameProductCategoryException;
import com.generation.backend.exception.InvalidProductCategoryException;
import com.generation.backend.service.ProductCategoryService;
import jakarta.transaction.Transactional;
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
 * Controlador responsável por gerenciar endpoints relacionados às categorias de produtos.
 * <p>
 * Este controlador é responsável por lidar com todas as operações relacionadas a categorias de produtos, como criação, atualização, recuperação e exclusão de categorias. Ele define endpoints que podem ser acessados por meio de requisições HTTP, permitindo a interação com as informações das categorias de produtos.
 *
 * @apiNote A anotação "@RestController" indica que esta classe é um controlador Spring que lida com requisições HTTP e responde com dados em formato JSON. A anotação "@RequestMapping" especifica o caminho base para todos os endpoints definidos nesta classe, ou seja, todos os endpoints começarão com "/category". Além disso, a anotação "@CrossOrigin" permite que este controlador aceite requisições de diferentes origens (origens cruzadas) e cabeçalhos permitidos.
 */
@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductCategoryController {

    /**
     * Serviço de categorias de produtos.
     * <p>
     * Este campo representa uma instância do serviço de categorias de produtos que é injetado no controlador. O serviço é essencial para que o controlador tenha acesso às funcionalidades relacionadas às categorias de produtos, como criar, atualizar, recuperar e excluir categorias.
     *
     * @apiNote A injeção de dependência do Spring é usada para fornecer uma instância válida de `ProductCategoryService` a este campo durante a inicialização do controlador. Essa abordagem promove a separação eficaz de preocupações em uma aplicação, permitindo que o controlador se concentre na interação com a API REST, enquanto as operações de dados relacionadas a categorias de produtos são tratadas pelo serviço.
     * @see ProductCategoryService Um exemplo de interface de serviço que define operações relacionadas a categorias de produtos.
     */
    private final ProductCategoryService productCategoryService;


    /**
     * Construtor do Controlador de Categorias de Produtos.
     * <p>
     * Este construtor é responsável por criar uma instância do controlador de categorias de produtos. Recebe uma injeção de dependência do serviço de categorias de produtos, que é essencial para o funcionamento do controlador.
     *
     * @param productCategoryService O serviço de categorias de produtos injetado neste construtor. É necessário para que o controlador tenha acesso às funcionalidades relacionadas a categorias de produtos, como criar, atualizar, recuperar e excluir categorias.
     * @apiNote A injeção de dependência é uma prática comum no desenvolvimento Spring, que permite uma melhor organização e testabilidade do código. Ao receber uma instância válida do serviço de categorias de produtos, o controlador pode delegar operações relacionadas a categorias de produtos ao serviço.
     * @see ProductCategoryService Um exemplo de interface de serviço que define operações relacionadas a categorias de produtos.
     */
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    /**
     * Cria uma nova categoria de produtos.
     * <p>
     * Este método é acionado quando uma requisição HTTP POST é enviada para o endpoint "/category/create". Ele permite a criação de uma nova categoria de produtos com base nos dados fornecidos no corpo da requisição (em formato JSON). A categoria é criada usando o serviço de categoria de produtos e, se a criação for bem-sucedida, a categoria recém-criada é retornada com um status HTTP 200 (OK).
     *
     * @param productCategory A categoria de produtos a ser criada. Deve estar no formato JSON e conter todos os campos necessários para criar uma categoria válida.
     * @return Um objeto ResponseEntity contendo a categoria de produtos recém-criada e um status HTTP 200 (OK) se a criação for bem-sucedida. Em caso de falha na criação, podem ser retornados outros códigos de status HTTP e uma mensagem de erro.
     * @throws InvalidNameProductCategoryException Se o nome da categoria de produtos fornecida for inválido ou já estiver em uso, uma exceção será lançada. Nesse caso, um status HTTP apropriado será retornado juntamente com uma mensagem de erro.
     */
    @PostMapping("/create")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) throws InvalidNameProductCategoryException {
        ProductCategory createdProductCategory = productCategoryService.createProductCategory(productCategory);
        return ResponseEntity.status(HttpStatus.OK).body(createdProductCategory);
    }

    /**
     * Cria várias categorias de produtos simultaneamente.
     * <p>
     * Este método é acionado quando uma requisição HTTP POST é enviada para o endpoint "/category/create-multiple". Ele permite a criação de várias categorias de produtos ao mesmo tempo com base nos dados fornecidos no corpo da requisição (em formato JSON). As categorias são criadas usando o serviço de categoria de produtos e, se a criação for bem-sucedida, a lista de categorias recém-criadas é retornada com um status HTTP 200 (OK).
     *
     * @param productCategories Uma lista de categorias de produtos a serem criadas. Cada categoria deve estar no formato JSON e conter todos os campos necessários para criar uma categoria válida.
     * @return Um objeto ResponseEntity contendo a lista de categorias de produtos recém-criadas e um status HTTP 200 (OK) se a criação for bem-sucedida. Em caso de falha na criação de qualquer categoria, podem ser retornados outros códigos de status HTTP e uma mensagem de erro.
     * @throws InvalidProductCategoryException Se uma ou mais categorias de produtos fornecidas forem inválidas ou se ocorrerem outros erros durante a criação, uma exceção será lançada. Nesse caso, um status HTTP apropriado será retornado juntamente com uma mensagem de erro.
     */
    @PostMapping("/create-multiple")
    public ResponseEntity<Iterable<ProductCategory>> createMultipleProductCategories(@RequestBody List<ProductCategory> productCategories) throws InvalidProductCategoryException {
        Iterable<ProductCategory> createdProductCategories = productCategoryService.createMultipleProductCategories(productCategories);
        return ResponseEntity.status(HttpStatus.OK).body(createdProductCategories);
    }

    /**
     * Recupera todas as categorias de produtos existentes.
     * <p>
     * Este método é acionado quando uma requisição HTTP GET é enviada para o endpoint "/category/all". Ele permite a recuperação de todas as categorias de produtos existentes no sistema. As categorias são obtidas usando o serviço de categoria de produtos e são retornadas em formato JSON com um status HTTP 200 (OK).
     *
     * @return Um objeto ResponseEntity contendo a lista de todas as categorias de produtos existentes e um status HTTP 200 (OK). Se não houver categorias disponíveis, a lista estará vazia, mas o status HTTP ainda será 200. Em caso de erro ao recuperar as categorias, podem ser retornados outros códigos de status HTTP e uma mensagem de erro.
     */
    @GetMapping("/all")
    public ResponseEntity<Iterable<ProductCategory>> getAllProductCategories() {
        Iterable<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        return ResponseEntity.status(HttpStatus.OK).body(productCategories);
    }

    /**
     * Recupera uma categoria de produtos pelo seu ID.
     * <p>
     * Este método é acionado quando uma requisição HTTP GET é enviada para o endpoint "/category/{id}", onde "{id}" é o ID da categoria desejada. Ele permite a recuperação de uma categoria de produtos específica com base no ID fornecido.
     *
     * @param id O ID da categoria de produtos a ser recuperada.
     * @return Um objeto ResponseEntity contendo a categoria de produtos recuperada e um status HTTP 200 (OK) se a categoria for encontrada com sucesso. Se o ID não corresponder a nenhuma categoria existente, será retornado um status HTTP 404 (Not Found). Em caso de erro ao recuperar a categoria, podem ser retornados outros códigos de status HTTP e uma mensagem de erro.
     * @throws InvalidIdProductCategoryException Uma exceção que pode ser lançada se o ID da categoria de produtos for inválido, como nulo ou negativo.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Long id) throws InvalidIdProductCategoryException {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productCategory);
    }

    /**
     * Recupera uma categoria de produtos pelo seu nome.
     * <p>
     * Este método é acionado quando uma requisição HTTP GET é enviada para o endpoint "/category/name/{name}", onde "{name}" é o nome da categoria de produtos desejada. Ele permite a recuperação de uma categoria de produtos específica com base no nome fornecido.
     *
     * @param name O nome da categoria de produtos a ser recuperada.
     * @return Um objeto ResponseEntity contendo a categoria de produtos recuperada e um status HTTP 200 (OK) se a categoria for encontrada com sucesso. Se o nome não corresponder a nenhuma categoria existente, será retornado um status HTTP 404 (Not Found). Em caso de erro ao recuperar a categoria, podem ser retornados outros códigos de status HTTP e uma mensagem de erro.
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductCategory> getProductCategoryByName(@PathVariable String name) {
        ProductCategory productCategory = productCategoryService.getProductCategoryByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(productCategory);
    }

    /**
     * Atualiza uma categoria de produtos existente.
     * <p>
     * Este método é acionado quando uma requisição HTTP PUT é enviada para o endpoint "/category/update". Ele permite a atualização de uma categoria de produtos com base nos dados fornecidos no corpo da requisição.
     *
     * @param productCategory O objeto `ProductCategory` que contém as informações atualizadas da categoria.
     * @return Um objeto ResponseEntity contendo a categoria de produtos atualizada e um status HTTP 200 (OK) se a atualização for bem-sucedida. Se o ID da categoria não for válido ou ocorrer um erro durante a atualização, serão retornados outros códigos de status HTTP e uma mensagem de erro.
     * @throws InvalidIdProductCategoryException Lançada quando o ID da categoria fornecida não é válido.
     */
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategory) throws InvalidIdProductCategoryException {
        ProductCategory updatedProductCategory = productCategoryService.updateProductCategory(productCategory);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProductCategory);
    }

    /**
     * Atualiza a descrição de uma categoria de produtos existente.
     * <p>
     * Este método permite atualizar a descrição de uma categoria de produtos específica. A categoria de produtos
     * é identificada pelo ID fornecido no objeto `productCategory`. A nova descrição é definida no mesmo objeto
     * `productCategory`. Se o ID fornecido não corresponder a nenhuma categoria de produtos existente, uma
     * exceção `InvalidIdProductCategoryException` será lançada.
     *
     * @param productCategory O objeto `ProductCategory` contendo o ID da categoria de produtos a ser atualizada
     *                        e a nova descrição.
     * @return ResponseEntity contendo a categoria de produtos atualizada e o status HTTP 200 (OK) em caso de sucesso.
     * @throws InvalidIdProductCategoryException Se o ID da categoria de produtos fornecido for inválido ou não
     *                                           corresponder a nenhuma categoria existente.
     */
    @PutMapping("/update-description")
    @Transactional
    public ResponseEntity<ProductCategory> updateProductCategoryDescription(@RequestBody ProductCategory productCategory) throws InvalidIdProductCategoryException {
        ProductCategory updatedProductCategory = productCategoryService.updateProductCategoryDescription(productCategory);
        return ResponseEntity.status(200).body(updatedProductCategory);
    }

    /**
     * Exclui uma categoria de produtos pelo seu ID.
     * <p>
     * Este método é acionado quando uma requisição HTTP DELETE é enviada para o endpoint "/category/delete/{id}". Ele permite a exclusão de uma categoria de produtos com base no ID fornecido.
     *
     * @param id O ID da categoria de produtos a ser excluída.
     * @return Um objeto ResponseEntity contendo um mapa com informações sobre a operação de exclusão e um status HTTP 200 (OK) se a exclusão for bem-sucedida. Se o ID da categoria não for válido ou ocorrer um erro durante a exclusão, serão retornados outros códigos de status HTTP e uma mensagem de erro.
     */
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteProductCategory(@PathVariable Long id) {
        Map<String, String> response = productCategoryService.deleteProductCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Exclui todas as categorias de produtos.
     * <p>
     * Este método é acionado quando uma requisição HTTP DELETE é enviada para o endpoint "/category/delete-all". Ele permite a exclusão de todas as categorias de produtos no sistema.
     *
     * @return Um objeto ResponseEntity contendo um mapa com informações sobre a operação de exclusão e um status HTTP 200 (OK) se a exclusão for bem-sucedida. Se ocorrer um erro durante a exclusão, serão retornados outros códigos de status HTTP e uma mensagem de erro.
     */
    @DeleteMapping("/delete-all")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteAllProductCategories() {
        Map<String, String> response = productCategoryService.deleteAllProductCategories();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Exclui uma categoria de produtos pelo seu nome.
     * <p>
     * Este método é acionado quando uma requisição HTTP DELETE é enviada para o endpoint "/category/delete-by-name/{name}". Ele permite a exclusão de uma categoria de produtos com base em seu nome.
     *
     * @param name O nome da categoria de produtos a ser excluída.
     * @return Um objeto ResponseEntity contendo um mapa com informações sobre a operação de exclusão e um status HTTP 200 (OK) se a exclusão for bem-sucedida. Se a categoria com o nome especificado não for encontrada, será retornado um status HTTP 404 (Not Found). Em caso de erro durante a exclusão, serão retornados outros códigos de status HTTP e uma mensagem de erro.
     */
    @DeleteMapping("/delete-by-name/{name}")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteProductCategoryByName(@PathVariable String name) {
        Map<String, String> response = productCategoryService.deleteProductCategoryByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Pesquisa categorias de produtos por descrição.
     * <p>
     * Este método é acionado quando uma requisição HTTP GET é enviada para o endpoint "/category/search-description/{description}". Ele permite a pesquisa de categorias de produtos cuja descrição contenha o texto especificado.
     *
     * @param description A descrição a ser usada na pesquisa.
     * @return Um objeto ResponseEntity contendo uma lista de categorias de produtos que correspondam à pesquisa e um status HTTP 200 (OK) se a pesquisa for bem-sucedida. Se nenhuma categoria corresponder à descrição ou se ocorrer um erro na pesquisa, será retornado um status HTTP 404 (Not Found) ou outros códigos de status HTTP apropriados, juntamente com uma mensagem de erro.
     */
    @GetMapping("/search-description/{description}")
    public ResponseEntity<List<ProductCategory>> searchProductCategoriesByDescription(@PathVariable String description) {
        List<ProductCategory> productCategories = productCategoryService.searchProductCategoriesByDescription(description);
        return ResponseEntity.status(HttpStatus.OK).body(productCategories);
    }

    /**
     * Obtém categorias de produtos ordenadas por nome.
     * <p>
     * Este método é acionado quando uma requisição HTTP GET é enviada para o endpoint "/category/sort-by-name". Ele permite a obtenção de todas as categorias de produtos ordenadas alfabeticamente por nome.
     *
     * @return Um objeto ResponseEntity contendo uma lista de categorias de produtos ordenadas por nome e um status HTTP 200 (OK) se a operação for bem-sucedida. Se não houver categorias de produtos cadastradas ou se ocorrer um erro na ordenação, será retornado um status HTTP 404 (Not Found) ou outros códigos de status HTTP apropriados, juntamente com uma mensagem de erro.
     */
    @GetMapping("/sort-by-name")
    public ResponseEntity<List<ProductCategory>> getProductCategoriesSortedByName() {
        List<ProductCategory> sortedCategories = productCategoryService.getProductCategoriesSortedByName();
        return ResponseEntity.status(HttpStatus.OK).body(sortedCategories);
    }

    /**
     * Obtém categorias de produtos filtradas por material.
     * <p>
     * Este método é acionado quando uma requisição HTTP GET é enviada para o endpoint "/category/material/{material}". Ele permite a obtenção de todas as categorias de produtos que correspondem ao material especificado.
     *
     * @param material O material pelo qual as categorias de produtos devem ser filtradas.
     * @return Um objeto ResponseEntity contendo uma lista de categorias de produtos que correspondem ao material e um status HTTP 200 (OK) se a operação for bem-sucedida. Se não houver categorias de produtos que correspondam ao material ou se ocorrerem erros, será retornado um status HTTP 404 (Not Found) ou outros códigos de status HTTP apropriados, juntamente com uma mensagem de erro.
     */
    @GetMapping("/material/{material}")
    public ResponseEntity<List<ProductCategory>> getProductCategoriesByMaterial(@PathVariable String material) {
        List<ProductCategory> categoriesByMaterial = productCategoryService.getProductCategoriesByMaterial(material);
        return ResponseEntity.status(HttpStatus.OK).body(categoriesByMaterial);
    }

    /**
     * Obtém categorias de produtos com base na descrição exata.
     * <p>
     * Este método é acionado quando uma requisição HTTP GET é enviada para o endpoint "/category/description-exact/{description}". Ele permite a obtenção de todas as categorias de produtos cuja descrição corresponde exatamente à descrição especificada.
     *
     * @param description A descrição exata a ser usada na pesquisa.
     * @return Um objeto ResponseEntity contendo uma lista de categorias de produtos cuja descrição corresponde exatamente à descrição especificada e um status HTTP 200 (OK) se a operação for bem-sucedida. Se não houver categorias de produtos que correspondam à descrição exata ou se ocorrerem erros, será retornado um status HTTP 404 (Not Found) ou outros códigos de status HTTP apropriados, juntamente com uma mensagem de erro.
     */
    @GetMapping("/description-exact/{description}")
    public ResponseEntity<List<ProductCategory>> getProductCategoriesByExactDescription(@PathVariable String description) {
        List<ProductCategory> categoriesByExactDescription = productCategoryService.getProductCategoriesByExactDescription(description);
        return ResponseEntity.status(HttpStatus.OK).body(categoriesByExactDescription);
    }
}