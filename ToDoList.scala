import IOHelper._
import scala.util.{Try, Success, Failure}

object ToDoList {

        def main(args: Array[String]): Unit = {
                val db= new Database("./ToDoList.dat")
                val ip= new InputProcessor(db)

                def mainLoop(): Try[Unit] = {
                        for {
                                _ <- promptUser()
                                input <- readInput()
                                _ <- {
                                        ip.handleInput(input)
                                        mainLoop()
                                }
                        } yield ()
                }

                mainLoop()
        }
}

