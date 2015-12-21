Requirements

Java JDK (http://www.oracle.com/technetwork/java/javase/downloads/index.html)
Maven (https://maven.apache.org/)

Usage:

To build the application navigate to the root directory (where this file is) and run 'mvn clean install'

To run (using an external test website):
    java -jar target/image-downloader.jar http://adambarnes5000.weebly.com downloads

This will create a new folder 'downloads' which will be populated with pictures of a dog, a cat and a monkey
in the original size/format, and resized to 100, 220, 320 width in jpg and png formats.

Design Summary

The application is built using Spring/Spring Integration. This decision was made to take advantage of some components offered
out of the box by Spring Integration notably Splitter, Filter and Aggregator and also simple integration of multithreading.
This is implemented by using a splitter to go from a webpage --> list of image urls, each image becomes a new payload.
The image payloads are then placed on a channel and dispatched using a task executor (configured with a pool of 20 threads).
The messages are then run through 2 filters, one filtering out previously downloaded and unchanged images, the other images of insufficient size.
The remaining messages are then resized and converted to various formats and saved to disk.
The messages (both succesful and filtered) are then aggregated and a number indicating how many images were downloaded is returned to the main class.

In terms of other possible approaches, an alternative would be to implement from scratch. This would have meant much more boilerplate code
particularly around multithreading, this was not needed in my version leaving me free to concentrate on business logic.

Besides the Spring the following libraries were also used:

Scalr (http://www.thebuzzmedia.com/software/imgscalr-java-image-scaling-library/) Used for resizing images
Jsoup (http://jsoup.org) For parsing web pages
Spock (https://code.google.com/p/spock/) Groovy unit and integration tests