package com.generation.backend.controller;

import com.generation.backend.entity.OrderItem;
import com.generation.backend.service.OrderItemService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
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

/**
 * Esta classe é um controlador que lida com endpoints relacionados a itens de pedidos.
 * <p>
 *
 * @RestController - Indica que esta classe é um controlador Spring MVC.
 * <p>
 * @RequestMapping("/order-item") - Define o mapeamento de URL de base para todos os endpoints deste controlador como "/order-item".
 * <p>
 * @CrossOrigin(origins="*",allowedHeaders="*") - Permite solicitações de qualquer origem e com qualquer cabeçalho,
 * permitindo o acesso a este controlador a partir de diferentes domínios.
 * <p>
 * @apiNote - Esta classe é responsável por lidar com as operações relacionadas a itens de pedidos, como criação, busca e atualização.
 * Ela define os endpoints e utiliza o serviço OrderItemService para executar as operações.
 * <p>
 * @see OrderItemService
 */
@RestController
@RequestMapping("/order-item")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderItemController {

    /**
     * <p>
     * O serviço de itens de pedido a ser injetado neste controlador.
     * <p>
     * Este campo é responsável por manter uma referência à instância do serviço OrderItemService no controlador OrderItemController.
     * A anotação @Autowired é frequentemente usada para realizar a injeção de dependência, permitindo que o controlador acesse
     * e utilize os métodos e funcionalidades fornecidos pelo serviço para lidar com itens de pedido.
     * <p>
     *
     * @see OrderItemService
     */
    private final OrderItemService orderItemService;

    /**
     * Este construtor é responsável por injetar uma instância do serviço de itens de pedido no controlador.
     * <p>
     *
     * @param orderItemService O serviço de itens de pedido a ser injetado no controlador.
     *                         <p>
     * @apiNote - Este construtor é utilizado para injetar a dependência do serviço OrderItemService no controlador OrderItemController.
     * A anotação @Autowired é usada para realizar a injeção de dependência.
     * <p>
     * @example Exemplo de uso:
     * <p>
     * @Autowired <p>public OrderItemController(OrderItemService orderItemService) {
     * <p>    this.orderItemService = orderItemService;
     * <p>}<p>
     * <p>
     * Neste exemplo, uma instância de OrderItemService é injetada no controlador para que ele possa acessar e utilizar os métodos do serviço.
     * <p>
     * @see OrderItemService
     */
    @Contract(pure = true)
    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    /**
     * Este método é um endpoint REST que permite criar um novo item de pedido.
     * <p>
     *
     * @param orderItem O objeto de item de pedido a ser criado.
     *                  <p>
     * @return Uma ResponseEntity contendo o item de pedido recém-criado.
     * <p>
     * @apiNote - Endpoint: POST /create<p>
     * <p>- Este endpoint permite a criação de um novo item de pedido.<p>
     * <p>- O corpo da solicitação (RequestBody) deve conter um objeto de item de pedido a ser criado.
     * <p>
     * @implNote - Este método chama o serviço orderItemService.createOrderItem(orderItem)
     * para criar o item de pedido.
     * <p>
     * @returnCode - 200 OK: A operação de criação do item de pedido é realizada com sucesso e a resposta contém o item de pedido recém-criado.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de criação do item de pedido.
     * <p>
     * @example Exemplo de uso:
     * POST /create
     * Corpo da Solicitação (RequestBody):
     * <p>{
     * <p> "productId": 1,
     * <p> "quantity": 2
     * <p>}<p>
     * Neste exemplo, um novo item de pedido será criado com os dados fornecidos, incluindo o ID do produto e a quantidade.
     * <p>
     * @see OrderItemService#createOrderItem(OrderItem)
     */
    @PostMapping("/create")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem createdOrderItem = orderItemService.createOrderItem(orderItem);
        return ResponseEntity.ok(createdOrderItem);
    }

    /**
     * Este método é um endpoint REST que permite criar vários itens de pedido de uma vez.
     * <p>
     *
     * @param orderItems Uma coleção de objetos de itens de pedido a serem criados.
     *                   <p>
     * @return Uma ResponseEntity contendo uma coleção dos itens de pedido recém-criados.
     * <p>
     * @apiNote - Endpoint: POST /create-multiple<p>
     * <p>- Este endpoint permite a criação de vários itens de pedido de uma vez.<p>
     * <p>- O corpo da solicitação (RequestBody) deve conter uma coleção de objetos de itens de pedido a serem criados.
     * <p>
     * @implNote - Este método chama o serviço orderItemService.createMultipleOrderItems(orderItems)
     * para criar vários itens de pedido ao mesmo tempo.
     * <p>
     * @returnCode - 200 OK: A operação de criação dos itens de pedido é realizada com sucesso e a resposta contém a coleção dos itens recém-criados.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de criação dos itens de pedido.
     * @example Exemplo de uso:
     * POST /create-multiple
     * Corpo da Solicitação (RequestBody):
     * <p>[
     * <p> {
     * <p>   "productId": 1,
     * <p>  "quantity": 2
     * <p> },
     * <p>  {
     * <p>    "productId": 2,
     * <p>    "quantity": 3
     * <p> }
     * <p>]<p>
     * Neste exemplo, dois novos itens de pedido serão criados com os dados fornecidos, incluindo o ID do produto e a quantidade.
     * <p>
     * @see OrderItemService#createMultipleOrderItems(Iterable)
     */
    @PostMapping("/create-multiple")
    public ResponseEntity<Iterable<OrderItem>> createMultipleOrderItems(@RequestBody Iterable<OrderItem> orderItems) {
        Iterable<OrderItem> createdOrderItems = orderItemService.createMultipleOrderItems(orderItems);
        return ResponseEntity.ok(createdOrderItems);
    }

    /**
     * Este método é um endpoint REST que permite obter um item de pedido pelo seu ID.
     * <p>
     *
     * @param id O ID do item de pedido a ser buscado.
     *           <p>
     * @return Uma ResponseEntity contendo o item de pedido encontrado.
     * <p>
     * @apiNote - Endpoint: GET /read/{id}<p>
     * <p>- Este endpoint permite buscar um item de pedido com base no ID fornecido como parâmetro na URL.<p>
     * <p>- O parâmetro {id} na URL deve ser preenchido com o ID do item de pedido desejado.
     *
     * <p>
     * @implNote - Este método chama o serviço orderItemService.getOrderItemById(id)
     * para buscar o item de pedido pelo ID.
     * <p>
     * @returnCode - 200 OK: A operação de busca do item de pedido é realizada com sucesso e a resposta contém o item de pedido encontrado.<p>
     * <p>- 404 Not Found: Se nenhum item de pedido com o ID fornecido for encontrado.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de busca do item de pedido.
     * <p>
     * @example Exemplo de uso:
     * GET /read/1
     * Neste exemplo, o parâmetro {id} na URL é "1".
     * Isso resultará na busca do item de pedido com o ID igual a 1.
     * <p>
     * @see OrderItemService#getOrderItemById(Long)
     */
    @GetMapping("/read/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);
        if (orderItem != null) {
            return ResponseEntity.ok(orderItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Este método é um endpoint REST que permite obter todos os itens de pedido existentes.
     * <p>
     *
     * @return Uma ResponseEntity contendo uma coleção de todos os itens de pedido.
     * <p>
     * @apiNote - Endpoint: GET /read-all<p>
     * <p>- Este endpoint permite buscar todos os itens de pedido cadastrados no sistema.
     * <p>
     * @implNote - Este método chama o serviço orderItemService.getAllOrderItems()
     * para buscar todos os itens de pedido.
     * <p>
     * @returnCode - 200 OK: A operação de busca de todos os itens de pedido é realizada com sucesso e a resposta contém a coleção de itens de pedido.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de busca de todos os itens de pedido.
     * <p>
     * @example Exemplo de uso:
     * GET /read-all
     * Neste exemplo, o endpoint buscará e retornará todos os itens de pedido cadastrados no sistema.
     * <p>
     * @see OrderItemService#getAllOrderItems()
     */
    @GetMapping("/read-all")
    public ResponseEntity<Iterable<OrderItem>> getAllOrderItems() {
        Iterable<OrderItem> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    /**
     * Este método é um endpoint REST que permite atualizar um item de pedido existente pelo seu ID.
     * <p>
     *
     * @param id        O ID do item de pedido a ser atualizado.
     *                  <p>
     * @param orderItem O objeto de item de pedido com as informações atualizadas.
     *                  <p>
     * @return Uma ResponseEntity contendo o item de pedido atualizado.
     * <p>
     * @apiNote - Endpoint: PUT /update/{id}
     * <p>- Este endpoint permite atualizar um item de pedido existente com base no ID fornecido como parâmetro na URL.<p>
     * <p>- O parâmetro {id} na URL deve ser preenchido com o ID do item de pedido que se deseja atualizar.<p>
     * <p>- O corpo da solicitação (RequestBody) deve conter um objeto de item de pedido com as informações atualizadas.
     * <p>
     * @implNote - Este método chama o serviço orderItemService.updateOrderItem(orderItem)
     * para atualizar o item de pedido pelo ID.
     * <p>
     * @returnCode - 200 OK: A operação de atualização do item de pedido é realizada com sucesso e a resposta contém o item de pedido atualizado.<p>
     * <p>- 400 Bad Request: Se o ID fornecido na URL não coincidir com o ID no corpo da solicitação (orderItem).<p>
     * <p> - 404 Not Found: Se nenhum item de pedido com o ID fornecido for encontrado.<p>
     * <p> - 500 Internal Server Error: Se ocorrerem erros durante a operação de atualização do item de pedido.
     * <p>
     * @example Exemplo de uso:
     * PUT /update/1
     * Corpo da Solicitação (RequestBody):
     * <p>{
     * <p>  "productId": 2,
     * <p>  "quantity": 5
     * <p>}<p>
     * Neste exemplo, o item de pedido com ID igual a 1 será atualizado com as informações fornecidas no corpo da solicitação.
     * <p>
     * @see OrderItemService#updateOrderItem(OrderItem)
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id,
                                                     @RequestBody @NotNull OrderItem orderItem) {
        orderItem.setId(id);
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(orderItem);
        if (updatedOrderItem != null) {
            return ResponseEntity.ok(updatedOrderItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Este método é um endpoint REST que permite excluir um item de pedido pelo seu ID.
     * <p>
     *
     * @param id O ID do item de pedido a ser excluído.
     *           <p>
     * @return Uma ResponseEntity sem corpo para indicar que a operação foi realizada com sucesso.
     * <p>
     * @apiNote - Endpoint: DELETE /delete/{id}<p>
     * <p>- Este endpoint permite excluir um item de pedido existente com base no ID fornecido como parâmetro na URL.<p>
     * <p>- O parâmetro {id} na URL deve ser preenchido com o ID do item de pedido que se deseja excluir.
     *
     * <p>
     * @implNote - Este método chama o serviço orderItemService.deleteOrderItemById(id)
     * para excluir o item de pedido pelo ID.
     * <p>
     * @returnCode - 200 OK: A operação de exclusão do item de pedido é realizada com sucesso.<p>
     * <p>- 404 Not Found: Se nenhum item de pedido com o ID fornecido for encontrado.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de exclusão do item de pedido.
     * <p>
     * @example Exemplo de uso:
     * DELETE /delete/1
     * Neste exemplo, o item de pedido com ID igual a 1 será excluído.
     * <p>
     * @see OrderItemService#deleteOrderItemById(Long)
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItemById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Este método é um endpoint REST que permite excluir todos os itens de pedido existentes.
     * <p>
     *
     * @return Uma ResponseEntity sem corpo para indicar que a operação foi realizada com sucesso.
     * <p>
     * @apiNote - Endpoint: DELETE /delete-all<p>
     * <p>- Este endpoint permite excluir todos os itens de pedido cadastrados no sistema.
     * <p>
     * @implNote - Este método chama o serviço orderItemService.deleteAllOrderItems()
     * para excluir todos os itens de pedido.
     * <p>
     * @returnCode - 200 OK: A operação de exclusão de todos os itens de pedido é realizada com sucesso.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de exclusão dos itens de pedido.
     * <p>
     * @example Exemplo de uso:
     * DELETE /delete-all
     * Neste exemplo, todos os itens de pedido cadastrados no sistema serão excluídos.
     * <p>
     * @see OrderItemService#deleteAllOrderItems()
     */
    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAllOrderItems() {
        orderItemService.deleteAllOrderItems();
        return ResponseEntity.ok().build();
    }
}