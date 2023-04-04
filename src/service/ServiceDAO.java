package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class ServiceDAO {

    private Connection connection;

    public ServiceDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Service> getAllServices() {
        String query = "SELECT * FROM services";
        List<Service> services = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Service service = new Service(resultSet.getInt("service_id"), 
                                              resultSet.getString("name"),
                                              resultSet.getString("description"), 
                                              resultSet.getDouble("price"));
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    public Service getServiceById(int serviceId) {
        String query = "SELECT * FROM services WHERE service_id = ?";
        Service service = null;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, serviceId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                service = new Service(resultSet.getInt("service_id"), 
                                      resultSet.getString("name"),
                                      resultSet.getString("description"), 
                                      resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return service;
    }

    public void addService(Service service) {
        String query = "INSERT INTO services (name, description, price) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, service.getName());
            statement.setString(2, service.getDescription());
            statement.setDouble(3, service.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateService(Service service) {
        String query = "UPDATE services SET name = ?, description = ?, price = ? WHERE service_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, service.getName());
            statement.setString(2, service.getDescription());
            statement.setDouble(3, service.getPrice());
            statement.setInt(4, service.getServiceId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteService(int serviceId) {
        String query = "DELETE FROM services WHERE service_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, serviceId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
