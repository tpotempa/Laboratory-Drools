package pl.edu.pwsztar.universityrecruitment;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
//import org.drools.core.event.DebugAgendaEventListener;
import org.kie.api.*;

public class UniversityMain {

	public static void main(String[] args) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");
		
		UniversityCandidate uc1 = new UniversityCandidate(1L, "Anna", "Kowalska", 120.0, Boolean.FALSE, "Informatyka", Boolean.TRUE, "Female");
		UniversityCandidate uc2 = new UniversityCandidate(2L, "Jacek", "Nowak", 80.0, Boolean.FALSE, "Informatyka", Boolean.FALSE, "Male");		
		UniversityCandidate uc3 = new UniversityCandidate(3L, "Ewa", "Wiśniowa", 35.0, Boolean.FALSE, "Elektrotechnika", Boolean.FALSE, "Female");
		UniversityCandidate uc4 = new UniversityCandidate(4L, "Karol", "Gruszka", 135.0, Boolean.FALSE, "Automatyka i robotyka", Boolean.TRUE, "Male");
		UniversityCandidate uc5 = new UniversityCandidate(5L, "Kinga", "Poziomka", 30.0, Boolean.FALSE, "Elektrotechnika", Boolean.TRUE, "Female");

		/*
		// Examples :: SET 1
		// Przetwarzanie zbioru 5 faktów (Zestaw reguł prostych) 
		kSession.insert(uc1);		
		kSession.insert(uc2);
		kSession.insert(uc3);
		kSession.insert(uc4);
		kSession.insert(uc5);
		
		// Example 1.1
		// OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczjących się reguł kwalifikacyjnych. 
		// kSession.getAgenda().getAgendaGroup("one_set_of_rules").setFocus();

		// Example 1.2
		// OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczjących się reguł kwalifikacyjnych z MODFIKACJĄ faktu powodującą uruchomienie ponownego wnioskowania. 
		// PYTANIE: W jakim celu używany jest counter?
		// kSession.getAgenda().getAgendaGroup("one_set_of_rules_modify").setFocus();

		// Example 1.3
		// OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczjących się reguł kwalifikacyjnych z MODFIKACJĄ faktu powodującą uruchomienie ponownego wnioskowania
		// oraz klauzulą no-loop uniemożliwiającą ponowne uruchomienie reguły przez samą siebie w przypadku ponownego wnioskowania
		// kSession.getAgenda().getAgendaGroup("one_set_of_rules_modify_no-loop").setFocus();

		// Example 1.4
		// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie wykluczają się tj. mogą być uruchomione reguły z obu zbiorów. 
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules").setFocus();

		// Example 1.5
		// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie wykluczają się tj. mogą być uruchomione reguły z obu zbiorów. 
		// Reguły sa uruchamiane z parametrem salience, który określa priorytet kolejności uruchomienia (wyższa wartość to wyższy priorytet)
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience").setFocus();

		// Example 1.6
		// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie wykluczają się tj. mogą być uruchomione reguły z obu zbiorów. 
		// Reguły ze zbioru "OlympicQualification" są wykonywane z MODFIKACJĄ faktu powodującą uruchomienie ponownego wnioskowania.
		// Reguły posiadają parametry no-loop oraz salience.
 		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_bonus_salience_modify_no-loop").setFocus();

		// Example 1.7
		// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie wykluczają się tj. mogą być uruchomione reguły z obu zbiorów. 
		// Uruchamianie tylko jednej reguły kwalifikacyjnej, gdyż nie jest zasadne 2-krotna kwalifikacja kandydata,
		// z wykorzystaniem parametru activation-group, który określa, że gdy dowolna reguła należąca do activation-group 
		// zostanie aktywowana, uruchomienie wszystkich pozostałych należących do grupy jest anulowane 
		// PYTANIE: Dlaczego nie wszystie fakty tj. kandydaci mają określoną decyzję?
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group").setFocus();
		 
		System.out.println("Number of facts in Working Memory (Entry Point): " + kSession.getFactCount());
        kSession.addEventListener(new DebugAgendaEventListener());
        kSession.addEventListener(new DebugRuleRuntimeEventListener());		
		kSession.fireAllRules();
		kSession.dispose();
				
		System.out.println(uc1.getCandidateInformation());
		System.out.println(uc2.getCandidateInformation());
		System.out.println(uc3.getCandidateInformation());
		System.out.println(uc4.getCandidateInformation());
		System.out.println(uc5.getCandidateInformation());
		// End Examples :: SET 1
		*/
		
		
		/*
		// Examples :: SET 2
		// Przetwarzanie pojedynczego faktu (Zestaw reguł prostych)
		kSession.insert(uc1);
				
		// Example 2.1 
		// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
		// Uruchamianie tylko jednej reguły kwalifikacyjnej, gdyż nie jest zasadne 2-krotna kwalifikacja kandydata,
		// z wykorzystaniem parametru activation-group, który określa, że gdy dowolna reguła należąca do activation-group 
		// zostanie aktywowana, uruchomienie wszystkich pozostałych należących do grupy jest anulowane.
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group").setFocus();

		// Example 2.2
		// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
		// Reguły z obu zbiorow "ExamQualification" oraz "OlympicQualification" są wykonywane z MODFIKACJĄ faktu powodującą uruchomienie ponownego wnioskowania.
		// Reguły posiadają parametry activation-group oraz salience.
		// PYTANIE: Dlaczego reguły zostały uruchomione 10-krotnie?
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group_modify").setFocus();

		// Example 2.3 
		// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
		// Reguły z obu zbiorow "ExamQualification" oraz "OlympicQualification" są wykonywane z MODFIKACJĄ faktu powodującą uruchomienie ponownego wnioskowania.
		// Reguły posiadają parametry activation-group, salience oraz no-loop.
		// PYTANIE: Dlaczego mimo użycia parametru no-loop reguły zostały uruchomione 10-krotnie?
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group_modify_no-loop").setFocus();

		// Example 2.4 
		// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
		// Reguły z obu zbiorow "ExamQualification" oraz "OlympicQualification" są wykonywane z MODFIKACJĄ faktu powodującą uruchomienie ponownego wnioskowania.
		// Reguły posiadają parametry activation-group, salience oraz lock-on-active.
		// PYTANIE: Jak działa parametr lock-on-active?
		// kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group_modify_lock-on-active").setFocus();

		System.out.println("Number of facts in Working Memory (Entry Point): " + kSession.getFactCount());
        kSession.addEventListener(new DebugAgendaEventListener());
        kSession.addEventListener(new DebugRuleRuntimeEventListener());		
		kSession.fireAllRules();
		kSession.dispose();
				
		System.out.println(uc1.getCandidateInformation());
		// End Examples :: SET 2		 
		*/
		
		
		/*
		// Examples :: SET 3
		// Przetwarzanie zbioru 5 faktów (Zestaw reguł zaawansowanych) 
		kSession.insert(uc1);
		kSession.insert(uc2);
		kSession.insert(uc3);
		kSession.insert(uc4);		
		kSession.insert(uc5);
		
		// Example 3.1 
		// OPIS: Uruchamianie 3 zbiorów reguł kwalifikacyjnych. Zbiory reguł nie wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.		
		// PYTANIE: W jakiej kolejności wykonują się reguły?
		kSession.getAgenda().getAgendaGroup("two_sets_of_rules_bonus_salience_modify_lock-on-active").setFocus();

		System.out.println("Number of facts in Working Memory (Entry Point): " + kSession.getFactCount());
        kSession.addEventListener(new DebugAgendaEventListener());
        kSession.addEventListener(new DebugRuleRuntimeEventListener());		
		kSession.fireAllRules();
		kSession.dispose();
				
		System.out.println(uc1.getCandidateInformation());
		System.out.println(uc2.getCandidateInformation());
		System.out.println(uc3.getCandidateInformation());
		System.out.println(uc4.getCandidateInformation());
		System.out.println(uc5.getCandidateInformation());
		// End Examples :: SET 3		 
		*/
	}

}





