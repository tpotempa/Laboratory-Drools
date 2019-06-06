package pl.edu.pwsztar.universityrecruitment;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
//import org.kie.*;

public class UniversityMain {

	public static void main(String[] args) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");
		
		UniversityCandidate uc1 = new UniversityCandidate(1L, "Anna", "Kowalska", 120, Boolean.FALSE, "Informatyka", Boolean.TRUE);
		UniversityCandidate uc2 = new UniversityCandidate(2L, "Jacek", "Nowak", 80, Boolean.FALSE, "Informatyka", Boolean.FALSE);		
		UniversityCandidate uc3 = new UniversityCandidate(3L, "Ewa", "Wiśniowa", 90, Boolean.FALSE, "Elektrotechnika", Boolean.FALSE);
		UniversityCandidate uc4 = new UniversityCandidate(4L, "Karol", "Gruszka", 135, Boolean.FALSE, "Automatyka i robotyka", Boolean.TRUE);
		UniversityCandidate uc5 = new UniversityCandidate(5L, "Kinga", "Poziomka", 25, Boolean.FALSE, "Elektrotechnika", Boolean.TRUE);

		
		// Examples :: SET 1
		kSession.insert(uc1);		
		kSession.insert(uc2);
		kSession.insert(uc3);
		kSession.insert(uc4);
		kSession.insert(uc5);
		
		// Example 1.1
		kSession.getAgenda().getAgendaGroup("one_set_of_rules").setFocus();
		// Example 1.2
		// kSession.getAgenda().getAgendaGroup("one_set_of_rules_modify").setFocus();
		// Example 1.3
		// kSession.getAgenda().getAgendaGroup("one_set_of_rules_modify_no-loop").setFocus();
		// Example 1.4
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules").setFocus();
		// Example 1.5
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience").setFocus();
		// Example 1.6
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group").setFocus();
		 
		System.out.println("Number of facts in Working Memory (Entry Point): " + kSession.getFactCount() + "\n");
		kSession.fireAllRules();
		kSession.dispose();
				
		System.out.println(uc1.getCandidateInformation());
		System.out.println(uc2.getCandidateInformation());
		System.out.println(uc3.getCandidateInformation());
		System.out.println(uc4.getCandidateInformation());
		System.out.println(uc5.getCandidateInformation());
		// End Examples :: SET 1
		
		
		/*
		// Examples :: SET 2
		kSession.insert(uc1);
		
		// Example 2.1 
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group").setFocus();
		// Example 2.2 
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group_modify").setFocus();
		// Example 2.3 
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group_modify_no-loop").setFocus();
		// Example 2.4 
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group_modify_lock-on-active").setFocus();

		System.out.println("Number of facts in Working Memory (Entry Point): " + kSession.getFactCount() + "\n");
		kSession.fireAllRules();
		kSession.dispose();
				
		System.out.println(uc1.getCandidateInformation());
		// End Examples :: SET 2		 
		*/
	}

}





