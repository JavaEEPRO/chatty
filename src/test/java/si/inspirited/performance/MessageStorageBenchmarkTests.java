package si.inspirited.performance;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import si.inspirited.persistence.model.Message;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 7)
public class MessageStorageBenchmarkTests {

    @Param({"10000"})
    private int N;

    private ConcurrentHashMap<String, Message> CONCURRENT_POSTED_MESSAGES;

    private HashMap<String, Message> SIMPLE_POSTED_MESSAGES;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MessageStorageBenchmarkTests.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        CONCURRENT_POSTED_MESSAGES = createConcurrentData();
        SIMPLE_POSTED_MESSAGES = createSimpleData();
    }

    @Benchmark
    public void iterateConcurrentForEach(Blackhole blackhole) {
        CONCURRENT_POSTED_MESSAGES.forEach((id, message) -> blackhole.consume(message));
    }

    @Benchmark
    public void iterateSimpleForEach(Blackhole blackhole) {
        SIMPLE_POSTED_MESSAGES.forEach((id, message) -> blackhole.consume(message));
    }

    @Benchmark
    public void iterateConcurrentFor(Blackhole blackhole) {
        for(Map.Entry<String, Message> entry : CONCURRENT_POSTED_MESSAGES.entrySet()) {
            blackhole.consume(entry.getValue());
        }
    }

    @Benchmark
    public void iterateSimpleFor(Blackhole blackhole) {
        for(Map.Entry<String, Message> entry : SIMPLE_POSTED_MESSAGES.entrySet()) {
            blackhole.consume(entry.getValue());
        }
    }

    //
    private ConcurrentHashMap<String, Message> createConcurrentData() {
         ConcurrentHashMap<String, Message> data = new ConcurrentHashMap<>(N);
        for (int i = 0; i < N; i++) {
            Message message = new Message("PerformanceTester","hello test, concurrent message-" + i);
            data.put(message.id, message);
        }
        return data;
    }

    private HashMap<String, Message> createSimpleData() {
        HashMap<String, Message> data = new HashMap<>(N);
        for (int i = 0; i < N; i++) {
            Message message = new Message("PerformanceTester","hello test, simple message-" + i);
            data.put(message.id, message);
        }
        return data;
    }
}
