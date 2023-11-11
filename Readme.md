## Project Loom Demonstration - JVM Meetup #59

### Introduction

Welcome to the Project Loom demonstration for JVM Meetup #59. This repository contains various Java applications designed to showcase the capabilities and features of Project Loom, a part of the Java Project aiming to simplify concurrency in the Java platform.

Project Loom introduces a new concept of virtual threads, offering a lightweight and efficient way of handling concurrency in Java applications. In this demonstration, we will explore different scenarios where virtual threads can be utilized, ranging from simple initialization to CPU-intensive tasks.


### Folder Structure
The repository is organized into the following structure:

```shell
├── Makefile
├── Readme.md
├── cpu-intensive-virtual-thread
├── exception-virtual-thread
├── init-virtual-thread
├── massive-virtual-thread
├── pinned-virtual-thread
├── thread-overflow
└── thread-pool
```

### Prerequisites

Each subdirectory contains a specific Java application demonstrating a unique aspect of virtual threads.

Prerequisites
* Java JDK 21 or higher
* Gradle 8.4 or higher

### Commands
#### Building the Project
To compile all modules, use the following command:
```shell
make compile
```

#### Running the Examples

* Thread Overflow Example:
```shell
make run-thread-overflow
```

* Thread Pool Example:
```shell
make run-thread-pool
```

* Initializing Virtual Thread Example:
```shell
make run-init-virtual-thread
```

* Massive Virtual Thread Example:
```shell
make run-massive-virtual-thread
```

* CPU Intensive Virtual Thread Example:
```shell
make run-pinned-virtual-thread
```

* run-exception-virtual-thread
```shell
make run-exception-virtual-thread
```