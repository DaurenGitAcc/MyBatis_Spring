package ru.gpm.example.mybatis.min.repository;

import org.apache.ibatis.annotations.Mapper;
import ru.gpm.example.mybatis.min.domain.Stock;
import ru.gpm.example.mybatis.min.domain.Warehouse;

import java.util.List;

@Mapper
public interface WarehouseRepository {

    void save(Warehouse warehouse);

    Warehouse findOne(int id);
}
