package ru.gpm.example.mybatis.min.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Наличие на складе.
 */
@Data
@Accessors(chain = true)
public class Stock {

    private Product product;
    private Warehouse warehouse;
    private int count;
}
