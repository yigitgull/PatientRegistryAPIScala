package service

import com.example.patientregistryapi.entities.Document
import com.example.patientregistryapi.entities.Patient
import com.example.patientregistryapi.entities.PatientRecord
import com.example.patientregistryapi.repository._
import java.time.LocalDateTime

class DocumentService() extends BaseService[Nothing, Nothing](new Nothing) {
  def savePatientFromDocument(localDateTime: LocalDateTime, filePath: String) = {
    val text = new Reader().getDocument(filePath)
    System.out.println(text)
    val regexFinder = new Nothing(text)
    val document = new Nothing(localDateTime, text)
    val patient = PatientBuilder.start.setName(regexFinder.findName).setSurname(regexFinder.findSurname).setEmail(regexFinder.findEmail).setPhoneNumber(regexFinder.findPhoneNumber).setIdentityNo(regexFinder.findIdentityNo).setBirthDate(regexFinder.findBirthDate).setDocument(document).setPatientRecord(new Nothing(localDateTime)).build
    saveDB(patient)
  }

  def save(document: Nothing) = repository.persist(document)

  private def isThereAPatient(patientId: Long) = {
    val repository = new Nothing
    repository.getById(patientId).orElseThrow(() => new RuntimeException("There is no patient with this id"))
  }

  private def saveDB(patient: Nothing) = try new PatientService().save(patient)
  catch {
    case e: Exception =>
      e.printStackTrace()
      throw new RuntimeException("Document was not saved")
  }

  /*private LocalDateTime parseDateTime(String dateTime){
          try {
              LocalDateTime localDateTime=LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yyyy"));
              return localDateTime;
          }catch (DateTimeException exception){
              throw new RuntimeException("Wrong format! Please write the date in this form.  hh:mm:ss dd/MM/yyyy");
          }
      }*/ def getDocumentByPatientId(patientId: Long) = repository.getAll.stream.filter((doc) => doc.getPatient.getId eq patientId).findFirst.orElseThrow(() => new RuntimeException("There is no patient with this id"))
}