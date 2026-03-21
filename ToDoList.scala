import IOHelper._
import scala.util.{Try, Success, Failure}

object ToDoList {
        def handleInput(input: String): Try[Unit] = Try {
                println("handle: $s",input)
        }

        def main(args: Array[String]): Unit = {
                val db= new Database("./ToDoList.dat")

                def mainLoop(): Try[Unit] = {
                        for {
                                _ <- promptUser()
                                input <- readInput()
                                _ <- {
                                        handleInput(input)
                                        mainLoop()
                                }
                        } yield ()
                }

                mainLoop()
        }
}

