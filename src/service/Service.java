package service;

public class Service {
    private int serviceId;
    private String name;
    private String description;
    private double price;

    public Service(int serviceId, String name, String description, double price) {
        this.serviceId = serviceId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
