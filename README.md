bayes-dota
==========

## Solution

Because of the time limitation, I decided to focus on the core 
of the challenge, which is being able to process the match logs, persist
and retrieve them.

I have chosen to use a Chain of Responsibility design pattern
for ingesting and handling the match log payload.

[MatchProcessor](src/main/java/gg/bayes/challenge/service/MatchProcessor.java)
is responsible for setting up the flow of the chain and orchestrating
the processing of the match logs.

In the [parser](src/main/java/gg/bayes/challenge/parser) directory
contains the interface and a few implementations for the parsers 
of events and any new event type can be handled just 
by implementing the interface and configuring it in the Processor
mentioned above.
 
## Project Structure:
* [entity](src/main/java/gg/bayes/challenge/entity): Object domains representing database tables
* [parser](src/main/java/gg/bayes/challenge/parser): Chain of Responsibility implemented for processing the match logs 
* [repository](src/main/java/gg/bayes/challenge/repository): Spring Data comunication with the database
* [rest](src/main/java/gg/bayes/challenge/rest): API controller and DTOs
* [service](src/main/java/gg/bayes/challenge/service): Business logic
* [util](src/main/java/gg/bayes/challenge/util): Util classes

## Design Patterns
- Chain of Responsibility
- SOLID Principals

## Improvements
* Use Spring Batch to process the combat log files
* Analyze a better DB structure to store the events
* Store the patterns in the application config
* Exception Handling
* Better logs
* Integration tests and better test coverage overall
* Use a better project architecture (Clean Architecture)