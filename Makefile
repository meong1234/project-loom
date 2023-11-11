
compile:
	./gradlew clean build

run-thread-overflow:
	java -jar thread-overflow/build/libs/thread-overflow-all.jar

run-thread-pool:
	java -jar thread-pool/build/libs/thread-pool-all.jar

run-init-virtual-thread:
	java -jar init-virtual-thread/build/libs/init-virtual-thread-all.jar

run-massive-virtual-thread:
	java -jar massive-virtual-thread/build/libs/massive-virtual-thread-all.jar

run-cpu-intensive-virtual-thread:
	java -jar cpu-intensive-virtual-thread/build/libs/cpu-intensive-virtual-thread-all.jar

run-pinned-virtual-thread:
	java -jar pinned-virtual-thread/build/libs/pinned-virtual-thread-all.jar

run-exception-virtual-thread:
	java -jar exception-virtual-thread/build/libs/exception-virtual-thread-all.jar