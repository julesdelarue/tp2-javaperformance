/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package fr.polytechtours.javaperformance.tp2;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@Warmup(iterations = 2)
public class MyBenchmark {

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public String testStringConcat1() {
        String res = "";
        for(int i =0; i < 1000; i++){
            res += i;
        }
        return res;
    }
    @Benchmark
    @Fork(value = 1, warmups = 1)
    public String testStringConcat2() {
       StringBuilder b = new StringBuilder();
        for(int i =0; i < 1000; i++){
            b.append(i);
        }
        return b.toString();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}

/**
 * Output
 *
 * "D:\Polytech\Java Performance\corretto-11.0.17\bin\java.exe" "-javaagent:D:\Tools\IntelliJ IDEA Community Edition 2021.2.3\lib\idea_rt.jar=53651:D:\Tools\IntelliJ IDEA Community Edition 2021.2.3\bin" -Dfile.encoding=UTF-8 -classpath "D:\Polytech\Java Performance\tp02\jmh\target\classes;C:\Users\jdelarue\.m2\repository\org\openjdk\jmh\jmh-core\1.35\jmh-core-1.35.jar;C:\Users\jdelarue\.m2\repository\net\sf\jopt-simple\jopt-simple\5.0.4\jopt-simple-5.0.4.jar;C:\Users\jdelarue\.m2\repository\org\apache\commons\commons-math3\3.2\commons-math3-3.2.jar" fr.polytechtours.javaperformance.tp2.MyBenchmark
 * # JMH version: 1.35
 * # VM version: JDK 11.0.17, OpenJDK 64-Bit Server VM, 11.0.17+8-LTS
 * # VM invoker: D:\Polytech\Java Performance\corretto-11.0.17\bin\java.exe
 * # VM options: -javaagent:D:\Tools\IntelliJ IDEA Community Edition 2021.2.3\lib\idea_rt.jar=53651:D:\Tools\IntelliJ IDEA Community Edition 2021.2.3\bin -Dfile.encoding=UTF-8
 * # Blackhole mode: full + dont-inline hint (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
 * # Warmup: 2 iterations, 10 s each
 * # Measurement: 5 iterations, 10 s each
 * # Timeout: 10 min per iteration
 * # Threads: 1 thread, will synchronize iterations
 * # Benchmark mode: Throughput, ops/time
 * # Benchmark: fr.polytechtours.javaperformance.tp2.MyBenchmark.testStringConcat1
 *
 * # Run progress: 0,00% complete, ETA 00:04:40
 * # Warmup Fork: 1 of 1
 * # Warmup Iteration   1: 4399,814 ops/s
 * # Warmup Iteration   2: 4511,987 ops/s
 * Iteration   1: 4070,804 ops/s
 * Iteration   2: 4463,157 ops/s
 * Iteration   3: 4714,124 ops/s
 * Iteration   4: 5031,786 ops/s
 * Iteration   5: 5038,661 ops/s
 *
 * # Run progress: 25,00% complete, ETA 00:03:34
 * # Fork: 1 of 1
 * # Warmup Iteration   1: 4954,535 ops/s
 * # Warmup Iteration   2: 5145,912 ops/s
 * Iteration   1: 5149,997 ops/s
 * Iteration   2: 5169,309 ops/s
 * Iteration   3: 5094,723 ops/s
 * Iteration   4: 5043,314 ops/s
 * Iteration   5: 5158,607 ops/s
 *
 *
 * Result "fr.polytechtours.javaperformance.tp2.MyBenchmark.testStringConcat1":
 *   5123,190 ±(99.9%) 204,579 ops/s [Average]
 *   (min, avg, max) = (5043,314, 5123,190, 5169,309), stdev = 53,129
 *   CI (99.9%): [4918,611, 5327,769] (assumes normal distribution)
 *
 *
 * # JMH version: 1.35
 * # VM version: JDK 11.0.17, OpenJDK 64-Bit Server VM, 11.0.17+8-LTS
 * # VM invoker: D:\Polytech\Java Performance\corretto-11.0.17\bin\java.exe
 * # VM options: -javaagent:D:\Tools\IntelliJ IDEA Community Edition 2021.2.3\lib\idea_rt.jar=53651:D:\Tools\IntelliJ IDEA Community Edition 2021.2.3\bin -Dfile.encoding=UTF-8
 * # Blackhole mode: full + dont-inline hint (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
 * # Warmup: 2 iterations, 10 s each
 * # Measurement: 5 iterations, 10 s each
 * # Timeout: 10 min per iteration
 * # Threads: 1 thread, will synchronize iterations
 * # Benchmark mode: Throughput, ops/time
 * # Benchmark: fr.polytechtours.javaperformance.tp2.MyBenchmark.testStringConcat2
 *
 * # Run progress: 50,00% complete, ETA 00:02:22
 * # Warmup Fork: 1 of 1
 * # Warmup Iteration   1: 77909,990 ops/s
 * # Warmup Iteration   2: 84904,845 ops/s
 * Iteration   1: 80385,808 ops/s
 * Iteration   2: 76418,961 ops/s
 * Iteration   3: 81107,093 ops/s
 * Iteration   4: 80717,757 ops/s
 * Iteration   5: 75075,041 ops/s
 *
 * # Run progress: 75,00% complete, ETA 00:01:11
 * # Fork: 1 of 1
 * # Warmup Iteration   1: 84635,538 ops/s
 * # Warmup Iteration   2: 85646,910 ops/s
 * Iteration   1: 78523,515 ops/s
 * Iteration   2: 80850,027 ops/s
 * Iteration   3: 81981,913 ops/s
 * Iteration   4: 80822,934 ops/s
 * Iteration   5: 81336,513 ops/s
 *
 *
 * Result "fr.polytechtours.javaperformance.tp2.MyBenchmark.testStringConcat2":
 *   80702,980 ±(99.9%) 5029,151 ops/s [Average]
 *   (min, avg, max) = (78523,515, 80702,980, 81981,913), stdev = 1306,054
 *   CI (99.9%): [75673,830, 85732,131] (assumes normal distribution)
 *
 *
 * # Run complete. Total time: 00:04:44
 *
 * REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
 * why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
 * experiments, perform baseline and negative tests that provide experimental control, make sure
 * the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
 * Do not assume the numbers tell you what you want them to tell.
 *
 * Benchmark                       Mode  Cnt      Score      Error  Units
 * MyBenchmark.testStringConcat1  thrpt    5   5123,190 ±  204,579  ops/s
 * MyBenchmark.testStringConcat2  thrpt    5  80702,980 ± 5029,151  ops/s
 *
 * Process finished with exit code 0
 */
