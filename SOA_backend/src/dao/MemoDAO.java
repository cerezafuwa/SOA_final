package dao;

import entity.Memo;
import entity.MemoPK;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hubeini on 2016/12/30.
 */
public class MemoDAO extends BaseDAO<Memo> {

    private static MemoDAO instance = new MemoDAO();

    private MemoDAO(){

    }

    public static MemoDAO getInstance() {
        return instance;
    }

    public void delete(MemoPK pk) {
        super.delete(Memo.class, pk);
    }

    public Memo findById(MemoPK pk) {
        return super.findById(Memo.class, pk);
    }

    public List<Memo> findByUser(String username)
    {
        Session session = BaseDAO.getSession();
        Query query = session.createQuery("from Memo where user = :username");
        query.setParameter("username", username);
        List result = query.getResultList();
        session.close();
        return result;
    }

}
