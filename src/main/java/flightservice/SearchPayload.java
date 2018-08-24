package flightservice;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class SearchPayload implements Callable{

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		String flightName = eventContext.getMessage().getInvocationProperty("fName");
		String flightNumber =  eventContext.getMessage().getInvocationProperty("fNo");
		//String sortBy = eventContext.getMessage().getInvocationProperty("sortBy");
		String source = eventContext.getMessage().getInvocationProperty("src");
		String destination = eventContext.getMessage().getInvocationProperty("dst");
		System.out.println("flightName  :: " +flightName + "  flightNumber::: " +flightNumber);
		String query = "SELECT * FROM Flights_Data Where SourceLocation="+source+" and Destination="+destination;
		// SELECT * FROM Flights_Data Where SourceLocation=#[flowVars.src] and Destination=#[flowVars.dst]
		String payload = "";
		/*boolean isLowtoHigh = false;
		if(sortBy == null || sortBy.equalsIgnoreCase("lowPrice"))
			isLowtoHigh = true;*/
		if(flightName == null && flightNumber == null)
			payload = query;
		else if(flightName != null && flightNumber != null)
			payload = (new StringBuilder(query).append(" and FlightName="+flightName).append(" and FlightNumber="+flightNumber).toString());
		else if(flightName != null)
			payload = (new StringBuilder(query).append(" and FlightName="+flightName).toString());
		else if(flightNumber != null)
			payload = (new StringBuilder(query).append(" and FlightNumber="+flightNumber).toString());
		System.out.println("payload :::: "+payload);
		/*if(isLowtoHigh)
			payload = (new StringBuilder(payload).append(" order by Price ASC").toString());
		else
			payload = (new StringBuilder(payload).append(" order by Price DESC").toString());
		System.out.println("Final payload :::: "+payload);*/
		eventContext.getMessage().setPayload(payload);
		return eventContext.getMessage().getPayload();
	}
}
