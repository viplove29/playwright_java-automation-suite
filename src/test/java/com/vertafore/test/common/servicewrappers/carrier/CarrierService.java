package com.vertafore.test.common.servicewrappers.carrier;

import com.vertafore.test.common.models.services.carrier.AppointmentV1;
import com.vertafore.test.common.util.ServiceUtils;
import io.restassured.response.Response;

import java.io.IOException;

public class CarrierService {

    private ServiceUtils serviceUtils;

    private final String POST_APPOINTMENT = "/appointments";

    public CarrierService() throws IOException {
        serviceUtils = new ServiceUtils();
    }

    public AppointmentV1 postAppointment(AppointmentV1 requestBody){
        Response response = serviceUtils.sendGetRequest(POST_APPOINTMENT);
        return response.getBody().jsonPath().getObject("content", AppointmentV1.class);
    }

}
