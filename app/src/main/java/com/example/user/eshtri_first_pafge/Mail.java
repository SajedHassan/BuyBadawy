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
        super();
        _host = "smtp.gmail.com"; // default smtp server
        _port = "587"; // default smtp port
        this._sport = "587"; // default socketfactory port

        _user = "";
        _pass = "";
        _from = "";
        _subject = "";
        _body = "";
        _debuggable = false;
        _auth = true; // smtp authentication - default on

        _multipart = new MimeMultipart();

        final MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
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
    public Mail(final String user, final String pass) {
        this();

        _user = user;
        _pass = pass;
    }

    /**
     * Sends the email.
     *
     * @return success feedback.
     * @throws Exception if fails to send the email.
     */
    public boolean send() throws Exception {
        final Properties props = _setProperties();

        if (!_user.equals("") && !_pass.equals("") && (this._to.length > 0) && !_from.equals("")
                && !_subject.equals("") && !_body.equals("")) {

            final Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("Eshtery.Badawy@gmail.com", "BedouinMafia#2017");
                }
            });
            final SMTPAuthenticator authentication = new SMTPAuthenticator();
            final Message msg = new MimeMessage(Session.getDefaultInstance(props, authentication));
            msg.setFrom(new InternetAddress(_from));

            final InternetAddress[] addressTo = new InternetAddress[_to.length];
            for (int i = 0; i < _to.length; i++) {
                addressTo[i] = new InternetAddress(_to[i]);
            }
            msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);

            msg.setSubject(_subject);
            msg.setSentDate(new Date());

            final BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(_body);
            _multipart.addBodyPart(messageBodyPart);

            msg.setContent(_multipart);

            final String protocol = "smtp";
            props.put("mail." + protocol + ".auth", "true");
            final Transport t = session.getTransport(protocol);
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
        return new PasswordAuthentication(_user, _pass);
    }

    /**
     * Sets the properties of the email.
     *
     * @return the properties.
     */
    private Properties _setProperties() {
        final Properties props = new Properties();

        props.put("mail.smtp.host", _host);

        if (_debuggable) {
            props.put("mail.debug", "true");
        }

        if (_auth) {
            props.put("mail.smtp.auth", "true");
        }

        props.put("mail.smtp.port", _port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.enable", true);

        return props;
    }

    // the getters and setters
    public String getBody() {
        return _body;
    }

    public void setBody(final String _body) {
        this._body = _body;
    }

    public void setTo(final String[] to) {
        this._to = to;
    }

    public void setFrom(final String from) {
        this._from = from;
    }

    public void setSubject(final String subject) {
        this._subject = subject;
    }
}