package ru.sudakovoleg.dashboardView;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by Oleg on 29.03.2016.
 */

@ManagedBean
@ViewScoped
public class LoginHandler {
    private String loginText = "Login";

    public LoginHandler(){

    }

    public String getLoginText() {
        return loginText;
    }

    public void setLoginText(String loginText) {
        this.loginText = loginText;
    }
}
