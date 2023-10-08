package ru.gpm.example.mybatis.min;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gpm.example.mybatis.min.domain.Product;
import ru.gpm.example.mybatis.min.domain.Stock;
import ru.gpm.example.mybatis.min.domain.Warehouse;
import ru.gpm.example.mybatis.min.repository.ProductRepository;
import ru.gpm.example.mybatis.min.repository.WarehouseRepository;
import ru.gpm.example.mybatis.min.service.StockService;

import java.util.List;

@Slf4j
@SpringBootTest
class MyBatisApplicationTest {

    @Autowired
    private StockService stockService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Test
    void init() {
        // Товары
        productRepository.save(new Product().setName("name-1").setSku("sku-1"));
        productRepository.save(new Product().setName("name-2").setSku("sku-2"));
        final List<Product> all = productRepository.findAll();
        Assertions.assertEquals(2, all.size());
        // Склад
        final Warehouse warehouse = new Warehouse().setName("склад-1");
        warehouseRepository.save(warehouse);
        Assertions.assertNotNull(warehouseRepository.findOne(warehouse.getId()));
        // Остатки
        final Stock stock1 = new Stock().setProduct(all.get(0)).setWarehouse(warehouse).setQty(10);
        final Stock stock2 = new Stock().setProduct(all.get(1)).setWarehouse(warehouse).setQty(50);
        stockService.save(stock1);
        stockService.save(stock2);
        List<Stock> allByWarehouse = stockService.getAllByWarehouse(warehouse);
        Assertions.assertEquals(2, allByWarehouse.size());
        log.info("{}", allByWarehouse);
        stockService.save(stock1.setQty(20));
        allByWarehouse = stockService.getAllByWarehouse(warehouse);
        Assertions.assertEquals(2, allByWarehouse.size());
        log.info("{}", allByWarehouse);
    }
}