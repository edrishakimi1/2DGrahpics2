package s1.demo
import s1.image.ImageExtensions._
import java.awt.Color._
import java.awt.BasicStroke
import scala.util.Random

/**
 * This simple effect features a bouncing ball
 */
object Epilepse extends Effect(500, 500){

  // The ball has coordinates and speed
  class Ball(val x: Int, val y:Int, val width: Int)
  class Recta


  var pic      = emptyImage
  // ...and here is our initial instance
  var ball = new Ball(100, 100, 400)
  
  val random = new Random
  var clock  = 0
  
  /**
   * Here we draw a BufferedImage on the current state of the [[Effect]]
   */
  var speed = 0.0

  def makePic() = {
    // Get an empty space where to draw 

    
    // Get the tools to draw with
    val graphics = pic.graphics

    // We can pick a random color

    // And then use it to fill an oval
    if (clock % 2 == 0)
    graphics.setColor(java.awt.Color.WHITE)
    else graphics.setColor(java.awt.Color.BLACK)
    if (clock % 2 == 0) graphics.fillOval(100, 100, ball.width, ball.width)
    else graphics.fillRect(100, 100, ball.width, ball.width)
      
    // Finally we return the picture we created.
    pic
  }
  
  /**
   * Here we modify the state (the position and speed of the ball)
   */
  def changeThings() = {
    clock += 1


    var newRadius = 200

    if (clock < 100) {
      speed += 0.1
      newRadius = ball.width - (speed).toInt

    } else {
      speed += 0.1
      newRadius = ball.width + (speed + 3.0).toInt

    }


    
   /* val y = ball.y + ball.ySpeed
    val x = ball.x + ball.xSpeed
    
    val (nextY, nextYSpeed) = 
      if ( y > height) {
        (height - (y-height), -ball.ySpeed)
      } else if (y < 0)
        (-y, -ball.ySpeed)
      else
        (y, ball.ySpeed)
    
    val (nextX, nextXSpeed) = 
      if ( x > width) {
        (width - (x-width), -ball.xSpeed)
      } else if (x < 0)
        (-x, -ball.xSpeed)
      else
        (x, ball.xSpeed) */
    
    // We could have done this with a ball with it's coordinates in var's
    // It can also be done in a more functional way, replacing the ball
    // itself
    ball = new Ball(100, 100, newRadius)
    
  }

  
  def next = clock > 300
}