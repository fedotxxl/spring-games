/*
 * BeanRun
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */

import com.google.common.base.Stopwatch;
import org.atteo.evo.classindex.ClassIndex;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.StaticApplicationContext;
import ru.grails.Config;
import ru.grails.Services;

public class BeanRun {

    public static void main(String[] args) {
        a();
        b();

        Stopwatch timer = new Stopwatch().start();

        StaticApplicationContext innerContext = new StaticApplicationContext();

        System.out.println("App context started: " + timer);

        innerContext.registerSingleton("beanPostProcessor", CommonAnnotationBeanPostProcessor.class);

        for (Class clazz : ClassIndex.getAnnotated(Config.class)) {
            registerLazyBean(innerContext, clazz.getName(), clazz);
        }

        for (Class clazz : ClassIndex.getSubclasses(Services.class)) {
            innerContext.registerSingleton(clazz.getName(), clazz);
        }

        System.out.println("Beans are registered: " + timer);

        innerContext.refresh();

        System.out.println("App context refreshed: " + timer);

        BeanB bean = innerContext.getBean(BeanB.class);
        System.out.println(bean.getStarted());

        System.out.println(timer.stop());
    }

    private static void registerLazyBean(StaticApplicationContext ctx, String name, Class clazz) {
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(clazz);
        bd.setLazyInit(true);
        ctx.getDefaultListableBeanFactory().registerBeanDefinition(name, bd);
    }


    private static void a() {
        long start = System.currentTimeMillis();
        Iterable<Class<? extends Services>> classes = ClassIndex.getSubclasses(Services.class);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
        for (Class klass : classes) {
            System.out.println(klass.getName());
        }
    }

    private static void b() {
        long start = System.currentTimeMillis();
        Iterable<Class<?>> classes = ClassIndex.getAnnotated(Config.class);
        long end = System.currentTimeMillis();

        System.out.println(end - start);
        for (Class klass : classes) {
            System.out.println(klass.getName());
        }
    }


//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
//
//        BeanA newBean = (BeanA) beanFactory.createBean(BeanA.class,AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
//        beanFactory.initializeBean(newBean, "bean1");
//
//        BeanB b2 = (BeanB) beanFactory.createBean(BeanB.class,AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
//        beanFactory.initializeBean(b2, "bean2");
//    }

}
