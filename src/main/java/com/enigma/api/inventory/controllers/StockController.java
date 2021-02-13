package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.controllers.defaults.Components;
import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.entities.summaries.StockSummary;
import com.enigma.api.inventory.models.response.stock.StockResponseFiltered;
import com.enigma.api.inventory.models.request.stock.StockSearch;
import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.models.response.Response;
import com.enigma.api.inventory.models.pagination.PageList;
import com.enigma.api.inventory.models.request.stock.StockRequest;
import com.enigma.api.inventory.models.response.stock.StockResponse;
import com.enigma.api.inventory.models.validations.Validations;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/stocks")
@RestController
public class StockController extends Components {


    @GetMapping("/{id}")
    public Response findById(@Valid @PathVariable Integer id) {
        Stock stock = stockService.findById(id);
        Validations.entityNotNull(stock);
        return Response.success(stock);
    }


    @GetMapping
    public Response<PageList<StockResponseFiltered>> findAll(@Valid StockSearch request) {
        Stock stock = modelMapper.map(request, Stock.class);

        Page<Stock> pagination = stockService.findAll(stock, request.getPage(),
                request.getSize(), request.getSort());

        List<StockResponseFiltered> stockResponseFilteredList = pagination.stream()
                .map(e -> modelMapper.map(e, StockResponseFiltered.class))
                .collect(Collectors.toList());

        PageList<StockResponseFiltered> response = new PageList(stockResponseFilteredList,
                pagination.getNumber(), pagination.getSize(),
                pagination.getTotalElements());

        return Response.success(response);
    }


    @GetMapping("/summary")
    public Response<List<StockSummary>> summary() {
        List<StockSummary> summary = stockService.findAllSummary();
        return Response.success(summary);
    }


    @PostMapping
    public Response<StockResponse> add(@RequestBody @Valid StockRequest request) {
        Stock stock = modelMapper.map(request, Stock.class);
        Item item = itemService.findById(request.getItemId());
        stock.setItem(item);
        stock = stockService.save(stock);
        StockResponse response = modelMapper.map(stock, StockResponse.class);
        return Response.success(response);
    }


    @PutMapping("/{id}")
    public Response<StockResponse> edit(@PathVariable Integer id,
                                        @RequestBody @Valid StockRequest request) {

        Stock stock = stockService.findById(id);
        Validations.entityNotNull(stock);

        Item item = itemService.findById(request.getItemId());
        stock.setItem(item);
        modelMapper.map(request, stock);

        stock = stockService.save(stock);
        StockResponse data = modelMapper.map(stock, StockResponse.class);
        return Response.success(data);
    }


    @DeleteMapping("/{id}")
    public Response removeById(@PathVariable Integer id) {
        Stock stock = stockService.removeById(id);
        Validations.entityNotNull(stock);
        return Response.success(stock);
    }
}
