package com.sample;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
public class KieServerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//user "anton" must have role "kie-server" assigned
		KieServicesConfiguration config =  KieServicesFactory.
		        newRestConfiguration("http://localhost:7070/kie-server-7.4.1.Final-ee7/services/rest/server",
		        "eswaribala",
		        "test@123");
		 KieServicesClient client = KieServicesFactory.newKieServicesClient(config);
		
		//ServiceResponse<String> response = client.executeCommands("ListenerReproducer", xStreamXml); 
	   
					 
	}

}
