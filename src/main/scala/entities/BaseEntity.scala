package scala.entities
import java.io.Serializable

case class BaseEntity() extends Serializable{
  def id: Long = IdCreator.create
  def recordStatus: Int= 1
}
