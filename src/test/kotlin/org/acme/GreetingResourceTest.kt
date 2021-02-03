package org.acme

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test

@QuarkusTest
class GreetingResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
            .`when`().get("/names")
            .then()
            .statusCode(200)
            .body(containsString("Sidney James Jordan-Regan"))
    }
}