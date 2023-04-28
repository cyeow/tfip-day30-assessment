package ibf2022.assessment.paf.batch3.models;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class Order {

    private String orderId;
    private LocalDate date;
    private Integer breweryId;
    private List<LineItem> orders = new LinkedList<>();

    public Order() {
    }

    public Order(String orderId, LocalDate date, Integer breweryId, List<LineItem> orders) {
        this.orderId = orderId;
        this.date = date;
        this.breweryId = breweryId;
        this.orders = orders;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(Integer breweryId) {
        this.breweryId = breweryId;
    }

    public List<LineItem> getOrders() {
        return orders;
    }

    public void setOrders(List<LineItem> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", date=" + date + ", breweryId=" + breweryId + ", orders=" + orders + "]";
    }

    public String toJsonString() {
        return toJson().toString();
    }

    public JsonObject toJson() {
        JsonArrayBuilder ab = Json.createArrayBuilder();
        orders.forEach(o -> ab.add(o.toJson()));

        return Json.createObjectBuilder()
                .add("orderId", getOrderId())
                .add("date", getDate().toString())
                .add("breweryId", getBreweryId())
                .add("orders", ab)
                .build();
    }
}
