package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class ProductMgmtApp {
    public static void main(String[] args) {
        // Create sample products
        Product[] products = {
            new Product(3128874119L, "Banana", LocalDate.parse("2023-01-24"), 124, 0.55),
            new Product(2927458265L, "Apple", LocalDate.parse("2022-12-09"), 18, 1.09),
            new Product(9189927460L, "Carrot", LocalDate.parse("2023-03-31"), 89, 2.99)
        };

        // Sort and print products
        printProducts(products);
    }

    private static void printProducts(Product[] products) {
        // Sort products by name
        Arrays.sort(products, Comparator.comparing(Product::getName));

        System.out.println("JSON-formatted list of all Products:");
        printAsJson(products);
        
        System.out.println("\nXML-formatted list of all Products:");
        printAsXml(products);
        
        System.out.println("\nCSV-formatted list of all Products:");
        printAsCsv(products);
    }

    private static void printAsJson(Product[] products) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            System.out.println(mapper.writeValueAsString(products));
        } catch (Exception e) {
            System.err.println("Error printing JSON: " + e.getMessage());
        }
    }

    private static void printAsXml(Product[] products) {
        try {
            JAXBContext context = JAXBContext.newInstance(Product.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            for (Product product : products) {
                marshaller.marshal(product, System.out);
            }
        } catch (JAXBException e) {
            System.err.println("Error printing XML: " + e.getMessage());
        }
    }

    private static void printAsCsv(Product[] products) {
        System.out.println("ProductId,Name,DateSupplied,QuantityInStock,UnitPrice");
        for (Product product : products) {
            System.out.printf("%d,%s,%s,%d,%.2f%n",
                product.getProductId(),
                product.getName(),
                product.getDateSupplied(),
                product.getQuantityInStock(),
                product.getUnitPrice());
        }
    }
} 