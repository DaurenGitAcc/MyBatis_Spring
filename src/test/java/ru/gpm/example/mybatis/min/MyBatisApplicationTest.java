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
        // Добавим товары в БД
        productRepository.save(new Product().setName("name-1").setSku("sku-1"));
        productRepository.save(new Product().setName("name-2").setSku("sku-2"));
        final List<Product> all = productRepository.findAll();
        Assertions.assertEquals(2, all.size());

        // Добавим склад.
        final Warehouse warehouse = new Warehouse().setName("склад-1");
        warehouseRepository.save(warehouse);
        Assertions.assertNotNull(warehouseRepository.findOne(warehouse.getId()));

        // Сохраним остатки по товарам на складе
        final Stock stock1 = new Stock().setProduct(all.get(0)).setWarehouse(warehouse).setCount(10);
        final Stock stock2 = new Stock().setProduct(all.get(1)).setWarehouse(warehouse).setCount(50);
        stockService.save(stock1);
        stockService.save(stock2);

        // Получим текущие остатки на складе
        List<Stock> allByWarehouse = stockService.getAllByWarehouse(warehouse);
        Assertions.assertEquals(2, allByWarehouse.size());
        log.info("{}", allByWarehouse);

        // Поменяем остаток товара
        stockService.save(stock1.setCount(20));
        allByWarehouse = stockService.getAllByWarehouse(warehouse);
        Assertions.assertEquals(2, allByWarehouse.size());
        final Stock stockEdit =  stockService.getBy(warehouse, stock1.getProduct());
        Assertions.assertNotNull(stockEdit);
        Assertions.assertEquals(20, stockEdit.getCount());
        log.info("{}", allByWarehouse);
    }
}