package com.lcp.learn.spring.hibernate;

import com.alibaba.fastjson.JSON;
import com.lcp.learn.spring.hibernate.beans.Goods;
import com.lcp.learn.spring.hibernate.beans.GoodsType;
import com.lcp.learn.spring.hibernate.config.HibernateConfig;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * desc:    <br/>
 *
 * @author lichunpeng
 * @since 2020/3/17-13:33
 */
public class SpringHibernateMain {

  public static void main(String[] args) {

    save();

    //        query();

    //        update();

  }

  private static void update() {
    SessionFactory sessionFactory = getSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();

    Goods goods = new Goods();
    goods.setId(1);
    goods.setGoodsName(RandomStringUtils.random(10, true, false));
    session.update(goods);

    transaction.commit();
    session.close();
    sessionFactory.close();
  }

  private static void query() {
    SessionFactory sessionFactory = getSessionFactory();
    Session session = sessionFactory.openSession();

    //        NativeQuery<UOrder> nativeQuery =
    //                session.createSQLQuery(
    //                        "select uorder.id as user_id, goods_name " +
    //                        "from user_order uorder " +
    //                        "left join goods good on uorder.goods_id = good.id");
    //        nativeQuery.addEntity(UOrder.class);
    //        nativeQuery.getResultList().forEach(uorder ->
    //                System.out.println("goods = " + JSON.toJSONString(uorder)));

    NativeQuery<Goods> nativeQuery =
        session.createSQLQuery("select id, goods_name, goods_type, create_time from goods");
    nativeQuery.addEntity(Goods.class);
    nativeQuery.getResultList().forEach(goods ->
        System.out.println("goods = " + JSON.toJSONString(goods)));

    // Query<Goods> result = session.createQuery("from Goods");
    // result.getResultList().forEach(goods ->
    //         System.out.println("goods = " + JSON.toJSONString(goods)));

  }

  private static void save() {
    SessionFactory sessionFactory = getSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    for (int i = 0; i < 7; i++) {
      Goods goods = new Goods();
      goods.setGoodsName(RandomStringUtils.random(10, true, false));
      goods.setGoodsType(GoodsType.BOOK);
      goods.setCreateTime(new Date());
      session.save(goods);
    }
    transaction.commit();
    session.close();
    sessionFactory.close();
  }

  private static SessionFactory getSessionFactory() {
    ApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(HibernateConfig.class);
    return applicationContext.getBean(SessionFactory.class);
  }
}
