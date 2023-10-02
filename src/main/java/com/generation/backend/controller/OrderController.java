package com.generation.backend.controller;

import com.generation.backend.entity.Order;
import com.generation.backend.service.OrderService;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.Contract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Esta classe é um controlador que lida com endpoints relacionados a pedidos.
 * <p>
 * @RestController - Indica que esta classe é um controlador Spring MVC.
 * <p>
 * @RequestMapping("/order") - Define o mapeamento de URL de base para todos os endpoints deste controlador como "/order".
 * <p>
 * @CrossOrigin(origins="*",allowedHeaders="*") - Permite solicitações de qualquer origem e com qualquer cabeçalho,
 * permitindo o acesso a este controlador a partir de diferentes domínios.
 * <p>
 * @apiNote - Esta classe é responsável por lidar com as operações relacionadas a pedidos, como criação, busca e atualização.
 * Ela define os endpoints e utiliza o serviço OrderService para executar as operações.
 * <p>
 * @see OrderService
 */
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    /**
     * <p>
     * O serviço de pedidos a ser injetado neste controlador.
     * <p>
     * Este campo é responsável por manter uma referência à instância do serviço OrderService no controlador OrderController.
     * A anotação @Autowired é frequentemente usada para realizar a injeção de dependência, permitindo que o controlador acesse
     * e utilize os métodos e funcionalidades fornecidos pelo serviço para lidar com pedidos.
     *<p>
     * @see OrderService
     */
    private final OrderService orderService;

    /**
     * Este construtor é responsável por injetar uma instância do serviço de pedidos no controlador.
     *<p>
     * @param orderService O serviço de pedidos a ser injetado no controlador.
     *                     <p>
     * @apiNote - Este construtor é utilizado para injetar a dependência do serviço OrderService no controlador OrderController.
     * A anotação @Autowired é usada para realizar a injeção de dependência.
     * <p>
     * @example Exemplo de uso:
     * <p>@Autowired public OrderController(OrderService orderService) {
     * <p>this.orderService = orderService;
     * <p>}<p>
     * <p>
     * Neste exemplo, uma instância de OrderService é injetada no controlador para que ele possa acessar e utilizar os métodos do serviço.
     * <p>
     * @see OrderService
     */
    @Contract(pure = true)
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Este método é um endpoint REST que permite criar um novo pedido.
     *<p>
     * @param order O objeto de pedido a ser criado.
     *              <p>
     * @return Uma ResponseEntity contendo o pedido recém-criado.
     * <p>
     * @apiNote - Endpoint: POST /create<p>
     *<p> - Este endpoint permite a criação de um novo pedido.
     * <p>- O corpo da solicitação (RequestBody) deve conter um objeto de pedido a ser criado.
     * <p>
     * @implNote - Este método chama o serviço orderService.createOrder(order)
     * para criar o pedido.
     * <p>
     * @returnCode - 200 OK: A operação de criação do pedido é realizada com sucesso e a resposta contém o pedido recém-criado.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de criação do pedido.
     * <p>
     * @example Exemplo de uso:
     * POST /create
     * Corpo da Solicitação (RequestBody):
     * <p>
     * <p>{
     * <p>"customerName": "João da Silva",
     * <p>"totalAmount": 100.0
     * <p>}<p>
     *     <p>
     * Neste exemplo, um novo pedido será criado com as informações fornecidas, incluindo o nome do cliente e o valor total.
     * <p>
     * @see OrderService#createOrder(Order)
     */
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    /**
     * Este método é um endpoint REST que permite criar vários pedidos de uma vez.
     *<p>
     * @param orders Uma coleção de objetos de pedido a serem criados.
     *               <p>
     * @return Uma ResponseEntity contendo uma coleção dos pedidos recém-criados.
     * <p>
     * @apiNote - Endpoint: POST /create-multiple<p>
     * <p>- Este endpoint permite a criação de vários pedidos de uma vez.
     * <p>- O corpo da solicitação (RequestBody) deve conter uma coleção de objetos de pedido a serem criados.
     * <p>
     * @implNote - Este método chama o serviço orderService.createMultipleOrders(orders)
     * para criar vários pedidos ao mesmo tempo.
     * <p>
     * @returnCode - 200 OK: A operação de criação dos pedidos é realizada com sucesso e a resposta contém a coleção dos pedidos recém-criados.
     * - 500 Internal Server Error: Se ocorrerem erros durante a operação de criação dos pedidos.
     * <p>
     * @example Exemplo de uso:
     * POST /create-multiple
     * Corpo da Solicitação (RequestBody):
     * <p>
     * <p>[
     * <p>{
     * <p>"customerName": "Maria Santos",
     * <p>"totalAmount": 150.0
     * <p>},
     * <p>{
     * <p>"customerName": "José Oliveira",
     * <p>"totalAmount": 200.0
     * <p>}
     * <p>]<p>
     *     <p>
     * Neste exemplo, dois novos pedidos serão criados com as informações fornecidas, incluindo o nome do cliente e o valor total.
     * <p>
     * @see OrderService#createMultipleOrders(Iterable)
     */
    @PostMapping("/create-multiple")
    public ResponseEntity<Iterable<Order>> createMultipleOrders(@RequestBody Iterable<Order> orders) {
        Iterable<Order> createdOrders = orderService.createMultipleOrders(orders);
        return ResponseEntity.ok(createdOrders);
    }

    /**
     * Este método é um endpoint REST que permite buscar um pedido pelo seu ID.
     *<p>
     * @param id O ID do pedido a ser buscado.
     *           <p>
     * @return Uma ResponseEntity contendo o pedido encontrado.
     * <p>
     * @apiNote
     * - Endpoint: GET /read/{id}<p>
     * <p>- Este endpoint permite buscar um pedido com base no ID fornecido como parâmetro na URL.<p>
     *<p> - O parâmetro {id} na URL deve ser preenchido com o ID do pedido desejado.
     * <p>
     * @implNote
     * - Este método chama o serviço orderService.getOrderById(id)
     *   para buscar o pedido pelo ID.
     *   <p>
     * @returnCode
     * - 200 OK: A operação de busca do pedido é realizada com sucesso e a resposta contém o pedido encontrado.<p>
     * <p>- 404 Not Found: Se nenhum pedido com o ID fornecido for encontrado.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de busca do pedido.
     * <p>
     * @example
     * Exemplo de uso:
     * GET /read/1
     * Neste exemplo, o parâmetro {id} na URL é "1".
     * Isso resultará na busca do pedido com o ID igual a 1.
     * <p>
     * @see OrderService#getOrderById(Long)
     */
    @GetMapping("/read/{id}")
    public ResponseEntity<Order> readOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Este método é um endpoint REST que permite buscar todos os pedidos existentes.
     *<p>
     * @return Uma ResponseEntity contendo uma coleção de todos os pedidos.
     * <p>
     * @apiNote
     * - Endpoint: GET /read-all<p>
     * <p>- Este endpoint permite buscar todos os pedidos cadastrados no sistema.
     * <p>
     * @implNote
     * - Este método chama o serviço orderService.getAllOrders()
     *   para buscar todos os pedidos.
     *   <p>
     * @returnCode
     * - 200 OK: A operação de busca de todos os pedidos é realizada com sucesso e a resposta contém a coleção de pedidos.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de busca de todos os pedidos.
     * <p>
     * @example
     * Exemplo de uso:
     * GET /read-all
     * Neste exemplo, o endpoint buscará e retornará todos os pedidos cadastrados no sistema.
     * <p>
     * @see OrderService#getAllOrders()
     */
    @GetMapping("/read-all")
    public ResponseEntity<Iterable<Order>> readAllOrders() {
        Iterable<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Este método é um endpoint REST que permite atualizar um pedido existente.
     *<p>
     * @param order O objeto de pedido com as informações atualizadas.
     *              <p>
     * @return Uma ResponseEntity contendo o pedido atualizado.
     * <p>
     * @apiNote
     * - Endpoint: PUT /update/{id}<p>
     *<p> - Este endpoint permite atualizar um pedido existente com base no ID fornecido no objeto de pedido.<p>
     * <p>- O corpo da solicitação (RequestBody) deve conter um objeto de pedido com as informações atualizadas.
     * <p>
     * @implNote
     * - Este método chama o serviço orderService.updateOrder(order)
     *   para atualizar o pedido com base nas informações fornecidas.
     *   <p>
     * @returnCode
     * - 200 OK: A operação de atualização do pedido é realizada com sucesso e a resposta contém o pedido atualizado.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de atualização do pedido.
     * <p>
     * @example
     * Exemplo de uso:
     * PUT /update/1
     * Corpo da Solicitação (RequestBody):
     * <p>
     * <p>{
     * <p>  "id": 1,
     *  <p> "customerName": "Ana Silva",
     *  <p> "totalAmount": 120.0
     * <p>}<p>
     *     <p>
     * Neste exemplo, o pedido com ID igual a 1 será atualizado com as informações fornecidas no corpo da solicitação.
     * <p>
     * @see OrderService#updateOrder(Order)
     */
    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(order);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Este método é um endpoint REST que permite excluir um pedido pelo seu ID.
     *<p>
     * @param id O ID do pedido a ser excluído.
     *           <p>
     * @return Uma ResponseEntity contendo uma mensagem de confirmação.
     * <p>
     * @apiNote
     * - Endpoint: DELETE /delete/{id}<p>
     * <p>- Este endpoint permite excluir um pedido existente com base no ID fornecido como parâmetro na URL.<p>
     * <p>- O parâmetro {id} na URL deve ser preenchido com o ID do pedido que se deseja excluir.
     * <p>
     * @implNote
     * - Este método chama o serviço orderService.deleteOrderById(id)
     *   para excluir o pedido pelo ID.
     *   <p>
     * @returnCode
     * - 200 OK: A operação de exclusão do pedido é realizada com sucesso e a resposta contém uma mensagem de confirmação.<p>
     * <p>- 404 Not Found: Se nenhum pedido com o ID fornecido for encontrado.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de exclusão do pedido.
     * <p>
     * @example
     * Exemplo de uso:
     * DELETE /delete/1
     * Neste exemplo, o pedido com ID igual a 1 será excluído e a resposta conterá a mensagem "Order deleted successfully".
     * <p>
     * @see OrderService#deleteOrderById(Long)
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("Order deleted successfully");
    }

    /**
     * Este método é um endpoint REST que permite excluir todos os pedidos existentes.
     *<p>
     * @return Uma ResponseEntity contendo uma mensagem de confirmação.
     * <p>
     * @apiNote
     * - Endpoint: DELETE /delete-all<p>
     * <p>- Este endpoint permite excluir todos os pedidos cadastrados no sistema.
     * <p>
     * @implNote
     * - Este método chama o serviço orderService.deleteAllOrders()
     *   para excluir todos os pedidos.
     *   <p>
     * @returnCode
     * - 200 OK: A operação de exclusão de todos os pedidos é realizada com sucesso e a resposta contém uma mensagem de confirmação.<p>
     * <p>- 500 Internal Server Error: Se ocorrerem erros durante a operação de exclusão de todos os pedidos.
     * <p>
     * @example
     * Exemplo de uso:
     * DELETE /delete-all
     * Neste exemplo, todos os pedidos cadastrados no sistema serão excluídos e a resposta conterá a mensagem "All orders deleted successfully".
     * <p>
     * @see OrderService#deleteAllOrders()
     */
    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllOrders() {
        orderService.deleteAllOrders();
        return ResponseEntity.ok("All orders deleted successfully");
    }
}