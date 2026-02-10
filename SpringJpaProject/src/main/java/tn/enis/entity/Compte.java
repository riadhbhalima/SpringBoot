package tn.enis.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_compte")
public class Compte implements Serializable {
	private static final long serialVersionUID = 1L;
	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rib;
	private float solde;

	@ManyToOne
	private Client client;
	// FetchType.EAGER: lorsqu'on charge un compte, le client est chargé avec
	// FetchType.LAZY: lorsqu'on charge un compte, le client n'est pas chargé avec
	// il sera chargé lors de l'appel de la méthode getClient
	// default fetch since JPA2.1
	// 1--> EAGER
	// *--> LAZY

	public Compte(float solde, Client client) {
		super();
		this.solde = solde;
		this.client = client;
	}

}
