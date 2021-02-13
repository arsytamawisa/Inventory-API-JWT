package com.enigma.api.inventory.services;

import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.entities.summaries.StockSummary;
import com.enigma.api.inventory.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class StockService extends AbstractService<Stock, Integer> {

    @Autowired
    protected StockService(StockRepository repository) {
        super(repository);
    }

    @Autowired
    private EntityManager entityManager;


    public List<StockSummary> findAllSummary() {
        CriteriaBuilder builder                 = entityManager.getCriteriaBuilder();
        CriteriaQuery<StockSummary> criteria    = builder.createQuery(StockSummary.class);
        Root<Stock> root                        = criteria.from(Stock.class);

        criteria.multiselect(root.get("item"),
                builder.sum(root.get("quantity")),
                builder.sum(root.get("totalPrice")))
                .groupBy(root.get("item"));

        return entityManager.createQuery(criteria).getResultList();
    }
}
