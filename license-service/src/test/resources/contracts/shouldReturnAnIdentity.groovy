import net.minidev.json.JSONObject
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return an Identity"
    request {
        url "/"
        method "GET"
    }
    response {
        status 200
        body("Hello World")
    }
}