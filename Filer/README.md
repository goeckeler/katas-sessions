# Codes faster, runs faster, easier to adapt ...

+ Language: Java
+ Level: Easy
+ Requires: Java 1.8+, Maven (Gradle will be provided later), IDE recommended

## Objective

Done file copies in Java? Yes? Probably a standard task. But ever thought why you have to code it again and again? Or don't you? In this session we will try to code it in the most common styles and check which version is faster or easier to code, which code performs best in some given scenarios, and which version can be easier adapted to copy files a bit differently as used often nowadays.

## Summary

Algorithm | Time to code | Copies small files | Copies large files | Change to URL | Download files
----------|-------------:|-------------------:|-------------------:|--------------:|--------------------:
Reader | | | | |
Stream | | | | |
Channel | | | | |
Files | | | | |
FileUtils | | | | |
Other | | | | |
 
## Coding

There is an empty project to be cloned available on [https://github/goeckeler/katas](https://github/goeckeler/katas). The kata in questions is under `/filer/kata`. Check out and `mvn` in the root directory.

The main package is "`filer`". There is a prepared main class and a pseudo test class available. The implementations for the different algorithms are supposed to be coded in the given packages. Simply inherit from the given "`Filer`" interface.

If you opt for the `FileReader` implementation for example you could name your implementation `filer.reader.ReaderFiler`. If more than one pair opts for the same implementation feel free to name it whatever you like e.g. `WickedReaderFiler`.

In the end we will deploy the jar to the same server running the same scenarios for all implementations. The command line interface is pretty easy:

`java -jar filer-2.0.jar [--implementation=reader] <sourceDirectory> <targetDirectory>`

In order to submit your solution you must build it with `mvn -P submit`. This will create a self-contained jar with all dependencies and create an archive `filer-2.0.zip` that you are supposed to provide. 