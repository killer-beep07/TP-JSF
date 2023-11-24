/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;



import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lenovo
 */
@ManagedBean(name="loginBean")
@SessionScoped
    
public class LoginBean implements Serializable{

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    // Getters and setters
    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        // Vérifiez les informations d'identification dans la base de données
        if (isValidCredentials(username, password)) {
            return "faces/index.xhtml"; // Redirigez vers la page principale en cas de succès
        } else {
            // Affichez un message d'erreur en cas d'échec
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid credentials"));
            return null;
        }
    }
   

private static Connection connection;

static {
    try {
        String log = "root";
        String pass = "";
        String url = "jdbc:mysql://localhost:3306/jsf7";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, log, pass);
    } catch (ClassNotFoundException ex) {
        System.out.println("Driver introuvable");
    } catch (SQLException ex) {
        System.out.println("Erreur de connexion à la base de données");
    }
}
private boolean isValidCredentials(String username, String password) {
    try {
        // Obtenez la connexion à la base de données (à adapter en fonction de votre configuration)
        String log = "root";
        String pass= "";
        String url = "jdbc:mysql://localhost:3306/jsf7";
        Connection connection;

        connection = DriverManager.getConnection(url, log, pass);

        // Exemple de vérification des informations d'identification
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM login WHERE username = ? AND password = ?");
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();

        // Examinez le résultat
        boolean isValid = result.next();

        // Assurez-vous de fermer la connexion et les ressources associées
        statement.close();
        connection.close();

        return isValid;
    } catch (SQLException e) {
        // Gérez l'exception (par exemple, en la journalisant)
        e.printStackTrace();
        return false;
    }
}

}



