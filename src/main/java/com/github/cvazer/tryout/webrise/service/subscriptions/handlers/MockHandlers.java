package com.github.cvazer.tryout.webrise.service.subscriptions.handlers;

import com.github.cvazer.tryout.webrise.dao.repo.SubscriptionRepo;
import com.github.cvazer.tryout.webrise.service.SubscriptionService;
import org.springframework.stereotype.Component;

//Тут живут заглушки для хэндлеров
public class MockHandlers {

    @Component
    public static class YtPremiumHandler extends AbstractSubscriptionServiceHandler {

        public YtPremiumHandler(SubscriptionService subscriptionService,
                                SubscriptionRepo subscriptionRepo) {
            super(subscriptionService, subscriptionRepo);
        }

        @Override
        public String getServiceId() {
            return "YT_PREMIUM";
        }
    }

    @Component
    public static class VkMusicHandler extends AbstractSubscriptionServiceHandler {

        public VkMusicHandler(SubscriptionService subscriptionService,
                                SubscriptionRepo subscriptionRepo) {
            super(subscriptionService, subscriptionRepo);
        }

        @Override
        public String getServiceId() {
            return "VK_MUSIC";
        }
    }

    @Component
    public static class YaPlusHandler extends AbstractSubscriptionServiceHandler {

        public YaPlusHandler(SubscriptionService subscriptionService,
                              SubscriptionRepo subscriptionRepo) {
            super(subscriptionService, subscriptionRepo);
        }

        @Override
        public String getServiceId() {
            return "YA_PLUS";
        }
    }

    @Component
    public static class NetflixHandler extends AbstractSubscriptionServiceHandler {

        public NetflixHandler(SubscriptionService subscriptionService,
                             SubscriptionRepo subscriptionRepo) {
            super(subscriptionService, subscriptionRepo);
        }

        @Override
        public String getServiceId() {
            return "NETFLIX";
        }
    }

    @Component
    public static class DisneyPlusHandler extends AbstractSubscriptionServiceHandler {

        public DisneyPlusHandler(SubscriptionService subscriptionService,
                              SubscriptionRepo subscriptionRepo) {
            super(subscriptionService, subscriptionRepo);
        }

        @Override
        public String getServiceId() {
            return "DISNEY_PLUS";
        }
    }

    @Component
    public static class HboMaxHandler extends AbstractSubscriptionServiceHandler {

        public HboMaxHandler(SubscriptionService subscriptionService,
                                 SubscriptionRepo subscriptionRepo) {
            super(subscriptionService, subscriptionRepo);
        }

        @Override
        public String getServiceId() {
            return "HBO_MAX";
        }
    }
}
