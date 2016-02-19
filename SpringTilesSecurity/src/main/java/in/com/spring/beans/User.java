package in.com.spring.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "User")
public class User implements java.io.Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userName;
	private String password;
	private Byte enabled;
	private Role role;
	
	

	public User() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "userName", length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "password", length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	@Column(name = "enabled")
	public Byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the roleId
	 */
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="roleId",nullable=false)
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the roleId to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}


	
}
