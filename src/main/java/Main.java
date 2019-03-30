import dao.FirmsDao;
import dao.impl.ConferenceParticipantDaoImpl;
import dao.ConferenceParicipantsDao;
import dao.impl.FirmsDaoImpl;
import model.ConferenceParticipants;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        HibernateConfig config = new HibernateJavaConfig();
        SessionFactory sessionFactory = config.getSessionFactory();
        FirmsDao firmsDao = new FirmsDaoImpl(sessionFactory);
        System.out.println(firmsDao.getByName("NYSE"));
    }
}
