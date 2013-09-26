/*
 * BeanB
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */

import org.springframework.beans.factory.annotation.Autowired;
import ru.grails.Config;

import javax.annotation.PostConstruct;

@Config
public class BeanB {

    private Long started;

    @PostConstruct
    private void setUp() {
        System.out.println("Starting bean b");
        started = System.currentTimeMillis();
    }

    @Autowired
    private BeanA beanA;

    void useBeanA() {
        beanA.doJob();
    }

    public Long getStarted() {
        return started;
    }
}
