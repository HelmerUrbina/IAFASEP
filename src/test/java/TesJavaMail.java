
import com.iafas.Utiles.JavaMail;
import java.io.IOException;
import javax.mail.MessagingException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author helme
 */
public class TesJavaMail {

    public static void main(String[] args) throws IOException, MessagingException {
        JavaMail mail = new JavaMail("helmer_urbina@hotmail.com", "D:\\2020-06479.jpg");
        String aaa = mail.SendMail();
        System.out.println(aaa);

    }

}
