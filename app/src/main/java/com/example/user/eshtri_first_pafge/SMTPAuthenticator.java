package com.example.user.eshtri_first_pafge;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Authenticator class for password authentication.
 */
public class SMTPAuthenticator extends Authenticator {

    /**
     * The constructor.
     */
    public SMTPAuthenticator() {
        super();
    }

    @Override
    public final PasswordAuthentication getPasswordAuthentication() {
        final String username = "Eshtery.Badawy@gmail.com";
        final String password = "BedouinMafia#2017";
        if ((username != null) && (username.length() > 0)
                && (password != null) && (password.length() > 0)) {
            return new PasswordAuthentication(username, password);
        }
        return null;
    }
}