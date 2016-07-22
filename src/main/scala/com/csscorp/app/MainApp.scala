package com.csscorp.app

/**
  * Created by Nag Arvind Gudiseva on 21-July-2016.
  */
object MainApp extends App {
  println("MainApp")

  val text = TribuneUtils.getTribuneHeadlines
  println(text)

}
