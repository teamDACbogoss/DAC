package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class User extends Model {
 
    @Email
    @Required
    public String email;
    
    @Required
    public String password;
    
    public String firstname;
    public String lastname;
    
    public Questions.QuestionType secretQuestion;
    public String secretAnswer;
    
    public boolean isAdmin;
    
    
    
    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
    
	public static boolean checkCredentials(String email, String password) {
		Object o = User
				.em()
				.createNativeQuery(
						"select count(*) from User where password='" + password + "' and email='" + email
								+ "'").getSingleResult();
		return !"0".equals(o.toString());
	}
    
    public String toString() {
        return email;
    }
 
}