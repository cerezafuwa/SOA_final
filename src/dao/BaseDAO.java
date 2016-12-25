package dao;

/**
 * Created by hubeini on 2016/12/25.
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.Serializable;
import java.util.List;

public class BaseDAO<E> {

    private static SessionFactory factory;

    protected BaseDAO(){
        init();
    }

    private void init(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            factory = new MetadataSources(registry).
                    buildMetadata().
                    buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
            e.printStackTrace();
        }
    }

    protected static Session getSession(){
        return factory.openSession();
    }

    /**
     * 添加数据
     * @param e 新建的行
     */
    public void save(E e){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(e);
        tx.commit();
        session.close();
    }

    /**
     * 删除数据
     * @param e 目标表格
     * @param pk 主键
     */
    public void delete(Class<E> e, Serializable pk){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        E entity = this.findById(e, pk);
        if(entity!=null){
            session.delete(entity);
            tx.commit();
        }
        else{
            System.out.println("Data with primary key"+ pk.toString() +" does not exist, cannot delete.");
        }
        session.close();
    }

    /**
     * 查找数据
     * @param e 目标表格
     * @param pk 主键
     */
    public E findById(Class<E> e, Serializable pk){
        Session session = getSession();
        E result = session.get(e,pk);
        session.close();
        return result;
    }

    /**
     * 更新修改数据
     * @param e 更新后数据
     */
    public void update(E e){
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        session.update(e);
        tx.commit();
        session.close();
    }

    /**
     * 使用SQL语句查询
     * @param hql SQL语句
     */
    public static List query(String hql){
        Session session = getSession();
        List result = session.createQuery(hql).list();
        session.close();
        return result;
    }
}
