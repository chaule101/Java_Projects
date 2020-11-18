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

import poly.entity.Staff;

@Transactional
@Controller
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("index")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staff";
		Query query = session.createQuery(hql);
		List<Staff> list = query.list();
		model.addAttribute("staffs", list);
		return "staff/index";
	}

	public List<Staff> getStaff() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staff";
		Query query = session.createQuery(hql);
		List<Staff> list = query.list();
		return list;
	}

	// Thêm nhân viên
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("staff", new Staff());
		return "staff/insert";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("staff") Staff staff) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(staff);
			t.commit();
			// model.addAttribute("message", "Thêm mới thành công !");
		} catch (Exception e) {
			t.rollback();
			// model.addAttribute("message", "Thêm mới thất bại !");
		} finally {
			session.close();
		}
		model.addAttribute("staffs", getStaff());
		return "staff/index";
	}

	// Load dữ liệu lên form sửa
	@RequestMapping("{id}edit")
	public String edit(ModelMap model, @PathVariable("id") String id) {
		Session session = factory.getCurrentSession();
		Staff staff = (Staff) session.get(Staff.class, id);
		// Map vs attribute
		model.addAttribute("staff", staff);
		model.addAttribute("staffs", getStaff());
		return "staff/edit";
	}

	// Sửa dữ liệu sau khi load
	@RequestMapping("edit")
	public String update(ModelMap model, @ModelAttribute("staff") Staff staff) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(staff);
			t.commit();
			// model.addAttribute("message", "Cập nhập thành công !");
		} catch (Exception e) {
			t.rollback();
			// model.addAttribute("message", "Cập nhập thất bại !");
		} finally {
			session.close();
		}
		model.addAttribute("staffs", getStaff());
		return "staff/index";
	}
}
