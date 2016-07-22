package com.csscorp.app

import scala.collection.mutable.StringBuilder
import org.htmlcleaner.HtmlCleaner
import java.net.URL

import com.csscorp.util.Config

import scala.collection.mutable.ListBuffer
import org.apache.commons.lang3.StringEscapeUtils

/**
  * Created by Nag Arvind Gudiseva on 21-July-2016.
  */
object TribuneUtils {

  def getTribuneHeadlines: String = {
    try {
      val url = Config.url
      val stories = getHeadlinesFromUrlNew(url)
      val articles = getUniqueArticles(stories)
      val sentence = buildSentenceFromHeadlines(articles)
      sentence
    } catch {
      case e:  Exception => println("(TribuneHeadlines) Caught a plain Exception: " + e.printStackTrace())
        return "Sorry, I had a problem checking the headlines. Error 1."
      case unknown: Throwable => println("(EmailClient) Caught an unknown problem/exception." + unknown.printStackTrace())
        return "Sorry, I had a problem checking the headlines. Error 2."
    }
  }

  private def getHeadlinesFromUrlNew(url: String): List[String] = {
    var stories = new ListBuffer[String]
    val cleaner = new HtmlCleaner
    val props = cleaner.getProperties
    val rootNode = cleaner.clean(new URL(url))
    val elements = rootNode.getElementsByName("h2", true)
    for (elem <- elements) {
      //println("elem: " + elem)
      val classType = elem.getAttributeByName("class")
      //println("classType: " + classType)
      if (classType != null && classType.contains("trb_outfit_outfitTitle")) {
        val text = StringEscapeUtils.unescapeHtml4(elem.getText.toString)
        println("Text: " + text)
        stories += text
      }
    }

    // stories might be "dirty" with text like "&#039;", clean it up
    return stories.filter(storyContainsDesiredPhrase(_)).toList
  }

  private def getUniqueArticles(articles: List[String]): Set[String] = {
    return articles.toSet
  }

  private def buildSentenceFromHeadlines(headlines: Set[String]): String = {
    var sb = new StringBuilder
    for (h <- headlines) {
      sb.append(h + ". ")
    }
    sb.toString
  }

  private def storyContainsDesiredPhrase(s: String): Boolean = {

    val topics = (Config.topics).split(", ").toList

    for (t <- topics) {
      if (s.contains(t)) return true
    }
    false
  }

}