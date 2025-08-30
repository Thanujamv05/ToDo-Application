package firstApplications.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Task {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
private String title;
private String description;
private Date  dueDate;
private boolean status;

@ManyToOne
private Users users;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Date getDueDate() {
	return dueDate;
}

public void setDueDate(Date dueDate) {
	this.dueDate = dueDate;
}

public boolean isStatus() {
	return status;
}

public void setStatus(boolean status) {
	this.status = status;
}

public Users getUsers(Users user) {
	return users;
}

public void setUsers(Users users) {
	this.users = users;
}

@Override
public String toString() {
	return "Task [id=" + id + ", title=" + title + ", description=" + description + ", dueDate=" + dueDate + ", status="
			+ status + ", users=" + users + "]";
}



}
