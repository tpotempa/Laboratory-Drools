package pl.edu.pwsztar.universityrecruitment;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.logger.KieRuntimeLogger;

public class UniversityMainAdvanced {

	public static void main(String[] args) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");
		
		UniversityCandidate uc1 = new UniversityCandidate(1L, "Anna", "Kowalewska", 120.0, Boolean.FALSE, "Informatyka", Boolean.TRUE, "Female");
		UniversityCandidate uc2 = new UniversityCandidate(2L, "Jacek", "Nowak", 110.0, Boolean.FALSE, "Informatyka", Boolean.FALSE, "Male");		
		UniversityCandidate uc3 = new UniversityCandidate(3L, "Ewa", "Wiśniowa", 35.0, Boolean.FALSE, "Elektrotechnika", Boolean.FALSE, "Female");
		UniversityCandidate uc4 = new UniversityCandidate(4L, "Karol", "Gruszka", 135.0, Boolean.FALSE, "Automatyka i robotyka", Boolean.TRUE, "Male");
		UniversityCandidate uc5 = new UniversityCandidate(5L, "Kinga", "Poziomka", 30.0, Boolean.FALSE, "Elektrotechnika", Boolean.TRUE, "Female");
		
		// Przetwarzanie powinno być realizowane TYLKO dla pojedynczego faktu
		List<UniversityCandidate> uc = new ArrayList<UniversityCandidate>();
		uc.add(uc1);
		
		// Dodanie faktu do przetwarzania
		for(UniversityCandidate fact:uc) {
			kSession.insert(fact);			
		}

		// ZESTAW REGUŁ ZAAWANSOWANYCH

		// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Zbiory reguł nie
		// wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
		// UWAGA: Dla prawidłowej analizy działania własności PropertyReactive
		// należy zmodyfikować klasę UniversityCandidate dodając wiersz kodu:
		// @org.kie.api.definition.type.PropertyReactive
		// PYTANIE: W jakiej kolejności wykonują się reguły?
		// Rezultaty działania silnika wnioskującego są zwracane w oknie konsoli.
		
		kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group_MODIFY_PropertyReactive").setFocus();
		
		System.out.println("Number of facts in Working Memory (Entry Point): " + kSession.getFactCount());
		kSession.addEventListener(new DebugAgendaEventListener());
		kSession.addEventListener(new DebugRuleRuntimeEventListener());
		KieRuntimeLogger logger = ks.getLoggers().newFileLogger(kSession, "./rules-logger");
		
		kSession.fireAllRules();
		kSession.dispose();
				
		// Logowanie zebranych informacji
		for(UniversityCandidate fact:uc) {
			System.out.println(fact.getCandidateInformation());
		}
	}
}