package main

import commands.Command
import filesystem.{ Dir, State }
import java.util.Scanner


object FileSystem extends App {

  println("this is fantastic virtual filesystem that you can use creatively")

  val firstRoot = Dir.newRoot

  // mutate pointer for current state
  var state = State(firstRoot, firstRoot)

  val scanner = new Scanner(System.in)

  // main infinite loop
  while(true) {
    state.show
    state = Command.from(scanner.nextLine()).apply(state)
  }
}
