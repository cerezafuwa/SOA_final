package dao;

import entity.Schedule;
import entity.SchedulePK;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hubeini on 2016/12/31.
 */
public class ScheduleDAO extends BaseDAO<Schedule>{
    private static ScheduleDAO instance = new ScheduleDAO();
    private ScheduleDAO(){

    }
    public static ScheduleDAO getInstance() {
        return instance;
    }

    public void delete(SchedulePK pk) {
        super.delete(Schedule.class, pk);
    }

    public Schedule findById(SchedulePK pk) {
        return super.findById(Schedule.class, pk);
    }

    public List<Schedule> findByUser(String username)
    {
        Session session = BaseDAO.getSession();
        Query query = session.createQuery("from Schedule where user = :username");
        query.setParameter("username", username);
        List<Schedule> result = query.getResultList();
        session.close();
        return result;
    }

}
