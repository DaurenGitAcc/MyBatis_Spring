package ru.gpm.example.mybatis.min.repository;

import org.apache.ibatis.annotations.Mapper;
import ru.gpm.example.mybatis.min.domain.Product;

import java.util.List;

@Mapper
public interface ProductRepository {

    void save(Product product);

    List<Product> findAll();
}
