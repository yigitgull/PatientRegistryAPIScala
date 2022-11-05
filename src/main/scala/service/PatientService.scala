package service

import entities.Patient
import repository.BaseRepository

import java.time.chrono.ChronoLocalDate
import java.util.Comparator
import scala.entities.BaseEntity

class PatientService() extends BaseService[Patient, BaseRepository[BaseEntity]](new BaseRepository) {
  private val patientList: Seq[Patient] = repository.getAll

  def getPatientList: Seq[Patient] = {
    patientList
  }

  def getSortedPatientListByBirthDate: Seq[Patient] = {
    return patientList.filter((patient: Patient) => patient.birtDate != null).sorted(Comparator.comparing(Patient)).collect(Collectors.toList)
  }

  def getPatientByIdentityNo(identityNo: String): Option[Patient] = {
    val result: Option[Patient] = patientList.parallelStream.filter((x: Patient) => x.getIdentityNo == identityNo).findFirst
    return result
  }

  def getPatientById(patientId: Long): Option[Patient] = {
    return patientList.find((patient: Patient) => patient.id == patientId)
  }

  def getPatientByRecordDate(localDateStartPoint: LocalDate, localDateEndPoint: LocalDate): Seq[Patient] = {
    val patientList1: Seq[Patient] = new ArrayList[Patient]
    System.out.println(patientList)
    import scala.collection.JavaConversions._
    for (patient <- patientList) {
      System.out.println("1")
      import scala.collection.JavaConversions._
      for (patientRecord <- patient.getPatientRecords) {
        System.out.println("2")
        //System.out.println(localDateStartPoint.isBefore(ChronoLocalDate.from(patientRecord.getRecordDate())));
        System.out.println(localDateStartPoint.isAfter(ChronoLocalDate.from(patientRecord.getRecordDate)))
        if (localDateStartPoint.isAfter(ChronoLocalDate.from(patientRecord.getRecordDate)) && localDateStartPoint.isAfter(ChronoLocalDate.from(patientRecord.getRecordDate))) {
          System.out.println("3")
          patientList1.add(patient)
        }
      }
    }
    System.out.println(patientList1)
    return patientList1
  }

  def getPatientByRecordDateStream(localDateTimeStartPoint: LocalDateTime, localDateTimeEndPoint: LocalDateTime): Seq[Patient] = {
    /*Seq<Patient> list= patientList.stream().map(patient-> patient.getPatientRecords())
                                      .filter(patientRecords ->patientRecords.stream().anyMatch(patientRecord ->patientRecord.getRecordDate() .isAfter(localDateTimeStartPoint) && patientRecord.getRecordDate() .isBefore(localDateTimeEndPoint)))
                                       .map(patientRecords ->patientRecords.stream().findAny().get()).map(PatientRecord::getPatient).collect(Collectors.toList());*/ val list: Seq[Patient] = patientList.stream.filter((patient: Patient) => patient.getPatientRecords.stream.anyMatch((patientRecord: PatientRecord) => patientRecord.getRecordDate.isAfter(localDateTimeStartPoint) && patientRecord.getRecordDate.isBefore(localDateTimeEndPoint))).collect(Collectors.toList)
    System.out.println(list)
    return list
  }

  def getPatientByFullName(firstname: String, surname: String): Seq[Patient] = {
    val list: Seq[Patient] = patientList.stream.filter((patient: Patient) => patient.getRecordStatus == 1).filter((patient: Patient) => patient.getSurname == surname).filter((patient: Patient) => patient.getName == firstname).collect(Collectors.toList)
    return list
  }

  def getNumberOfPatients: Long = {
    return patientList.stream.count
  }

  def getAgeOfPatient(identityNo: String): Long = {
    val patient: Option[Patient] = getPatientByIdentityNo(identityNo)
    val years: Long = ChronoUnit.YEARS.between(patient.orElseThrow(() => new RuntimeException("There is no patient")).getBirthDate, LocalDateTime.now)
    return years
  }

  def deletePatient(id: Long): Unit = {
    throwExceptionIfPatientAbsent(id)
    repository.delete(id)
  }

  def save(patient: Patient): Unit = {
    repository.persist(patient)
  }

  private def throwExceptionIfPatientAbsent(id: Long): Unit = {
    if (!(isThereAnyPatient(id))) {
      throw new RuntimeException("There is no patient with this id")
    }
  }

  private def isThereAnyPatient(id: Long): Boolean = {
    return repository.getAll.stream.filter((x: Patient) => x.getId == id).findFirst.isPresent
  }
object PatientService {
  def apply():PatientService = new PatientService()
}
}