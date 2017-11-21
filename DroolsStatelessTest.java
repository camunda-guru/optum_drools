package com.javainuse.main;

import java.io.File;
import java.util.Arrays;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.runtime.StatelessKnowledgeSession;

import com.javainuse.model.Counter;
import com.optum.models.Alarm;
import com.optum.models.Sprinkler;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsStatelessTest {

    public static final void main(String[] args) {
        
    	KieServices ks = KieServices.Factory.get();
		KieFileSystem kfs = ks.newKieFileSystem();
		File file = new File("src/main/resources/firestatelessrules/FiringRule.drl");
		Resource resource = ks.getResources().newFileSystemResource(file).setResourceType(ResourceType.DRL);
		 kfs.write( resource);  
		KieBuilder kb =ks.newKieBuilder(kfs);
		 kb.buildAll();
		
			
			KieContainer kContainer = ks.newKieContainer(kb.getKieModule().getReleaseId());
			KieBaseConfiguration kbconf = ks.newKieBaseConfiguration();
			KieBase kbase = kContainer.newKieBase(kbconf);
			
			StatelessKieSession ksession = kbase.newStatelessKieSession();
	        Alarm alarm =new Alarm();
	        alarm.setStatus(false);
	        Sprinkler sprinkler =new Sprinkler();
	        sprinkler.setWaterLine(false);
	        //System.out.println("State of the Alarm Before Rule Firing"+alarm.isStatus());
	        ksession.execute(Arrays.asList(new Object[]{alarm,sprinkler}));
    	
			/*
			try {        	
        	
        	
        	
       
        	
        	// load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    	    
    	    //get stateless session
        	StatelessKieSession kSession = kContainer.newStatelessKieSession();

           
        	Counter cnt1 = new Counter(1,"cnt1");
            Counter cnt2 = new Counter(2,"cnt2");
           
            System.out.println();
			System.out.println("Execute after inserting counter1");
			System.out.println();

            //execute with counter1
            kSession.execute(cnt1);
            
            System.out.println();
			System.out.println("Execute after inserting counter2");
			System.out.println();

          //execute with counter2
            kSession.execute(cnt2);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        */
    }

    

}
