package io.gatling
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PostAndPutWithRampUpScn extends BaseScenario {

  val rampUpScn = scenario("POST_and_PUT_withRampUpScenario")
    .exec(createSatellite())
    .pause(2)
    .exec(updateSatellite())

  setUp (
    rampUpScn.inject (
      rampUsers(10).during(5),
      constantUsersPerSec(20).during(15)
    ).protocols(httpProtocol.inferHtmlResources()))
}
