ThisBuild / version      := "0.1.1"
ThisBuild / scalaVersion := "2.13.10"
name                     := "fs2-kafka-App"
val Logback           = "1.2.11"
val http4sVersion     = "0.23.11"
val circeVersion      = "0.14.1"
val cirisVersion      = "2.3.2"
val catsEffectVersion = "3.3.11"
val fs2KafkaVersion   = "3.5.1"

val httpDependencies = Seq(
  "org.http4s" %% "http4s-ember-server" % http4sVersion,
  "org.http4s" %% "http4s-dsl"          % http4sVersion,
  "org.http4s" %% "http4s-circe"        % http4sVersion
)

val loggingDependencies = Seq(
  "ch.qos.logback" % "logback-classic" % Logback
)

val jsonDependencies = Seq(
  "io.circe" %% "circe-core"    % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion
)

val catsEffectDependencies = Seq(
  "org.typelevel" %% "cats-effect" % catsEffectVersion
)

val cirisDependencies = Seq(
  "is.cir" %% "ciris" % cirisVersion
)

val fs2KafkaDependencies = Seq(
  "com.github.fd4s" %% "fs2-kafka" % fs2KafkaVersion
)

lazy val backend = (project in file(".")).settings(
  libraryDependencies ++= jsonDependencies ++ loggingDependencies
    ++ httpDependencies ++ cirisDependencies ++ fs2KafkaDependencies ++ catsEffectDependencies
)
