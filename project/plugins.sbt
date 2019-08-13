// reading/publishing to s3 things
resolvers += Resolver.jcenterRepo
addSbtPlugin("ohnosequences" % "sbt-s3-resolver" % "0.19.+")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.7")

// java 9+ requirements
lazy val versions = new {
  val jabx = "2.2.+"
}

libraryDependencies ++= Seq(
  "com.sun.activation" % "javax.activation" % "1.2.+",
  "javax.xml.bind" % "jaxb-api" % versions.jabx,
  "com.sun.xml.bind" % "jaxb-core" % versions.jabx
)
