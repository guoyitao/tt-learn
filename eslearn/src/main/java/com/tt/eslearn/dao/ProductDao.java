package com.tt.eslearn.dao;


import com.tt.eslearn.pojo.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {

    List<Product> findByTitle(String title);

    List<Product> findByCategory(String category, Pageable pageable);
}

