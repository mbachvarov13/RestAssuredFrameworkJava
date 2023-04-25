package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        if (StepDef.placeId == null) {
            StepDef sd = new StepDef();
            sd.createPlacePayload("TestHookName", "TestHookAddress", "TestHookLanguage");
            sd.createHttpRequest("addPlaceAPI", "POST");
            sd.checkName("TestHookName", "getPlaceAPI");
        }
    }
}

