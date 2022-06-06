import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ISSPerfScenario extends Simulation{

  val httpProtocol = http
    .baseUrl("http://localhost:8080")

  val atOnceScn = scenario("atOnceUsersScenario")
    .exec(
      http("getSatellites")
        .get("http://localhost:8080")
    )

  val rampUpScn = scenario("rampUpUsersScenario")
    .exec(
      http("getSatellites_rampUp")
        .get("http://localhost:8080")
    )

  val constantConcScn = scenario("constantConcurrentUsersScenario")
    .exec(
      http("whereIsISSNow")
        .get("/25544")
    )

  val concurrentScn = scenario("concurrentUsersScenario")
    .exec(
      http("concurrentCheck_whereISISSNow")
        .get("/25544")
    )

  setUp(
    atOnceScn.inject(atOnceUsers(20)),

    rampUpScn.inject(
      rampUsers(10).during(5),
      constantUsersPerSec(20).during(15),

    ),

    constantConcScn.inject(
      nothingFor(10),
      atOnceUsers(10),
      rampUsersPerSec(10).to(20).during(10))

//commented out due to No implicit found for parameter evidence Injection Profile Factory error
/*    concurrentScn.inject(
      constantConcurrentUsers(10).during(10),
      rampConcurrentUsers(10).to(20).during(10),
      rampConcurrentUsers(20) to(1),
      constantConcurrentUsers(0).during(1))*/

  .protocols(httpProtocol));}