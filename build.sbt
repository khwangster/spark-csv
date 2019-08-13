name := "spark-csv"

version := "2.0.3"

organization := "com.truex"

scalaVersion := "2.11.12"

//spName := "truex/spark-csv"

//crossScalaVersions := Seq("2.11.8")

//sparkVersion := "2.2.+"

val testSparkVersion = settingKey[String]("The version of Spark to test against.")

//testSparkVersion := sys.props.get("spark.testVersion").getOrElse(sparkVersion.value)

scalacOptions := Seq(
  "-target:jvm-1.8",
  "-deprecation",
  "-feature"
)

//sparkComponents := Seq("core", "sql")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.2.+" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.2.+" % "provided",
  "org.apache.commons" % "commons-csv" % "1.1",
  "com.univocity" % "univocity-parsers" % "2.6.+",
  "org.slf4j" % "slf4j-api" % "1.7.5" % "provided",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "com.novocode" % "junit-interface" % "0.9" % "test"
)

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % scalaVersion.value % "compile"
)

resolvers ++= Seq[Resolver](
  "Will's bintray" at "https://dl.bintray.com/willb/maven/",
  s3resolver.value("Releases resolver", s3("bin.truex.com/releases/")) withIvyPatterns,
  s3resolver.value("Snapshots resolver", s3("bin.truex.com/snapshots/")) withIvyPatterns
)

publishMavenStyle := false

s3overwrite := true

publishTo := {
  val prefix = // if (sys.env.get("TRAVIS_TAG").nonEmpty) {
    "releases"
//  } else {
//    "snapshots"
//  }

  Some(s3resolver.value(s"true[x] S3 binary repo", s3(s"bin.truex.com/${prefix}/")) withIvyPatterns)
}

// This is necessary because of how we explicitly specify Spark dependencies
// for tests rather than using the sbt-spark-package plugin to provide them.
//spIgnoreProvided := true

//publishMavenStyle := true

//spAppendScalaVersion := true

//spIncludeMaven := true

//publishTo := {
//  val nexus = "https://oss.sonatype.org/"
//  if (version.value.endsWith("SNAPSHOT"))
//    Some("snapshots" at nexus + "content/repositories/snapshots")
//  else
//    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
//}

//pomExtra := (
//  <url>https://github.com/truex/spark-csv</url>
//  <licenses>
//    <license>
//      <name>Apache License, Version 2.0</name>
//      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
//      <distribution>repo</distribution>
//    </license>
//  </licenses>
//  <scm>
//    <url>git@github.com:truex/spark-csv.git</url>
//    <connection>scm:git:git@github.com:truex/spark-csv.git</connection>
//  </scm>
//  <developers>
//    <developer>
//      <id>falaki</id>
//      <name>Hossein Falaki</name>
//      <url>http://www.falaki.net</url>
//    </developer>
//  </developers>)

parallelExecution in Test := false

// Skip tests during assembly
test in assembly := {}

//ScoverageSbtPlugin.ScoverageKeys.coverageHighlighting := {
//  if (scalaBinaryVersion.value == "2.10") false
//  else true
//}

// -- MiMa binary compatibility checks ------------------------------------------------------------

//import com.typesafe.tools.mima.core._
//import com.typesafe.tools.mima.plugin.MimaKeys.binaryIssueFilters
//import com.typesafe.tools.mima.plugin.MimaKeys.previousArtifact
//import com.typesafe.tools.mima.plugin.MimaPlugin.mimaDefaultSettings
//
//mimaDefaultSettings ++ Seq(
////  previousArtifact := Some("com.truex" %% "spark-csv" % "1.2.0"),
//  binaryIssueFilters ++= Seq(
//    // These classes are not intended to be public interfaces:
//    ProblemFilters.excludePackage("com.truex.spark.csv.CsvRelation"),
//    ProblemFilters.excludePackage("com.truex.spark.csv.util.InferSchema"),
//    ProblemFilters.excludePackage("com.truex.spark.sql.readers"),
//    ProblemFilters.excludePackage("com.truex.spark.csv.util.TypeCast"),
//    // We allowed the private `CsvRelation` type to leak into the public method signature:
//    ProblemFilters.exclude[IncompatibleResultTypeProblem](
//      "com.truex.spark.csv.DefaultSource.createRelation")
//  )
//)

// ------------------------------------------------------------------------------------------------
