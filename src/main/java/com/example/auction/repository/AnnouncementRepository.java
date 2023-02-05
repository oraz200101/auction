package com.example.auction.repository;

import com.example.auction.model.entity.Announcement;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {
    @Query("FROM Announcement where status='ACTIVE' and (endDate between:startDate and :endDate)")
    List<Announcement> getActiveAnnouncementEndDate(@Param("startDate")LocalDateTime startDate,@Param("endDate")LocalDateTime endDate);
    @Query("FROM Announcement  a where a.endDate>=:dateTimeNow")
   Page<Announcement> getAnnouncementActive(@Param("dateTimeNow")LocalDateTime dateTimeNow,Pageable pageable);
    @Query("FROM Announcement a where a.endDate>=:dateTimeNow order by a.price desc")
    Page<Announcement> getWithPriceDesc(@Param("dateTimeNow")LocalDateTime dateTimeNow,Pageable pageable);
}
