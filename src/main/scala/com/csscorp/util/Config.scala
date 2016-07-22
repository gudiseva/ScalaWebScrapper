package com.csscorp.util

import com.typesafe.config.ConfigFactory

/**
  * Created by sysadmin on 7/21/2016.
  */
object Config {

  /** Loads all key / value pairs from the application configuration file. */
  var conf = ConfigFactory.load("params.conf")

  /** Seed locations for which URLs are valid, example ca, nj etc. */
  lazy val url = conf.getString("TRIB_URL")
  lazy val topics = conf.getString("NEWS_TOPICS")

}
