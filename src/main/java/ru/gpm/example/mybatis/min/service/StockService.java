package ru.gpm.example.mybatis.min.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gpm.example.mybatis.min.domain.Product;
import ru.gpm.example.mybatis.min.domain.Stock;
import ru.gpm.example.mybatis.min.domain.Warehouse;
import ru.gpm.example.mybatis.min.repository.StockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository repository;

    public Stock save(Stock stock) {
        repository.save(stock);
        return stock;
    }

    public List<Stock> getAllByWarehouse(Warehouse warehouse) {
        return repository.findStockByWarehouse(warehouse);
    }

    public Stock getBy(Warehouse warehouse, Product product) {
        return repository.findStockByWarehouseAndProduct(warehouse.getId(), product.getId());
    }
}
