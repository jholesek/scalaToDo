import java.io._
import scala.util.{Try, Success, Failure}

class Database(val dbFilename: String) {
        private def writeToFile(lines: Seq[String], append: Boolean): Try[Unit] = {
                var bw: BufferedWriter = null

                try {
                        bw = new BufferedWriter(new FileWriter(new File(dbFilename), append))
                        for(line <- lines) {
                                bw.write(s"$line\n")
                        }
                        Success(true)
                } catch {
                        case e: Throwable => Failure(e)
                } finally {
                        if( bw!=null) bw.close
                }
        }

        def insert(record: String): Try[Unit] = {
                writeToFile(List(record), true)
        }

        def selectAll(): Try[Seq[String]] = ???
        def delete(index: Int): Try[Int] = ???

}

