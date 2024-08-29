import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private String email_ID;
    private int age;
    private String Gender;

    public User(String username, String password, String email_ID, int age ,String Gender) {
        this.username = username;
        this.password = password;
        this.email_ID=email_ID;
        this.age=age;
        this.Gender=Gender;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public boolean getEmail(String email_ID)
    {
        return this.email_ID.equals(email_ID);
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return Gender;
    }
}

class UserManager {
    private Map<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public void registerUser(String username, String password, String email_ID,int age ,String Gender) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password, email_ID, age, Gender));
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Username already taken.");
        }
    }

    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.authenticate(password)) {
            System.out.println("Login successful.");
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }
}

class Product {
    private String productId;
    private String name;
    private double price;
    private int quantity;

    public Product(String productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
        } else {
            System.out.println("Insufficient stock!");
        }
    }

    @Override
    public String toString() {
        return productId + ": " + name + " - $" + price + " (" + quantity + " in stock)";
    }
}

class ShoppingCart {
    private List<Product> cartItems;
    private double totalAmount;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
        totalAmount = 0;
    }

    public void addToCart(Product product, int quantity) {
        if (product.getQuantity() >= quantity) {
            cartItems.add(product);
            totalAmount += product.getPrice() * quantity;
            product.reduceQuantity(quantity);
            System.out.println(quantity + " x " + product.getName() + " added to cart.");
        } else {
            System.out.println("Insufficient stock for " + product.getName());
        }
    }

    public void viewCart() {
        System.out.println("Your Cart:");
        for (Product item : cartItems) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
        System.out.println("Total: $" + totalAmount);
    }

    public double checkout() {
        System.out.println("Checking Out---->\n");
        System.out.println("Total amount: $" + totalAmount);
        cartItems.clear();
        double amountToPay = totalAmount;
        totalAmount = 0;
        return amountToPay;
    }
}

class Order {
    private ShoppingCart cart;
    private User user;

    public Order(ShoppingCart cart, User user) {
        this.cart = cart;
        this.user = user;
    }

    public void processOrder() {
        double amountToPay = cart.checkout();
        System.out.println("Order placed successfully. Amount paid: $" + amountToPay);
    }
}

public class ECommercePlatform {
    private static UserManager userManager = new UserManager();
    private static ShoppingCart cart = new ShoppingCart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        User loggedInUser = null;

        while (true) {
            System.out.println("\n--- E-Commerce Platform ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. List of Products");
            System.out.println("4. Add to Cart");
            System.out.println("5. View Your Cart");
            System.out.println("6. Checkout ");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loggedInUser = loginUser();
                    break;
                case 3:
                    listProducts();
                    break;
                case 4:
                    addToCart();
                    break;
                case 5:
                    cart.viewCart();
                    break;
                case 6:
                    if (loggedInUser != null) {
                        processOrder(loggedInUser);
                    } else {
                        System.out.println("You need to log in first.");
                    }
                    break;
                case 7:
                    System.out.println("Thank you for using the E-Commerce Platform!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Email_ID: ");
        String email_ID = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Gender: ");
        String Gender = scanner.nextLine();
        userManager.registerUser(username, password, email_ID, age, Gender);
    }

    private static User loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        return userManager.loginUser(username, password);
    }

    private static void listProducts() {
        // Add some products for demonstration
        Product[] products = {
                new Product("P001", "Laptop", 1000.0, 10),
                new Product("P002", "Smartphone", 700.0, 20),
                new Product("P003", "Tablet", 400.0, 15),
                new Product("P004", "SmartWatch", 100.0, 30),
                new Product("P005", "WebCam", 178.90, 12),
                new Product("P006", "VR Stereo", 850.10, 15)
        };

        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void addToCart() {
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        // Normally, you would look up the product by ID from a database
        // Here, for simplicity, we use a predefined list of products
        Product product = null;
        switch (productId) {
            case "P001":
                product = new Product("P001", "Laptop", 1000.0, 10);
                break;
            case "P002":
                product = new Product("P002", "Smartphone", 700.0, 20);
                break;
            case "P003":
                product = new Product("P003", "Tablet", 400.0, 15);
                break;
            case "P004":
                product=new Product("P004", "SmartWatch", 100.0, 30);
                break;
            case "P005":
                product=  new Product("P005", "WebCam", 178.90, 12);
                break;
            case "P006":
                product=new Product("P006", "VR Stereo", 850.10, 15);
                break;
            default:
                System.out.println("Product not found.");
                return;
        }

        cart.addToCart(product, quantity);
    }

    private static void processOrder(User user) {
        Order order = new Order(cart, user);
        order.processOrder();
    }
}