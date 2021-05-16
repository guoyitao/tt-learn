package com.tt.eslearn.dao;

import com.tt.eslearn.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void saveProduct(){
        Product product = new Product();
        product.setId(1L);
        product.setTitle("小米6");
        product.setCategory("小米");
        product.setPrice(11111.0D);
        product.setImages("1231.jpg");
        productDao.save(product);
    }

    @Test
    public void updateProduct(){
        Product product = new Product();
        product.setId(1L);
        product.setTitle("小米111");
        product.setCategory("小米");
        product.setPrice(11111.0D);
        product.setImages("1231.jpg");
        productDao.save(product);
    }

    @Test
    public void findById(){
        Optional<Product> byId = productDao.findById(1L);
        byId.ifPresent(System.out::println);
    }

    @Test
    public void findAll(){
        Iterable<Product> all = productDao.findAll();
        for (Product product : all) {
            System.out.println(product);
        }
    }

    @Test
    public void deleteById(){
        Product product = new Product();
        product.setId(1L);
        productDao.delete(product);
    }

    @Test
    public void saveAll(){
        List<Product> list =new ArrayList<>(101);
        for (int i = 0; i < 100; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setTitle("小米111");
            product.setCategory("小米");
            product.setPrice(11111.0D);
            product.setImages("1231.jpg");
            list.add(product);
        }
        productDao.saveAll(list);
    }

    @Test
    public void findByPageable(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        int currentPage = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(currentPage, pageSize, sort);
        Page<Product> all = productDao.findAll(pageable);
        all.getContent().forEach(System.out::println);

    }


}