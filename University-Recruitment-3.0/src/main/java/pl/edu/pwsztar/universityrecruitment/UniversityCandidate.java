package pl.edu.pwsztar.universityrecruitment;

@javax.persistence.Entity
public class UniversityCandidate implements java.io.Serializable
{

   static final long serialVersionUID = 1L;

   @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "UNIVERSITYCANDIDATE_ID_GENERATOR")
   @javax.persistence.Id
   @javax.persistence.SequenceGenerator(sequenceName = "UNIVERSITYCANDIDATE_ID_SEQ", name = "UNIVERSITYCANDIDATE_ID_GENERATOR")
   private java.lang.Long id;

   private java.lang.String firstName;

   private java.lang.String lastName;

   private java.lang.Integer examResult;

   private java.lang.Boolean admission = Boolean.FALSE;
   
   private java.lang.String fieldOfStudy;

   private java.lang.Boolean olympicFinalist;

   private java.lang.String qualificationType = "None";
   
   private java.lang.String logger = "";   

   private java.lang.Integer counter = 0;
   
public UniversityCandidate()
   {
   }

   public java.lang.Long getId()
   {
      return this.id;
   }

   public void setId(java.lang.Long id)
   {
      this.id = id;
   }

   public java.lang.String getFirstName()
   {
      return this.firstName;
   }

   public void setFirstName(java.lang.String firstName)
   {
      this.firstName = firstName;
   }

   public java.lang.String getLastName()
   {
      return this.lastName;
   }

   public void setLastName(java.lang.String lastName)
   {
      this.lastName = lastName;
   }

   public java.lang.Integer getExamResult()
   {
      return this.examResult;
   }

   public void setExamResult(java.lang.Integer examResult)
   {
      this.examResult = examResult;
   }

   public java.lang.Boolean getAdmission()
   {
      return this.admission;
   }

   public void setAdmission(java.lang.Boolean admission)
   {
      this.admission = admission;
   }
   
   public java.lang.String getFieldOfStudy()
   {
      return this.fieldOfStudy;
   }

   public void setFieldOfStudy(java.lang.String fieldOfStudy)
   {
      this.fieldOfStudy = fieldOfStudy;
   }

   public java.lang.Boolean getOlympicFinalist()
   {
      return this.olympicFinalist;
   }

   public void setOlympicFinalist(java.lang.Boolean olympicFinalist)
   {
      this.olympicFinalist = olympicFinalist;
   }

   public java.lang.String getQualificationType()
   {
      return this.qualificationType;
   }

   public void setQualificationType(java.lang.String qualificationType)
   {
      this.qualificationType = qualificationType;
   }
   
   public java.lang.String getLogger()
   {
      return this.logger;
   }

   public void setLogger(java.lang.String logger)
   {
      this.logger = logger;
   }

   public void appendLogger(java.lang.String logger)
   {
      this.logger += logger + "\n";
   }   

   public java.lang.Integer getCounter() {
	   return counter;
   }

   public void setCounter(java.lang.Integer counter) {
	   this.counter = counter;
   }
   
   public void incrementCounter() {
	   this.counter++;
   }
   
   public java.lang.String getCandidateInformation()
   {
      return this.firstName + " " + this.lastName + ". Field of study: " + this.fieldOfStudy + " (" + this.examResult + " points, OlympicFinalist: " + this.olympicFinalist + ").\n⮕ Admission: " +
    		 this.admission + ". Qualification type: " + this.qualificationType + ". \n" + this.logger;
   }
   
   public UniversityCandidate(java.lang.Long id, java.lang.String firstName,
	         java.lang.String lastName, java.lang.Integer examResult,
	         java.lang.Boolean admission, java.lang.String fieldOfStudy,
	         java.lang.Boolean olympicFinalist, java.lang.String qualificationType, java.lang.String logger, java.lang.Integer counter)
	   {
	      this.id = id;
	      this.firstName = firstName;
	      this.lastName = lastName;
	      this.examResult = examResult;
	      this.admission = admission;
	      this.fieldOfStudy = fieldOfStudy;
	      this.olympicFinalist = olympicFinalist;
	      this.qualificationType = qualificationType;
	      this.logger = logger;
	      this.counter = counter;
	   }

   public UniversityCandidate(java.lang.Long id, java.lang.String firstName,
	         java.lang.String lastName, java.lang.Integer examResult,
	         java.lang.Boolean admission, java.lang.String fieldOfStudy,
	         java.lang.Boolean olympicFinalist)
	   {
	      this.id = id;
	      this.firstName = firstName;
	      this.lastName = lastName;
	      this.examResult = examResult;
	      this.admission = admission;
	      this.fieldOfStudy = fieldOfStudy;
	      this.olympicFinalist = olympicFinalist;
	   }
}