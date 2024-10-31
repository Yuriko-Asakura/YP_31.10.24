package com.example.office_store.Controller;

import com.example.office_store.model.*;
import com.example.office_store.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/men")
@PreAuthorize("hasAnyAuthority('MENED')")
public class MenedgerController {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderitemRepo orderitemRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShopRepo shoppingCartRepo;

    @GetMapping("/view")
    public String list(Model model) {
        model.addAttribute("orderList", orderRepo.findAll());
        return "view";
    }

    @GetMapping("/view_order")
    public String listOrders(Model model) {
        model.addAttribute("orderList", orderRepo.findAll());
        return "order_view";
    }

    @GetMapping("/{id}/update")
    public String updateOrderView(@PathVariable Long id, Model model) {
        model_Order order = orderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + id));
        model.addAttribute("order", order);
        model.addAttribute("customers", customerRepository.findAll());
        return "order_update";
    }

    @PostMapping("/{id}/update")
    public String updateOrder(@PathVariable Long id,
                              @RequestParam LocalDate orderDate,
                              @RequestParam Long customerId) {
        model_Order order = orderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + id));
        order.setOrderDate(orderDate);
        model_Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + customerId));
        order.setCustomer(customer);

        orderRepo.save(order);
        return "redirect:/men/order_view";
    }

    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderRepo.deleteById(id);
        return "redirect:/order_view";
    }

    @PostMapping("/create")
    public String createOrder(@RequestParam LocalDate orderDate,
                              @RequestParam Long customerId) {
        model_Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + customerId));

        model_Order newOrder = new model_Order();
        newOrder.setOrderDate(orderDate);
        newOrder.setCustomer(customer);

        orderRepo.save(newOrder);
        return "redirect:/men/order_view";
    }

    @GetMapping("/create")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "order_create";
    }


    @GetMapping("/view_shop")
    public String viewShoppingCarts(Model model) {
        model.addAttribute("shoppingCarts", shoppingCartRepo.findAll());
        return "shopping_cart_view";
    }


    @GetMapping("/{id}/update_shop")
    public String updateShoppingCartView(@PathVariable Long id, Model model) {
        model_ShoppingCart shoppingCart = shoppingCartRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shopping cart ID: " + id));
        model.addAttribute("shoppingCart", shoppingCart);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "shopping_cart_update";
    }

    @PostMapping("/{id}/update_shop")
    public String updateShoppingCart(@PathVariable Long id,
                                     @RequestParam Long userId,
                                     @RequestParam Long productId) {
        model_ShoppingCart shoppingCart = shoppingCartRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shopping cart ID: " + id));

        ModelUser  user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
        model_Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));

        shoppingCart.setUser (user);
        shoppingCart.setProductID(product);

        shoppingCartRepo.save(shoppingCart);
        return "redirect:/men/view_shop";
    }

    @GetMapping("/{id}/delete_shop")
    public String deleteShoppingCart(@PathVariable Long id) {
        shoppingCartRepo.deleteById(id);
        return "redirect:/men/view_shop";
    }

    @PostMapping("/create_shop")
    public String createShoppingCart(@RequestParam Long userId,
                                     @RequestParam Long productId) {
        ModelUser  user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
        model_Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));

        model_ShoppingCart newShoppingCart = new model_ShoppingCart();
        newShoppingCart.setUser (user);
        newShoppingCart.setProductID(product);

        shoppingCartRepo.save(newShoppingCart);
        return "redirect:/men/view_shop";
    }

    @GetMapping("/create_shop")
    public String showCreateShoppingCartForm(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "shopping_cart_create";
    }

    @GetMapping("/view_OrderItem")
    public String viewOrderItems(Model model) {
        List<model_OrderItem> orderItems = (List<model_OrderItem>) orderitemRepo.findAll();
        model.addAttribute("orderItems", orderItems);
        return "order_item_view";
    }

    @GetMapping("/create_OrderItem")
    public String showCreateOrderItemForm(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("orders", orderRepo.findAll());
        return "order_item_create";
    }

    @PostMapping("/create_OrderItem")
    public String createOrderItem(@RequestParam Long productId,
                                  @RequestParam Long orderId,
                                  @RequestParam int quantity) {
        model_Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));
        model_Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + orderId));

        model_OrderItem newOrderItem = new model_OrderItem();
        newOrderItem.setProduct(product);
        newOrderItem.setOrder(order);
        newOrderItem.setQuantity(quantity);

        orderitemRepo.save(newOrderItem);
        return "redirect:/view_OrderItem";
    }

    @GetMapping("/{id}/update_OrderItem")
    public String updateOrderItemView(@PathVariable Long id, Model model) {
        model_OrderItem orderItem = orderitemRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order item ID: " + id));
        model.addAttribute("orderItem", orderItem);
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("orders", orderRepo.findAll());
        return "order_item_update";
    }


    @PostMapping("/{id}/update_OrderItem")
    public String updateOrderItem(@PathVariable Long id,
                                  @RequestParam Long productId,
                                  @RequestParam Long orderId,
                                  @RequestParam int quantity) {
        model_OrderItem orderItem = orderitemRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order item ID: " + id));

        model_Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));
        model_Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + orderId));

        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setQuantity(quantity);

        orderitemRepo.save(orderItem);
        return "redirect:/men/view_OrderItem";
    }


    // Обработка удаления элемента заказа
    @GetMapping("/{id}/delete_OrderItem")
    public String deleteOrderItem(@PathVariable Long id) {
        orderitemRepo.deleteById(id);
        return "redirect:/view_OrderItem";
    }
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepo categoryRepository;

    @GetMapping("/product")
    public String product(Model model){
        model.addAttribute("product_list", productRepository.findAll());
        return "product_view_men";
    }
    @GetMapping("/create_p")
    public String showCreateProductForm(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "product_create";
    }

    // Обработка создания нового продукта
    @PostMapping("/create_p")
    public String createProduct(@RequestParam String name,
                                @RequestParam String description,
                                @RequestParam double price,
                                @RequestParam int stockQuantity,
                                @RequestParam Long categoryId) {
        model_Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + categoryId));

        model_Product newProduct = new model_Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        newProduct.setStockQuantity(stockQuantity);
        newProduct.setCategory(category);

        productRepository.save(newProduct);
        return "redirect:/men/product";
    }

    @GetMapping("/{id}/update_p")
    public String updateProductView(@PathVariable Long id, Model model) {
        model_Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());
        return "product_update";
    }

    // Обработка обновления продукта
    @PostMapping("/{id}/update_p")
    public String updateProduct(@PathVariable Long id,
                                @RequestParam String name,
                                @RequestParam String description,
                                @RequestParam double price,
                                @RequestParam int stockQuantity,
                                @RequestParam Long categoryId) {
        model_Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));

        model_Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + categoryId));

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setCategory(category);

        productRepository.save(product);
        return "redirect:/men/product";
    }

    // Обработка удаления продукта
    @GetMapping("/{id}/delete_p")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/men/product";
    }
}
