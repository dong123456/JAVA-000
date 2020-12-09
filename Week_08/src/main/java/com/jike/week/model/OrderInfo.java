package com.jike.week.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
public class OrderInfo {

    @JsonProperty("id")
    private int orderId;

    @JsonProperty("order_no")
    private String orderNo;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("order_amount")
    private int orderAmount;

    @JsonProperty("shipping_amount")
    private int shippingAmount;

    @JsonProperty("pay_amount")
    private int payAmount;

    @JsonProperty("status")
    private int status;

}
