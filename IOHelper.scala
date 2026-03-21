import scala.io.StdIn
import scala.util.{Try, Success, Failure}

object IOHelper {
        def promptUser(): Try[Unit] = {
                Try {
                        println("\nCommands: a \"task\", d 1, h, q, v.\n")
                        print("Yo:")
                }
        }

        def readInput(): Try[String] = {
                Try {
                        StdIn.readLine()
                }
        }

        def showHelp(): Try[Unit]= Try {
                val text="""
                |Possible commands:
                |------------------
                |a <task name> - add a task
                |d <task num>  - delete task num
                |h             - show this help
                |v             - view all tasks
                |q             - quit
                """.stripMargin.trim
                println(text)
        }

}

