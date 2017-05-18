package Test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import com.teledove.dao.HibernateUtil;
import com.teledove.model.User;

public class hibtest {

	@Test
	public void test() {
		Session session = HibernateUtil.getSession();
		User user = new User();
		user.setId(1);
		user.setUsername("noneface");
		user.setPassword("123456");
		session.getTransaction().begin();
		session.save(user);
		session.getTransaction().commit();
	}

}
