package pl.edu.pwsztar.universityrecruitment;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.logger.KieRuntimeLogger;

public class UniversityMainIntermediate {

	public static void main(String[] args) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");
		
		// Fakty
		UniversityCandidate uc = new UniversityCandidate(1L, "Anna", "Kowalewska", 120.0, Boolean.FALSE, "Informatyka", Boolean.TRUE, "Female");

		// Dodanie do przetwarzania pojedynczego faktu
		kSession.insert(uc);

		// ZESTAW REGUŁ ŚREDNIOZAAWANSOWANYCH		
		// HOW-TO :: Uruchomienie przykładu.
		// Każdy przykład (Example 1 - Example 4) należy uruchamiać niezależnie.
		// W celu uruchomienia określonego przykładu należy ustawić wartość zmiennej example. 
		// Rezultaty działania silnika wnioskującego są zwracane w oknie konsoli.

		// Uruchamiany przykład
		Integer example = 1;

		switch (example) {
		case 1:
			// Set 3. Example 1.
			// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
			// wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
			// Uruchamianie tylko jednej reguły kwalifikacyjnej, gdyż nie jest zasadne
			// 2-krotna kwalifikacja kandydata, z wykorzystaniem parametru activation-group,
			// który określa, że gdy dowolna reguła należąca do activation-group
			// zostanie aktywowana, uruchomienie wszystkich pozostałych należących do grupy
			// jest anulowane.
			kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group").setFocus();
			break;
		case 2:
			// Set 3. Example 2.
			// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
			// wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
			// Reguły z obu zbiorow "ExamQualification" oraz "OlympicQualification" są
			// wykonywane z MODFIKACJĄ faktu powodującą uruchomienie ponownego wnioskowania.
			// Reguły posiadają parametry activation-group oraz salience.
			// PYTANIE: Dlaczego reguły zostały uruchomione 10-krotnie?
			kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group_modify").setFocus();
			break;
		case 3:
			// Set 3. Example 3.
			// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
			// wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
			// Reguły z obu zbiorow "ExamQualification" oraz "OlympicQualification" są
			// wykonywane z MODFIKACJĄ faktu powodującą uruchomienie ponownego wnioskowania.
			// Reguły posiadają parametry activation-group, salience oraz no-loop.
			// PYTANIE: Dlaczego mimo użycia parametru no-loop reguły zostały uruchomione
			// 10-krotnie?
			kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group_modify_no-loop").setFocus();
			break;
		case 4:
			// Set 3. Example 4.
			// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
			// wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
			// Reguły z obu zbiorow "ExamQualification" oraz "OlympicQualification" są
			// wykonywane z MODFIKACJĄ faktu powodującą uruchomienie ponownego wnioskowania.
			// Reguły posiadają parametry activation-group, salience oraz lock-on-active.
			// PYTANIE: Jak działa parametr lock-on-active?
			kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group_modify_lock-on-active").setFocus();
			break;
		}
		
		System.out.println("Number of facts in Working Memory (Entry Point): " + kSession.getFactCount());
		kSession.addEventListener(new DebugAgendaEventListener());
        kSession.addEventListener(new DebugRuleRuntimeEventListener());
		KieRuntimeLogger logger = ks.getLoggers().newFileLogger(kSession, "./rules-logger");
		
		kSession.fireAllRules();
		kSession.dispose();
				
		// Logowanie zebranych informacji
		System.out.println(uc.getCandidateInformation());
	}
}