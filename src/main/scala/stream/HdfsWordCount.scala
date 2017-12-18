package stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * Counts words in new text files created in the given directory
 * Usage: HdfsWordCount <directory>
 *   <directory> is the directory that Spark Streaming will use to find and read new text files.
 *
 * To run this on your local machine on directory `localdir`, run this example
 *    $ bin/run-example \
 *       org.apache.spark.examples.streaming.HdfsWordCount localdir
 *
 * Then create a text file in `localdir` and the words in the file will get counted.
 */
object HdfsWordCount {

  def main(args: Array[String]) {
    //val args = Array("D:\\workspace\\idea\\spark-test\\data")//z这里是目录不是文件
    if (args.length < 1) {
      System.err.println("Usage: HdfsWordCount <directory>")
      System.exit(1)
    }

    StreamingExamples.setStreamingLogLevels()
    val sparkConf = new SparkConf().setAppName("sadsadas").setMaster("local")
    // Create the context
    val ssc = new StreamingContext(sparkConf, Seconds(10))

    // Create the FileInputDStream on the directory and use the
    // stream to count words in new files created
    val lines = ssc.textFileStream(args(0))
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
    print("!!!!!!!!!!!!!!!!!!!!!!\n")
    wordCounts.print()
    print("!!!!!!!!!!!!!!!!!!!!!!\n")
    ssc.start()
    ssc.awaitTermination()
  }
}
