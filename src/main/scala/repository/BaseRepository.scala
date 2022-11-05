package repository

import scala.entities.BaseEntity

class BaseRepository [A <: BaseEntity] extends TBaseRepository[A] {
  override def getAll(): Seq[A] = Seq("asd",12)

  override def getId(id: Long): A = ???

  override def persist(entity: A): Unit = ???

  override def delete(id: Long): Unit = ???

  override def update(entity: A): Unit = ???

  override def delete(entity: A): Unit = ???
}
