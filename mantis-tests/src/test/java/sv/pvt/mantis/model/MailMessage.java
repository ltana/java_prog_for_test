package sv.pvt.mantis.model;

/**
 * Created by Ltana on 23.04.2016.
 */
public class MailMessage {

    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}

