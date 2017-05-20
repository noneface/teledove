package Test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.teledove.dao.Dao;
import com.teledove.dao.HibernateUtil;
import com.teledove.model.User;

public class hibtest {

	/*
	 * @Test
	 
	public void test() {
		Session session = HibernateUtil.getSession();
		User user = new User();
		user.setId(2);
		user.setUsername("xiaoyu");
		user.setPassword("123456");
		session.getTransaction().begin();
		session.save(user);
		session.getTransaction().commit();
	}
	*/
	@Test
	public void load(){
		Session session = HibernateUtil.getSession();
		Dao dao = new Dao();
		List<User> userlist = dao.load();
		for(int i=0;i<userlist.size();i++)
		{
			System.out.println(userlist.get(i).getId());
		}
	}

}
