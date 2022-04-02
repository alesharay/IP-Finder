package ipfinder;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {
    private IP ip;
    private String actualNetwork;
    private String actualBroadcast;
    private String actualHostMin;
    private String actualHostMax;
    private double actualHostCount;

    @Given("an ip address of {string}")
    public void an_ip_address_of(String ip) {
        String ipAddress = ip.split("/")[0];
        int CIDR = Integer.parseInt(ip.split("/")[1]);
        this.ip = new IP(ipAddress, CIDR);
    }

    @When("I request the Network address for the given IP")
    public void i_request_the_network_address_for_the_given_ip() {
        actualNetwork = ip.getNetwork();
    }

    @When("I request the available host count")
    public void i_request_the_available_host_count() {
        actualHostCount = ip.getHostCount();
    }

    @When("I request the Broadcast address for the given IP")
    public void i_request_the_broadcast_address_for_the_given_ip() {
        actualBroadcast = ip.getBroadcast();
    }

    @When("I request the Host min address for the given IP")
    public void i_request_the_host_min_address_for_the_given_ip() {
        actualHostMin = ip.getHostMin();
    }

    @When("I request the Host max address for the given IP")
    public void i_request_the_host_max_address_for_the_given_ip() {
        actualHostMax = ip.getHostMax();
    }

    @Then("the Network address should be {string}")
    public void the_network_address_should_be(String expectedAnswer) {
        assertEquals(expectedAnswer, actualNetwork);
    }

    @Then("the host count should be {double}")
    public void the_host_count_should_be(double expectedAnswer) {
        assertEquals(expectedAnswer, actualHostCount);
    }

    @Then("the Broadcast address should be {string}")
    public void the_broadcast_address_should_be(String expectedAnswer) {
        assertEquals(expectedAnswer, actualBroadcast);
    }

    @Then("the Host min address should be {string}")
    public void the_host_min_address_should_be(String expectedAnswer) {
        assertEquals(expectedAnswer, actualHostMin);
    }

    @Then("the Host max address should be {string}")
    public void the_host_max_address_should_be(String expectedAnswer) {
        assertEquals(expectedAnswer, actualHostMax);
    }
}