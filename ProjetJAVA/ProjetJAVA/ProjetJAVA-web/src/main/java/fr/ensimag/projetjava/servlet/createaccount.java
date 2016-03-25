/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensimag.projetjava.servlet;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import fr.ensimag.projetjava.entity.Client;
import fr.ensimag.projetjava.entity.SecretQuestion;
import javax.ejb.EJB;

/**
 *
 * @author johanna gogo-dago
 */
@Named(value = "createaccount")
@RequestScoped
public class createaccount implements Serializable {

    //public create
    
    /**
     * Creates a new instance of user
     */
    
    @EJB
    private fr.ensimag.projetjava.stateless.ClientFacadeLocal clientFacade;
    
    //Message général
    private String msg_general;
    
    //Message pour le prénom
    private String msg_fn; 
    
    //Message pour le nom
    private String msg_n;
   
    //Message pour l'email
    private String msg_email;
    
    //Message pour le mdp
    private String msg_mdp;
    
    //Message pour la confirmation de mdp
    private String msg_cmdp;
    
    //Message pour la réponse à la question secrète
    private String msg_sqa;
   

    public String getMsg_general() {
        return msg_general;
    }

    public void setMsg_general(String msg_general) {
        this.msg_general = msg_general;
    }
    
    public String getMsg_fn() {
        return msg_fn;
    }

    public void setMsg_fn(String msg_fn) {
        this.msg_fn = msg_fn;
    }

    public String getMsg_n() {
        return msg_n;
    }

    public void setMsg_n(String msg_n) {
        this.msg_n = msg_n;
    }

    public String getMsg_email() {
        return msg_email;
    }

    public void setMsg_email(String msg_email) {
        this.msg_email = msg_email;
    }

    public String getMsg_mdp() {
        return msg_mdp;
    }

    public void setMsg_mdp(String msg_mdp) {
        this.msg_mdp = msg_mdp;
    }

    public String getMsg_cmdp() {
        return msg_cmdp;
    }

    public void setMsg_cmdp(String msg_cmdp) {
        this.msg_cmdp = msg_cmdp;
    }

    public String getMsg_sqa() {
        return msg_sqa;
    }

    public void setMsg_sqa(String msg_sqa) {
        this.msg_sqa = msg_sqa;
    }
   
    public createaccount(){
        this.msg_cmdp = "";
        this.msg_email = "";
        this.msg_fn = "";
        this.msg_general = "";
        this.msg_mdp = "";
        this.msg_n = "";
        this.msg_sqa = "";
    }
    
    public String test() {
        return "";
    }
    
    public String createMember(String name, String firstName, String email, String mdp, String cmdp, String secretQ, String secretQA) {
        
        //Les champs doivent être remplis
        //Les champs e-mail, nom, prénom, mot de passe et réponse 
        //secrète doivent contenir moins de 256 caractères
        if (name.equals(""))  {
            this.msg_n = "Saisissez un nom!";
            return "create-account";
        }
        if (name.length() > 256)  {
            this.msg_n = "Moins de 256 caractères!";
            return "create-account";
        }
        
        if (firstName.equals("")) {
            this.msg_fn = "Saisissez un prénom!";
            return "create-account";
        }
        if (firstName.length() > 256) {
            this.msg_fn = "Moins de 256 caractères!";
            return "create-account";
        }
        
        if (email.equals("")) {
            this.msg_email = "Saisissez un e-mail!";
            return "create-account";
        }
        if (email.length() > 256) {
            this.msg_email = "Moins de 256 caractères!";
            return "create-account";
        }
        //L'adresse e-mail doit avoir une forme acceptable: xx@x.com
        if (email.contains("@")) {
            int indexOfAt = email.indexOf("@");
            boolean dotIsPresent = false;
            int dotPosition = -1;
            Character dot = '.';
            for (int i=indexOfAt; i < email.length(); i++) {
                if (dot.equals(email.charAt(i))) {
                    dotIsPresent = true;
                    dotPosition = i;
                }
            }
            if ((!dotIsPresent) || (dotPosition == (email.length()-1)) || 
                (indexOfAt==0) || (dot.equals(email.charAt(0))) ||
                (indexOfAt==(dotPosition+1)))
            {
                this.msg_email="Email de la forme xx@xx.xx";
                return "create-account";
            }
        } else {
            this.msg_email = "L'email doit contenir '@'!";
            return "create-account";
        }
       
        if (mdp.equals("")) {
            this.msg_mdp = "Saisissez un mot de passe!";
            return "create-account";
        }
        if (mdp.length() > 256) {
            this.msg_mdp = "Moins de 256 caractères!";
            return "create-account";
        }
        //Le mot de passe doit contenir plus de 6 caractères!
        if (mdp.length() < 6) {
            this.msg_mdp = "Plus de 6 caractères requis!";
            return "create-account";
        }
        
        if (cmdp.equals("")) {
            this.msg_cmdp = "Confirmez votre mot de passe!";
            return "create-account";
        }
        if (cmdp.length() > 256) {
            this.msg_cmdp = "Moins de 256 caractères!";
            return "create-account";
        }
         if (!mdp.equals(cmdp)) {
            this.msg_cmdp = "Mot de passe et confirmation différents!";
            return "create-account";
        } 
        
        /*if (secretQA.equals("")) {
            this.msg_sqa = "Entrez une réponse!";
            return "create-account" ;
        }
        if (secretQA.length() > 256) {
            this.msg_sqa = "Moins de 256 caractères!";
            return "create-account";
        }*/
        SecretQuestion secretQuestion;
        if (secretQ.equals("ecole")) {
            secretQuestion= SecretQuestion.q1;
        }
        if (secretQ.equals("animal")) {
            secretQuestion = SecretQuestion.q2;
        }
        if (secretQ.equals("hopital")) {
            secretQuestion = SecretQuestion.q3;
        }
        if (secretQ.equals("enfant")) {
            secretQuestion = SecretQuestion.q4;
        } else {
            secretQuestion = SecretQuestion.q5;
        }
        this.msg_general = secretQuestion.toString();
        return "creation-account";
        //Creation du client et retour sur page d'accueil*/
        /*Client membre = new Client(email, mdp, false, name, firstName, secretQuestion, secretQA, 10000);
        clientFacade.create(membre);
        return "login";*/

    }
}
