name := """play-java-seed"""
organization := "com.example"

version := "1.0.0"

// general package information (can be scoped to Windows)
maintainer := "mrzhqiang"
packageSummary := "randall-server"
packageDescription := """Base on Play framework"""

// wix build information
wixProductId := "ce07be71-510d-414a-92d4-dff476318481"
wixProductUpgradeId := "4552fb0e-e257-4dbd-9ecb-dba9dbacf422"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  guice,
  "mysql" % "mysql-connector-java" % "5.1.41",
  ehcache,
  jcache,
  ws,
  "redis.clients" % "jedis" % "2.8.1",
)