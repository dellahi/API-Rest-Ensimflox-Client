package fr.ensim.interop.eval.exo3;

import java.util.Scanner;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.lang3.StringUtils;

import fr.ensim.interop.eval.exo3.ClientInteropMessenger;
import modelexo2.Plantation;
import modelexo3.Channel;
import modelexo3.Message;
import modelexo3.RestTemplateBasicAuth;

public class ClientInteropMessenger {
    // TODO: Exercice 3 - Implémentation d'un client du service Interop Messenger (si langage de programmation Java).
	 // Utilise REST

    private String url;
    private String mail;
    private String token;

    private RestTemplateBasicAuth restTemplateBasicAuth;
    private RestTemplate restTemplate;

    /**
     * REST Client
     * @param mail
     * @param token
     * @param url
     */
    public ClientInteropMessenger(String mail, String token, String url) {
        this.mail = mail;
        this.token = token;
        this.url = url;
        restTemplateBasicAuth = new RestTemplateBasicAuth(mail, token);
        restTemplate = restTemplateBasicAuth.getRestTemplate();
    }
   
    /**
     * Envoie de msg dans un channel choisi
     * @param channelName
     * @param msg
     */
  public void sendMessage(String channelName, String msg) {
        restTemplate.postForObject(url + "channels/{channelName}/messages", msg, Message.class, channelName);
    }
 
    /**
     * Récupère les messages d'un channel choisi
     * @param channelName
     * @return
     */
    @GetMapping("/channels/{channelName}/messages")
   public void fetchMessages(@PathVariable("channelName") String channelName) {
        	
        ResponseEntity<Message[]> response = restTemplate.getForEntity(url + "channels/{channelName}/messages", Message[].class, channelName);
        
        Message[] msg= response.getBody();
        
        for(Message ms :msg) {
			System.out.println(ms.content);
		}
    }

    /**
     * Créer un channel
     * @param channelName
     * @return
     * @throws RestClientException
     */
    public Channel createChannel(String channelName) throws RestClientException{
        return restTemplate.postForObject(url + "channels/{channelName}", channelName, Channel.class, channelName);
    }
   /**
     * Liste les channels
     * @return
     */
    public void listChannels() {
    	
    	ResponseEntity<Channel[]> response = this.restTemplate.getForEntity(url+"/channels",Channel[].class);
		
        Channel[] list_Channel = response.getBody();
        			
        		for(Channel ch : list_Channel) {
        			System.out.println(ch.getName());
        		}
    }
    /**
     * Ajouter un membre au channel à l'aide de l'adresse mail
     * @param channelName
     * @param memberMail
     * @throws RestClientException
     */
  public void addToChannel(String channelName, String memberMail) throws RestClientException {
        restTemplate.postForObject(url + "channels/{channelName}/members/{email}", channelName, Channel.class, channelName, memberMail);
    }

    public static void main(String[] args) {
    	
        String url = "https://ensim.flox.dev/";

        // Utils inside the loop
        String str = "";
        String option = "";
        String channel;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez votre mail :");
        String  mail = scanner.next();
        System.out.print("Entrez votre token :");
        String  token = scanner.next();

        // le client se connecte
        ClientInteropMessenger client = new ClientInteropMessenger(mail, token, url);

        // Entering ":q" breaks the loop and ends the app
       while ( !StringUtils.equals(option, "exit") ) {
            // Initialize the mode
            if( StringUtils.isBlank(option) ) {
                System.out.println("Choisir une option :" +"\n"+ 
                			" 1: Créer un channel" +"\n"+
                			" 2: Ajouter un menbre au channel"+"\n"+
                			" 3: lister les channel"+"\n"+
                			" 4: échanger de msg dans un channel"+"\n"+
                			" exit: tapez q pour quitter" +"\n");
                option = scanner.next();

                if( StringUtils.equals(option, "exit") ) break;
            }

         // Creation d'un Channel 
            if ( StringUtils.equals(option, "1") ) {
            	System.out.println("Entrer le nom du channel : ");
            	channel= scanner.next();
                try {
                   Channel ch = client.createChannel(channel);
                   System.out.println("le channel : "+ch.getName()+" a été crée");
                   option = "";
                
                } catch (RestClientException e) {
                    System.out.println("Erreur création channel : " + e.getMessage());
                    option = "";
                    channel = "";
                }
            }
            if ( StringUtils.equals(option, "2") ) {
            	System.out.println("Entrer le nom du channel : ");
            	channel= scanner.next();
            	System.out.println("Entrer le mail du membre : ");
            	mail= scanner.next();
                try {
                   client.addToChannel(channel, mail);;
                   System.out.println("ajout avec succès");
                   option = "";
                
                } catch (RestClientException e) {
                    System.out.println("Erreur d'ajout du membre au channel : " + e.getMessage());
                    option = "";
                    channel = "";
                }
            }
         // Lister les channels
            if( StringUtils.equals(option, "3") ) {
            	client.listChannels();
            	option = "";
            }
            // Channel join mode and allows sending messages
            if( StringUtils.equals(option, "4") ) {
            	System.out.println("Entrer le nom du channel : ");
            	channel= scanner.next();
                System.out.println("Connexion au channel : " + channel);
                System.out.println("Attente de messages pendant 5 secondes...");
                try {
                	
                    Thread.sleep(2000);

                    // Fetches the messages
                    client.fetchMessages(channel);

                    System.out.print("Entrez votre message (exit pour quitter) : ");
                    str = scanner.next();

                    // Sends the message
                    if( !StringUtils.equals(str, "exit") )
                        client.sendMessage(channel, str);
                    else break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (HttpClientErrorException e) {
                    System.out.println("Erreur de connexion : " + e.getMessage());
                    channel = "";
                    option = "";
                }
            }
        }

        System.out.println("Fin du programme ");
    }
}
