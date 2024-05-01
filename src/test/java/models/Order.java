package models;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "petId",
        "quantity",
        "shipDate",
        "status",
        "complete"
})
@Generated("jsonschema2pojo")
public class Order {

    @JsonProperty("id")
    private long id;
    @JsonProperty("petId")
    private long petId;
    @JsonProperty("quantity")
    private long quantity;
    @JsonProperty("shipDate")
    private String shipDate;
    @JsonProperty("status")
    private String status;
    @JsonProperty("complete")
    private boolean complete;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Order() {
    }

    /**
     *
     * @param petId
     * @param quantity
     * @param id
     * @param shipDate
     * @param complete
     * @param status
     */
    public Order(long id, long petId, long quantity, String shipDate, String status, boolean complete) {
        super();
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    public Order withId(long id) {
        this.id = id;
        return this;
    }

    @JsonProperty("petId")
    public long getPetId() {
        return petId;
    }

    @JsonProperty("petId")
    public void setPetId(long petId) {
        this.petId = petId;
    }

    public Order withPetId(long petId) {
        this.petId = petId;
        return this;
    }

    @JsonProperty("quantity")
    public long getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Order withQuantity(long quantity) {
        this.quantity = quantity;
        return this;
    }

    @JsonProperty("shipDate")
    public String getShipDate() {
        return shipDate;
    }

    @JsonProperty("shipDate")
    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public Order withShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public Order withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("complete")
    public boolean isComplete() {
        return complete;
    }

    @JsonProperty("complete")
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Order withComplete(boolean complete) {
        this.complete = complete;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Order withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}