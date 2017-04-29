# International Fixed Calendar MicroService
The International Fixed Calendar is a jersy/spring implementation of an International Fixed Calendar. It provides a 
gradle build to a shadow jar which can be deployed as a runnable jar.

## The Problem
The _International Fixed Calendar_ is a **totally real** calendar that has 13 months, each with 28 days. It synchronizes with the Gregorian calendar that most of us know and some of us love by sharing the same January 1st.

The months of the IFC are mostly the same as what you're used to:

1. January
2. February
3. March
4. April
5. May
6. June
7. Sol
8. July
9. August
10. September
11. October
12. November
13. December

# The Problem

**NOTE** The original requirements on this exrecise were seriously problematic, mostly because date offsets
in the example didnt match the calendar api. This implementation normalizes the requirements.

We'd like to support the _International Fixed Calendar_ in Java. Please implement the following API:

    GregorianCalendar gregorianDate = new GregorianCalendar(2015, 0, 31);
    IFCDate ifcDate = new IFCDate(date);

    ifcDate.month
     #=> 1
    ifcDate.day
     #=> 3

Because the year still needs to have 365 days, "Year Day" is always inserted as the 29th day of December:

    GregorianCalendar gregorianDate = new GregorianCalendar(2015,11,31)
	IFCDate ifcDate = new IFCDate(date);

	ifc_date.month
	 #=> 12
	ifc_date.day
	 #=> 29
	ifc_date.year_day?
	 #=> true

Similarly, in leap years, "Leap Day" is inserted as the 29th day of June:

	GregorianCalendar gregorianDate = new GregorianCalendar(2016,5,17)
	IFCDate ifcDate = new IFCDate(date);

	ifc_date.month
 	#=> 5
	ifc_date.day
 	#=> 29
	ifc_date.leap_day?
 	#=> true
 	


# Runbook

## Building the microservice container
bootstrap project for quickly making a webservices based project that uses spring, provides support for single jar runnable app. For more information on shadow jars goto [http://imperceptiblethoughts.com/shadow/](The ShadowJar Docs)

```bash
$ ./gradlew clean build shadowJar
```

## Coburtura HTML Coverage Reports
Cobertura is used to generate coverage reports.

```bash
$ open build/reports/cobertura/index.html
```

## Running your microservice

```bash
$ java -jar ./build/libs/ifc-app-0.0.1-SNAPSHOT-all.jar
```

## Testing your IFC Service
```bash
$ curl -X POST \
    http://localhost:8080/rest/app/ifc/date \
    -H 'cache-control: no-cache' \
    -H 'content-type: application/json' \
    -d '{"gregorianYear":2015,"gregorianMonth":3,"gregorianDay":4}'
```

## Leap Day
```bash
$ curl -X POST \
  http://localhost:8080/rest/app/ifc/date \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{"gregorianYear":2016,"gregorianMonth":5,"gregorianDay":17}'
   	
   	
{
  "gregorianYear": 2016,
  "gregorianMonth": 5,
  "gregorianDay": 17,
  "formattedDate": "17 Jun 2016",
  "dayOfYear": "169",
  "ifcDate": {
    "year": 2016,
    "month": 5,
    "day": 29,
    "leapDay": true,
    "yearDay": false
  }
}
```

## Year Day
```bash
$ curl -X POST \
  http://localhost:8080/rest/app/ifc/date \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{"gregorianYear":2015,"gregorianMonth":11,"gregorianDay":31}'
  
 {
   "gregorianYear": 2015,
   "gregorianMonth": 11,
   "gregorianDay": 31,
   "formattedDate": "31 Dec 2015",
   "dayOfYear": "365",
   "ifcDate": {
     "year": 2015,
     "month": 12,
     "day": 29,
     "leapDay": false,
     "yearDay": true
   }
 } 
```




