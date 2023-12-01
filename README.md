# Data Structures and Algorithms Assignment
Java assignment completed in COMP1002 : Data Structures and Algorithms unit  @ Curtin 2019

<h2>How to Run:</h2>
This project was split into four distinct parts: 

 - a linked list
 - a heap
 - a graph 
 - and a simulation of a social media network
 
Each section has a test harness that provides verification of the working code. Additionally, the social simulation can be ran manually. 
To set code in workng order run in termenal from appropriate workspace: 

```
javac *.java
```
The following subsections give a walkthough on how to run and verify each part:

<h3>Linked List</h3>
Run in terminal: 

```
java UnitTestLinkedList
```
Test results will be output to terminal.

<h3>Heap</h3>
Run in terminal: 

```
java UnitTestHeap
```
Test results will be output to terminal.

<h3>Graph</h3>
Run in terminal: 

```
java UnitTestDSAGraph
```
Test results will be output to terminal.
<h3>Social Simulation</h3>
 
 - <h3> Test Harness</h3>
Run in terminal: 

```
java UnitTestsimulation
```

Network will be loaded from `netfile.txt` and output to terminal. The network will also be saved to text file of format `yyyy.MM.dd.hh.mm.ss.txt`
 - <h3> Manual</h3>
For manual use, run the following in terminal: 
```
java SocialSim -i
```
A simple UI will appear in termianl and allow you to create and display your own netwrok.

> NOTE: Some functionality of the code is yet to be completed, hence some .java files contain additional unused code. 
 
