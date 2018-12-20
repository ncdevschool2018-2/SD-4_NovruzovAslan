package com.netcracker.edu.backend.scheduler;

import com.netcracker.edu.backend.entity.*;
import com.netcracker.edu.backend.service.SpecialSubscriptionService;
import com.netcracker.edu.backend.service.SubscriptionService;
import com.netcracker.edu.backend.service.UserService;
import com.netcracker.edu.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class ScheduleTask {

    @Autowired
    UserService userService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    SpecialSubscriptionService specialSubscriptionService;

    @Autowired
    WalletService walletService;

    //(seconds, minutes, hours, day of month, month, day of week, year(optional))

    @Scheduled(cron = "0 */1 * * * ?")
    public void cronSchedule() {
        System.out.println("Date of transaction:" + new Date());

        Iterable<Subscription> subscriptions = subscriptionService.getAllSubscriptions(null);
        Iterable<SpecialSubscription> specialSubscriptions = specialSubscriptionService.getAllSpecialSubscriptions(null);

        for (Subscription subscription: subscriptions) {
            Double price = subscriptionService.calculatePrice(subscription);
            transaction(subscription,walletService,price);
        }

//        for (SpecialSubscription specialSubscription: specialSubscriptions) {
//            Double price = specialSubscriptionService.calculatePrice(specialSubscription);
//            transaction(specialSubscription,walletService,price);
//        }
    }

    private void transaction(Subscr subscription, WalletService walletService, Double price) {
        Wallet userWallet = subscription.getUserWallet();
        Wallet wallet = subscription.getWallet();
        if (subscription.getActive() && (userWallet.getValue() - price <= 0 || !checkDateOfSubscr(subscription))) {
            subscription.setActive(false);
            subscriptionService.saveSubscription((Subscription)subscription);
        }
        if(!subscription.getActive() && userWallet.getValue() - price >= 0 && checkDateOfSubscr(subscription)){
            subscription.setActive(true);
            subscriptionService.saveSubscription((Subscription)subscription);
        }
        if (subscription.getActive()) {
            walletService.decreaseWallet(userWallet.getId(), price);
            System.out.println("The wallet of user " + userWallet.getUser().getUsername() + " had decreased by " + price + "$ by product purchase");
            walletService.increaseWallet(wallet.getId(), price);
            System.out.println("The wallet of content manager " + wallet.getUser().getUsername() + " had increased by " + price + "$ by product sale");
        }
    }

    private static Boolean checkDateOfSubscr(Subscr subscription) {
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = subscription.getEnd().toLocalDate();
        LocalDate startDate = subscription.getStart().toLocalDate();
        return currentDate.isAfter(startDate) && currentDate.isBefore(endDate);
    }

}