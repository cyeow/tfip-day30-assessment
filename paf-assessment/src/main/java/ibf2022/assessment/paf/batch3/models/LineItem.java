package ibf2022.assessment.paf.batch3.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class LineItem {

    private Integer beerId;
    private Integer quantity;

    public LineItem() {
    }

    public LineItem(Integer beerId, Integer quantity) {
        this.beerId = beerId;
        this.quantity = quantity;
    }

    public Integer getBeerId() {
        return beerId;
    }

    public void setBeerId(Integer beerId) {
        this.beerId = beerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "LineItem [beerId=" + beerId + ", quantity=" + quantity + "]";
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("beerId", getBeerId())
                .add("quantity", getQuantity())
                .build();
    }
}
