/*
 * BeanB
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */

public class BeanB {

    private Long started;

    private void setUp() {
        System.out.println("Starting bean b");
        started = System.currentTimeMillis();
    }

    private BeanA beanA;

    void useBeanA() {
        beanA.doJob();
    }

    public Long getStarted() {
        return started;
    }
}
