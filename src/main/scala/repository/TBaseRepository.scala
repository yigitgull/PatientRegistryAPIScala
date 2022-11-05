package repository

import scala.entities.BaseEntity

trait TBaseRepository[A <: BaseEntity] {
  def getAll(): Seq[A]
  def getId(id: Long):A
  def persist(entity: A):Unit
  def delete(id: Long)
  def update(entity: A):Unit
  def delete(entity: A)

}


