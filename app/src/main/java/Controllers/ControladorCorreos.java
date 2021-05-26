package Controllers;



import android.os.AsyncTask;
import android.os.StrictMode;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import Constantes.Constantes;

/**
 * Clase que manejara el envio automativo de correos
 * @author alvar
 *
 */
public class ControladorCorreos  extends AsyncTask<Void,Void,Void> {

    String destinatario;
    String asunto;
    String cuerpo;

    /**
     * Constructor
     * @param destinatario
     * @param asunto
     * @param cuerpo
     */
	public ControladorCorreos(String destinatario, String asunto, String cuerpo) {
	    super();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
	    this.asunto=asunto;
	    this.cuerpo=cuerpo;
	    this.destinatario=destinatario;
	}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Show progress dialog while sending email

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        enviarConGMail();

    }


    @Override
    protected Void doInBackground(Void... params) {
        
        return null;
    }

    /**
     * Envia correo por gamil
     */
    public void enviarConGMail() {
    	String remitente = Constantes.CORREO;  //Para la direcci�n nomcuenta@gmail.com
        String clave=Constantes.CLAVE;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(remitente, clave);
                    }
                });


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(destinatario));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            Transport.send(message);


            System.out.println("Correo "+asunto +" enviado a "+destinatario);
        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }

    }
}
