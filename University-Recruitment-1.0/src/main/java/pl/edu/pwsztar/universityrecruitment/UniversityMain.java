package pl.edu.pwsztar.universityrecruitment;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class UniversityMain {

	public static void main(String[] args) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");
		
		UniversityCandidate uc = new UniversityCandidate(1L, "Anna", "Kowalewska", 120, Boolean.FALSE);

		kSession.insert(uc);		
		
		kSession.fireAllRules();
		kSession.dispose();
		
	}

}