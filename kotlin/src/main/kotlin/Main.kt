package io.andygrove.ballista

import org.apache.arrow.flight.*
import org.apache.arrow.memory.RootAllocator
import java.util.concurrent.TimeUnit

class Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val client = FlightClient.builder()
                    .allocator(RootAllocator(Long.MAX_VALUE))
                    .location(Location.forGrpcInsecure("localhost", 50051))
                    .build();

            val callOptions = CallOptions.timeout(5, TimeUnit.SECONDS)

            val stream = client.getStream(Ticket("SELECT foo FROM bar".toByteArray()), callOptions)

            while (stream.next()) {
                //TODO
            }
        }
    }
}