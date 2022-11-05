package service

import repository.{BaseRepository, TBaseRepository}

import scala.entities.BaseEntity


class BaseService[E <: BaseEntity, R <: TBaseRepository[BaseEntity]]( repository: R) {

}