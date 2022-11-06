package service

import com.example.patientregistryapi.entities.Document
import com.example.patientregistryapi.entities.Patient
import com.example.patientregistryapi.entities.PatientRecord
import java.time.LocalDate
import java.util.Optional

object PatientBuilder {
  def start = new PatientBuilder
}

class PatientBuilder private() {
  patient = new Nothing
  private var patient = null

  def setEmail(email: String) = {
    patient.email(email)
    this
  }

  def setName(name: String) = {
    patient.setName(name)
    this
  }

  def setSurname(surname: String) = {
    patient.setSurname(surname)
    this
  }

  def setIdentityNo(identityNo: String) = {
    patient.setIdentityNo(identityNo)
    this
  }

  def setBirthDate(birthDate: Optional[LocalDate]) = {
    patient.setBirthDate(birthDate.orElse(null))
    this
  }

  def setEmail(email: Optional[String]) = {
    patient.setEmail(email.orElse("unknown"))
    this
  }

  def setName(name: Optional[String]) = {
    patient.setName(name.orElse("unknown"))
    this
  }

  def setSurname(surname: Optional[String]) = {
    patient.setSurname(surname.orElse(null))
    this
  }

  def setIdentityNo(identityNo: Optional[String]) = {
    patient.setIdentityNo(identityNo.orElse("unknown"))
    this
  }

  def setBirthDate(birthDate: LocalDate) = {
    patient.setBirthDate(birthDate)
    this
  }

  def setPhoneNumber(phoneNumber: String) = {
    patient.setPhoneNumber(phoneNumber)
    this
  }

  def setPhoneNumber(phoneNumber: Optional[String]) = {
    patient.setPhoneNumber(phoneNumber.orElse(null))
    this
  }

  def setDocument(document: Nothing) = {
    patient.setDocument(document)
    this
  }

  def setPatientRecord(patientRecord: Nothing) = {
    patient.setPatientRecord(patientRecord)
    this
  }

  def build = patient
}