name := "ScalaWebScrapper"

version := "1.0"

scalaVersion := "2.10.3"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "oracle driver repo" at "http://mirrors.ibiblio.org/maven2/"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.1.2",
  "com.typesafe" % "config" % "1.2.1",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5",
  "org.clapper" %% "grizzled-slf4j" % "1.0.2",
  "com.novocode" % "junit-interface" % "0.8" % "test->default",
  "org.jsoup" % "jsoup" % "1.8.3",
  "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.2",
  //"commons-lang" % "commons-lang" % "2.6",
  "org.apache.commons" % "commons-lang3" % "3.4"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-language:implicitConversions",
  "-language:higherKinds")

mainClass in assembly := some("com.csscorp.app.MainApp")
assemblyJarName := "web_scape.jar"

// put all libs in the lib_managed directory, that way we can distribute eclipse project files
retrieveManaged := true