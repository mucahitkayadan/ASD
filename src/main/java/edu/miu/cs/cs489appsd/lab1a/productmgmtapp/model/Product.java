package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

/**
 * Represents a product in the product management system.
 * This class is annotated with @XmlRootElement for XML serialization/deserialization.
 */
@XmlRootElement
public class Product {
    /** Unique identifier for the product */
    private Long productId;
    
    /** Name of the product */
    private String name;
    
    /** Date when the product was supplied */
    private LocalDate dateSupplied;
    
    /** Current quantity available in stock */
    private Integer quantityInStock;
    
    /** Price per unit of the product */
    private Double unitPrice;

    /**
     * Default constructor required for XML/JSON serialization.
     */
    public Product() {
    }

    /**
     * Constructs a new Product with all fields.
     *
     * @param productId Unique identifier for the product
     * @param name Name of the product
     * @param dateSupplied Date when the product was supplied
     * @param quantityInStock Current quantity available in stock
     * @param unitPrice Price per unit of the product
     */
    public Product(Long productId, String name, LocalDate dateSupplied, 
                  Integer quantityInStock, Double unitPrice) {
        this.productId = productId;
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }

    /**
     * Copy constructor to create a new Product instance from an existing one.
     *
     * @param other The Product instance to copy from
     */
    public Product(Product other) {
        this.productId = other.productId;
        this.name = other.name;
        this.dateSupplied = other.dateSupplied;
        this.quantityInStock = other.quantityInStock;
        this.unitPrice = other.unitPrice;
    }

    /**
     * Gets the product's unique identifier.
     *
     * @return The product ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Sets the product's unique identifier.
     *
     * @param productId The product ID to set
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * Gets the product's name.
     *
     * @return The product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product's name.
     *
     * @param name The product name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the date when the product was supplied.
     *
     * @return The supply date
     */
    public LocalDate getDateSupplied() {
        return dateSupplied;
    }

    /**
     * Sets the date when the product was supplied.
     *
     * @param dateSupplied The supply date to set
     */
    public void setDateSupplied(LocalDate dateSupplied) {
        this.dateSupplied = dateSupplied;
    }

    /**
     * Gets the current quantity in stock.
     *
     * @return The quantity in stock
     */
    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    /**
     * Sets the current quantity in stock.
     *
     * @param quantityInStock The quantity to set
     */
    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    /**
     * Gets the price per unit.
     *
     * @return The unit price
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the price per unit.
     *
     * @param unitPrice The unit price to set
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}