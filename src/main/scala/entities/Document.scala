package entities

import java.time.LocalDate
import scala.entities.BaseEntity

case class Document(registryDate: LocalDate,
                    text: String,
                    patient: Patient) extends BaseEntity{

}
