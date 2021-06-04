package pl.edu.pwsztar.universityrecruitment;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.logger.KieRuntimeLogger;

public class UniversityMainExpert {

	public static void main(String[] args) {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");
		
		ArrayList<ExamSubjectResult> esr1 = new ArrayList<>();
		esr1.add(new ExamSubjectResult("język polski", "podstawowy", 35D));
		esr1.add(new ExamSubjectResult("matematyka", "rozszerzony", 80D));
		esr1.add(new ExamSubjectResult("język angielski", "podstawowy", 55D));
		esr1.add(new ExamSubjectResult("informatyka", "podstawowy", 95D));
		esr1.add(new ExamSubjectResult("biologia", "podstawowy", 77D));
		
		ArrayList<ExamSubjectResult> esr2 = new ArrayList<>();
		esr2.add(new ExamSubjectResult("język polski", "podstawowy", 44D));
		esr2.add(new ExamSubjectResult("matematyka", "podstawowy", 40D));
		esr2.add(new ExamSubjectResult("język angielski", "podstawowy", 33D));
		
		ArrayList<ExamSubjectResult> esr3 = new ArrayList<>();
		esr3.add(new ExamSubjectResult("język polski", "podstawowy", 87D));
		esr3.add(new ExamSubjectResult("matematyka", "rozszerzony", 56D));
		esr3.add(new ExamSubjectResult("język angielski", "podstawowy", 44D));
		esr3.add(new ExamSubjectResult("fizyka", "rozszerzony", 31D));
		
		ArrayList<ExamSubjectResult> esr4 = new ArrayList<>();
		esr4.add(new ExamSubjectResult("język polski", "podstawowy", 59D));
		esr4.add(new ExamSubjectResult("matematyka", "podstawowy", 72D));
		esr4.add(new ExamSubjectResult("język angielski", "podstawowy", 33D));
		esr4.add(new ExamSubjectResult("informatyka", "rozszerzony", 88D));

		ArrayList<ExamSubjectResult> esr5 = new ArrayList<>();
		esr5.add(new ExamSubjectResult("język polski", "podstawowy", 99D));
		esr5.add(new ExamSubjectResult("matematyka", "podstawowy", 44D));
		esr5.add(new ExamSubjectResult("język angielski", "podstawowy", 77D));
		esr5.add(new ExamSubjectResult("chemia", "rozszerzony", 100D));

		UniversityCandidate uc1 = new UniversityCandidate(1L, "Anna", "Kowalewska", esr1, Boolean.FALSE, "Informatyka", Boolean.TRUE, "Female");
		UniversityCandidate uc2 = new UniversityCandidate(2L, "Jacek", "Nowak", esr2, Boolean.FALSE, "Informatyka", Boolean.FALSE, "Male");		
		UniversityCandidate uc3 = new UniversityCandidate(3L, "Ewa", "Wiśniowa", esr3, Boolean.FALSE, "Elektrotechnika", Boolean.FALSE, "Female");
		UniversityCandidate uc4 = new UniversityCandidate(4L, "Karol", "Gruszka", esr4, Boolean.FALSE, "Automatyka i robotyka", Boolean.TRUE, "Male");
		UniversityCandidate uc5 = new UniversityCandidate(5L, "Kinga", "Poziomka", esr5, Boolean.FALSE, "Elektrotechnika", Boolean.TRUE, "Female");
		uc1.setExamSubjectResult(esr1);
		// Utworzenie kolekcji faktów
		// Przetwarzanie powinno być realizowane TYLKO dla pojedynczego faktu
		List<UniversityCandidate> uc = new ArrayList<UniversityCandidate>();
		uc.add(uc3);
		
		// Dodanie faktów do przetwarzania
		for(UniversityCandidate fact:uc) {
			kSession.insert(fact);			
		}

		// Uruchomianie zbiorów reguł kwalifikacyjnych "exam_result_calculation"
		kSession.getAgenda().getAgendaGroup("exam_result_calculation").setFocus();
		System.out.println("Number of facts in Working Memory (Entry Point): " + kSession.getFactCount());
		kSession.addEventListener(new DebugAgendaEventListener());
		kSession.addEventListener(new DebugRuleRuntimeEventListener());
		KieRuntimeLogger logger = ks.getLoggers().newFileLogger(kSession, "./rules-logger");
		
		kSession.fireAllRules();
		System.out.println("Number of facts in Working Memory (Exit Point): " + kSession.getFactCount());
		kSession.dispose();
				
		// Logowanie zebranych informacji
		for(UniversityCandidate fact:uc) {
			System.out.println(fact.getCandidateInformation());
		}
	}

}