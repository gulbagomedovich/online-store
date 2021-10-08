package com.gulbagomedovich.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private Long id;
    @EqualsAndHashCode.Include
    private List<ItemDto> items;
    @EqualsAndHashCode.Include
    private BigDecimal totalAmount;
    @EqualsAndHashCode.Include
    private String customerUsername;
}
