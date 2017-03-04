name := """play-mybatis-guice"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,

  "org.mybatis"                         %     "mybatis"               %     "3.3.0",

  "org.mybatis"                         %     "mybatis-guice"         %     "3.6",

  "com.google.inject.extensions"        %     "guice-multibindings"   %     "4.0",

  "org.postgresql"                      %     "postgresql"            %     "9.4-1206-jdbc42"



)
