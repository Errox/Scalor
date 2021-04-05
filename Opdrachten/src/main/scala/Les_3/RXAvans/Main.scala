package Les_3. RXAvans

object Main extends App {
  val pg = new PersonGenerator("https://randomuser.me/api/?format=csv&results=10&inc=name,nat,email")

  val logger = new Logger[String]()
  pg.observe(logger)

  val misterFilter = new Filter[String](_.contains("Mr"))
  logger.observe(misterFilter)

  val missFilter = new Filter[String](_.contains("Miss"))
  logger.observe(missFilter)

  val misterExtractor = new Map[String](_.split(",")(1))
  misterFilter.observe(misterExtractor)

  val missExtractor = new Map[String](_.split(",")(1))
  missFilter.observe(missExtractor)

  val misterPrint = new Printer[String]("mister ")
  misterExtractor.observe(misterPrint)

  val missPrint = new Printer[String]("miss ")
  missExtractor.observe(missPrint)

  pg.trigger()
}
