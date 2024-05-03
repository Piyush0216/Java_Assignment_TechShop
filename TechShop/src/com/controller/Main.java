package com.controller;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.model.Customer;
import com.model.Order;
import com.model.OrderDetails;
import com.model.Product;
import com.service.CustomerService;
import com.service.InventoryService;
import com.service.OrderService;
import com.service.OrderDetailsService;
import com.service.ProductService;

public class Main {
    public static void main(String[] Args) throws SQLException {

        Scanner sc = new Scanner(System.in);

        System.out.println("--------Press the Number -------------------");
        System.out.println(" ");

        while (true) {
        	
            System.out.println("Press 1. CalculateTotalOders");
            System.out.println("Press 2. GetCustomerDetails");
            System.out.println("Press 3. UpdateCustomerInfo");
            System.out.println("Press 4. GetProductDetails");
            System.out.println("Press 5. UpdateProductInfo");
            System.out.println("Press 6. IsProductInStock");
            System.out.println("Press 7. CalculateTotalAmountByOrderId");
            System.out.println("Press 8. GetOrderDetails");
            System.out.println("Press 9. UpdateOrderStatusByOrderId");
            System.out.println("Press 10. CancleOrderByOrderId");  // not working giving error 
            System.out.println("Press 11. CalculateSubtotal");     // not working Properly
            System.out.println("Press 12. UpdateQuantity");
            System.out.println("Press 13. GetProduct");
            System.out.println("Press 14. GetQuantityInStock");
            System.out.println("Press 15. AddToInventory");
            System.out.println("Press 16 RemoveFromInventory");
            System.out.println("Press 17. UpdateStockQuantity");
            System.out.println("Press 18. IsProductAvailable");
            System.out.println("Press 19. GetInventoryValue");
            System.out.println("Press 20. ListOutOfStockProducts");
                                   
            System.out.println("Press 21. Exit");

            System.out.println("------------------------------------------------");
            System.out.println("Enter Your choice from the above menu");

            
            int input = sc.nextInt();
            if (input == 21)
                break;
          
            switch (input) {
               
                
            case 1:
                try {
                    CustomerService customerService = new CustomerService();

                    
                    List<Order> orders = customerService.CalculateTotalOders();
                    
                    if (orders.isEmpty()) {
                        System.out.println("No orders found for the specified customer.");
                    } else {
                        System.out.println("Total orders are:");
                        for (Order order : orders) {
                            System.out.println("Order ID: " + order.getOrderId() + ", Order Date: " + order.getOrderDate() +
                                               ", Total Amount: " + order.getTotalAmount());
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("An error occurred while calculating total orders: " + e.getMessage());
                }
                break;

                case 2:
                    try {
                        CustomerService customerService = new CustomerService();

                        System.out.println("Enter the CustomerId:");
                        int id = sc.nextInt();

                        Customer customer = customerService.GetCustomerDetails(id);
                        
                        if (customer == null) {
                            System.out.println("No Customer found for the specified ID.");
                        } else {
                            System.out.println("Customer Details:");
                            System.out.println("Customer ID: " + customer.getCustomerId());
                            System.out.println("Customer FirstName: " + customer.getFirstName());
                            System.out.println("Customer LastName: " + customer.getLastName());
                            System.out.println("Customer Address: " + customer.getAddress());
                            System.out.println("Customer Email: " + customer.getEmail());
                            System.out.println("Customer Phone: " + customer.getPhone());
                            System.out.println("-----------------------------------");
                        } 
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for CustomerId. Please enter an integer.");
                        sc.nextLine(); // Consume invalid input
                    } catch (SQLException e) {
                        System.out.println("An error occurred while retrieving customer details: " + e.getMessage());
                        e.printStackTrace(); // Print stack trace for debugging
                    }
                    break;

                    
                case 3:
                    try {
                    	                    	 
                        CustomerService customer = new CustomerService();
                      
                        System.out.println("Enter the CustomerId To Update the Customer");
                        int Id = sc.nextInt();
                        sc.nextLine();
                       
                        System.out.println("Enter the Customer FirstName");
                        String FirstName = sc.nextLine();
                        
                        System.out.println("Enter the Customer LastName");
                        String LastName = sc.nextLine();
                        
                        System.out.println("Enter the Customer Phone");
                        String Phone = sc.nextLine();
                        
                        if(Phone.length() != 10) {
                        	System.out.println("Only 10 digit number is accepted , try again!!");
                        	break;
                        }
                        
                        System.out.println("Enter the Customer Address");
                        String Address = sc.nextLine();
                        
                        System.out.println("Enter the Customer Email");
                        String Email = sc.nextLine();
                        
                        if(!Email.endsWith("@gmail.com")) {
                        	System.out.println("Email only ends with @gmail.com are accepted");
                        	break;
                        }
                       
                     
                        customer.UpdateCustomerInfo(Id, FirstName, LastName, Phone, Address, Email);
                        
                        System.out.println("Customer information updated successfully.");
                    } catch (SQLException ex) {
                        System.out.println("Error updating customer information: " + ex.getMessage());
                    }
                    break;
                    
                    
                    
                case 4 :
                    try {
                        ProductService productService = new ProductService();
                        
                        // Fetch all product IDs
                        List<Integer> productIds = productService.getAllProductIds();
                        
                        if (productIds.isEmpty()) {
                            System.out.println("No products found.");
                        } else {
                            System.out.println("Available Product IDs:");
                            for (Integer productId : productIds) {
                                System.out.println(productId);
                            }
                        }

                        System.out.println("Enter the Product Id:");
                        int id = sc.nextInt();
                        
                        Product product = productService.GetProductDetails(id);
                        
                        if ( product == null) {
                            System.out.println("No Product found for the specified ID.");
                        } else {
                            System.out.println("Product Details:");
                            System.out.println("Product ID: " + product.getProductId());
                            System.out.println("Product Name: " + product.getProductName());
                            System.out.println("Product Description: " + product.getDescription());
                            System.out.println("Product Price: " + product.getPrice());
                            System.out.println("Product Status: " + product.getStatus());
                            
                           
                            System.out.println("-----------------------------------");
                        } 
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Product Id. Please enter an integer.");
                        sc.nextLine(); // Consume invalid input
                    } catch (SQLException e) {
                        System.out.println("An error occurred while retrieving Product details: " + e.getMessage());
                        e.printStackTrace(); // Print stack trace for debugging
                    }
                    break;
                    
                case 5:
                    try {
                    	                    	 
                        ProductService product = new ProductService();
                       
                        
                        // Fetch all product IDs
                        List<Integer> productIds = product.getAllProductIds();
                        
                        if (productIds.isEmpty()) {
                            System.out.println("No products found.");
                        } else {
                            System.out.println("Available Product IDs:");
                            for (Integer productId : productIds) {
                                System.out.println(productId);
                            }
                        }
                        System.out.println("Enter the ProductId To Update the Product");
                        int Id = sc.nextInt();
                        sc.nextLine();
                       
                       
                        
                        System.out.println("Enter the Product Name");
                        String ProductName = sc.nextLine();
                        
                        System.out.println("Enter the Product Description");
                        String Description = sc.nextLine();
                        
                        System.out.println("Enter the Product Price");
                        Double Price = sc.nextDouble();
                        sc.nextLine(); 
                        
                        System.out.println("Enter the Product Status");
                        String Status = sc.nextLine();
                        
                        if(!(Status.equalsIgnoreCase("InStock") || Status.equalsIgnoreCase("OutOfStock"))) {
                        	System.out.println("Enter the Status only from (InStock/OutOfStock)");
                        }
                        
                       
                        if(product.UpdateProductInfo(Id, ProductName, Description, Price, Status)) {
                        	 System.out.println("Product information updated successfully.");
                        }
                        else {
                        	System.out.println("Unable to update product information");
                        }
                       
                        
                    }catch (InputMismatchException e) {
                        System.out.println("Invalid input for Product  Please enter an integer.");
                        sc.nextLine();
                    
                        }catch (SQLException ex) {
                        System.out.println("Error updating Product information: " + ex.getMessage());
                    }
                    break;
                    
                case 6:
                    try {
                        ProductService product = new ProductService();
                        
                        List<Integer> productIds = product.getAllProductIds();
                        
                        if (productIds.isEmpty()) {
                            System.out.println("No products found.");
                            break;
                        } else {
                            System.out.println("Available Product IDs:");
                            for (Integer productId : productIds) {
                                System.out.println(productId);
                            }
                        }
                        
                        System.out.println("Enter the ProductId To Check the Product Availability");
                        int Id = sc.nextInt();
                        
                        int result = product.IsProductInStock(Id);
                        if(result == 0 ) {
                        System.out.println("Product Availability: Out of Stock");
                        }
                        else if(result == 1) {
                        	System.out.println("Product Availability: InStock");
                        }
                        else {
                        	System.out.println("Product Availability: Product does not exist");

                        }
                        
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Product Id. Please enter an integer.");
                        sc.nextLine();
                    } catch (SQLException ex) {
                        System.out.println("Error checking product availability: " + ex.getMessage());
                    }
                    break;
                    
                case 7:
                    try {
                    	
                        OrderService orderService = new OrderService();
                        
                        List<Integer> orderIds = orderService.getAllOrderIds();
                        
                        if (orderIds.isEmpty()) {
                            System.out.println("No orders found.");
                        } else {
                            System.out.println("Available Order IDs:");
                            for (Integer orderId : orderIds) {
                                System.out.println(orderId);
                            }
                        }
                        
                        System.out.println("Enter the Oder Id To Calculate the Total Amount");
                        int Id = sc.nextInt();
                        

                        
                        List<Order> orders = orderService.CalculateTotalAmountByOrderId(Id);
                        
                        if (orders.isEmpty()) {
                            System.out.println("No orders found for the specified OrderId.");
                        } else {
                            
                            for (Order order : orders) {
                                System.out.println("Total Amount: " + order.getTotalAmount());
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("An error occurred while calculating Total Amount: " + e.getMessage());
                    }
                    break;
                    
                case 8 :
                    try {
                                               
                        OrderService orderService = new OrderService();
                        
                        List<Integer> orderIds = orderService.getAllOrderIds();
                        
                        if (orderIds.isEmpty()) {
                            System.out.println("No orders found.");
                        } else {
                            System.out.println("Available Order IDs:");
                            for (Integer orderId : orderIds) {
                                System.out.println(orderId);
                            }
                        }

                        System.out.println("Enter the Order Id:");
                        int id = sc.nextInt();

                        Order order = orderService.GetOrderDetailsByOrderId(id);
                        
                        if ( order == null) {
                            System.out.println("No Product found for the specified ID.");
                        } else {
                            System.out.println("Order Details:");
                            System.out.println("Order ID: " + order.getOrderId());
                            System.out.println("Customer ID: " + order.getCustomerId());
                            System.out.println("Order Date: " + order.getOrderDate());
                            System.out.println("Order Amount: " + order.getTotalAmount());
                            System.out.println("Order Status: " + order.getStatus());
                            
                           
                            System.out.println("-----------------------------------");
                        } 
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Order Id. Please enter an integer.");
                        sc.nextLine(); // Consume invalid input
                    } catch (SQLException e) {
                        System.out.println("An error occurred while retrieving Order details: " + e.getMessage());
                       
                    }
                    break;

                case 9:
                    try {
                        OrderService orderService = new OrderService();
                        
                        // Fetch all order IDs
                        List<Integer> orderIds = orderService.getAllOrderIds();
                        
                        if (orderIds.isEmpty()) {
                            System.out.println("No orders found.");
                        } else {
                            System.out.println("Available Order IDs:");
                            for (Integer orderId : orderIds) {
                                System.out.println(orderId);
                            }
                        }

                        System.out.println("Enter the Order Id:");
                        int id = sc.nextInt();
                        sc.nextLine(); // Consume newline character
                        
                        System.out.println("Enter the new status ( Placed, Pending, Deliver,Cancled):");
                        String status = sc.nextLine();
                        
                        // Update order status
                        boolean updated = orderService.UpdateOrderStatusByOrderId(id, status);
                        
                        if (updated) {
                            System.out.println("Order status updated successfully.");
                        } else {
                            System.out.println("Failed to update order status.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Order Id. Please enter an integer.");
                        sc.nextLine(); // Consume invalid input
                    } catch (SQLException e) {
                        System.out.println("An error occurred while updating Order status: " + e.getMessage());
                       
                    }
                    break;
                    
                case 10:  // error in status truncate
                	
                	try {
                        OrderService orderService = new OrderService();
                        
                        // Fetch all order IDs
                        List<Integer> orderIds = orderService.getAllOrderIds();
                        
                        if (orderIds.isEmpty()) {
                            System.out.println("No orders found.");
                        } else {
                            System.out.println("Available Order IDs:");
                            for (Integer orderId : orderIds) {
                                System.out.println(orderId);
                            }
                        }

                        System.out.println("Enter the Order Id:");
                        int id = sc.nextInt();
                        sc.nextLine(); // Consume newline character
                        
                                               
                        // Update order status
                        boolean Cncl = orderService.CancelOrderByOrderId(id);
                        
                        if (Cncl) {
                            System.out.println("Order Cancled successfully.");
                        } else {
                            System.out.println("Failed to Cancle order status.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Order Id. Please enter an integer.");
                        sc.nextLine(); // Consume invalid input
                    } catch (SQLException e) {
                        System.out.println("An error occurred while Cancling Order status: " + e.getMessage());
                       
                    }
                    break;
                    
                case 11:   // subtotal thik se print nhi ho rha
                    try {
                      
                        System.out.println("Enter the Order Id:");
                        int orderId = sc.nextInt();
                        
                       
                        OrderDetailsService orderDetails = new OrderDetailsService();
                        
                        List<OrderDetails> orderDetail= OrderDetailsService.GetOrderDetailsByOrderId(orderId);
                        
                        if (orderDetails.isEmpty()) {
                            System.out.println("No details found for the order with ID " + orderId);
                        } else {
                            
                            double totalSubtotal = 0.0;
                            System.out.println("Order Details:");
                            System.out.println("Item ID\t\tQuantity\t\tUnit Price\t\tSubtotal");
                            for (OrderDetails detail : orderDetail) {
                                double subtotal = detail.getQuantity() * detail.getUnitPrice();
                                totalSubtotal += subtotal;
                                System.out.println(detail.getItemId() + "\t\t" + detail.getQuantity() + "\t\t" + detail.getUnitPrice() + "\t\t" + subtotal);
                            }
                            System.out.println("Total Subtotal: " + totalSubtotal);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Order Id. Please enter an integer.");
                        sc.nextLine(); 
                    } catch (SQLException e) {
                        System.out.println("An error occurred while calculating subtotal: " + e.getMessage());
                        
                    }
                    break;
                    
               
                case 12:
                    try {
                    	                    	 
                    	OrderDetailsService orderD = new OrderDetailsService();
                       
                        
                        // Fetch all product IDs
                        List<Integer> productIds = orderD.getAllProductIds();
                        
                        if (productIds.isEmpty()) {
                            System.out.println("No products found.");
                        } else {
                            System.out.println("Available Product IDs:");
                            for (Integer productId : productIds) {
                                System.out.println(productId);
                            }
                        }
                        System.out.println("Enter the ProductId To Update the Q");
                        int Id = sc.nextInt();
                        sc.nextLine();
                       
                       
                        
                        System.out.println("Enter the Product Quantity");
                        int quantity = sc.nextInt();
                        
                                              
                       
                        int res = orderD.UpdateQuantity(Id, quantity);
                        if(res == 1) {
                        	 System.out.println("Product quantity updated successfully.");
                        }
                        else {
                        	System.out.println("Enter a Valid Product ID");
                        }
                       
                        
                    }catch (InputMismatchException e) {
                        System.out.println("Invalid input for Product Id. Please enter an integer.");
                        sc.nextLine();
                    
                        }catch (SQLException ex) {
                        System.out.println("Error updating Product Quantity: " + ex.getMessage());
                    }
                    break;
                    
                case 13:
                    // GetProduct() method implementation
                    try {
                        InventoryService inventoryService = new InventoryService();
                        
                        // Fetch all product IDs
                        List<Integer> productIds = inventoryService.getAllProductIds();
                        
                        if (productIds.isEmpty()) {
                            System.out.println("No products found.");
                        } else {
                            System.out.println("Available Product IDs:");
                            for (Integer productId : productIds) {
                                System.out.println(productId);
                            }
                        }

                        System.out.println("Enter the Product Id:");
                        int productId = sc.nextInt();

                        Product product = inventoryService.GetProduct(productId);
                        
                        if ( product == null) {
                            System.out.println("No Product found for the specified ID.");
                        } else {
                            System.out.println("Product Details:");
                            System.out.println("Product ID: " + product.getProductId());
                            System.out.println("Product Name: " + product.getProductName());
                            System.out.println("Product Description: " + product.getDescription());
                            System.out.println("Product Price: " + product.getPrice());
                            System.out.println("Product Status: " + product.getStatus());
                            System.out.println("-----------------------------------");
                        } 
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Product Id. Please enter an integer.");
                        sc.nextLine(); // Consume invalid input
                    } catch (SQLException e) {
                        System.out.println("An error occurred while retrieving Product details: " + e.getMessage());
                        e.printStackTrace(); // Print stack trace for debugging
                    }
                    break;

                case 14:
                    // GetQuantityInStock() method implementation
                    try {
                        InventoryService inventoryService = new InventoryService();
                        
                        // Fetch all product IDs
                        List<Integer> productIds = inventoryService.getAllProductIds();
                        
                        if (productIds.isEmpty()) {
                            System.out.println("No products found.");
                        } else {
                            System.out.println("Available Product IDs:");
                            for (Integer productId : productIds) {
                                System.out.println(productId);
                            }
                        }
                        
                        System.out.println("Enter the ProductId To Check the Quantity in Stock:");
                        int productId = sc.nextInt();
                        
                        int quantity = inventoryService.GetQuantityInStock(productId);
                        System.out.println("Quantity in Stock: " + quantity);
                        
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Product Id. Please enter an integer.");
                        sc.nextLine(); // Consume invalid input
                    } catch (SQLException ex) {
                        System.out.println("Error retrieving quantity in stock: " + ex.getMessage());
                    }
                    break;

                case 15:  
                    // AddToInventory() method implementation
                    try {
                        InventoryService inventoryService = new InventoryService();
                        
                        System.out.println("Enter the ProductId To Add to Inventory:");
                        int productId = sc.nextInt();
                        
                        System.out.println("Enter the quantity to add:");
                        int quantity = sc.nextInt();
                        
                        inventoryService.AddToInventory(productId, quantity);
                        System.out.println("Quantity added to inventory successfully.");
                        
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Product Id or quantity. Please enter an integer.");
                        sc.nextLine(); // Consume invalid input
                    } catch (SQLException ex) {
                        System.out.println("Error adding quantity to inventory: " + ex.getMessage());
                    }
                    break;

                case 16:   
                    // RemoveFromInventory() method implementation
                    try {
                        InventoryService inventoryService = new InventoryService();
                        
                        System.out.println("Enter the ProductId To Remove from Inventory:");
                        int productId = sc.nextInt();
                        
                        System.out.println("Enter the quantity to remove:");
                        int quantity = sc.nextInt();
                        
                        inventoryService.RemoveFromInventory(productId, quantity);
                        System.out.println("Quantity removed from inventory successfully.");
                        
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Product Id or quantity. Please enter an integer.");
                        sc.nextLine(); // Consume invalid input
                    } catch (SQLException ex) {
                        System.out.println("Error removing quantity from inventory: " + ex.getMessage());
                    }
                    break;

                case 17:  
                    // UpdateStockQuantity() method implementation
                    try {
                        InventoryService inventoryService = new InventoryService();
                        
                        System.out.println("Enter the ProductId To Update Stock Quantity:");
                        int productId = sc.nextInt();
                        
                        System.out.println("Enter the new quantity:");
                        int newQuantity = sc.nextInt();
                        
                        inventoryService.UpdateStockQuantity(productId, newQuantity);
                        System.out.println("Stock quantity updated successfully.");
                        
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Product Id or new quantity. Please enter an integer.");
                        sc.nextLine(); // Consume invalid input
                    } catch (SQLException ex) {
                        System.out.println("Error updating stock quantity: " + ex.getMessage());
                    }
                    break;

                case 18:
                    // IsProductAvailable() method implementation
                    try {
                        InventoryService inventoryService = new InventoryService();
                        
                        System.out.println("Enter the ProductId To Check Availability:");
                        int productId = sc.nextInt();
                        
                        boolean result = inventoryService.IsProductAvailable(productId);
                        if(result) 
                        	System.out.println("InStock");
                        else 
                        {
                        	System.out.println("OutOfStock");
                        }
                        
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input for Product Id. Please enter an integer.");
                        sc.nextLine(); // Consume invalid input
                    }catch (SQLException ex) {
                        System.out.println("Error updating stock quantity: " + ex.getMessage());
                    }
                    break;

                case 19:
                    // GetInventoryValue() method implementation
                    try {
                        InventoryService inventoryService = new InventoryService();
                        
                        double inventoryValue = inventoryService.GetInventoryValue();
                        System.out.println("Total Inventory Value: " + inventoryValue);
                        
                    } catch (SQLException ex) {
                        System.out.println("Error calculating inventory value: " + ex.getMessage());
                    }
                    break;

                

                case 20:
                    // ListOutOfStockProducts() method implementation
                    try {
                        InventoryService inventoryService = new InventoryService();
                        
                        List<Product> outOfStockProducts = inventoryService.ListOutOfStockProducts();
                        if (outOfStockProducts.isEmpty()) {
                            System.out.println("No products are out of stock.");
                        } else {
                            System.out.println("Out of Stock Products:");
                            for (Product p : outOfStockProducts) {
                                System.out.println("Product ID: " + p.getProductId() + ", Product Name: " + p.getProductName());
                            }
                        }
                        
                    } catch (SQLException ex) {
                        System.out.println("Error listing out of stock products: " + ex.getMessage());
                    }
                    break;
                	
                 }       
            }
        sc.close();
    }
}
