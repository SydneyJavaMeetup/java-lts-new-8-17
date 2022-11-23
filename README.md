# Sydney Java Meetup #11
https://www.meetup.com/sydney-java/events/288493646/

### Program
6pm - Networking/Drinks/Food
6:30pm - Event Intro and Job Shouts
6:35pm - What's new in Java LTS
Let's take a quick look at some of the awesome new features since Java 8, and hopefully inspire you to upgrade to 17 if you haven't already!
7:00pm - 15 minute break
7.15pm - Let's build components, not layers
Layers are often not the best way to organize code. Usually, a better approach to structure the code is to build components. Like lego bricks, we can compose multiple (vertical and horizontal) components to bigger and bigger components, like when building a car. We can zoom in and out of the codebase along the seams of the components to understand what we need to understand at the level of abstraction that we are currently concerned with.

In this talk, we’ll explore a lightweight way of structuring the codebase of a Spring Boot application that will feel like building lego. This component-based structure is easy to understand and reason about. It’s also easy to maintain over time thanks to a single rule that can be enforced by tooling. Java, Spring Boot, and ArchUnit bring all the tools we need for a nice component-based codebase, we just need to use them.

Tom Hombergs is a software engineer at Atlassian. When he's not coding in his day job, he likes to write about his learnings on his blog and newsletter at https://reflectoring.io.

8pm Event Finish

### Intro
Program

Quick acknowledgement of:
* Microsoft for hosting us!

Ethos, vibe
* openness
* inclusiveness
* learning
* no experts, we're a community of peers rather than a mixture of experts and novices
* supportiveness, we're here for each other

### Job Shouts
“Before we get into the tech talks, are there any job shoutouts?”


Meetup

# Java LTS 8->17
I wanted to have a quick chat about Java versions, because I read an article by New Relic showing the percentages of users on different versions:
https://newrelic.com/resources/report/2022-state-of-java-ecosystem#toc-java-11-is-the-new-standard 

It tells a story of a massive shift from Java 8 to 11 over the last 2 years, and a chunk of users having made it to 14 (records anyone?). 
My view is that Java 17 is a great place to be in production right now, and that’s what I’m running in my company. 
To try to convince you (if you’re not there yet - here are some great features in more recent versions I’m using and wouldn’t go without.
I recently tried writing some old Java 11 code for a Lambda PoC (which we could get into another night) but it felt so awful having to unwind my beautiful little Record based data objects into POJOs I quickly excluded Java 11 from the PoC.
On distros, I’m happy to see the rise of non-Oracle free support alternatives like AWS Corretto, which I use in most places (perhaps just because James Gosling is working there now :) ).

5 changes in descending order of importance (purely subjective - these are my favourites):

### 5: Java 9 - Modules + JLink
https://openjdk.org/jeps/282 
Personally I got put off using modules early on. This is because of the often frustrating need to declare dependencies, and deal with libraries that haven’t yet modularised themselves.

However, later on as I started to use containers more frequently, the facility to reduce the container size to the absolute minimum needed became more interesting.
 
You can specify which modules of the JDK you are using in your services, and use the JLink tool to create a custom JDK just for your distribution with only the modules you really need. 

I use a slimmed down version of the JDK produced with JLink for my services, and it’s worth noting that even if you don’t remove any modules from the JDK you can still build a much trimmed JDK using flags like these:
https://github.com/corretto/corretto-docker/blob/main/17/slim/alpine/Dockerfile 

### 4 (Java 10): Container Awareness + CPU/Memory limiter flags
JVMs are now aware of being run in a Docker container and will extract container-specific configuration instead of querying the operating system itself – it applies to data like the number of CPUs and total memory that have been allocated to the container.

Since Java 10, container limits are automatically recognised and enforced instead of the limits of the docker host. 

This also adds a JVM option that provides the ability to specify the number of CPUs that the JVM will use:
-XX:ActiveProcessorCount=count

Also, three new JVM options have been added to allow Docker container users to gain more fine-grained control over the amount of system memory that will be used for the Java Heap:
-XX:InitialRAMPercentage
-XX:MaxRAMPercentage
-XX:MinRAMPercentage

### 3 Little language improvements, a few of which I wouldn’t like to do without:
- (Java 14) Switch expressions
boolean isWeekend = switch (day) {
case SATURDAY, SUNDAY -> true;
default -> false;
};
- (Java 15) Multi-line strings (text blocks)
- String helper methods (Java 11) repeat, isBlank, strip, lines,  (Java 15) formatted, stripIndent, translateEscapes
- (Java 9) Unmodifiable Collection Helpers - List.of(a, b, c); Set.of(d, e, f, g); Map.of(k1, v1, k2, v2);

### 2 Performance
There have been significant performance enhancements over the course of these versions. Just changing from Java 8 to Java 17 without any other code changes (and I found relatively few changes to make) can give you a significant boost in performance for free! 
https://kstefanj.github.io/2021/11/24/gc-progress-8-17.html

### 1 (Java 14/16) Records
These are my favourite new language feature. 
I’ve long been a believer in immutability as a core fundamental of coding in concurrent environments. I think the majority of Java code runs in a multi-threaded or otherwise concurrent fashion, and inevitably has some shared state between threads. In these conditions you need immutability for the shared state in order to prevent bugs. 
Whenever I am creating state that could be shared, I prefer a record over a plain old Java object where possible.
They drastically reduce the probability of typos and simple copy-paste type mistakes in the massively bloated alternative POJO data class equivalents.
https://www.baeldung.com/java-record-keyword



