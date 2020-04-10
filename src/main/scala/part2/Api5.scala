package part2

import caliban.GraphQL.graphQL
import caliban.RootResolver
import caliban.schema.GenericSchema
import zio.{Task, ZIO}
import zquery.{DataSource, Request, ZQuery}


case class MyEnv(number: Int)

object Api5 extends GenericSchema[MyEnv] {
  type MyQuery[+A] = ZQuery[MyEnv, Throwable, A]

  case class Receipt(order: Task[EventOrderView])

  case class EventOrderView(id: String, event: MyQuery[EventView])

  case class EventView(id: String)

  case class Queries(receipt: Receipt)

  def queries = Queries(Receipt(getOrder()))

  val api = graphQL(RootResolver(queries))

  def getOrder(): Task[EventOrderView] = Task.succeed(EventOrderView("1", getEvent("1")))

  case class GetEvent(id: String) extends Request[Throwable, EventView]

  val eventDataSource: DataSource[MyEnv, GetEvent] =
    DataSource.fromFunctionM("EventDataSource") { req =>
      for {
        env <- ZIO.environment[MyEnv]
      } yield EventView("1")
    }

    def getEvent(id: String): MyQuery[EventView] = ZQuery.fromRequest(GetEvent(id))(eventDataSource)
//  def getEvent(id: String): Task[EventView] = Task.succeed(EventView("1"))

  def execute(query: String) = {
    val io = (for {
      interpreter <- api.interpreter
      result <- interpreter.execute(query)
    } yield result).provide(MyEnv(100))

    io.fold(
      _ => "error",
      rv => rv.data.toString
    )
  }
}
