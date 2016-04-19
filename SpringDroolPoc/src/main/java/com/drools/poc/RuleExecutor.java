package com.drools.poc;

import java.util.Collection;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.ObjectFilter;
import org.drools.runtime.StatefulKnowledgeSession;

public class RuleExecutor {


	public TestResult processRules(TestRecord testRecord)
	{
		KnowledgeBase kbase = null;
		TestResult testResult=null;
		try {
			// Reading knowledgeBase
			kbase = readKnowledgeBase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Creating a state-full session
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		//Inserting the object in session
		ksession.insert(testRecord);
		// Firing all the rules
		ksession.fireAllRules();
		//Getting objects from session
		Collection<Object> result = findFacts(ksession, TestResult.class);
		//Getting objects in array
		Object[] objects = result.toArray();
		//Getting object from array
		if(objects.length == 1)
		{
		testResult = (TestResult) objects[0];
		}
		return testResult;
	}

	// Read all the rules from the rules file 
	private KnowledgeBase readKnowledgeBase() throws Exception {
		// Creating knowledge builder object
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		//Loading all rules in knowledge builder
		kbuilder.add(ResourceFactory.newClassPathResource("rules.drl"), ResourceType.DRL);
		//If any errors in rules file
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		// Creating knowledge base
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		//adding knowledege packages in knowledge base 
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

	// For getting the collection of  objects inserted in the rules file 
	protected static Collection<Object> findFacts(final StatefulKnowledgeSession session, @SuppressWarnings("rawtypes") final Class factClass) {
		ObjectFilter filter = new ObjectFilter() {
			public boolean accept(Object object) {
				return object.getClass().equals(factClass);
			}
		};
		Collection<Object> results = session.getObjects(filter);
		return results;
	}

}
