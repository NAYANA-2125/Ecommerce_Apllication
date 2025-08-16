package com.gqt.core_java.mini_project;
/**
 * @author nayana
 * @category Mini Project
 * Ecommerce Apllication
 *
 */
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Product {
    String productName;
    String brand;
    int price;
    int quantity;
    String description;

    public Product(String productName, String brand, int price, int quantity, String description) {
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}

public class MiniProject4 extends JFrame implements ActionListener {

    JPanel welcomePanel, categoryPanel, subcategoryPanel, productDetailPanel, cartPanel, billPanel;
    CardLayout cardLayout; 
    Container contentPane; 
    JButton clothesButton, electronicsButton, homeDecorButton, furnitureButton;

    JButton continueShoppingButton, checkoutButton;
    JTextArea productDetailsTextArea; 
    JTextPane billTextPane; 
    Product[] cart = new Product[20]; 
    int cartIndex = 0; 

    String currentCategory = "";
    String currentSubcategory = ""; 
    Product[] currentlyDisplayedProducts; 

    public MiniProject4() {
        setTitle("Shopping Application"); 
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        cardLayout = new CardLayout();
        contentPane = getContentPane();
        contentPane.setLayout(cardLayout);

        welcomePanel = new JPanel(new BorderLayout(20, 20));
        welcomePanel.setBackground(new Color(230, 240, 250)); 

        JLabel welcomeLabel = new JLabel("Welcome to Our Shopping Platform!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 32)); 
        welcomeLabel.setForeground(new Color(25, 100, 150));
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel categorySelectionPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        categorySelectionPanel.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150)); 
        categorySelectionPanel.setBackground(new Color(230, 240, 250));
        
        clothesButton = createCategoryButton("Clothes");
        electronicsButton = createCategoryButton("Electronics");
        homeDecorButton = createCategoryButton("Home Decor");
        furnitureButton = createCategoryButton("Furniture");
        
        clothesButton.addActionListener(this);
        electronicsButton.addActionListener(this);
        homeDecorButton.addActionListener(this);
        furnitureButton.addActionListener(this);

        categorySelectionPanel.add(clothesButton);
        categorySelectionPanel.add(electronicsButton);
        categorySelectionPanel.add(homeDecorButton);
        categorySelectionPanel.add(furnitureButton);

        welcomePanel.add(categorySelectionPanel, BorderLayout.CENTER); 
        contentPane.add(welcomePanel, "Welcome");

        cartPanel = new JPanel(new BorderLayout(10, 10));
        cartPanel.setBackground(new Color(240, 250, 240)); 

        productDetailsTextArea = new JTextArea(10, 40);
        productDetailsTextArea.setEditable(false);
        productDetailsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        productDetailsTextArea.setForeground(new Color(50, 50, 50));
        JScrollPane scrollPane = new JScrollPane(productDetailsTextArea);
        cartPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel cartButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15)); 
        cartButtonsPanel.setBackground(new Color(240, 250, 240)); 

        continueShoppingButton = createStyledButton("Continue Shopping", new Color(76, 175, 80));
        checkoutButton = createStyledButton("Checkout", new Color(255, 165, 0)); 
        
        continueShoppingButton.addActionListener(this);
        checkoutButton.addActionListener(this);
        cartButtonsPanel.add(continueShoppingButton);
        cartButtonsPanel.add(checkoutButton);
        cartPanel.add(cartButtonsPanel, BorderLayout.SOUTH);
        contentPane.add(cartPanel, "Cart"); 

        billPanel = new JPanel(new BorderLayout(10, 10));
        billPanel.setBackground(new Color(255, 255, 230));

        billTextPane = new JTextPane();
        billTextPane.setEditable(false);
        billTextPane.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
        JScrollPane billScrollPane = new JScrollPane(billTextPane);
        billPanel.add(billScrollPane, BorderLayout.CENTER);
        contentPane.add(billPanel, "Bill"); 

        cardLayout.show(contentPane, "Welcome"); 
        setVisible(true); 
    }
    private JButton createCategoryButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(100, 180, 230)); 
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false); 
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 
        return button;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false); 
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); 

        if (command.equals("Clothes")) {
            currentCategory = "Clothes";
            showSubcategories("Clothes", new String[]{"Men's Fashion", "Women's Fashion", "Children's Fashion"});
        } else if (command.equals("Electronics")) {
            currentCategory = "Electronics";
            showSubcategories("Electronics", new String[]{"Mobiles", "Laptops", "Earphones", "Speakers"});
        } else if (command.equals("Home Decor")) { 
            currentCategory = "Home Decor";
            showSubcategories("Home Decor", new String[]{"Kitchen Appliances", "Living Area Appliances", "Bedroom Appliances"});
        } else if (command.equals("Furniture")) { 
            currentCategory = "Furniture";
            showSubcategories("Furniture", new String[]{"Bedroom Furniture", "Living Room Furniture", "Dining Room Furniture"});
        }
        
        else if (command.equals("Men's Fashion") || command.equals("Women's Fashion") || command.equals("Children's Fashion") ||
                 command.equals("Mobiles") || command.equals("Laptops") || command.equals("Earphones") || command.equals("Speakers") ||
                 command.equals("Kitchen Appliances") || command.equals("Living Area Appliances") || command.equals("Bedroom Appliances") ||
                 command.equals("Bedroom Furniture") || command.equals("Living Room Furniture") || command.equals("Dining Room Furniture")) {
            currentSubcategory = command;
            displayProducts(currentCategory, currentSubcategory);
            }
        else if (command.startsWith("AddProduct:")) {
            int productIndex = Integer.parseInt(command.substring("AddProduct:".length()));
           
            Product selectedProduct = currentlyDisplayedProducts[productIndex];

            if (selectedProduct == null) {
                JOptionPane.showMessageDialog(this, "Selected product is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int quantity = 1; 
            String quantityStr = JOptionPane.showInputDialog(this, "Enter quantity for " + selectedProduct.getProductName() + ":", "1");
            
            if (quantityStr != null && !quantityStr.trim().isEmpty()) {
                try {
                    quantity = Integer.parseInt(quantityStr.trim());
                    if (quantity <= 0) { 
                        JOptionPane.showMessageDialog(this, "Quantity must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) { 
                    JOptionPane.showMessageDialog(this, "Invalid quantity. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                return; 
            }

            if (cartIndex < cart.length) {
                Product productToAdd = new Product(
                    selectedProduct.getProductName(), 
                    selectedProduct.getBrand(), 
                    selectedProduct.getPrice(), 
                    quantity, 
                    selectedProduct.getDescription()
                );
                cart[cartIndex] = productToAdd;
                cartIndex++; 
                productDetailsTextArea.setText(quantity + " " + selectedProduct.getProductName() + " added to cart.\n");
                productDetailsTextArea.append("Do you want to:\n1. Continue Shopping\n2. Checkout\n");
                cardLayout.show(contentPane, "Cart");
            } else {
                JOptionPane.showMessageDialog(this, "Cart is full. Cannot add more products.", "Cart Full", JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (command.equals("Continue Shopping")) {
            cardLayout.show(contentPane, "Welcome"); 
        } else if (command.equals("Checkout")) {
            generateBill(); 
            cardLayout.show(contentPane, "Bill"); 
        } else if (command.equals("Back to Categories")) {
            cardLayout.show(contentPane, "Welcome"); 
        } else if (command.equals("Back to Subcategories")) {

             showSubcategories(currentCategory, getSubcategoriesForCategory(currentCategory));
        }
    }

    private String[] getSubcategoriesForCategory(String category) {
        if (category.equals("Clothes")) {
            return new String[]{"Men's Fashion", "Women's Fashion", "Children's Fashion"};
        } else if (category.equals("Electronics")) {
            return new String[]{"Mobiles", "Laptops", "Earphones", "Speakers"};
        } else if (category.equals("Home Decor")) {
            return new String[]{"Kitchen Appliances", "Living Area Appliances", "Bedroom Appliances"};
        } else if (category.equals("Furniture")) {
            return new String[]{"Bedroom Furniture", "Living Room Furniture", "Dining Room Furniture"};
        }
        return new String[]{}; 
    }

    private void showSubcategories(String category, String[] subcategories) {
        subcategoryPanel = new JPanel(new BorderLayout(10, 10));
        subcategoryPanel.setBackground(new Color(245, 245, 220)); 
        
        JLabel titleLabel = new JLabel("Select Subcategory in " + category, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));
        titleLabel.setForeground(new Color(50, 50, 50));
        subcategoryPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(subcategories.length + 1, 1, 10, 10)); // +1 for back button
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        buttonsPanel.setBackground(new Color(245, 245, 220));

        for (String sub : subcategories) {
            JButton subButton = createStyledButton(sub, new Color(135, 206, 250)); 
            subButton.addActionListener(this);
            buttonsPanel.add(subButton);
        }

        JButton backToCategoriesButton = createStyledButton("Back to Categories", new Color(150, 150, 150)); 
        backToCategoriesButton.addActionListener(this);
        buttonsPanel.add(backToCategoriesButton);

        subcategoryPanel.add(buttonsPanel, BorderLayout.CENTER);
        contentPane.add(subcategoryPanel, "Subcategory"); 
        cardLayout.show(contentPane, "Subcategory"); 
    }

    private void displayProducts(String category, String subcategory) {
        productDetailPanel = new JPanel(new BorderLayout(10, 10));
        productDetailPanel.setBackground(new Color(250, 240, 230)); 

        JPanel productsListPanel = new JPanel(new GridLayout(0, 1, 10, 10)); 
        productsListPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        productsListPanel.setBackground(new Color(250, 240, 230)); 

        JLabel titleLabel = new JLabel("Products in " + subcategory, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));
        titleLabel.setForeground(new Color(80, 80, 80));
        productDetailPanel.add(titleLabel, BorderLayout.NORTH);

        currentlyDisplayedProducts = getProducts(category, subcategory);
        for (int i = 0; i < currentlyDisplayedProducts.length; i++) {
            if (currentlyDisplayedProducts[i] != null) { 
                Product p = currentlyDisplayedProducts[i];
                JPanel productEntryPanel = new JPanel(new BorderLayout(5, 5)); 
                productEntryPanel.setBackground(new Color(255, 255, 255)); 
                productEntryPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); 

                JLabel productInfoLabel = new JLabel(String.format(
                    "<html><b>%s</b><br/>Company: %s<br/>Rate: ₹%d<br/>Description: <i>%s</i></html>",
                    p.getProductName(), p.getBrand(), p.getPrice(), p.getDescription()));
                productInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                productInfoLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                JButton addToCartBtn = createStyledButton("Add to Cart", new Color(60, 179, 113)); 
                addToCartBtn.setActionCommand("AddProduct:" + i); 
                addToCartBtn.addActionListener(this); 
                addToCartBtn.setToolTipText("Click to add " + p.getProductName() + " to your shopping cart.");

                productEntryPanel.add(productInfoLabel, BorderLayout.CENTER);
                JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonWrapper.setBackground(Color.WHITE);
                buttonWrapper.add(addToCartBtn);
                productEntryPanel.add(buttonWrapper, BorderLayout.SOUTH);

                productsListPanel.add(productEntryPanel);
            }
        }
        JScrollPane scrollPane = new JScrollPane(productsListPanel);
        productDetailPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = createStyledButton("Back to Subcategories", new Color(150, 150, 150)); 
        backButton.addActionListener(this);
        productDetailPanel.add(backButton, BorderLayout.SOUTH);

        contentPane.add(productDetailPanel, "ProductDetails");
        cardLayout.show(contentPane, "ProductDetails");
    }

    private Product[] getProducts(String category, String subcategory) {
        Product[] products = new Product[5]; 
        if (category.equals("Clothes")) { 
            if (subcategory.equals("Men's Fashion")) {
                products[0] = new Product("Shirt", "Peter England", 999, 1, "Classic fit formal shirt for office or casual.");
                products[1] = new Product("T-shirt", "Puma", 799, 1, "Comfortable cotton casual t-shirt for daily wear.");
                products[2] = new Product("Pant", "Levi's", 1199, 1, "Stylish slim fit denim jeans, perfect for outings.");
                products[3] = new Product("Jacket", "Woodland", 1599, 1, "Warm winter jacket, ideal for cold weather.");
                products[4] = new Product("Tracksuit", "Nike", 1899, 1, "Lightweight athletic tracksuit for workouts and sports.");
            } else if (subcategory.equals("Women's Fashion")) {
                products[0] = new Product("Saree", "Biba", 1299, 1, "Elegant traditional Indian saree with intricate design.");
                products[1] = new Product("Kurti", "Aurelia", 999, 1, "Stylish ethnic kurti, suitable for casual and festive occasions.");
                products[2] = new Product("Jeans", "Pepe", 1499, 1, "Fashionable skinny jeans, a versatile wardrobe essential.");
                products[3] = new Product("Top", "ONLY", 899, 1, "Trendy casual top, perfect for a chic look.");
                products[4] = new Product("Leggings", "Jockey", 499, 1, "Comfortable stretch leggings, ideal for everyday use.");
            } else if (subcategory.equals("Children's Fashion")) {
                products[0] = new Product("Kids T-shirt", "Babyhug", 399, 1, "Soft cotton t-shirt for kids, gentle on skin.");
                products[1] = new Product("Frock", "ToffyHouse", 499, 1, "Cute and playful frock for girls, perfect for parties.");
                products[2] = new Product("Shorts", "FirstCry", 299, 1, "Comfortable shorts for children, ideal for play time.");
                products[3] = new Product("Sweater", "Mini Klub", 699, 1, "Cozy knitted sweater, keeps kids warm in winter.");
                products[4] = new Product("Cap", "TinyOne", 199, 1, "Stylish cap for toddlers, adds a cute touch.");
            }
        } else if (category.equals("Electronics")) {
            if (subcategory.equals("Mobiles")) {
                products[0] = new Product("Mobile", "Samsung Galaxy", 15999, 1, "Latest smartphone with advanced features and great camera.");
                products[1] = new Product("Mobile", "Redmi Note", 11999, 1, "Budget-friendly smartphone with long-lasting battery.");
                products[2] = new Product("Mobile", "Realme Narzo", 13999, 1, "Gaming-focused smartphone with high refresh rate display.");
                products[3] = new Product("Mobile", "Vivo Y Series", 12999, 1, "Camera-centric smartphone, captures stunning photos.");
                products[4] = new Product("Mobile", "iQOO Z Series", 17999, 1, "High-performance gaming phone, smooth and fast.");
            } else if (subcategory.equals("Laptops")) {
                products[0] = new Product("Laptop", "HP Pavilion", 45999, 1, "Powerful laptop for everyday use and multitasking.");
                products[1] = new Product("Laptop", "Dell Inspiron", 48999, 1, "Reliable laptop for work and study, excellent build.");
                products[2] = new Product("Laptop", "Lenovo IdeaPad", 43999, 1, "Sleek and lightweight laptop, easy to carry.");
                products[3] = new Product("Laptop", "ASUS VivoBook", 49999, 1, "Vibrant display and fast performance, great for media.");
                products[4] = new Product("Laptop", "Acer Aspire", 42999, 1, "Affordable laptop for general tasks and browsing.");
            } else if (subcategory.equals("Earphones")) {
                products[0] = new Product("Earphones", "boAt Rockerz", 999, 1, "Wireless earphones with deep bass and clear audio.");
                products[1] = new Product("Earphones", "Realme Buds", 1299, 1, "True wireless earbuds, perfect for on-the-go listening.");
                products[2] = new Product("Earphones", "JBL C100", 1499, 1, "In-ear headphones with signature sound quality.");
                products[3] = new Product("Earphones", "Sony Wired", 1799, 1, "High-resolution audio earphones, crisp and rich sound.");
                products[4] = new Product("Earphones", "Noise Buds", 1099, 1, "Noise-cancelling earbuds, immerse yourself in music.");
            } else if (subcategory.equals("Speakers")) { 
                products[0] = new Product("Speaker", "JBL Flip", 5999, 1, "Portable Bluetooth speaker, great for outdoor parties.");
                products[1] = new Product("Speaker", "Sony SRS-XB", 7499, 1, "Extra bass wireless speaker, powerful sound experience.");
                products[2] = new Product("Speaker", "Bose SoundLink", 12999, 1, "Premium portable speaker, crisp and balanced audio.");
                products[3] = new Product("Speaker", "Philips BT", 2999, 1, "Compact Bluetooth speaker, easy to carry anywhere.");
                products[4] = new Product("Speaker", "Ultimate Ears Boom", 8999, 1, "Waterproof and durable speaker, perfect for adventures.");
            }
        } else if (category.equals("Home Decor")) { 
            if (subcategory.equals("Kitchen Appliances")) {
                products[0] = new Product("Mixer Grinder", "Prestige", 2799, 1, "Efficient kitchen mixer for all your grinding needs.");
                products[1] = new Product("Microwave Oven", "LG", 7499, 1, "Convection microwave oven, ideal for baking and grilling.");
                products[2] = new Product("Induction Cooktop", "Philips", 3499, 1, "Fast and safe cooking, energy-efficient.");
                products[3] = new Product("Toaster", "Morphy Richards", 1599, 1, "Pop-up toaster with multiple settings for perfect toast.");
                products[4] = new Product("Electric Kettle", "Bajaj", 899, 1, "Quick boiling electric kettle, perfect for hot beverages.");
            } else if (subcategory.equals("Living Area Appliances")) {
                products[0] = new Product("Smart TV", "Samsung", 35999, 1, "4K UHD Smart TV, immersive viewing experience.");
                products[1] = new Product("Air Purifier", "Dyson", 18999, 1, "Advanced air purification system, ensures clean air.");
                products[2] = new Product("Vacuum Cleaner", "Eureka Forbes", 5499, 1, "Powerful cyclonic vacuum cleaner, cleans thoroughly.");
                products[3] = new Product("Home Theatre", "Sony", 25999, 1, "Immersive surround sound system, cinematic audio at home.");
                products[4] = new Product("Smart Speaker", "Amazon Echo", 4499, 1, "Voice-controlled smart speaker, plays music and more.");
            } else if (subcategory.equals("Bedroom Appliances")) {
                products[0] = new Product("Air Conditioner", "Voltas", 29999, 1, "Energy-efficient split AC, provides comfortable cooling.");
                products[1] = new Product("Room Heater", "Orient", 1999, 1, "Compact room heater, provides quick warmth.");
                products[2] = new Product("Table Lamp", "Philips", 1299, 1, "Dimmable LED table lamp, perfect for bedside reading.");
                products[3] = new Product("Alarm Clock", "Casio", 799, 1, "Digital alarm clock with snooze function.");
                products[4] = new Product("Humidifier", "Daikin", 4999, 1, "Maintain optimal room humidity for better comfort.");
            }
        } else if (category.equals("Furniture")) { 
            if (subcategory.equals("Bedroom Furniture")) {
                products[0] = new Product("Bed Frame", "Ikea", 15000, 1, "Queen size wooden bed frame, sturdy and stylish.");
                products[1] = new Product("Wardrobe", "Godrej Interio", 12000, 1, "Spacious 3-door wardrobe, ample storage for clothes.");
                products[2] = new Product("Dressing Table", "Urban Ladder", 7000, 1, "Modern dressing table with mirror, elegant design.");
                products[3] = new Product("Nightstand", "Nilkamal", 2500, 1, "Compact bedside nightstand, perfect for small spaces.");
                products[4] = new Product("Mattress", "Wakefit", 9000, 1, "Orthopedic memory foam mattress, provides excellent back support.");
            } else if (subcategory.equals("Living Room Furniture")) {
                products[0] = new Product("Sofa Set", "Durian", 45000, 1, "Luxurious 3+2 seater sofa set, ultimate comfort.");
                products[1] = new Product("Coffee Table", "HomeTown", 8000, 1, "Stylish wooden coffee table, complements any living room.");
                products[2] = new Product("TV Unit", "Nilkamal", 6000, 1, "Contemporary TV entertainment unit, organized storage.");
                products[3] = new Product("Recliner", "La-Z-Boy", 22000, 1, "Comfortable single-seater recliner, perfect for relaxation.");
                products[4] = new Product("Bookshelf", "Ikea", 4000, 1, "Modular wooden bookshelf, great for organizing books.");
            } else if (subcategory.equals("Dining Room Furniture")) {
                products[0] = new Product("Dining Table", "Urban Ladder", 18000, 1, "6-seater solid wood dining table, perfect for family meals.");
                products[1] = new Product("Dining Chair", "Nilkamal", 2000, 1, "Ergonomic dining chair, comfortable seating.");
                products[2] = new Product("Crockery Unit", "Godrej Interio", 11000, 1, "Elegant crockery display unit, showcases your dinnerware.");
                products[3] = new Product("Bar Stool", "Furlenco", 1500, 1, "Modern bar stool, adds a chic touch to your bar area.");
                products[4] = new Product("Sideboard", "HomeTown", 9000, 1, "Functional sideboard for storage and display.");
            }
        }
        return products;
    }

    private void appendStyledText(StyledDocument doc, String text, SimpleAttributeSet attrs) throws BadLocationException {
        doc.insertString(doc.getLength(), text, attrs);
    }

    private void generateBill() {
        StyledDocument doc = billTextPane.getStyledDocument();
        try {
            doc.remove(0, doc.getLength());

            SimpleAttributeSet centerBoldBlue = new SimpleAttributeSet();
            StyleConstants.setAlignment(centerBoldBlue, StyleConstants.ALIGN_CENTER);
            StyleConstants.setBold(centerBoldBlue, true);
            StyleConstants.setFontSize(centerBoldBlue, 20);
            StyleConstants.setForeground(centerBoldBlue, new Color(0, 102, 204)); 

            SimpleAttributeSet headerAttrs = new SimpleAttributeSet();
            StyleConstants.setBold(headerAttrs, true);
            StyleConstants.setFontSize(headerAttrs, 14);
            StyleConstants.setForeground(headerAttrs, new Color(60, 60, 60)); 

            SimpleAttributeSet defaultAttrs = new SimpleAttributeSet();
            StyleConstants.setFontSize(defaultAttrs, 12);
            StyleConstants.setForeground(defaultAttrs, new Color(51, 51, 51)); 

            SimpleAttributeSet discountAttrs = new SimpleAttributeSet();
            StyleConstants.setBold(discountAttrs, true);
            StyleConstants.setForeground(discountAttrs, new Color(220, 50, 50)); 
            StyleConstants.setFontSize(discountAttrs, 12);

            SimpleAttributeSet grandTotalAttrs = new SimpleAttributeSet();
            StyleConstants.setBold(grandTotalAttrs, true);
            StyleConstants.setFontSize(grandTotalAttrs, 18);
            StyleConstants.setForeground(grandTotalAttrs, new Color(50, 180, 70)); 

            SimpleAttributeSet lineAttrs = new SimpleAttributeSet();
            StyleConstants.setForeground(lineAttrs, new Color(150, 150, 150)); 
            StyleConstants.setFontSize(lineAttrs, 12);

            appendStyledText(doc, "===========================================================\n", lineAttrs);
            appendStyledText(doc, "Shopping Bill\n", centerBoldBlue);
            appendStyledText(doc, "===========================================================\n\n", lineAttrs);

            String headerFormat = "%-5s %-25s %-12s %-10s %-12s %-10s\n";
            appendStyledText(doc, String.format(headerFormat, "S.No.", "Product Name", "Quantity", "Price", "Item Total", "GST (5%)"), headerAttrs);
            appendStyledText(doc, "-------------------------------------------------------------------------------------------\n", lineAttrs);

            double totalBeforeDiscountAndGST = 0;
            for (int i = 0; i < cartIndex; i++) {
                Product p = cart[i];
                if (p != null) {
                    double itemTotal = (double) p.getPrice() * p.getQuantity();
                    double itemGST = itemTotal * 0.05; 
                    totalBeforeDiscountAndGST += itemTotal; 

                    String productLine = String.format(headerFormat, 
                        (i + 1) + ".", // Serial Number
                        p.getProductName(), p.getQuantity(), 
                        String.format("₹%d", p.getPrice()), String.format("₹%.2f", itemTotal), String.format("₹%.2f", itemGST));
                    appendStyledText(doc, productLine, defaultAttrs);
                }
            }
            appendStyledText(doc, "-------------------------------------------------------------------------------------------\n\n", lineAttrs);

            appendStyledText(doc, String.format("Total Price Before Discount: ₹%.2f\n", totalBeforeDiscountAndGST), defaultAttrs);
            
            double overallDiscount = totalBeforeDiscountAndGST * 0.10; 
            appendStyledText(doc, String.format("Discount (10%%): - ₹%.2f\n", overallDiscount), discountAttrs);
            
            double totalAfterDiscount = totalBeforeDiscountAndGST - overallDiscount;
            double totalGSTOnDiscountedPrice = totalAfterDiscount * 0.05; 
            appendStyledText(doc, String.format("Total GST (5%% on discounted price): ₹%.2f\n\n", totalGSTOnDiscountedPrice), defaultAttrs);

            double finalPayableAmount = totalAfterDiscount + totalGSTOnDiscountedPrice;
            appendStyledText(doc, String.format("Grand Total: ₹%.2f\n", finalPayableAmount), grandTotalAttrs);
            
            SimpleAttributeSet thankYouAttrs = new SimpleAttributeSet();
            StyleConstants.setAlignment(thankYouAttrs, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontSize(thankYouAttrs, 14);
            StyleConstants.setForeground(thankYouAttrs, new Color(100, 100, 100)); 

            appendStyledText(doc, "\n\nThank you for shopping with us!\n", thankYouAttrs);
            appendStyledText(doc, "===========================================================\n", lineAttrs);

            SimpleAttributeSet alignCenter = new SimpleAttributeSet();
            StyleConstants.setAlignment(alignCenter, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), alignCenter, false);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MiniProject4());
    }
}
