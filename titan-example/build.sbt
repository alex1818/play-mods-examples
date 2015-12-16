name := "titan-example"

version := "1.0.0-SNAPSHOT"

lazy val root = project in file(".") enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  "me.tfeng.play-mods" % "spring" % "0.6.0",
  "me.tfeng.toolbox" % "titan" % "0.6.0",
  "org.hamcrest" % "hamcrest-all" % "1.3" % "test",
  javaWs % "test"
)

routesGenerator := InjectedRoutesGenerator
