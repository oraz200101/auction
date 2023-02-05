package com.example.auction.controller;


import com.example.auction.model.dto.AnnouncementCreateDto;
import com.example.auction.model.dto.BidCreateDto;
import com.example.auction.model.entity.Announcement;
import com.example.auction.model.entity.Bid;
import com.example.auction.service.impl.AnnouncementServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.example.auction.constants.swagger.AnnouncementSwaggerConstants.*;


@RestController
@RequestMapping("/api/announcement")
@RequiredArgsConstructor
@Tag(name = NAME_OF_ANNOUNCEMENT_CONTROLLER,description = DESCRIPTION_OF_ANNOUNCEMENT_CONTROLLER)
public class AnnouncementController {
    private final AnnouncementServiceImpl announcementService;

    @PostMapping
    @Operation(description = DESCRIPTION_OF_API_FOR_CREATING_ANNOUNCEMENT)
    public ResponseEntity<Announcement> create(@RequestBody @Validated AnnouncementCreateDto announcementCreateDto, Principal principal){
        return ResponseEntity.ok(announcementService.create(announcementCreateDto, principal.getName()));
    }

    @PostMapping("/{announcementId}/bid")
    @Operation(description = DESCRIPTION_OF_API_FOR_CREATING_BID)
    public ResponseEntity<Bid> createBid(@PathVariable Long announcementId, @RequestBody @Validated BidCreateDto bidCreateDto
            ,Principal principal){

        return ResponseEntity.ok(announcementService.createBid(announcementId,bidCreateDto, principal.getName()));
    }
    @GetMapping("/all/{pageNumber}")
    @Operation(description = DESCRIPTION_OF_API_FOR_GET_ALL_ACTUAL_ANNOUNCEMENT)
    public ResponseEntity<Page<Announcement>>getAll(@PathVariable Integer pageNumber, @PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(announcementService.getActive(pageable,pageNumber));
    }

    @GetMapping("/{id}")
    @Operation(description = DESCRIPTION_OF_API_FOR_GET_ANNOUNCEMENT_BY_ID)
    public ResponseEntity<Announcement> getById(@PathVariable Long id){
        return ResponseEntity.ok(announcementService.getById(id));
    }

    @GetMapping("/all/{pageNumber}/priceDesc")
    @Operation(description = DESCRIPTION_OF_API_FOR_GET_ALL_ACTUAL_ANNOUNCEMENT_WITH_DESC_PRICE)
    public ResponseEntity<Page<Announcement>>getWithPriceDesc(@PathVariable Integer pageNumber,@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(announcementService.getWihPriceDesc(pageable,pageNumber));
    }


}
