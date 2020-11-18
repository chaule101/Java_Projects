package poly.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Departs")
public class Depart {
	@Id
	@Column(name = "Id")
	private String id;
	@Column(name = "Name")
	private String name;
	@OneToMany(mappedBy = "depart", fetch = FetchType.EAGER)
	private Collection<Staff> staff;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Staff> getStaff() {
		return staff;
	}

	public void setStaff(Collection<Staff> staff) {
		this.staff = staff;
	}

	public Depart(String id, String name, Collection<Staff> staff) {
		super();
		this.id = id;
		this.name = name;
		this.staff = staff;
	}

	public Depart() {
		super();
		// TODO Auto-generated constructor stub
	}

}
