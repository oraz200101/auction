package com.example.auction.model.entity;

import com.example.auction.model.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static com.example.auction.constants.swagger.AnnouncementSwaggerConstants.BID_PRICE_DESCRIPTION;
import static com.example.auction.constants.swagger.AnnouncementSwaggerConstants.BID_PRICE_EXAMPLE;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Bid extends BaseEntity implements Comparable<Bid>{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "bid_announcement_id") ,name = "announcement_id",referencedColumnName = "id")
    @JsonIgnore
    private Announcement announcement;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name = "bid_user_id") ,name = "bidder_id",referencedColumnName = "id")
    private User bidder;
    @Schema(description = BID_PRICE_DESCRIPTION,example = BID_PRICE_EXAMPLE)
    private BigDecimal bidPrice;


    @Override
    public int compareTo(Bid o) {
        return getBidPrice().compareTo(o.getBidPrice());
    }
}
