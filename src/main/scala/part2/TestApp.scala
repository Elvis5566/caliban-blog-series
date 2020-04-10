package part2

import caliban.CalibanError.ValidationError
import caliban.GraphQL.graphQL
import caliban.RootResolver
import caliban.schema.GenericSchema
import zio.console.{Console, putStrLn}
import zio.{App, ZIO}

object TestApp extends App {

//  val test1: ZIO[Console, ValidationError, Int] =
//    for {
//      dbService   <- DBService()
//      resolver    = Api1.resolver(dbService)
//      api         = graphQL(RootResolver(resolver))
//      interpreter <- api.interpreter
//      _           <- interpreter.execute(Query.orders)
//      dbHits      <- dbService.hits
//      _           <- putStrLn(s"Naive Approach - DB Hits: $dbHits")
//    } yield 0
//
//  val test2: ZIO[Console, ValidationError, Int] =
//    for {
//      dbService   <- DBService()
//      resolver    = Api2.resolver(dbService)
//      api         = graphQL(RootResolver(resolver))
//      interpreter <- api.interpreter
//      _           <- interpreter.execute(Query.orders)
//      dbHits      <- dbService.hits
//      _           <- putStrLn(s"Nested Effects - DB Hits: $dbHits")
//    } yield 0
//
//  object schema extends GenericSchema[Int] // ref: https://ghostdogpr.github.io/caliban/docs/schema.html#effects
//  import schema._
//
//  val test3: ZIO[Console, ValidationError, Int] =
//
//    for {
//      dbService   <- DBService()
//      resolver    = Api3.resolver(dbService)
//      api         = graphQL(RootResolver(resolver))
//      interpreter <- api.interpreter
//      _           <- interpreter.execute(Query.orders)
//      dbHits      <- dbService.hits
//      _           <- putStrLn(s"ZQuery - DB Hits: $dbHits")
//    } yield 0
//
//  val test4: ZIO[Console, ValidationError, Int] =
//    for {
//      dbService   <- DBService()
//      resolver    = Api4.resolver(dbService)
//      api         = graphQL(RootResolver(resolver))
//      interpreter <- api.interpreter
//      _           <- interpreter.execute(Query.orders)
//      dbHits      <- dbService.hits
//      _           <- putStrLn(s"ZQuery with Batch - DB Hits: $dbHits")
//    } yield 0

//  val test5 =
  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, Int] =
    for {
      _ <- putStrLn("test")
      _ <- putStrLn(Api5.api.render)
    } yield 0
//    (test1 *> test2 *> test3 as 0).catchAll(error => zio.console.putStrLn(error.toString).as(1))
}
