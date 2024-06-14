package kafka

object Example extends App {

  import cats.Traverse

  def palindromeCheck(value: String): Boolean =
    if (value.reverse == value) true else false

  def maxProduct(list: List[Int]) =
    list.flatMap(num => list.map(nu => num * nu)).max

  def maxProduct3(li: List[Int]) =
    li.map(x => x * x).max

  // println(maxProduct(List(5,3,2,5,7,0)))

  println(maxProduct3(List(5, 3, 2, 5, 7, 0)))

}
