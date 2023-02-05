package com.example.auction.service.impl;

import com.example.auction.exception.domain.*;
import com.example.auction.model.dto.AnnouncementCreateDto;
import com.example.auction.model.dto.BidCreateDto;
import com.example.auction.model.entity.Announcement;
import com.example.auction.model.entity.Bid;
import com.example.auction.model.entity.User;
import com.example.auction.model.enums.Status;
import com.example.auction.repository.AnnouncementRepository;
import com.example.auction.repository.BidRepository;
import com.example.auction.repository.UserRepository;
import com.example.auction.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;
    private final BidRepository bidRepository;
    private final MailServiceImpl mailService;
    @Override
    @Transactional
    public  Announcement create(AnnouncementCreateDto announcementCreateDto, String username) {
        User user=userRepository.getByEmail(username).orElseThrow(UserNotFoundByEmailException::new);
        Announcement announcement=new Announcement();
        announcement.setName(announcementCreateDto.getName());
        announcement.setPrice(announcementCreateDto.getPrice());
        announcement.setDescription(announcementCreateDto.getDescription());
        announcement.setStatus(Status.ACTIVE);
        announcement.setCreatedBy(user);
        return announcementRepository.save(announcement);
    }

    @Scheduled(fixedRate = 1800000)
    public void endBidding(){
        LocalDateTime starDate=LocalDateTime.now().minusMinutes(30);
        LocalDateTime endDate=LocalDateTime.now().plusMinutes(10);
        List<Announcement>announcements=announcementRepository.getActiveAnnouncementEndDate(starDate,endDate);
        for (Announcement announcement:announcements){
            if(announcement.getEndDate().isBefore(LocalDateTime.now())){
                log.info("Завершается прием ставок");
                log.info("Прием ставок завершен на обьявление: {}, созданное: {}",announcement.getName(),announcement.getCreatedBy().getName());
                updateAnnouncement(announcement);
                log.info("Сделано");
            }
        }
    }


    @Transactional
    public void updateAnnouncement(Announcement announcement){
        if (announcement.getBids()!=null&&!announcement.getBids().isEmpty()) {
            Bid bidMax = getMax(announcement);
            log.info("Победитель обьявления {}",bidMax.getBidder().getName());
            announcement.setPrice(bidMax.getBidPrice());
            sentToEmailAboutWin(bidMax);
        }
        announcement.setStatus(Status.ARCHIVE);
        announcementRepository.save(announcement);
    }


    @Override
    public Page<Announcement> getActive(Pageable pageable,Integer pageNumber){
        return announcementRepository.getAnnouncementActive(LocalDateTime.now(),pageable.withPage(pageNumber));
    }

    @Override
    public Page<Announcement> getWihPriceDesc(Pageable pageable,Integer pageNumber) {
        return announcementRepository.getWithPriceDesc(LocalDateTime.now(),pageable.withPage(pageNumber));
    }

    @Override
    public Announcement getById(Long id) {
        return announcementRepository.findById(id).orElseThrow(AnnouncementNotFoundByIdException::new);
    }

    @Override
    @Transactional
    public synchronized Bid createBid(Long announcementId, BidCreateDto bidCreateDto, String username) {

        Announcement announcement = getById(announcementId);
        if(announcement.getEndDate()!= null&&announcement.getEndDate().isBefore(LocalDateTime.now())){
            throw new BidDateHasPassedException();
        }
//         if (!announcement.getCreatedBy().getName().equals(username)) {
//             throw new UserCannotBidOnTheirAdException();
//         }
         if (announcement.getBids().size() > 0) {
            Bid maxBid = getMax(announcement);
            if (bidCreateDto.getPrice().doubleValue() <= maxBid.getBidPrice().doubleValue()) {
                throw new BidLowerThanActualPriceException();
            }
            sendToEmailAboutBeat(username,maxBid);
            log.info("Было отправлен уведомление о перебивке пользователю {}",maxBid.getBidder().getEmail());
        }
        if (bidCreateDto.getPrice().doubleValue() < announcement.getPrice().doubleValue()) {
            throw new BidLowerThanActualPriceException();
        }

        Bid bid = new Bid();
        bid.setBidPrice(bidCreateDto.getPrice());
        bid.setBidder(userRepository.getByEmail(username).orElseThrow(UserNotFoundByEmailException::new));
        bid.setAnnouncement(announcement);
        if (announcement.getStartDate()==null&&announcement.getEndDate()==null){
        announcement.setStartDate(LocalDateTime.now());
        announcement.setEndDate(LocalDateTime.now().plusHours(1));
        }
        return bidRepository.save(bid);
    }


   public void sendToEmailAboutBeat(String username,Bid maxBid){
        String message = String.format(
                "Привет, " + maxBid.getBidder().getName() + " " + username + " перебил вашу ставку");
        mailService.send(maxBid.getBidder().getEmail(), "Auction", message);
    }
    public void sentToEmailAboutWin(Bid maxBid){
        String message =String.format(
                "Привет, " + maxBid.getBidder().getName() +" ваша ставка выиграла на обьяление по названию "+maxBid.getAnnouncement().getName()
        );
        mailService.send(maxBid.getBidder().getEmail(),"Auction",message);
        log.info("Уведомление на электронную почту отправлено{}",maxBid.getBidder().getEmail());
    }

    public  Bid getMax(Announcement announcement) {
        return announcement.getBids().stream().max(Bid::compareTo).orElseThrow(BootstrapMethodError::new);
    }

}
