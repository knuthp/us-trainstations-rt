package com.knuthp.microservices.trainstations.rt;

import com.knuthp.microservices.trainstations.rt.domain.RtDepartures;

public interface Publisher {
	void publishRtDepartures(RtDepartures rtDepartures);
}
