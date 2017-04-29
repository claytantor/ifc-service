package com.crowcompass.takehome.resource;

import com.crowcompass.takehome.model.IFCDate;
import com.crowcompass.takehome.service.FirstService;
import com.crowcompass.takehome.service.InternationalFixedCalendarService;
import com.crowcompass.takehome.util.InternationalFixedCalendar;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by claytongraham on 10/25/15.
 */

@Component
@Path("/ifc")
public class InternationalFixedCalendarResource extends BaseResource {

    private static SimpleDateFormat COMMON_DATE_FORMAT  = new SimpleDateFormat("d MMM yyyy");

    @Inject
    InternationalFixedCalendarService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/date")
    public Response getIFCDate(@RequestBody Map<String,Object> model) {

        try {

            TimeZone tz_pacific = TimeZone.getTimeZone("America/Los_Angeles");
            InternationalFixedCalendar gcal = new InternationalFixedCalendar(tz_pacific);

            int gregorianYear = Integer.parseInt(model.get("gregorianYear").toString());
            int gregorianMonth = Integer.parseInt(model.get("gregorianMonth").toString());
            int gregorianDay = Integer.parseInt(model.get("gregorianDay").toString());
            gcal.set(gregorianYear,gregorianMonth,gregorianDay);

            model.put("formattedDate", COMMON_DATE_FORMAT.format(gcal.getTime()));
            model.put("dayOfYear", ""+gcal.get(Calendar.DAY_OF_YEAR));

            IFCDate ifcDate = gcal.getIFCDate();

            model.put("ifcDate", ifcDate);

            return Response.status(Status.OK).entity(buildJson(model)).build();

        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(getErrorJson()).build();
        }

    }

}