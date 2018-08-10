package flightservice;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class SeatValidation implements Callable{

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		int availableSeats = eventContext.getMessage().getInvocationProperty("availableSeats");
		int requestedSeats =Integer.parseInt( eventContext.getMessage().getInvocationProperty("requestedSeats"));
		int finalSeatCount = 0;
		System.out.println("availableSeats  ::  " +availableSeats + "  :: requestedSeats :::  "+requestedSeats);
		if(availableSeats >= requestedSeats){
			System.out.println("Allowed to Book! Requested seats are available!!!");
			finalSeatCount = availableSeats-requestedSeats;
			System.out.println("finalSeatCount ::: " +finalSeatCount);
			eventContext.getMessage().setInvocationProperty("finalSeatCount", finalSeatCount);
		}
		return null;
	}
	

}
