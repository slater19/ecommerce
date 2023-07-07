package coms.EcommerceApp.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "purchases")   
public class Purchase { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "gross_total")
	private BigDecimal total;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date2) {
		this.date = (Date) date2;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Purchase [ID=" + ID + ", userId=" + userId + ", date=" + date + ", total=" + total + "]";
	}
	
}

	
	