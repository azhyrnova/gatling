package io.gatling
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PostAndGetAtOnceScn extends BaseScenario {

  val atOnceScn = scenario("POST_and_GET_atOnceScenario")
    .exec(createSatellite())
    .pause(2)
    .exec(getSatellite())

setUp(
    atOnceScn.inject(atOnceUsers(userCount))
  .protocols(httpProtocol.inferHtmlResources()))
}
