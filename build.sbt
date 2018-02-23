name := """play-java-seed"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  guice,
  javaJpa,
  "mysql" % "mysql-connector-java" % "5.1.41",
)

lazy val myProject = (project in file("."))
  .enablePlugins(PlayJava, PlayEbean)

// 这里不是必须的配置，只是为了检验PLAY文档中的说法是否正确

// 这里是说，项目可能有多个子目录，可以这样设置用来产生关联
playEbeanModels in Compile := Seq("models.*")
// 这里是调试日志的深度，最多设为9
playEbeanDebugLevel := 4