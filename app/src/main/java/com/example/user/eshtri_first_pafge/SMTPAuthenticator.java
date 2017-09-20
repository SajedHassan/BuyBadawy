package com.example.user.eshtri_first_pafge;

/**
 * Created by aya_a_000 on 9/18/2017.
 */

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
    public SMTPAuthenticator() {

        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "Eshtery.Badawy@gmail.com";
        String password = "BedouinMafia#2017";
        if ((username != null) && (username.length() > 0) && (password != null)
                && (password.length() > 0)) {

            return new PasswordAuthentication(username, password);
        }

        return null;
    }
}