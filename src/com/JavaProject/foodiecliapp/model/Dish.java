package com.JavaProject.foodiecliapp.model;

import java.util.Objects;

public class Dish {

    private String id;

    private String name;

    private String description;

    private double price;

    public Dish() {
    }

    public String getId() {
        return id;
    }

    public Dish setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dish setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Dish setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Dish setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dishes = (Dish) o;
        return Double.compare(price, dishes.price) == 0 && Objects.equals(id, dishes.id) && Objects.equals(name, dishes.name) && Objects.equals(description, dishes.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
