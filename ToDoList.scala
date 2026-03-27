import IOHelper._
import InputHelper._
import scala.util.{Try, Success, Failure}

object ToDoList {

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

