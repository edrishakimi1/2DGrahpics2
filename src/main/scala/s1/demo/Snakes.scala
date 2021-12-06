package s1.demo
import s1.image.ImageExtensions._

import java.awt.Color._
import java.awt.BasicStroke
import scala.collection.mutable
import scala.util.Random

/**
 * This simple effect features a bouncing ball
 */
object BouncingBall extends Effect(500, 500){

  // The ball has coordinates and speed
  class Rectangle(val x: Int, val y:Int,nimi: String,  val xSpeed: Int, val ySpeed: Int)

  // ...and here is our initial instance
  var ball = new Rectangle(100, 100,"Samsung", 1, 2
  )

  val random = new Random
  var clock  = 1

  /**
   * Here we draw a BufferedImage on the current state of the [[Effect]]
   */
  def makePic() = {
    // Get an empty space where to draw
    val pic      = emptyImage

    // Get the tools to draw with
    val graphics = pic.graphics

    // We can pick a random color
    val randomColor = new java.awt.Color(random.nextInt(2))

    // And then use it to fill an oval)
    val radius =  mutable.Buffer[Int](1,2,3,4,5,6,8)

    for (i <- radius){

      if(i ==1){
    graphics.setColor(randomColor)
    graphics.fillOval(0, 0, 70, 70)
    }
      else if (i==2)
        if(i ==1)
    graphics.setColor(randomColor)
    graphics.fillOval(0, 0, 70, 70)
    }
    // Finally we return the picture we created.
    pic
  }

  /**
   * Here we modify the state (the position and speed of the ball)
   */
  def changeThings() = {
    clock += 1

  }
    /*
    // We could have done this with a ball with it's coordinates in var's
    // It can also be done in a more functional way, replacing the ball
    // itself
    ball = new Rectangle(nextX, nextY,"Samsung", nextXSpeed, nextYSpeed)

     */



      def next = clock > 300
}