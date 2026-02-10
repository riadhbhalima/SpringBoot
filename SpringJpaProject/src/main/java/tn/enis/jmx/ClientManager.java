package tn.enis.jmx;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "client.service:name=ClientManager", description = "Client Service Manager")
public class ClientManager {

	private int clientCount = 0;

	@ManagedAttribute(description = "Nombre de clients")
	public int getClientCount() {
		return clientCount;
	}

	@ManagedOperation(description = "Ajouter un client")
	public void addClient() {
		clientCount++;
		System.out.println("Client ajouté. Total = " + clientCount);
	}
}
