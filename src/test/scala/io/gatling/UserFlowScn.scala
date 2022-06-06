package io.gatling
import io.gatling.core.Predef._
import io.gatling.http.HeaderNames.ContentType
import io.gatling.http.HeaderValues.ApplicationJson
import io.gatling.http.Predef._

class UserFlowScn extends BaseScenario {

  val userFlowScn = scenario("User Flow Scenario")
    .exec(createSatellite())
    .pause(2)
    .exec(getSatellite())
    .pause(2)
    .exec(updateSatellite())
    .pause(2)
    .exec(deleteSatellite())

  setUp(
    userFlowScn
      .inject(atOnceUsers(userCount),
      rampUsers(10).during(timeDuration),
      constantUsersPerSec(20).during(15))
    .protocols(httpProtocol.inferHtmlResources()))
}