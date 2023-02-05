package com.example.auction.service;

import com.example.auction.model.dto.AnnouncementCreateDto;
import com.example.auction.model.dto.BidCreateDto;
import com.example.auction.model.entity.Announcement;
import com.example.auction.model.entity.Bid;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnnouncementService {
    Announcement create(AnnouncementCreateDto announcementCreateDto,String username);
    Page<Announcement> getActive(Pageable pageable,Integer pageNumber);
    Page<Announcement> getWihPriceDesc(Pageable pageable,Integer pageNumber);
    Announcement getById(Long id);

    Bid createBid(Long announcementId, BidCreateDto bidCreateDto,String username);

}
