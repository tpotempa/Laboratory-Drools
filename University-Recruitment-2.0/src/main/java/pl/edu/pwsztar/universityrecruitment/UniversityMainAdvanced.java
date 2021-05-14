package pl.edu.pwsztar.universityrecruitment;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

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
		
		// Utworzenie kolekcji 5 faktów
		List<UniversityCandidate> uc = new ArrayList<UniversityCandidate>();
		uc.add(uc1);
		uc.add(uc2);
		uc.add(uc3);
		uc.add(uc4);
		uc.add(uc5);
		
		// Dodanie do przetwarzania zbioru 5 faktów
		for(UniversityCandidate fact:uc) {
			kSession.insert(fact);			
		}

		// ZESTAW REGUŁ ZAAWANSOWANYCH
		// HOW-TO :: Uruchomienie przykładu.
		// Każdy przykład (Example 1 - Example 1) należy uruchamiać niezależnie.
		// W celu uruchomienia określonego przykładu należy ustawić wartość zmiennej example. 
		// Rezultaty działania silnika wnioskującego są zwracane w oknie konsoli.

		// Uruchamiany przykład
		Integer example = 1;
		
		switch (example) {
		case 1:
			// Set 3. Example 1.
			// OPIS: Uruchamianie 3 zbiorów reguł kwalifikacyjnych. Zbiory reguł nie
			// wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
			// PYTANIE: W jakiej kolejności wykonują się reguły?
			kSession.getAgenda().getAgendaGroup("three_sets_of_rules_bonus_salience_modify_lock-on-active").setFocus();
			break;
		}
		
		System.out.println("Number of facts in Working Memory (Entry Point): " + kSession.getFactCount());
		kSession.addEventListener(new DebugAgendaEventListener());
		kSession.addEventListener(new DebugRuleRuntimeEventListener());
		kSession.fireAllRules();
		kSession.dispose();
				
		// Logowanie zebranych informacji
		for(UniversityCandidate fact:uc) {
			System.out.println(fact.getCandidateInformation());
		}
	}

}