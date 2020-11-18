package poly.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.entity.User;

@Transactional
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	SessionFactory factory;

	@RequestMapping("index")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		model.addAttribute("users", list);
		return "user/index";
	}

	public List<User> getUser() {
		Session session = factory.getCurrentSession();
		String hql = "FROM User";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		return list;
	}

	// Thêm người dùng
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("user", new User());
		return "user/insert";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("user") User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(user);
			t.commit();
			// model.addAttribute("message", "Thêm mới thành công !");
		} catch (Exception e) {
			t.rollback();
			// model.addAttribute("message", "Thêm mới thất bại !");
		} finally {
			session.close();
		}
		model.addAttribute("users", getUser());
		return "user/index";
	}

	// Load dữ liệu lên form sửa
	@RequestMapping("{id}edit")
	public String edit(ModelMap model, @PathVariable("id") String id) {
		Session session = factory.getCurrentSession();
		User user = (User) session.get(User.class, id);
		// Map vs attribute
		model.addAttribute("user", user);
		model.addAttribute("users", getUser());
		return "user/edit";
	}

	// Sửa dữ liệu sau khi load
	@RequestMapping("edit")
	public String update(ModelMap model, @ModelAttribute("user") User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(user);
			t.commit();
			// model.addAttribute("message", "Cập nhập thành công !");
		} catch (Exception e) {
			t.rollback();
			// model.addAttribute("message", "Cập nhập thất bại !");
		} finally {
			session.close();
		}
		model.addAttribute("users", getUser());
		return "user/index";
	}

	// Xóa người dùng
	@RequestMapping("{id}delete")
	public String delete(ModelMap model, @PathVariable("id") String id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		User user = (User) session.get(User.class, id);
		// Map vs attribute
		model.addAttribute("user", user);
		model.addAttribute("users", getUser());
		try {
			session.delete(user);
			t.commit();
			// model.addAttribute("message", "Xóa thành công !");
		} catch (Exception e) {
			t.rollback();
			// model.addAttribute("message", "Xóa thất bại !");
		} finally {
			session.close();
		}
		model.addAttribute("users", getUser());
		return "user/index";
	}
}
