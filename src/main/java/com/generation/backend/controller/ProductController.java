package com.generation.backend.controller;

import com.generation.backend.entity.Product;
import com.generation.backend.entity.ProductCategory;
import com.generation.backend.service.ProductService;
import org.jetbrains.annotations.Contract;
import org.springframework.http.ResponseEntity;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Esta classe é um controlador que lida com operações relacionadas a produtos em um sistema.
 * Ela fornece endpoints REST para criar, atualizar, recuperar e excluir produtos.
 * Além disso, permite acesso cruzado (CORS) de todas as origens e cabeçalhos permitidos.
 *
 * <p>
 * @RestController</p> Indica que esta classe é um controlador REST. </p>
 * @RequestMapping</p> Define o mapeamento de URL base para todos os endpoints desta classe.
 * @CrossOrigin</p> Permite o acesso cruzado (CORS) de todas as origens e cabeçalhos.
 * </p>
 */
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    /**
     * O serviço para produtos que fornece operações relacionadas a produtos.
     */
    private final ProductService productService;

    /**
     * Cria um novo controlador de produtos.
     *
     * @param productService O serviço para produtos.
     */
    @Contract(pure = true)
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Este método é um endpoint REST que permite criar um único produto.
     *
     * <p>
     *
     * @param product O objeto de produto a ser criado.</p>
     *                <p>
     * @return Uma ResponseEntity contendo o produto recém-criado.</p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a operação de criação do produto.</p>
     *                   <p>
     * @apiNote</p> - Endpoint: POST /create</p>
     * - O corpo da solicitação (RequestBody) deve conter um objeto de produto a ser criado.</p>
     * <p>
     * @implNote Este método chama o serviço productService.createProduct(product) para criar um único produto.</p>
     * <p>
     * @returnCode</p> - 200 OK: A operação de criação do produto é realizada com sucesso e a resposta contém o produto recém-criado.</p>
     * - 500 Internal Server Error: Se ocorrerem erros durante a operação de criação do produto.</p>
     * <p>
     * @example</p> Exemplo: Criar um único produto.</p>
     * <p>POST /create</p>
     * <p>Corpo da Solicitação (RequestBody):</p>
     *
     * <p>
     * <p>{ </p>
     * <p> "id": 1,</p>
     * <p> "name": "Produto Novo",</p>
     * <p> "price": 39.99</p>
     * <p>}</p>
     * </p>
     * @see ProductService#createProduct(Product)
     */
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    /**
     * Este método é um endpoint REST que permite criar vários produtos de uma só vez.
     *
     * <p>
     *
     * @param products Uma coleção de objetos de produto a serem criados.</p>
     *                 <p>
     * @return Uma ResponseEntity contendo a coleção de produtos recém-criados.</p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a operação de criação dos produtos.</p>
     *                   <p>
     * @apiNote</p> - Endpoint: POST /create-multiple</p>
     * - O corpo da solicitação (RequestBody) deve conter uma coleção de objetos de produto a serem criados.</p>
     * <p>
     * @implNote Este método chama o serviço productService.createMultipleProducts(products) para criar vários produtos de uma só vez.</p>
     * <p>
     * @returnCode</p> - 200 OK: A operação de criação dos produtos é realizada com sucesso e a resposta contém a coleção de produtos recém-criados.</p>
     * - 500 Internal Server Error: Se ocorrerem erros durante a operação de criação dos produtos.</p>
     * <p>
     * @example</p> Exemplo: Criar vários produtos de uma só vez.</p>
     * <p>POST /create-multiple</p>
     * <p>Corpo da Solicitação (RequestBody):</p>
     *
     * <p>
     * <p>[ </p>
     * <p>  {</p>
     * <p>      "id": 1,</p>
     * <p>      "name": "Produto 1",</p>
     * <p>      "price": 19.99</p>
     * <p> },</p>
     * <p>  {</p>
     * <p>      "id": 2,</p>
     * <p>      "name": "Produto 2",</p>
     * <p>      "price": 29.99</p>
     * <p> }</p>
     * <p>]</p>
     * </p>
     * @see ProductService#createMultipleProducts(Iterable)
     */
    @PostMapping("/create-multiple")
    public ResponseEntity<Iterable<Product>> createMultipleProducts(@RequestBody Iterable<Product> products) {
        Iterable<Product> createdProducts = productService.createMultipleProducts(products);
        return ResponseEntity.ok(createdProducts);
    }

    /**
     * Este método é um endpoint REST que permite recuperar todas as informações de produtos disponíveis.
     *
     * <p>
     *
     * @return Uma ResponseEntity contendo uma coleção de produtos disponíveis.</p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a operação de recuperação dos produtos.</p>
     *                   <p>
     * @apiNote</p> - Endpoint: GET /all</p>
     * <p>
     * @implNote Este método chama o serviço productService.getAllProducts() para recuperar todas as informações de produtos disponíveis.</p>
     * <p>
     * @returnCode</p> - 200 OK: A operação de recuperação dos produtos é realizada com sucesso e a resposta contém a coleção de produtos disponíveis.</p>
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a operação de recuperação dos produtos.</p>
     * <p>
     * @example</p> Exemplo: Recuperar todas as informações de produtos disponíveis.</p>
     * <p>GET /all</p>
     * @see ProductService#getAllProducts()
     */
    @GetMapping("/all")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        Iterable<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Este método é um endpoint REST que permite recuperar informações de um produto pelo seu ID.
     *
     * <p>
     *
     * @param id O ID do produto a ser recuperado.</p>
     *           <p>
     * @return Uma ResponseEntity contendo o produto correspondente ao ID especificado.</p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a operação de recuperação do produto.</p>
     *                   <p>
     * @apiNote</p> - Endpoint: GET /{id}</p>
     * - O parâmetro "id" especifica o ID do produto a ser recuperado.</p>
     * <p>
     * @implNote Este método chama o serviço productService.getProductById(id) para recuperar as informações do produto pelo ID.</p>
     * <p>
     * @returnCode</p> - 200 OK: A operação de recuperação do produto é realizada com sucesso e a resposta contém o produto correspondente ao ID especificado.</p>
     * <p> - 404 Not Found: Se não for encontrado nenhum produto com o ID especificado.</p>
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a operação de recuperação do produto.</p>
     * <p>
     * @example</p> Exemplo: Recuperar informações de um produto pelo ID.</p>
     * <p>GET /123</p>
     * @see ProductService#getProductById(Long)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Este método é um endpoint REST que permite recuperar informações de um produto pelo seu nome.
     *
     * <p>
     *
     * @param name O nome do produto a ser recuperado.</p>
     *             <p>
     * @return Uma ResponseEntity contendo o produto correspondente ao nome especificado.</p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a operação de recuperação do produto.</p>
     *                   <p>
     * @apiNote</p> - Endpoint: GET /name/{name}</p>
     * - O parâmetro "name" especifica o nome do produto a ser recuperado.</p>
     * <p>
     * @implNote Este método chama o serviço productService.getProductByName(name) para recuperar as informações do produto pelo nome.</p>
     * <p>
     * @returnCode</p> - 200 OK: A operação de recuperação do produto é realizada com sucesso e a resposta contém o produto correspondente ao nome especificado.</p>
     * <p> - 404 Not Found: Se não for encontrado nenhum produto com o nome especificado.</p>
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a operação de recuperação do produto.</p>
     *
     * <p>
     * @example</p> Exemplo: Recuperar informações de um produto pelo nome.</p>
     * <p>GET /name/ProdutoA</p>
     * </p>
     * @see ProductService#getProductByName(String)
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productService.getProductByName(name);
        return ResponseEntity.ok(product);
    }

    /**
     * Este método é um endpoint REST que permite atualizar as informações de um produto.
     *
     * <p>
     *
     * @param product O objeto de produto contendo as informações atualizadas.</p>
     *                <p>
     * @return Uma ResponseEntity contendo o produto atualizado com sucesso.</p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a operação de atualização das informações do produto.</p>
     *                   <p>
     * @apiNote</p> - Endpoint: PUT /update</p>
     * - O corpo da solicitação (RequestBody) deve conter um objeto de produto com as informações atualizadas.</p>
     * <p>
     * @implNote Este método é transacional e chama o serviço productService.updateProduct(product) para atualizar as informações do produto.</p>
     * <p>
     * @returnCode</p> - 200 OK: A operação de atualização das informações do produto é realizada com sucesso e a resposta contém o produto atualizado.</p>
     * <p> - 400 Bad Request: Se os dados fornecidos no objeto de produto não forem válidos.</p>
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a operação de atualização das informações do produto.</p>
     *
     * <p>
     * @example</p> Exemplo: Atualizar as informações de um produto com os dados atualizados.</p>
     * <p>PUT /update</p>
     * <p>Corpo da Solicitação (RequestBody):</p>
     *
     * <p>
     * <p>{ </p>
     * <p> "id": 123,</p>
     * <p> "name": "Novo Nome do Produto",</p>
     * <p> "price": 49.99</p>
     * <p>}</p>
     * </p>
     * @see ProductService#updateProduct(Product)
     */
    @PutMapping("/update")
    @Transactional
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);

        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Este método é um endpoint REST que permite atualizar o preço de um produto.
     *
     * <p>
     *
     * @param product O objeto de produto contendo as informações atualizadas, incluindo o ID e o novo preço.</p>
     *                <p>
     * @return Uma ResponseEntity contendo o produto atualizado com sucesso.</p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a operação de atualização do preço do produto.</p>
     *                   <p>
     * @apiNote</p> - Endpoint: PUT /update-price</p>
     * - O corpo da solicitação (RequestBody) deve conter um objeto de produto com as informações atualizadas, incluindo o ID e o novo preço.</p>
     * <p>
     * @implNote Este método é transacional e chama o serviço productService.updateProductPrice(product) para atualizar o preço do produto.</p>
     * <p>
     * @returnCode</p> <p> 200 OK: A operação de atualização do preço do produto é realizada com sucesso e a resposta contém o produto atualizado.</p>
     * <p> 400 Bad Request: Se os dados fornecidos no objeto de produto não forem válidos.</p>
     * <p> 500 Internal Server Error: Se ocorrerem erros durante a operação de atualização do preço do produto.</p>
     * <p>
     * @example</p> Exemplo: Atualizar o preço de um produto com as informações atualizadas.</p>
     * PUT /update-price</p>
     * Corpo da Solicitação (RequestBody):</p>
     * {
     * "id": 123,
     * "price": 49.99
     * }</p>
     * @see ProductService#updateProductPrice(Product)
     */
    @PutMapping("/update-price")
    @Transactional
    public ResponseEntity<Product> updateProductPrice(@RequestBody Product product) {
        Product updatedProduct = productService.updateProductPrice(product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Este método é um endpoint REST que permite excluir um produto com base em seu ID.
     *
     * <p>
     *
     * @param id O ID do produto a ser excluído.</p>
     *           <p>
     * @return Uma ResponseEntity vazia indicando que a operação de exclusão foi concluída com sucesso.</p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a operação de exclusão do produto.</p>
     *                   <p>
     * @apiNote - Endpoint: DELETE /delete/{id}</p>
     * - O parâmetro "id" especifica o ID do produto a ser excluído.
     * <p>
     * @implNote Este método é transacional e chama o serviço productService.deleteProductById(id) para excluir o produto com o ID especificado.</p>
     * <p>
     * @returnHTTP 200 OK: A operação de exclusão do produto é realizada com sucesso e a resposta indica sucesso.
     * <p>500 Internal Server Error: Se ocorrerem erros durante a operação de exclusão do produto.</p>
     * </p>
     *
     * <p>
     * @example <b>Exemplo</b>: Excluir um produto com o ID 123.
     * DELETE /delete/123</p>
     * </p>
     * @see ProductService#deleteProductById(Long)
     */
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Este método é um endpoint REST que permite excluir todos os produtos do sistema.
     *
     * <p>
     *
     * @return Uma ResponseEntity contendo um mapa com a resposta da operação de exclusão de todos os produtos.</p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a operação de exclusão dos produtos.</p>
     *                   <p>
     * @apiNote - Endpoint: DELETE /delete-all</p>
     * - Este endpoint exclui todos os produtos do sistema.
     * <p>
     * @implNote Este método é transacional e chama o serviço productService.deleteAllProducts() para excluir todos os produtos.</p>
     * <p>
     * @returnHTTP 200 OK: A operação de exclusão de todos os produtos é realizada com sucesso e a resposta indica sucesso.
     * <p>500 Internal Server Error: Se ocorrerem erros durante a operação de exclusão dos produtos.</p>
     * </p>
     *
     * <p>
     * @example <b>Exemplo</b>: Excluir todos os produtos do sistema.
     * DELETE /delete-all</p>
     * </p>
     * @see ProductService#deleteAllProducts()
     */
    @DeleteMapping("/delete-all")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteAllProducts() {
        Map<String, String> response = productService.deleteAllProducts();
        return ResponseEntity.ok(response);
    }

    /**
     * Este método é um endpoint REST que permite excluir um produto com base em seu nome.
     *
     * <p>
     *
     * @param name O nome do produto a ser excluído.</p>
     *             <p>
     * @return Uma ResponseEntity contendo um mapa com a resposta da operação de exclusão.</p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a operação de exclusão do produto.</p>
     *                   <p>
     * @apiNote - Endpoint: DELETE /delete-by-name/{name}</p>
     * - O parâmetro "name" especifica o nome do produto a ser excluído.
     * <p>
     * @implNote Este método é transacional e chama o serviço productService.deleteProductByName(name) para excluir o produto com o nome especificado.</p>
     * <p>
     * @returnHTTP 200 OK: A operação de exclusão é realizada com sucesso e a resposta indica sucesso.
     * <p>400 Bad Request: Se o nome fornecido não for válido ou se a operação de exclusão não for possível.</p>
     * <p>500 Internal Server Error: Se ocorrerem erros durante a operação de exclusão do produto.</p>
     * </p>
     *
     * <p>
     * @example <b>Exemplo</b>: Excluir um produto com o nome "Produto A".
     * DELETE /delete-by-name/Produto%20A</p>
     * </p>
     * @see ProductService#deleteProductByName(String)
     */
    @DeleteMapping("/delete-by-name/{name}")
    @Transactional
    public ResponseEntity<Map<String, String>> deleteProductByName(@PathVariable String name) {
        Map<String, String> response = productService.deleteProductByName(name);
        return ResponseEntity.ok(response);
    }

    /**
     * Este método é um endpoint REST que permite recuperar uma lista de produtos com base em uma categoria específica.
     *
     * <p>
     *
     * @param category A categoria dos produtos a serem recuperados.</p>
     *                 <p>
     * @return Uma ResponseEntity contendo uma lista de produtos correspondentes à categoria especificada.</p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a recuperação dos produtos.</p>
     *                   <p>
     * @apiNote - Endpoint: GET /by-category/{category}</p>
     * - O parâmetro "category" especifica a categoria dos produtos a serem recuperados.
     * <p>
     * @implNote Este método chama o serviço productService.getProductsByCategory(category) para recuperar a lista de produtos da categoria especificada.</p>
     * <p>
     * @returnHTTP 200 OK: A lista de produtos é retornada com sucesso.</p>
     * <p>400 Bad Request: Se a categoria fornecida não for válida.</p>
     * <p>500 Internal Server Error: Se ocorrerem erros durante a recuperação dos produtos.</p>
     * </p>
     *
     * <p>
     * @example <b>Exemplo 1</b>: Recuperar todos os produtos da categoria "Eletrônicos".
     * GET /by-category/Eletrônicos</h>
     *
     * <p><b>Exemplo 2</b>: Recuperar todos os produtos da categoria "Roupas".
     * GET /by-category/Roupas</p> </p>
     * @see ProductService#getProductsByCategory(ProductCategory)
     */
    @GetMapping("/by-category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable ProductCategory category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    /**
     * Este método é um endpoint REST que permite recuperar uma lista de produtos com base em seu status de ativação.
     * <p>
     *
     * @param isActivated Um valor booleano que indica se os produtos estão ativos ou inativos.</p>
     *                    <p>
     * @return Uma ResponseEntity contendo uma lista de produtos correspondentes ao status de ativação especificado.</p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a recuperação dos produtos.</p>
     *                   <p>
     * @apiNote - Endpoint: GET /activated/{isActivated}</p>
     * - O parâmetro "isActivated" indica se os produtos devem ser ativos (true) ou inativos (false).
     * <p>
     * @implNote Este método chama o serviço productService.getProductsByActivation(isActivated) para recuperar a lista de produtos com base no status de ativação especificado.</p>
     * <p>
     * @returnHTTP 200 OK: A lista de produtos é retornada com sucesso.</p>
     * <p>400 Bad Request: Se o valor do parâmetro "isActivated" não for válido.</p>
     * <p>500 Internal Server Error: Se ocorrerem erros durante a recuperação dos produtos.</p>
     * </p>
     *
     * <p>
     * @example <b>Exemplo 1</b>: Recuperar todos os produtos ativos.
     * GET /activated/true</h>
     *
     * <p><b>Exemplo 2</b>: Recuperar todos os produtos inativos.
     * GET /activated/false</p> </p>
     * @see ProductService#getProductsByActivation(Boolean)
     */
    @GetMapping("/activated/{isActivated}")
    public ResponseEntity<List<Product>> getProductsByActivation(@PathVariable Boolean isActivated) {
        List<Product> products = productService.getProductsByActivation(isActivated);
        return ResponseEntity.ok(products);
    }

    /**
     * Este método é um endpoint REST que permite recuperar uma lista de produtos com base em um intervalo de preço especificado.
     *
     * <p>
     *
     * @param minPrice O preço mínimo do intervalo (opcional).
     * @param maxPrice O preço máximo do intervalo (opcional).
     *                 </p>
     *                 <p>
     * @return Uma ResponseEntity contendo uma lista de produtos correspondentes ao intervalo de preço especificado.
     * </p>
     * <p>
     * @throws Exception Se ocorrerem erros durante a recuperação dos produtos.
     *                   </p>
     *
     *                   <p>
     * @apiNote - Endpoint: GET /by-price
     * - O parâmetro "minPrice" é opcional e representa o preço mínimo do intervalo.
     * - O parâmetro "maxPrice" é opcional e representa o preço máximo do intervalo.
     * </p>
     *
     * <p>
     * @implNote Este método chama o serviço productService.getProductsByPriceRange(minPrice, maxPrice) para recuperar a lista de produtos dentro do intervalo de preço especificado.
     * </p>
     * @returnCode - 200 OK: A lista de produtos é retornada com sucesso.
     * - 400 Bad Request: Se os parâmetros fornecidos não são válidos.
     * - 500 Internal Server Error: Se ocorrerem erros durante a recuperação dos produtos.
     * </p>
     *
     * <p>
     * @example <b>Exemplo 1</b>: Recuperar todos os produtos dentro do intervalo de preço de 50 a 100.
     * GET /by-price?minPrice=50&maxPrice=100
     * </p>
     *
     * <p>
     * <b>Exemplo 2</b>: Recuperar todos os produtos sem especificar um intervalo de preço.
     * GET /by-price
     * </p>
     * @see ProductService#getProductsByPriceRange(BigDecimal, BigDecimal)
     */
    @GetMapping("/by-price")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam(name = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice) {

        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
}