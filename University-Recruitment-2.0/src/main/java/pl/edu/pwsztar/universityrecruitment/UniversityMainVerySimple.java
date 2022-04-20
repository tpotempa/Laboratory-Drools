package pl.edu.pwsztar.universityrecruitment;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.*;
import org.kie.api.logger.KieRuntimeLogger;

public class UniversityMainVerySimple {

	public static void main(String[] args) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");

		// Fakty
		UniversityCandidate uc1 = new UniversityCandidate(1L, "Anna", "Kowalewska", 120.0, Boolean.FALSE, "Informatyka", Boolean.TRUE, "Female");
		UniversityCandidate uc2 = new UniversityCandidate(2L, "Jacek", "Nowak", 110.0, Boolean.FALSE, "Informatyka", Boolean.FALSE, "Male");
		UniversityCandidate uc3 = new UniversityCandidate(3L, "Ewa", "Wiśniowa", 35.0, Boolean.FALSE, "Elektrotechnika", Boolean.FALSE, "Female");
		UniversityCandidate uc4 = new UniversityCandidate(4L, "Karol", "Gruszka", 135.0, Boolean.FALSE, "Automatyka i robotyka", Boolean.TRUE, "Male");
		UniversityCandidate uc5 = new UniversityCandidate(5L, "Kinga", "Poziomka", 45.0, Boolean.FALSE, "Elektrotechnika", Boolean.TRUE, "Female");

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
		
		// ZESTAW REGUŁ BARDZO PROSTYCH
		// HOW-TO :: Uruchomienie przykładu.
		// Każdy przykład (Example 1 - Example 5) należy uruchamiać niezależnie.
		// W celu uruchomienia określonego przykładu należy ustawić wartość zmiennej example. 
		// Rezultaty działania silnika wnioskującego są zwracane w oknie konsoli.

		// Uruchamiany przykład
		Integer example = 4;

		switch (example) {
		case 1:
			// Set 1. Example 1.
			// OPIS: Uruchamianie zbiorów reguł bez określonej agenda-group.
			// Agenda grupuje reguły w nazwane zbiory pozwalając sterować,
			// które zbiory reguł mają być podczas wnioskowania przetwarzane.
			// Reguły bez określonej agenda-group są niejawnie przyporządkowane 
			// do domyślnej agendy 'MAIN'! Agenda 'MAIN' jest w procesie wnioskowanie 
			// ZAWSZE wywoływana tj. uzyskuje FOCUS.
			// Wywołanie jest niejawne i odbywa się po jawnych wywołaniach agend.
			// UWAGA: W celu wykonania testu niniejszego przykładu należy w zbiorze
			// reguł Main_OlympicQualification.drl zakomentować n/w wiersz: 
			// agenda-group "do_not_focus_automatically"
			// Po przeprowadzeniu testów należy odkomentować w/w wiersz.
			break;
		case 2:
			// Set 1. Example 2.
			// OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczających się reguł
			// kwalifikacyjnych.
			kSession.getAgenda().getAgendaGroup("one_set_of_rules").setFocus();
			break;
		case 3:
			// Set 1. Example 3.
			// OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczających się reguł
			// kwalifikacyjnych oraz zbiorów reguł bez określonej agenda-group tj. z agendy 'MAIN'.
			// UWAGA: W celu wykonania testu niniejszego przykładu należy w zbiorze
			// reguł MainOlympicQualification.drl zakomentować n/w wiersz: 
			// agenda-group "do_not_focus_automatically"
			// Po przeprowadzeniu testów należy odkomentować w/w wiersz.
			kSession.getAgenda().getAgendaGroup("one_set_of_rules").setFocus();
			break;
		case 4:
			// Set 1. Example 4.
			// OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczających się reguł
			// kwalifikacyjnych z MODFIKACJĄ faktu powodującą uruchomienie ponownego
			// wnioskowania.
			// PYTANIE: W jakim celu używany jest counter?
			kSession.getAgenda().getAgendaGroup("one_set_of_rules_MODIFY").setFocus();
			break;
		case 5:
			// Set 1. Example 5.
			// OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczających się reguł
			// kwalifikacyjnych z MODFIKACJĄ faktu powodującą uruchomienie ponownego
			// wnioskowania
			// oraz klauzulą no-loop uniemożliwiającą ponowne uruchomienie reguły przez samą
			// siebie w przypadku ponownego wnioskowania
			kSession.getAgenda().getAgendaGroup("one_set_of_rules_no-loop_MODIFY").setFocus();
			break;
		}

		kSession.addEventListener(new DebugAgendaEventListener());
		kSession.addEventListener(new DebugRuleRuntimeEventListener());
		KieRuntimeLogger logger = ks.getLoggers().newFileLogger(kSession, "./rules-logger");
		
		System.out.println("Reasoning No 1. Number of facts in Working Memory (Entry Point): " + kSession.getFactCount());
		kSession.fireAllRules();
		System.out.println("Reasoning No 1. Number of facts in Working Memory (Exit Point): " + kSession.getFactCount() + "\n");
		
		// Uruchomienie dodatkowego wnioskowania, w celu pokazania, że fakty, które nie są intencjonalnie 
		// zmieniane w pamięcie roboczej, nie są przetwarzane ponownie tj. są przetwarzane tylko 1-krotnie.
		System.out.println("Reasoning No 2. Number of facts in Working Memory (Entry Point): " + kSession.getFactCount());
		kSession.fireAllRules();
		System.out.println("Reasoning No 2. Number of facts in Working Memory (Exit Point): " + kSession.getFactCount() + "\n");

		kSession.dispose();

		// Logowanie zebranych informacji
		for(UniversityCandidate fact:uc) {
			System.out.println(fact.getCandidateInformation());
		}
	}
}