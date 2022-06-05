package io.gatling
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PostAndPutWithPauseScn extends BaseScenario {

  val constantConcScn = scenario("POST_and_PUT_withPauseScenario")
    .exec(createSatellite())
    .pause(2)
    .exec(updateSatellite())
    .pause(2)
    .exec(createSatellite())
    .pause(2)
    .exec(updateSatellite())

  setUp(
    constantConcScn.inject(
    nothingFor(10),
    atOnceUsers(10),
    rampUsersPerSec(10).to(20).during(10))
      .protocols(httpProtocol.inferHtmlResources())
  )
}
