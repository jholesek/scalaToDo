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

        def handleDelete(index: String): Try[Unit] = Try {
                val taskId: Int = index.toInt
                val res: Try[Int] = db.delete(taskId-1)
                res match {
                        case Success(numRowsDeleted) => 
                                System.out.println(s"Deleted $index task, ${numRowsDeleted} rows\n")

                        case Failure(e) => System.err.println(e)
                }
        }

        def handleOther(): Try[Unit] = Try {
                System.err.println("Unknown command ...\n")
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
                        handleDelete(del.drop(2))
                        handleView()
                case _ => 
                        handleOther()
                        handleView()

                // println("handle: $s",input)
        }
}

