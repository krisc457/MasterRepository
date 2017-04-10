package hello;

import javax.validation.constraints.Size;

/**
 * Created by Bodsten on 2017-03-30.
 */
public class UserSignUp {

    @Size(min = 1)
    private String username;
    @Size(min = 1)
    private String password;


    public UserSignUp() {

    }
    public UserSignUp(String username, String password){

        this.username = username;
        this.password = password;
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
