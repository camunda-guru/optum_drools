package com.javainuse.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.javainuse.model.Product;
import com.nokia.entities.Fire;
import com.nokia.entities.Room;
import com.nokia.entities.Sprinkler;

public class DroolsTest {

	public static final void main(String[] args) {
		
		KieServices ks = KieServices.Factory.get();
		KieFileSystem kfs = ks.newKieFileSystem();
		File file = new File("src/main/resources/staterules/FireAlarm.drl");
		Resource resource = ks.getResources().newFileSystemResource(file).setResourceType(ResourceType.DRL);
		 kfs.write( resource);  
		KieBuilder kb =ks.newKieBuilder(kfs);
		 kb.buildAll();
		
			
			KieContainer kContainer = ks.newKieContainer(kb.getKieModule().getReleaseId());
			KieSession kSession = kContainer.newKieSession();
			String[] RoomNames={"Kitchen","Living Room","Bed Room","AV Room"};
			Map<String,Room> map = new HashMap<String,Room>();
			Room room=null;
			Sprinkler sprinkler=null;
			for(String name : RoomNames)
			{
			   room = new Room();
			   room.setName(name);
			   map.put(name, room);
			   kSession.insert(room);
			   sprinkler=new Sprinkler();
			   sprinkler.setRoom(room);
			   kSession.insert(sprinkler);
			   
			}
			
			Fire kitchenFire=new Fire();
			kitchenFire.setRoom(map.get("Kitchen"));
			
			Fire livingFire=new Fire();
			livingFire.setRoom(map.get("Living Room"));
			
			FactHandle khandle= kSession.insert(kitchenFire);
			FactHandle lhandle=kSession.insert(livingFire);
			kSession.fireAllRules();
			kSession.retract( khandle);

			kSession.retract( lhandle );
			kSession.fireAllRules();
		
		
	}

}
