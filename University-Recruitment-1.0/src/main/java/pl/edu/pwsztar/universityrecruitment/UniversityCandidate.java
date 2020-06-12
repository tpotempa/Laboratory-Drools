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
   
   public UniversityCandidate(java.lang.Long id, java.lang.String firstName,
	         java.lang.String lastName, java.lang.Integer examResult,
	         java.lang.Boolean admission)
	   {
	      this.id = id;
	      this.firstName = firstName;
	      this.lastName = lastName;
	      this.examResult = examResult;
	      this.admission = admission;
	   }

}