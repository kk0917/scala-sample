object MyApp extends App {

  import scala.io.Source

  if (args.length > 0) {
    for (line <- Source.fromFile(args(0)).getLines()) println(line.length + " " + line)
  } else {
    Console.err.println("Please enter fileName.")
  }

  for (arg <- args) {
    println(arg)
  }

}