package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.controllers.defaults.Components;
import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.models.request.file.MultipartRequest;
import com.enigma.api.inventory.models.request.item.ItemPatchRequest;
import com.enigma.api.inventory.models.response.item.ItemResponseFiltered;
import com.enigma.api.inventory.models.request.item.ItemRequest;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.response.Response;
import com.enigma.api.inventory.models.pagination.PageList;
import com.enigma.api.inventory.models.response.item.ItemResponse;
import com.enigma.api.inventory.models.request.item.ItemSearch;
import com.enigma.api.inventory.models.validations.Validations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/items")
@RestController
public class ItemController extends Components {

    @GetMapping("/{id}")
    public Response<Item> findById(@Valid @PathVariable Integer id) {
        Item item = itemService.findById(id);
        Validations.entityNotNull(item);
//        return ResponseEntity.ok(item);
        return Response.success(item);
    }


    @Operation(summary = "Add Item", description = "Some description..", tags = { "items" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Internal server error")
        }
    )

    @PostMapping
    public Response<ItemResponse> add(@RequestBody @Valid ItemRequest request) {
        Item item               = modelMapper.map(request, Item.class);
        Unit unit               = unitService.findById(request.getUnitId());
        item.setUnit(unit);
        item                    = itemService.save(item);
        System.out.println(item);
        ItemResponse response   = modelMapper.map(item, ItemResponse.class);

        return Response.success(response);
    }

    @GetMapping
    public Response<PageList<ItemResponseFiltered>> findAll(@Valid ItemSearch request) {
        Item item = modelMapper.map(request, Item.class);

        Page<Item> pagination = itemService.findAll(item, request.getPage(),
                request.getSize(), request.getSort());

        List<ItemResponseFiltered> itemResponseFilteredList = pagination.stream()
                .map(e -> modelMapper.map(e, ItemResponseFiltered.class))
                .collect(Collectors.toList());

        PageList<ItemResponseFiltered> response = new PageList(itemResponseFilteredList,
                pagination.getNumber(), pagination.getSize(),
                pagination.getTotalElements());

        return Response.success(response);
    }


    @PutMapping("/{id}")
    public Response<ItemResponse> edit(@PathVariable Integer id,
                                       @RequestBody @Valid ItemRequest request) {

        Item item = itemService.findById(id);
        Validations.entityNotNull(item);

        Unit unit = unitService.findById(request.getUnitId());
        item.setUnit(unit);

        modelMapper.map(request, item);
        item = itemService.save(item);
        ItemResponse response = modelMapper.map(item, ItemResponse.class);
        return Response.success(response);
    }


    @PatchMapping("/{id}")
    public Response<ItemResponse> editPartial(@PathVariable Integer id,
                                              @RequestBody @Valid ItemPatchRequest request) {

        Item item = itemService.findById(id);
        Validations.entityNotNull(item);

        request = getRequest(request, item);
        modelMapper.map(request, item);

        item = itemService.save(item);
        ItemResponse response = modelMapper.map(item, ItemResponse.class);

        return Response.success(response);
    }


    @DeleteMapping("/{id}")
    public Response removeById(@PathVariable Integer id) {
        Item item = itemService.removeById(id);
        Validations.entityNotNull(item);
        return Response.success(item);
    }


    @PostMapping(value = "/{id}/file", consumes = "multipart/form-data")
    public Response upload(@PathVariable Integer id, @Valid MultipartRequest file)
            throws IOException, MimeTypeException {

        Item item = itemService.findById(id);
        Validations.entityNotNull(item);
        fileService.upload(item, file.getFile().getInputStream());
        return Response.success(true);
    }


    @GetMapping(value = "/{id}/file")
    public void download(@PathVariable Integer id,
                         HttpServletResponse response) throws IOException {

        Item item = itemService.findById(id);
        Validations.entityNotNull(item);

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "filename=\"" + item.getId() + "\"");

        fileService.download(item, response.getOutputStream());
    }


    public ItemPatchRequest getRequest(ItemPatchRequest request, Item item) {
        if (request.getName() == null) request.setName(item.getName());
        if (request.getPrice() == null) request.setPrice(item.getPrice());
        if (request.getUnitId() == null) request.setUnitId(item.getUnit().getId());
        item.setUnit(unitService.findById(request.getUnitId()));
        return request;
    }
}


