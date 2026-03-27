import IOHelper._
import scala.util.{Try, Success, Failure}

class InputProcessor(db: Database) {

        def handleView(): Try[Unit] = Try {
                val res: Try[Seq[String]] =db.selectAll()
                res match {
                        case Success(tasks) =>
                                for((task, count) <- tasks.zip(Stream from 1))
                                        println(s"${count}. $task")
                        case Failure(e) =>
                                System.err.println(e)
                }

        }

        def handleAdd(task: String): Try[Unit] = {
                db.insert(task)
        }


        def handleInput(input: String): Try[Unit] = input match {
                case "q" =>
                        Try{System.exit(0)}
                case "h" =>
                        IOHelper.showHelp()
                case "v" =>
                        handleView()
                case add if add.startsWith("a ") =>
                        handleAdd(add.drop(2))
                        handleView()
                case del if del.startsWith("d ") =>
                        ???
                case _ => ???

                // println("handle: $s",input)
        }
}

