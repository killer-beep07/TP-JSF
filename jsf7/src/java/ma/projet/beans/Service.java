/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author HP
 */
@Entity
public class Service {
    
    
    @OneToMany(mappedBy = "service")
private List<Employe> Employes;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
    public List<Employe> getEmployes() {
        return Employes;
    }

    public void setEmployes(List<Employe> Employes) {
        this.Employes = Employes;
    }



}
