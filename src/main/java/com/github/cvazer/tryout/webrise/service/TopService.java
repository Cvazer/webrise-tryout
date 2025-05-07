package com.github.cvazer.tryout.webrise.service;

import java.util.Set;

public interface TopService {

    /**
     * @return TOP 3 Most popular services based on number of active subscriptions
     */
    Set<String> threePopular();

}
