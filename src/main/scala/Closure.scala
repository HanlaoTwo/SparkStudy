/**
  * Created by hanqian18790 on 2017/10/24.
  */
object Closure {
  def main(args: Array[String]): Unit = {
    val c = add(1)
    println(c)
  }

  val b = 10
  val add = (a: Int) => {
    b - a
  }
}
