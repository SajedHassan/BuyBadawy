package com.example.user.eshtri_first_pafge;

/**
 * Created by aya_a_000 on 9/18/2017.
 */

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

/**
 * Handles the email verification.
 */
public class Mail extends Authenticator {
    private String _user;
    private String _pass;

    private String[] _to;
    private String _from;

    private final String _port;
    private final String _sport;

    private final String _host;

    private String _subject;
    private String _body;

    private final boolean _auth;

    private final boolean _debuggable;

    private final Multipart _multipart;

    public Mail() {
        this._host = "smtp.gmail.com"; // default smtp server
        this._port = "587"; // default smtp port
        this._sport = "587"; // default socketfactory port

        this._user = "";
        this._pass = "";
        this._from = "";
        this._subject = "";
        this._body = "";
        this._debuggable = false;
        this._auth = true; // smtp authentication - default on

        this._multipart = new MimeMultipart();

        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);
    }

    /**
     * The one and only constructor.
     *
     * @param user user string.
     * @param pass password string.
     */
    public Mail(String user, String pass) {
        this();

        this._user = user;
        this._pass = pass;
    }

    /**
     * Sends the email.
     *
     * @return success feedback.
     * @throws Exception if fails to send the email.
     */
    public boolean send() throws Exception {
        Properties props = this._setProperties();

        if (!this._user.equals("") && !this._pass.equals("") && this._to.length > 0 && !this._from.equals("")
                && !this._subject.equals("") && !this._body.equals("")) {

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("Eshtery.Badawy@gmail.com", "BedouinMafia#2017");
                }
            });
            SMTPAuthenticator authentication = new SMTPAuthenticator();
            Message msg = new MimeMessage(Session.getDefaultInstance(props, authentication));
            msg.setFrom(new InternetAddress(this._from));

            InternetAddress[] addressTo = new InternetAddress[this._to.length];
            for (int i = 0; i < this._to.length; i++) {
                addressTo[i] = new InternetAddress(this._to[i]);
            }
            msg.setRecipients(RecipientType.TO, addressTo);

            msg.setSubject(this._subject);
            msg.setSentDate(new Date());

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(this._body);
            this._multipart.addBodyPart(messageBodyPart);

            msg.setContent(this._multipart);

            String protocol = "smtp";
            props.put("mail." + protocol + ".auth", "true");
            Transport t = session.getTransport(protocol);
            try {
                t.connect("smtp.gmail.com", "Eshtery.Badawy@gmail.com", "BedouinMafia#2017");
                t.sendMessage(msg, msg.getAllRecipients());
            } finally {
                t.close();
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this._user, this._pass);
    }

    /**
     * Sets the properties of the email.
     *
     * @return the properties.
     */
    private Properties _setProperties() {
        Properties props = new Properties();

        props.put("mail.smtp.host", this._host);

        if (this._debuggable) {
            props.put("mail.debug", "true");
        }

        if (this._auth) {
            props.put("mail.smtp.auth", "true");
        }

        props.put("mail.smtp.port", this._port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.enable", true);

        return props;
    }

    // the getters and setters
    public String getBody() {
        return this._body;
    }

    public void setBody(String _body) {
        this._body = _body;
    }

    public void setTo(String[] to) {
        _to = to;
    }

    public void setFrom(String from) {
        _from = from;
    }

    public void setSubject(String subject) {
        _subject = subject;
    }
}