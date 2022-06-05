package io.gatling
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.Predef.Simulation
import io.gatling.http.HeaderNames.ContentType
import io.gatling.http.HeaderValues.ApplicationJson

import scala.util.Properties

abstract  class BaseScenario extends Simulation {

  def httpProtocol = http.baseUrl("http://localhost:8080")

  def createSatellite() = {
    exec(
      http("Create a new satellite")
        .post("/satellite")
        .header(ContentType, ApplicationJson)
        .body(StringBody("""{ "name": "sampleName", "latitude":-74, "longitude":40 }"""))
        .check(status.is(200))
        .check(jsonPath("$.id").saveAs("id"))
    )
  }

  def getSatellite() = {
    exec(
      http("Get a new satellite")
        .get("/")
        .check(status.is(200))
    )
  }

  def updateSatellite() = {
    exec(
      http("Update a satellite")
        .put("/satellite/${id}")
        .header(ContentType, ApplicationJson)
        .body(StringBody("""{ "name": "sampleName_Updated", "latitude":-70, "longitude":20 }"""))
        .check(status.is(200))
    )
  }

  def deleteSatellite() = {
    exec(
      http("Delete a satellite")
        .delete("/satellite/${id}")
        .header(ContentType, ApplicationJson)
        .check(status.is(200))
    )
  }

  val userCount: Int = Properties.propOrElse("userCount", "20").toInt
  val timeDuration: Int = Properties.propOrElse("timeDuration", "10").toInt
}
