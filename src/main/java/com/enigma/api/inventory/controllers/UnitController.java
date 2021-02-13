package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.controllers.defaults.Components;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.request.unit.UnitPutRequest;
import com.enigma.api.inventory.models.response.Response;
import com.enigma.api.inventory.models.pagination.PageList;
import com.enigma.api.inventory.models.request.unit.UnitPostRequest;
import com.enigma.api.inventory.models.response.unit.UnitResponse;
import com.enigma.api.inventory.models.request.unit.UnitSearch;
import com.enigma.api.inventory.models.validations.Validations;
import com.enigma.api.inventory.services.UnitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/units")
@RestController
public class UnitController {
    @Autowired
    protected UnitService unitService;

    @Autowired
    protected ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Response findById(@Valid @PathVariable Integer id) {
        Unit unit = unitService.findById(id);
        Validations.entityNotNull(unit);
        return Response.success(unit);
    }


    @GetMapping
    public Response<PageList<UnitResponse>> findAll(@Valid UnitSearch request) {
        Unit unit = modelMapper.map(request, Unit.class);

        Page<Unit> pagination = unitService.findAll(unit, request.getPage(),
                request.getSize(), request.getSort());

        List<UnitResponse> unitResponseList = pagination.stream()
                .map(e -> modelMapper.map(e, UnitResponse.class))
                .collect(Collectors.toList());

        PageList<UnitResponse> response = new PageList<>(unitResponseList,
                pagination.getNumber(), pagination.getSize(),
                pagination.getTotalElements());

        return Response.success(response);
    }


    @PostMapping
    public Response<UnitResponse> add(@RequestBody @Valid UnitPostRequest request) {
        Unit unit               = modelMapper.map(request, Unit.class);
        unit                    = unitService.save(unit);
        UnitResponse response   = modelMapper.map(unit, UnitResponse.class);
        return Response.success(response);
    }


    @PutMapping("/{id}")
    public Response<UnitResponse> edit(@PathVariable Integer id, @RequestBody @Valid UnitPutRequest request) {
        Unit unit = unitService.findById(id);
        Validations.entityNotNull(unit);

        modelMapper.map(request, unit);
        unit = unitService.save(unit);

        UnitResponse response = modelMapper.map(unit, UnitResponse.class);
        return Response.success(response);
    }


    @DeleteMapping("/{id}")
    public Response removeById(@PathVariable Integer id) {
        Unit unit = unitService.removeById(id);
        Validations.entityNotNull(unit);
        return Response.success(unit);
    }
}
