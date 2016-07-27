/**
 * 
 */
package model;

/**
 * @author ajc
 *
 */
public class Login {

	/**
	 * 
	 */
	private int idLog;
	private String Login;
	private String motDePasse;
	private int admin;
	
	
	
	public Login() {
		
	}
	
	public Login(int idLog)
	{
		this();
		this.idLog = idLog;
	}

	public int getIdLog() {
		return idLog;
	}

	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Login [idLog=" + idLog + ", Login=" + Login + ", motDePasse=" + motDePasse + ", admin=" + admin + "]";
	}
	
	

}
