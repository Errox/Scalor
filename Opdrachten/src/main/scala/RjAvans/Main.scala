package RjAvans

object Main extends App {
  val pg = new PersonGenerator("https://randomuser.me/api/?format=csv&results=10&inc=name,nat,email")

  val logger = new Logger[String]()
  pg.observe(logger)

  val misterFilter = new StringFilter("Mr")
  logger.observe(misterFilter)

  val missFilter = new StringFilter("Miss")
  logger.observe(missFilter)

  val misterExtractor = new NameExtractor()
  misterFilter.observe(misterExtractor)

  val missExtractor = new NameExtractor()
  missFilter.observe(missExtractor)

  val misterPrint = new Printer[String]("mister ")
  misterExtractor.observe(misterPrint)

  val missPrint = new Printer[String]("miss ")
  missExtractor.observe(missPrint)

  pg.trigger()
}