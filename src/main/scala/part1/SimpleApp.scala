package part1

import zio.ZIO
import zio.interop.catz._

object SimpleApp extends CatsApp {

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, Int] =
    (for {
      interpreter <- MyApi.interpreter
      result      <- interpreter.execute("""
                                      |{
                                      |  findPug(name: "toto") {
                                      |    name
                                      |    pictureUrl
                                      |  }
                                      |}
                                      |""".stripMargin)
      _           <- zio.console.putStrLn(result.data.toString)
    } yield 0).catchAll(error => zio.console.putStrLn(error.toString).as(1))
}
