package pl.edu.pwsztar.universityrecruitment;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class UniversityMainSimple {

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
		
		// ZESTAW REGUŁ PROSTYCH
		// HOW-TO :: Uruchomienie przykładu.
		// Każdy przykład (Example 1 - Example 9) należy uruchamiać niezależnie.
		// W celu uruchomienia określonego przykładu należy ustawić wartość zmiennej example. 
		// Rezultaty działania silnika wnioskującego są zwracane w oknie konsoli.

		// Uruchamiany przykład
		Integer example = 1;

		switch (example) {
		case 1:
			// Set 1. Example 1.
			// OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczających się reguł
			// kwalifikacyjnych.
			kSession.getAgenda().getAgendaGroup("one_set_of_rules").setFocus();
			break;
		case 2:
			// Set 1. Example 2.
			// OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczających się reguł
			// kwalifikacyjnych z MODFIKACJĄ faktu powodującą uruchomienie ponownego
			// wnioskowania.
			// PYTANIE: W jakim celu używany jest counter?
			kSession.getAgenda().getAgendaGroup("one_set_of_rules_modify").setFocus();
			break;
		case 3:
			// Set 1. Example 3.
			// OPIS: Uruchamianie 1 zbioru składającego się z 3 wykluczających się reguł
			// kwalifikacyjnych z MODFIKACJĄ faktu powodującą uruchomienie ponownego
			// wnioskowania
			// oraz klauzulą no-loop uniemożliwiającą ponowne uruchomienie reguły przez samą
			// siebie w przypadku ponownego wnioskowania
			kSession.getAgenda().getAgendaGroup("one_set_of_rules_modify_no-loop").setFocus();
			break;
		case 4:
			// Set 1. Example 4.
			// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
			// wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
			kSession.getAgenda().getAgendaGroup("two_sets_of_rules").setFocus();
			break;
		case 5:
			// Set 1. Example 5.
			// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
			// wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
			// Reguły sa uruchamiane z parametrem salience, który określa priorytet
			// kolejności uruchomienia (wyższa wartość to wyższy priorytet)
			kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience").setFocus();
			break;
		case 6:
			// Set 1. Example 6.
			// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
			// wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
			// Reguły z obu zbiorów są wykonywane z MODFIKACJĄ faktu
			// powodującą uruchomienie ponownego wnioskowania.
			// Reguły posiadają parametry no-loop oraz salience.
			kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_modify_no-loop").setFocus();
			break;
		case 7:
			// Set 1. Example 7.
			// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
			// wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
			// Reguły z obu zbiorów są wykonywane z MODFIKACJĄ faktu
			// powodującą uruchomienie ponownego wnioskowania.
			// Reguły posiadają parametry lock-on-active oraz salience.
			kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_modify_lock-on-active").setFocus();
			break;
		case 8:
			// Set 1. Example 8.
			// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
			// wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
			// Reguła ze zbioru "OlympicQualification" jest wykonywana z MODFIKACJĄ faktu
			// powodującą uruchomienie ponownego wnioskowania.
			// Modyfikacja polega na 2-krotnym zwiększeniu liczby punktów egzaminacyjnych, 
			// uzyskanych przez kandydata będącego finalistą olimipiady.
			// Należy podkreślić, że kandydat będący finalistą olimpiady nie może zostać przyjęty
			// bezpośrednio na podstawie olimpiady. Jedyną podstawą kwalifikacji kandydata 
			// jest liczba egzaminacyjnych.
			// Reguły posiadają parametry no-loop oraz salience.
			kSession.getAgenda().getAgendaGroup("two_sets_of_rules_bonus_salience_modify_no-loop").setFocus();
			break;
		case 9:
			// Set 1. Example 9.
			// OPIS: Uruchamianie 2 zbiorów reguł kwalifikacyjnych. Oba zbiory reguł nie
			// wykluczają się tj. mogą być uruchomione reguły z obu zbiorów.
			// Uruchamianie tylko jednej reguły kwalifikacyjnej, gdyż nie jest zasadne
			// 2-krotna kwalifikacja kandydata, z wykorzystaniem parametru activation-group, 
			// który określa, że gdy dowolna reguła należąca do activation-group zostanie aktywowana, 
			// uruchomienie wszystkich pozostałych należących do grupy jest anulowane.
			// PYTANIE: Dlaczego nie wszystie fakty tj. kandydaci mają określoną decyzję?
			kSession.getAgenda().getAgendaGroup("two_sets_of_rules_salience_activation-group").setFocus();
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