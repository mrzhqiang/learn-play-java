name := """play-java-seed"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  guice,
  "mysql" % "mysql-connector-java" % "5.1.41",
  ehcache,
  jcache,
  ws,
)