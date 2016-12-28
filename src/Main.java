import entity.User;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;

/**
 * Created by hubeini on 2016/12/28.
 */
public class Main {
    private SessionFactory factory;
    protected void init() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            this.factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            System.out.println("hello");
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
            e.printStackTrace();
        }
    }
    public Session getSession() throws HibernateException {
        return factory.openSession();
    }
    void doSomething(){
        Session session = getSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            // do somthing...

            List users = session.createQuery("from User").list();
            for(Iterator it = users.iterator(); it.hasNext();){
                User user = (User) it.next();
                System.out.println(user.getUsername());
            }

//            tx.commit();
        }catch (HibernateError e){
            e.printStackTrace();
        }finally {
            if(tx!=null)tx.rollback();
            session.close();
        }
    }
    public static void main(String[] args) throws Exception{
        Main test = new Main();
        test.init();
        test.doSomething();
    }

}