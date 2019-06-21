package com.example.myapplication.business.services;

import android.os.StrictMode;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

public class GMailSender extends javax.mail.Authenticator
{
    private Session session;
    private Multipart _multipart;

    static
    {
        Security.addProvider(new JSSEProvider());
    }

    public GMailSender()
    {

        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Properties props = new Properties();
        props.setProperty("mail.user", ConfigMail.MAIL);
        props.setProperty("mail.password", ConfigMail.PASS);
        props.put("mail.smtp.host", ConfigMail.HOST);
        props.put("mail.smtp.auth", ConfigMail.SMTP_AUTH);
        props.put("mail.smtp.port", ConfigMail.PORT);
        props.put("mail.smtp.socketFactory.port", ConfigMail.PORT);
        props.put("mail.smtp.socketFactory.class",ConfigMail.SOCKET_FACTORY);

        session = Session.getDefaultInstance(props, this);
        _multipart = new MimeMultipart();

    }

    protected PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(ConfigMail.MAIL, ConfigMail.PASS);
    }

    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception
    {
       MimeMessage message = new MimeMessage(session);
        message.setSender(new InternetAddress(sender));
        message.setSubject(subject);
        message.setText(body);

        if (recipients.indexOf(',') > 0)
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        else
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));

        Transport.send(message);

    }

    public void addAttachment(String filename) throws Exception
    {
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);

        _multipart.addBodyPart(messageBodyPart);
    }

    public class ByteArrayDataSource implements DataSource
    {
        private final byte[] data;
        private String type;

        public ByteArrayDataSource(byte[] data, String type)
        {
            super();
            this.data = data;
            this.type = type;
        }

        public ByteArrayDataSource(byte[] data)
        {
            super();
            this.data = data;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        public String getContentType()
        {
            if (type == null)
                return "application/octet-stream";
            else
                return type;
        }

        public InputStream getInputStream() {
            return new ByteArrayInputStream(data);
        }

        public String getName()
        {
            return "ByteArrayDataSource";
        }

        public OutputStream getOutputStream() throws IOException
        {
            throw new IOException("Not Supported");
        }
    }





}
