package ru.gpm.example.mybatis.min.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Товар.
 */
@Data
@Accessors(chain = true)
public class Product {
    private Integer id;
    private String name;
    private String sku;
}
