package entities

import java.time.LocalDate
import scala.entities.BaseEntity

case class Patient (
                    identityNo: String,
                    name: String,
                    email: String,
                    surname: String,
                    phoneNumber: String,
                    birtDate: LocalDate,
                    patientRecords: Seq[PatientRecord]) extends BaseEntity {

}
