package com.phone.bdd.steps;

import com.phone.bdd.config.TestConfig;
import com.phone.bdd.service.APIService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@CucumberContextConfiguration
@SpringBootTest
@ContextConfiguration(classes = { TestConfig.class })
public class PhoneSteps {

    @Autowired
    private APIService apiService;

    private List<Map<String, Object>> phones;
    private Map<String, Object> lowestCostPhone;

    @Given("I have a list of phones")
    public void i_have_a_list_of_phones() {
        phones = apiService.getAllPhones();
    }

    @When("I filter phones with names starting with {string}")
    public void i_filter_phones_with_names_starting_with(String prefix) {
        phones.removeIf(phone -> !((String) phone.get("name")).startsWith(prefix));
    }

    @Then("I should get all phones with names starting with {string}")
    public void i_should_get_all_phones_with_names_starting_with(String prefix) {
        assertTrue(phones.stream().allMatch(phone -> ((String) phone.get("name")).startsWith(prefix)));
    }

    @When("I get the phone with the lowest cost")
    public void i_get_the_phone_with_the_lowest_cost() {
        lowestCostPhone = apiService.getPhoneWithLowestCost();
    }

    @Then("The phone with the lowest cost should be valid")
    public void the_phone_with_the_lowest_cost_should_be_valid() {
        if (lowestCostPhone == null) {
            System.out.println("No phone with a valid cost found.");
        } else {
            assertNotNull("Phone with lowest cost should not be null", lowestCostPhone);
        }

        // from api response test data we know the lowest price item is Airpods and the price is 120
        assert(lowestCostPhone.get("name").equals("Apple AirPods"));
        Map data = (Map<String, String>)lowestCostPhone.get("data");
        assert(data.get("price").equals(120));
    }

    @Then("The response ID should not be null")
    public void the_response_id_should_not_be_null() {
        if (lowestCostPhone == null) {
            throw new AssertionError("Phone with lowest cost is null.");
        }
        assertNotNull("ID should not be null", lowestCostPhone.get("id"));
    }
}
