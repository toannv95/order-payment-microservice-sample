package org.toannguyen.handlers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.toannguyen.models.entities.user;
import org.toannguyen.models.events.orderPurchaseEvent;
import org.toannguyen.models.events.paymentEvent;
import org.toannguyen.repositories.userRepository;

import static org.toannguyen.enums.paymentStatus.APPROVED;
import static org.toannguyen.enums.paymentStatus.DECLINED;

@Component
public class orderPurchaseEventHandler implements eventHandler<orderPurchaseEvent, paymentEvent>{
    private final userRepository userRepository;

    @Autowired
    public orderPurchaseEventHandler(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public paymentEvent handleEvent(orderPurchaseEvent event) {
        double orderPrice = event.getPrice();
        Integer userId = event.getUserId();
        paymentEvent paymentEvent = new paymentEvent();
        paymentEvent.setOrderId(event.getOrderId());
        paymentEvent.setPrice(event.getPrice());
        paymentEvent.setStatus(DECLINED);

        userRepository
                .findById(userId)
                .ifPresent(user -> deductUserBalance(orderPrice, paymentEvent, user));
        return paymentEvent;
    }

    private void deductUserBalance(double orderPrice, paymentEvent paymentEvent, user user) {
        double userBalance = user.getBalance();
        if (userBalance >= orderPrice) {
            user.setBalance(userBalance - orderPrice);
            userRepository.save(user);
            paymentEvent.setStatus(APPROVED);
        }
    }
}
