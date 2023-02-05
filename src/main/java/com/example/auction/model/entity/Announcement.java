package com.example.auction.model.entity;

import com.example.auction.model.entity.base.BaseEntity;
import com.example.auction.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Set;

import static com.example.auction.constants.swagger.AnnouncementSwaggerConstants.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Announcement extends BaseEntity {
    @Schema(description = NAME_OF_ANNOUNCEMENT_DESCRIPTION,example = NAME_OF_ANNOUNCEMENT_EXAMPLE)
    private String name;
    @Schema(description = DESCRIPTION_OF_ANNOUNCEMENT_DESCRIPTION,example = DESCRIPTION_OF_ANNOUNCEMENT_EXAMPLE)
    private String description;
    @Schema(description = PRICE_OF_ANNOUNCEMENT_DESCRIPTION,example = PRICE_OF_ANNOUNCEMENT_EXAMPLE)
    private BigDecimal price;
    @Schema(description = STATUS_OF_ANNOUNCEMENT_DESCRIPTION,example = STATUS_OF_ANNOUNCEMENT_EXAMPLE)
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "created_user_id"),name = "created_by",referencedColumnName = "id")
    private User createdBy;
    @OneToMany(mappedBy = "announcement",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Bid>bids;
    @Schema(description = START_DATE_OF_ANNOUNCEMENT_DESCRIPTION,example = START_DATE_OF_ANNOUNCEMENT_EXAMPLE)
    private LocalDateTime startDate;
    @Schema(description = END_DATE_OF_ANNOUNCEMENT_DESCRIPTION,example = END_DATE_OF_ANNOUNCEMENT_EXAMPLE)
    private LocalDateTime endDate;

}
